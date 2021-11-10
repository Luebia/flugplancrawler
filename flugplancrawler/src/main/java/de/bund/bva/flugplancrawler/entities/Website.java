package de.bund.bva.flugplancrawler.entities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Set;

/**Diese Klasse stammt von Burak. 
 * 
 * 
 */

public class Website {
    private Document wholeHTML;
    private String url;
    private Set<String> links;
    private boolean isWebsiteChanged;

    public Document getWholeHTML() {
        return wholeHTML;
    }

    public void setWholeHTML() {
        try {
            this.wholeHTML = Jsoup.connect(this.url).get();
            System.out.println("[X] Webseite besucht.");
        } catch (IOException e) {
            System.out.println("[ ] Webseite besucht.");
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (url.isEmpty()) {
            System.out.println("[ ] URL gesetzt.");
        } else {
            this.url = url;
            System.out.println("[X] URL gesetzt.");
            setWholeHTML();
        }
    }

    public Set<String> getLinks() {
        return links;
    }

    public void setLinks(Set<String> links) {
        this.links = links;
    }

    public boolean isWebsiteChanged() {
        return isWebsiteChanged;
    }

    public void setWebsiteChanged(boolean websiteChanged) {
        this.isWebsiteChanged = websiteChanged;
    }
}
