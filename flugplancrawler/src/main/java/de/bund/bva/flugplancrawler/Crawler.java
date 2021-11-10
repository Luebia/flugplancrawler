package de.bund.bva.flugplancrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.WebURL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;


/**
 * Diese Klasse ist von Burak. Eigentlich sollte der Crawler nach URLs suchen, doch als ich das Programm von Burak �bernommen hate
 * war der Crawler ohne Funktion. Der Scrapper bekommt vom Crawler also keine URLs, sondern die entsprechenden URLs wurden im
 * manuell gegeben. 
 * 
 * 
 * Dies ist die Crawler Klasse. Sie sucht auf der Webseite nach den Flugplänen und gibt die HTML Seite an die Scraper Klasse weiter.
 * Status: WIP
 */
public class Crawler extends WebCrawler {

    /**
     * RegEx für die URL-endungen die Exkludiert werden, damit z.B. keine Bilder oder ähnliches geöffnet werden.
     */
    private final static Pattern EXCLUSIONS = Pattern.compile(
            ".*(\\.(css|js|xml|gif|jpg|png|mp3|mp4|zip|gz|pdf))$"
    );
    /**
     * RegEx für die Trigger Begriffe die in der URL schon auf den Flugplan hinweisen.
     */
    private final static Pattern URLFLUGPLAN = Pattern.compile(
            "(abfl.ege.?|ankue?nfte?|abflug|fluege|ankommen)"
    );
    /**
     * RegEx die in der HTML nach bestimmten Begriffen sucht, damit erkannt werden kann, ob ein Flugplan sich auf der Webseite sich befindet.
     */
    private final static Pattern HTMLFLUGPLAN = Pattern.compile(
            "(flight.?plan|flight-?overview|flight.?result.?|flight.?list|flight.?.?table|airport.?schedule.?table|arrival.?|departure.?)" +
                    "|" +
                    "(<[a-z]{2,5}[\\s]*[a-z]{2,5}[\\s]*=[\\s]*\"flights\"[\\s]*>)"
    );
    public static int counter = 0; //Todo: kann gelöscht werden. Dient nur fürs testen.

    public static void main(String[] args) {
        int numCrawlers = 50;
        File crawlStorage = new File("src/main/resources/crawler4j");
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorage.getAbsolutePath());
        config.setPolitenessDelay(500);
        config.setIncludeHttpsPages(true);
        config.setMaxDepthOfCrawling(0);
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = null;
        try {
            controller = new CrawlController(config, pageFetcher, robotstxtServer);
        } catch (Exception e) {
            e.printStackTrace();
        }


        CrawlController.WebCrawlerFactory<Crawler> factory = Crawler::new;

        controller.start(factory, numCrawlers);
    }

    /**
     * In dieser Methode wird festgelegt, ob der Link weiter verfolgt werden soll oder nicht.
     *
     * @param referringPage Die Webseite als HTML Code.
     * @param url           Url der Webseite
     * @return Gibt zurück, ob die Seite besucht werden soll oder nicht.s
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        boolean besuchen = false;
        String urlString = url.getURL().toLowerCase();
        if (!isFile(urlString)) {
            besuchen = true;
        }
        return besuchen;
    }

    /**
     * Diese Methode überprüft, ob im HTML Code der Seite ein Flugplan zu finden ist.
     *
     * @param html Der HTML Code der Seite wird als String übergeben.
     * @return Gibt true zurück, wenn ein Flugplan zu finden ist.
     */
    public boolean containsFlightplan(String html) {
        boolean enthaeltFlugplan = false;
        enthaeltFlugplan = HTMLFLUGPLAN.matcher(html).find();
        return enthaeltFlugplan;
    }

    /**
     * Diese Methode überprüft, ob der Link zur einer Datei führt.
     *
     * @param url Der Übergabeparameter ist die URL als String.
     * @return Gibt true zurück, wenn der Link zur einer Datei führt.
     */
    public boolean isFile(String url) {
        boolean istDatei = false;
        istDatei = EXCLUSIONS.matcher(url).matches();
        return istDatei;
    }

    /**
     * Diese Methode überprüft, ob der Link zu einem Flugplan führt.
     *
     * @param url Der Link wird als String in die Methode gegeben.
     * @return Gibt true zurück, der Link zu einem Flugplan führt.
     */
    public boolean isURLFlightplan(String url) {
        boolean istFlugplan = false;
        istFlugplan = URLFLUGPLAN.matcher(url).find();
        return istFlugplan;
    }

    /**
     * Diese Methode überprüft, ob im Titel auf den Flugplan hingewiesen wird.
     *
     * @param title Der Titel der Webseite wird als String übergeben.
     * @return Gibt true zurück wenn im Titel bestimmte trigger begriffe auftauchen.
     */
    public boolean isTitleFlightplan(String title) {
        boolean istFlugplan = false;
        istFlugplan = URLFLUGPLAN.matcher(title).find();
        return istFlugplan;
    }

    /**
     * In Dieser Methode findet die Verarbeitung der Webseite statt.
     * Wenn die Kriterien auf einen Flugplan hinweisen wird zum schluss diese Methode für die Verarbeitung aufgerufen.
     * Status: todo - Muss noch für den Scrapper angepasst werden.
     * @param page Die Webseite wird mit als 'page' Objekt übergeben
     */
    @Override
    public void visit(Page page) {
        try {
            if (page.getParseData() instanceof HtmlParseData) {
                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();

                String title = htmlParseData.getTitle();
                String url = page.getWebURL().getURL();
                String text = htmlParseData.getText();
                String html = htmlParseData.getHtml();

                Set<WebURL> outgoingUrls = htmlParseData.getOutgoingUrls();
                title = title.replaceAll("\\\\", "_");

                if ((isTitleFlightplan(title) || isTitleFlightplan(url)) && containsFlightplan(html)) {
                    FileWriter urlWriter = new FileWriter("berichte/" + title + "_url_" + counter + ".txt");
                    BufferedWriter bufferedURLWriter = new BufferedWriter(urlWriter);
                    FileWriter htmlWriter = new FileWriter("berichte/" + title + "_html_" + counter + ".txt");
                    BufferedWriter bufferedHTMLWriter = new BufferedWriter(htmlWriter);
                    FileWriter textWriter = new FileWriter("berichte/" + title + "_text_" + counter + ".txt");
                    BufferedWriter bufferedTextWriter = new BufferedWriter(textWriter);
                    FileWriter outgoingURLsWriter = new FileWriter("berichte/" + title + "_links_" + counter + ".txt");
                    BufferedWriter bufferedoutgoingURLsWriter = new BufferedWriter(outgoingURLsWriter);

                    Iterator<WebURL> iterator;

                    bufferedURLWriter.write(url);
                    bufferedHTMLWriter.write(html);
                    bufferedTextWriter.write(text);

                    iterator = outgoingUrls.iterator();
                    while (iterator.hasNext()) {
                        bufferedoutgoingURLsWriter.write(String.valueOf(iterator.next()));
                        bufferedoutgoingURLsWriter.newLine();
                    }
                    counter++;

                    bufferedHTMLWriter.close();
                    bufferedoutgoingURLsWriter.close();
                    bufferedTextWriter.close();
                    bufferedURLWriter.close();

                    htmlWriter.close();
                    outgoingURLsWriter.close();
                    textWriter.close();
                    urlWriter.close();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
