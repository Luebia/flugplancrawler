package de.bund.bva.flugplancrawler.utils;

import de.bund.bva.flugplancrawler.Crawler;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrawlerControllerTest {

    @Test
    void shouldVisit() {
        Crawler hc = new Crawler();
        WebURL url = new WebURL();
        Page page = new Page(url);
        url.setURL("https://www.koeln-bonn-airport.de/fluege/abflug-ankunft.html");
        assertTrue(hc.shouldVisit(page, url));
        url.setURL("https://www.koeln-bonn-airport.de/fileadmin/cgn/lib/PhotoSwipe-master/dist/default-skin/default-skin.css");
        assertFalse(hc.shouldVisit(page, url));

        url.setURL("https://www.mdf-ag.com/tagesflugplan/abflug/");
        assertTrue(hc.shouldVisit(page, url));
        url.setURL("https://www.koeln-bonn-airport.de/typo3temp/assets/compressed/merged-63b5e55ffee7675722613246c4624e84.js?1583318136");
        assertFalse(hc.shouldVisit(page, url));

        url.setURL("https://www.berlin-airport.de/de/reisende-sxf/ankuenfte-und-abfluege/ankuenfte/index.php");
        assertTrue(hc.shouldVisit(page, url));
        url.setURL("https://www.youtube.com/channel/UCfC3r29U6ueu-AfOE0fSEfg");
        assertFalse(hc.shouldVisit(page, url));

        url.setURL("https://www.koeln-bonn-airport.de/fileadmin/cgn/theme/img/navigation/klima_stoerer_DE.png");
        assertFalse(hc.shouldVisit(page, url));
        url.setURL("https://www.koeln-bonn-airport.de/fileadmin/cgn/theme/favicon/flights/safari-pinned-tab.svg");
        assertFalse(hc.shouldVisit(page, url));

    }

    @Test
    void visit() {
    }

    @Test
    void main() {
    }


    @Test
    public void isURLFlightplan() {
        Crawler hc = new Crawler();
        assertTrue(hc.isURLFlightplan("abflug"));
        assertTrue(hc.isURLFlightplan("https://www.koeln-bonn-airport.de/fluege/abflug-ankunft.html"));
        assertTrue(hc.isURLFlightplan("https://www.berlin-airport.de/de/reisende-sxf/ankuenfte-und-abfluege/ankuenfte/index.php"));
        assertTrue(hc.isURLFlightplan("https://www.mdf-ag.com/tagesflugplan/abflug/"));

        assertFalse(hc.isURLFlightplan("https://www.frankfurt-airport.com/de.html"));
        assertFalse(hc.isURLFlightplan("https://www.koeln-bonn-airport.de/am-airport/general-aviation-terminal/charter-private-jets.html"));
        assertFalse(hc.isURLFlightplan("https://www.koeln-bonn-airport.de/fileadmin/cgn/lib/PhotoSwipe-master/dist/default-skin/default-skin.css"));
        assertFalse(hc.isURLFlightplan("https://www.koeln-bonn-airport.de/typo3temp/assets/compressed/merged-63b5e55ffee7675722613246c4624e84.js?1583318136"));

    }

    @Test
    public void isFile() {
        Crawler hc = new Crawler();
        assertTrue(hc.isFile(".css"));
        assertTrue(hc.isFile("https://www.koeln-bonn-airport.de/fileadmin/cgn/lib/PhotoSwipe-master/dist/default-skin/default-skin.css"));
        assertTrue(hc.isFile("https://www.koeln-bonn-airport.de/fileadmin/cgn/theme/favicon/flights/favicon-32x32.png"));
        assertTrue(hc.isFile("https://www.koeln-bonn-airport.de/fileadmin/cgn/theme/img/navigation/klima_banner_DE.png"));

        assertFalse(hc.isFile("https://www.frankfurt-airport.com/de.html"));
        assertFalse(hc.isFile("https://www.koeln-bonn-airport.de/am-airport/general-aviation-terminal/charter-private-jets.html"));
        assertFalse(hc.isFile("https://www.koeln-bonn-airport.de/am-airport/besucherfuehrungen/fuehrungen-fuer-einzelpersonen.html"));
        assertFalse(hc.isFile("https://www.koeln-bonn-airport.de/typo3temp/assets/compressed/merged-63b5e55ffee7675722613246c4624e84.js?1583318136"));
    }

    @Test
    void contaisFlightplan() {
        Crawler hc = new Crawler();
        String html = "<div id=\"flights\">\n\n"
                + "<div class=\"flightOverview aem-GridColumn aem-GridColumn--default--12\">\n\n"
                + "<div id=\"flight-plan-module\" style=\"position: relative;\" min-width=\"400px 700px 750px 625px 1240px 1000px\">\n\n"
                + "<table class=\"fp-flights-table fp-flights-table-large\">\n\n"
                + "<div class=\"flight-results-list flight-results-list--arrival d-block\">\n\n"
                + "<div class=\"flight-results-list flight-results-list--destination d-block\">\n\n"
                + "<table class=\"flightTable \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n\n"
                + "<div id=\"flight_table\" class=\"clearfix\" style=\"clear: both;\">\n\n"
                + "<div class=\"aero-tabs__tab aero-flight-overview-module__arrivals-tab\" tabindex=\"0\" role=\"tabpanel\" id=\"pnl-ef5c30c7-5a79-444a-9c87-245a98c0d8e2\" aria-labelledby=\"tab-437c899b-b7d5-4078-b08e-d35d74992fd3\">\n\n"
                + "<div class=\"aero-tabs__tab aero-flight-overview-module__departures-tab\" tabindex=\"0\" role=\"tabpanel\" hidden=\"\" id=\"pnl-1ef7ce6f-6feb-464a-a49b-015126ac2cab\" aria-labelledby=\"tab-ef463690-46a1-4b37-ad2f-56e22d859877\">\n\n"
                + "<div class=\"flight-list\">\n\n"
                + "<div id=\"airport-schedule-table-4054a8e6\" class=\"group timetable timetable-arrivals\">";
        assertTrue(hc.containsFlightplan(html));

        html = "<!DOCTYPE html>\n" +
                "<html lang=\"de-DE\">\n" +
                "<head>\n" +
                "\n" +
                "<meta charset=\"utf-8\">\n" +
                "<!-- \n" +
                "\tThis website is powered by TYPO3 - inspiring people to share!\n" +
                "\tTYPO3 is a free open source Content Management Framework initially created by Kasper Skaarhoj and licensed under GNU/GPL.\n" +
                "\tTYPO3 is copyright 1998-2020 of Kasper Skaarhoj. Extensions are copyright of their respective owners.\n" +
                "\tInformation and contribution at https://typo3.org/\n" +
                "-->\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<meta name=\"generator\" content=\"TYPO3 CMS\" />\n" +
                "<meta name=\"description\" content=\"Der Köln Bonn Airport ist einer der größten Verkehrsflughäfen Deutschlands. Jährlich entscheiden sich nahezu 10 Millionen Passagiere für den Flughafen der kurzen Wege.\" />\n" +
                "<meta name=\"author\" content=\"Flughafen Köln/Bonn GmbH\" />\n" +
                "<meta name=\"robots\" content=\"index,follow\" />\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\" />\n" +
                "<meta name=\"twitter:card\" content=\"summary\" />\n" +
                "<meta name=\"date\" content=\"2019-09-06\" />\n" +
                "\n" +
                "\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"/typo3temp/assets/compressed/merged-30638435f52861335b1230e0e250fbe7.css?1592825330\" media=\"all\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "<script src=\"/typo3temp/assets/compressed/merged-2648fc2c7cababa5354435f48633f525.js?1583931218\" type=\"text/javascript\"></script>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<!-- Google Tag Manager -->\n" +
                "<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({\\'gtm.start\\':\n" +
                "new Date().getTime(),event:\\'gtm.js\\'});var f=d.getElementsByTagName(s)[0],\n" +
                "j=d.createElement(s),dl=l!=\\'dataLayer\\'?\\'&l=\\'+l:\\'\\';j.async=true;j.src=\n" +
                "\\'https://www.googletagmanager.com/gtm.js?id=\\'+i+dl;f.parentNode.insertBefore(j,f);\n" +
                "})(window,document,\\'script\\',\\'dataLayer\\',\\'GTM-PL37ZX\\');</script>\n" +
                "<!-- End Google Tag Manager -->\n" +
                "\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"https://fonts.googleapis.com/css?family=Open+Sans\" media=\"all\">\n" +
                "<script type=\"text/javascript\">var MOBILE=\\'desktop\\';</script>                \n" +
                "<link rel=\"apple-touch-icon\" sizes=\"180x180\" href=\"/fileadmin/cgn/theme/favicon/flights/apple-touch-icon.png\">\n" +
                "<link rel=\"icon\" type=\"image/png\" href=\"/fileadmin/cgn/theme/favicon/flights/favicon-32x32.png\" sizes=\"32x32\">\n" +
                "<link rel=\"icon\" type=\"image/png\" href=\"/fileadmin/cgn/theme/favicon/flights/favicon-16x16.png\" sizes=\"16x16\">\n" +
                "<link rel=\"manifest\" href=\"/fileadmin/cgn/theme/favicon/flights/manifest.json\">\n" +
                "<link rel=\"mask-icon\" href=\"/fileadmin/cgn/theme/favicon/flights/safari-pinned-tab.svg\" color=\"#5bbad5\">\n" +
                "<link rel=\"shortcut icon\" href=\"/fileadmin/cgn/theme/favicon/flights/favicon.ico\">\n" +
                "<meta name=\"apple-mobile-web-app-title\" content=\"Köln Bonn Airport\">\n" +
                "<meta name=\"application-name\" content=\"Köln Bonn Airport\">\n" +
                "<meta name=\"theme-color\" content=\"#ffffff\">\n" +
                "<title>Köln Bonn Airport - Flüge und aktuelle Fluginformationen, Parken, Shopping</title><!--[if lte IE 9]><link rel=\"stylesheet\" href=\"/fileadmin/cgn/theme/css/styles_ie.css\" type=\"text/css\" media=\"screen\" /><![endif]-->\n" +
                "<meta name=\"google-site-verification\" content=\"ZqQ3DmdCL9bpiSgB4IlUh3iIpXTxEgHQ_ft7haZBVSU\" />\n" +
                "<meta property=\"og:title\" content=\"Startseite\" /><meta property=\"og:description\" content=\"Der Köln Bonn Airport ist einer der größten Verkehrsflughäfen Deutschlands. Jährlich entscheiden sich nahezu 10 Millionen Passagiere für den Flughafen der kurzen Wege.\" /><meta property=\"og:image\" content=\"https://www.koeln-bonn-airport.de/fileadmin/templates/images/facebook/Link.jpg\" />\n" +
                "<meta property=\"og:image:secure_url\" content=\"https://www.koeln-bonn-airport.de/fileadmin/templates/images/facebook/Link.jpg\" />\n" +
                "<meta property=\"og:image:type\" content=\"image/jpg\" />\n" +
                "<meta property=\"og:image:width\" content=\"1200\" />\n" +
                "<meta property=\"og:image:height\" content=\"630\" />\n" +
                "<meta property=\"og:type\" content=\"article\" /><link rel=\"alternate\" hreflang=\"de\" href=\"https://www.koeln-bonn-airport.de/index.html\" /><link rel=\"alternate\" hreflang=\"en\" href=\"https://www.cologne-bonn-airport.com/en/index.html\" /><link rel=\"alternate\" hreflang=\"nl\" href=\"https://www.keulen-bonn-airport.nl/nl/index.html\" /><link rel=\"alternate\" hreflang=\"x-default\" href=\"https://www.cologne-bonn-airport.com/en/index.html\" />\n" +
                "<link rel=\"stylesheet\" href=\"/fileadmin/cgn/lib/PhotoSwipe-master/dist/photoswipe.css\">\n" +
                "\t\t<link rel=\"stylesheet\" href=\"/fileadmin/cgn/lib/PhotoSwipe-master/dist/default-skin/default-skin.css\">\n" +
                "\t\t<script src=\"/fileadmin/cgn/lib/PhotoSwipe-master/dist/photoswipe.min.js\"></script>\n" +
                "\t\t<script src=\"/fileadmin/cgn/lib/PhotoSwipe-master/dist/photoswipe-ui-default.min.js\"></script>\n" +
                "<link rel=\"alternate\" hreflang=\"de-DE\" href=\"/index.html\"/>\n" +
                "<link rel=\"alternate\" hreflang=\"en-GB\" href=\"/en/index.html\"/>\n" +
                "<link rel=\"alternate\" hreflang=\"nl-NL\" href=\"/nl/index.html\"/>\n" +
                "<link rel=\"alternate\" hreflang=\"x-default\" href=\"/index.html\"/>\n" +
                "\n" +
                "<link rel=\"canonical\" href=\"/index.html\"/>\n" +
                "\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "\n" +
                "<!-- Google Tag Manager (noscript) -->\n" +
                "<noscript><iframe src=\"https://www.googletagmanager.com/ns.html?id=GTM-PL37ZX\"\n" +
                "height=\"0\" width=\"0\" style=\"display:none;visibility:hidden\"></iframe></noscript>\n" +
                "<!-- End Google Tag Manager (noscript) -->\n" +
                "\n" +
                "<div id=\"cgn-wrapper\">\n" +
                "\t<!-- - - - - - - - customHiddenNav - - - - - - - - - -->\n" +
                "\t<div class=\"customHiddenNav\">\n" +
                "\t    <h1>Köln Bonn Airport</h1>\n" +
                "\t    <hr />\n" +
                "\t    <h2><a id=\"navigation_local\">Seiteninterne Navigation:</a></h2>\n" +
                "\t    <ul>\n" +
                "\t        <li><a href=\"#servicenavi_global\">zur Servicenavigation des Internetauftitts</a></li>\n" +
                "\t        <li><a href=\"#areanavigation_global\">zur Bereichsnavigation des Internetauftritts</a></li>\n" +
                "\t        <li><a href=\"#content_local\">zum Inhaltsbereich des Internetauftritts</a></li>\n" +
                "\t        <li><a href=\"#service_global\">zum Servicebereich des Internetauftitts</a></li>\n" +
                "\t    </ul>\n" +
                "\t    <p><a href=\"#navigation_local\">Abschnittsende: Rücksprung zur seiteninternen Navigation</a></p>\n" +
                "\t    <hr /><hr />x\n" +
                "\t</div>\n" +
                "\t<!-- - - - - - - - customHiddenNav - End - - - - - - - - - -->\n" +
                "\n" +
                "\t<div class=\"carbon-neutral-teaser-startpage\"><a href=\"/fluege/klimaneutrales-fliegen.html\"><img class=\"img-responsive\" src=\"/fileadmin/cgn/theme/img/navigation/klima_stoerer_DE.png\" /></a></div>\n" +
                "\t<div class=\"carbon-neutral-banner-mobile\"><a href=\"/fluege/klimaneutrales-fliegen.html\"><img class=\"img-responsive\" src=\"/fileadmin/cgn/theme/img/navigation/klima_banner_DE.png\" /></a></div>\n" +
                "\n" +
                "\t<div class=\"tx-cgn-modules\">\n" +
                "\t\n" +
                "\t\n" +
                "\t\n" +
                "\n" +
                "</div>\t\n" +
                "\t<nav id=\"mobilenavigation\">\n" +
                "\t\t<div class=\"container\">\n" +
                "\t\t\t<ul><li><span><a href=\"/fluege.html\" data-url-mobile=\"\">Flüge</a></span><ul><li><a href=\"/fluege/abflug-ankunft.html\" data-url-mobile=\"\">Abflug &amp; Ankunft</a></li><li><a href=\"/fluege/gesund-ans-ziel.html\" data-url-mobile=\"\">Gesund ans Ziel</a></li><li><a href=\"/fluege/flug-buchen.html\" data-url-mobile=\"\">Flug buchen</a></li><li><a href=\"/fluege/airlines.html\" data-url-mobile=\"\">Airlines</a></li><li><a href=\"/fluege/flugziele.html\" data-url-mobile=\"\">Flugziele ab Köln/Bonn</a></li><li><a href=\"/fluege/vorabend-check-in.html\" data-url-mobile=\"\">Vorabend Check-in</a></li><li><a href=\"/fluege/reise-checkliste.html\" data-url-mobile=\"\">Reise-Checkliste</a></li><li><a href=\"/fluege/gepaeckermittlung.html\" data-url-mobile=\"\">Gepäckermittlung / Lost &amp; Found</a></li><li><a href=\"/fluege/sicherheitskontrolle.html\" data-url-mobile=\"\">Sicherheitskontrolle</a></li><li><a href=\"/fluege/begleit-und-porterservice.html\" data-url-mobile=\"\">Begleit- und Porterservice</a></li><li><a href=\"/fluege/vip-service.html\" data-url-mobile=\"\">VIP-Service</a></li><li><a href=\"/fluege/klimaneutrales-fliegen.html\" data-url-mobile=\"\">Klimaneutrales Fliegen</a><ul><li><a href=\"/fluege/klimaneutrales-fliegen/kompensation-berechnen.html\" data-url-mobile=\"\">Kompensation berechnen</a></li><li><a href=\"/fluege/klimaneutrales-fliegen/konkretes-projekt.html\" data-url-mobile=\"\">Konkretes Projekt</a></li><li><a href=\"/fluege/klimaneutrales-fliegen/berechnungsgrundlage.html\" data-url-mobile=\"\">Berechnungsgrundlage</a></li><li><a href=\"/fluege/klimaneutrales-fliegen/wer-ist-myclimate.html\" data-url-mobile=\"\">Wer ist myclimate?</a></li><li><a href=\"/fluege/klimaneutrales-fliegen/faqs.html\" data-url-mobile=\"\">FAQs</a></li></ul></li><li><a href=\"/fluege/barrierefrei-reisen.html\" data-url-mobile=\"\">Barrierefrei Reisen</a></li><li><a href=\"/fluege/app-multimedia.html\" data-url-mobile=\"\">App &amp; Multimedia</a></li></ul></li><li><span><a href=\"/parken-anreise.html\" data-url-mobile=\"\">Parken &amp; Anreise</a></span><ul><li><a href=\"/parken-anreise/parkplatz-reservierung.html\" data-url-mobile=\"\">Parkplatz-Reservierung</a></li><li><a href=\"/parken-anreise/infos-zum-parken.html\" data-url-mobile=\"\">Infos zum Parken</a><ul><li><a href=\"/parken-anreise/infos-zum-parken/parken-faqs.html\" data-url-mobile=\"\">Parken FAQs</a></li></ul></li><li><a href=\"/parken-anreise/anreise-mit-dem-pkw.html\" data-url-mobile=\"\">Anreise mit dem PKW</a></li><li><a href=\"/parken-anreise/bus-und-bahn.html\" data-url-mobile=\"\">Bus und Bahn</a></li><li><a href=\"/parken-anreise/fernbus.html\" data-url-mobile=\"\">Fernbus</a></li><li><a href=\"/parken-anreise/mietwagen.html\" data-url-mobile=\"\">Mietwagen</a></li><li><a href=\"/parken-anreise/carsharing.html\" data-url-mobile=\"\">Carsharing</a></li><li><a href=\"/parken-anreise/taxi.html\" data-url-mobile=\"\">Taxi</a></li><li><a href=\"/parken-anreise/fahrrad-und-zu-fuss.html\" data-url-mobile=\"\">Fahrrad und zu Fuß</a></li></ul></li><li><a href=\"/parken-anreise/parkplatz-reservierung.html\" data-url-mobile=\"\">Parkplatz-Reservierung</a></li><li><span><a href=\"/am-airport.html\" data-url-mobile=\"\">Am Airport</a></span><ul><li><a href=\"/am-airport/shoppen-und-essen.html\" data-url-mobile=\"\">Shoppen und Essen am Airport</a></li><li><a href=\"/am-airport/reisebueros.html\" data-url-mobile=\"\">Reisebüros</a></li><li><a href=\"/am-airport/dienstleister-und-service.html\" data-url-mobile=\"\">Dienstleister und Service</a></li><li><a href=\"/am-airport/aerztezentrum.html\" data-url-mobile=\"\">Ärztezentrum</a></li><li><a href=\"/am-airport/konferenzen-und-tagungen.html\" data-url-mobile=\"\">Konferenzen und Tagungen</a></li><li><a href=\"/am-airport/airport-business-lounge.html\" data-url-mobile=\"\">Airport Business Lounge</a></li><li><a href=\"/am-airport/besucherangebote.html\" data-url-mobile=\"\">Willkommen am Airport</a></li><li><span><a href=\"/am-airport/besucherfuehrungen.html\" data-url-mobile=\"\">Besucherführungen</a></span><ul><li><a href=\"/am-airport/besucherfuehrungen/fuehrungen-fuer-gruppen.html\" data-url-mobile=\"\">Führungen für Gruppen (ab 10 Personen)</a></li><li><a href=\"/am-airport/besucherfuehrungen/fuehrungen-fuer-einzelpersonen.html\" data-url-mobile=\"\">Führungen für Einzelpersonen</a></li></ul></li><li><a href=\"/am-airport/aktuelles.html\" data-url-mobile=\"\">Aktuelles</a></li><li><a href=\"/am-airport/hotels-buchen.html\" data-url-mobile=\"\">Hotels buchen</a></li><li><a href=\"/am-airport/airport-webcam.html\" data-url-mobile=\"\">Airport Webcam</a></li><li><a href=\"/am-airport/general-aviation-terminal.html\" data-url-mobile=\"\">General Aviation Terminal</a><ul><li><a href=\"/am-airport/general-aviation-terminal/charter-private-jets.html\" data-url-mobile=\"\">Charter &amp; Private Jets</a></li></ul></li><li><a href=\"/am-airport/pfandprojekt.html\" data-url-mobile=\"\">Pfandprojekt</a></li><li><a href=\"/am-airport/polizei-zoll.html\" data-url-mobile=\"\">Polizei, Zoll</a></li><li><a href=\"/am-airport/wegweiser.html\" data-url-mobile=\"\">Wegweiser</a></li></ul></li><li><a href=\"/am-airport/aktuelles.html\" data-url-mobile=\"\">Aktuelles</a></li><li><span><a href=\"/b2b.html\" data-url-mobile=\"\">B2B</a></span><ul><li><a href=\"/b2b/marketing-und-vertrieb.html\" data-url-mobile=\"\">Marketing und Vertrieb</a></li><li><a href=\"/b2b/cologne-bonn-cargo.html\" data-url-mobile=\"\">Cologne Bonn Cargo</a><ul><li><a href=\"/b2b/cologne-bonn-cargo/dienstleister-abfertigung.html\" data-url-mobile=\"\">Dienstleister &amp; Abfertigung</a></li><li><a href=\"/b2b/cologne-bonn-cargo/airlines.html\" data-url-mobile=\"\">Airlines</a></li><li><a href=\"/b2b/cologne-bonn-cargo/tiertransporte.html\" data-url-mobile=\"\">Tiertransporte</a></li><li><a href=\"/b2b/cologne-bonn-cargo/behoerden.html\" data-url-mobile=\"\">Behörden</a></li><li><a href=\"/b2b/cologne-bonn-cargo/kontakte.html\" data-url-mobile=\"\">Kontakte</a></li></ul></li><li><a href=\"/b2b/ground-handling.html\" data-url-mobile=\"\">Ground Handling</a></li><li><a href=\"/b2b/werben-am-airport.html\" data-url-mobile=\"\">Werben am Airport</a></li><li><a href=\"/b2b/event-lounge.html\" data-url-mobile=\"\">Event-Lounge</a></li><li><a href=\"/b2b/reisebuero-expedienten.html\" data-url-mobile=\"\">Reisebüro Expedienten</a></li><li><a href=\"/b2b/foto-und-filmaufnahmen.html\" data-url-mobile=\"\">Foto- und Filmaufnahmen</a></li><li><a href=\"/b2b/fernbusterminal.html\" data-url-mobile=\"\">Fernbusterminal</a></li><li><a href=\"/b2b/aoc-und-nutzerausschuss.html\" data-url-mobile=\"\">AOC und Nutzerausschuss</a></li><li><a href=\"/b2b/einkauf.html\" data-url-mobile=\"\">Einkauf</a><ul><li><a href=\"/b2b/einkauf/ausschreibungen-vergaben.html\" data-url-mobile=\"\">Ausschreibungen &amp; Vergaben</a></li><li><a href=\"/b2b/einkauf/dokumente.html\" data-url-mobile=\"\">Dokumente</a></li></ul></li><li><a href=\"/b2b/zugang-zum-sicherheitsbereich.html\" data-url-mobile=\"\">Zugang zum Sicherheitsbereich</a></li><li><a href=\"/b2b/luftsicherheitsschulung.html\" data-url-mobile=\"\">Luftsicherheitsschulung</a></li><li><a href=\"/b2b/flughafenlieferungen.html\" data-url-mobile=\"\">Flughafenlieferungen</a></li><li><a href=\"/b2b/vertragsbedingungen-entgelte.html\" data-url-mobile=\"\">Vertragsbedingungen &amp; Entgelte</a><ul><li><a href=\"/b2b/vertragsbedingungen-entgelte/versorgungsnetze.html\" data-url-mobile=\"\">Versorgungsnetze</a></li><li><a href=\"/b2b/vertragsbedingungen-entgelte/techn-vertragsbedingungen.html\" data-url-mobile=\"\">Technische Vertragsbedingungen</a></li></ul></li></ul></li><li><span><a href=\"/unternehmen.html\" data-url-mobile=\"\">Unternehmen</a></span><ul><li><a href=\"/unternehmen/flughafen-koelnbonn-gmbh.html\" data-url-mobile=\"\">Flughafen Köln/Bonn GmbH</a></li><li><a href=\"/unternehmen/unternehmensfuehrung.html\" data-url-mobile=\"\">Unternehmensführung</a></li><li><a href=\"/unternehmen/governance-kodex.html\" data-url-mobile=\"\">Public Corporate Governance Kodex</a></li><li><a href=\"/unternehmen/wirtschaftsfaktor.html\" data-url-mobile=\"\">Wirtschaftsfaktor</a></li><li><span><a href=\"/unternehmen/karriere.html\" data-url-mobile=\"\">Karriere</a></span></li><li><a href=\"/unternehmen/daten-fakten.html\" data-url-mobile=\"\">Daten &amp; Fakten</a></li><li><a href=\"/unternehmen/planfeststellung.html\" data-url-mobile=\"\">Planfeststellung</a><ul><li><a href=\"/unternehmen/planfeststellung/faq-zur-planfeststellung.html\" data-url-mobile=\"\">FAQ zur Planfeststellung</a></li></ul></li><li><a href=\"/unternehmen/geschichte.html\" data-url-mobile=\"\">Historische Meilensteine</a></li><li><a href=\"/unternehmen/umwelt-und-laermschutz.html\" data-url-mobile=\"\">Umwelt und Lärmschutz</a><ul><li><a href=\"/unternehmen/umwelt-und-laermschutz/fluglaerm.html\" data-url-mobile=\"\">Fluglärm</a></li><li><a href=\"/unternehmen/umwelt-und-laermschutz/passiver-schallschutz.html\" data-url-mobile=\"\">Passiver Schallschutz</a></li><li><a href=\"/unternehmen/umwelt-und-laermschutz/betriebsrichtung-live.html\" data-url-mobile=\"\">Betriebsrichtung live</a></li><li><a href=\"/unternehmen/umwelt-und-laermschutz/travisflugspuren-live.html\" data-url-mobile=\"\">Travis/Flugspuren live</a></li><li><a href=\"/unternehmen/umwelt-und-laermschutz/glossar.html\" data-url-mobile=\"\">Glossar</a></li></ul></li><li><a href=\"/unternehmen/sanierungsmassnahmen.html\" data-url-mobile=\"\">Sanierungsmaßnahmen</a><ul><li><a href=\"/unternehmen/sanierungsmassnahmen/bahnsanierung.html\" data-url-mobile=\"\">Bahnsanierung</a></li><li><a href=\"/unternehmen/sanierungsmassnahmen/kanalsanierung.html\" data-url-mobile=\"\">Kanalsanierung</a></li></ul></li><li><a href=\"/unternehmen/politikbrief.html\" data-url-mobile=\"\">Politikbrief</a></li><li><a href=\"/unternehmen/flughafenfeuerwehr.html\" data-url-mobile=\"\">Flughafenfeuerwehr</a></li><li><a href=\"/unternehmen/anti-korruption.html\" data-url-mobile=\"\">Anti-Korruptions-Management</a></li></ul></li><li><span><a href=\"/presse.html\" data-url-mobile=\"\">Presse</a></span><ul><li><a href=\"/presse/pressemitteilungen.html\" data-url-mobile=\"\">Pressemitteilungen</a></li><li><a href=\"/presse/bilder.html\" data-url-mobile=\"\">Bilder</a></li><li><a href=\"/presse/film-foto.html\" data-url-mobile=\"\">Film &amp; Foto</a></li><li><a href=\"/presse/publikationen.html\" data-url-mobile=\"\">Publikationen</a></li><li><a href=\"/presse/kontakte.html\" data-url-mobile=\"\">Kontakte</a></li></ul></li><li><span><a href=\"/karriere.html\" data-url-mobile=\"\">Karriere</a></span><ul><li><a href=\"/karriere/jobangebote.html\" data-url-mobile=\"\">Jobangebote</a></li><li><a href=\"/karriere/studiengaenge.html\" data-url-mobile=\"\">Studiengänge</a></li><li><a href=\"/karriere/ausbildungen.html\" data-url-mobile=\"\">Ausbildungen</a></li><li><a href=\"/karriere/praktika.html\" data-url-mobile=\"\">Praktika</a></li><li><a href=\"/karriere/unternehmen-am-flughafen.html\" data-url-mobile=\"\">Unternehmen am Flughafen</a></li></ul></li><li><span><a href=\"/serviceseiten.html\" data-url-mobile=\"\">Serviceseiten</a></span><ul><li><a href=\"/serviceseiten/kontakte.html\" data-url-mobile=\"\">Kontakte</a></li><li><a href=\"/serviceseiten/hilfe-faq.html\" data-url-mobile=\"\">Hilfe &amp; FAQ</a></li><li><a href=\"/serviceseiten/sitemap.html\" data-url-mobile=\"\">Sitemap</a></li><li><a href=\"/serviceseiten/datenschutz.html\" data-url-mobile=\"\">Datenschutz</a></li><li><a href=\"/serviceseiten/impressum.html\" data-url-mobile=\"\">Impressum</a></li></ul></li></ul>\n" +
                "\t\t</div>\n" +
                "\t</nav>\n" +
                "\n" +
                "\t<!-- - - - - - - - customHiddenNav - - - - - - - - - -->\n" +
                "\t<div class=\"customHiddenNav\">\n" +
                "\t    <h2><a id=\"servicenavi_global\">Übergreifender Servicebereich des Internetauftritts</a></h2>\n" +
                "\t</div>\n" +
                "\t<!-- - - - - - - - customHiddenNav - End - - - - - - - - - -->\n" +
                "\n" +
                "\t<!-- <header class=\"navbar navbar-static-top \"> -->\n" +
                "\t<div class=\"navbar\">\n" +
                "\t\t<div class=\"container\">\n" +
                "\t\t\t<div class=\"navbar-header hidden-sm hidden-md hidden-lg\">\n" +
                "\t\t\t\t<a href=\"#mobilenavigation\" id=\"hamburger\"><span><i style=\"display:none;\">Navigation</i></span></a>\n" +
                "\t\t\t\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<nav class=\"collapse navbar-collapse \">\n" +
                "\t\t\t\t<ul class=\"nav navbar-nav\">\n" +
                "\t\t\t\t\t<li><a href=\"/parken-anreise/parkplatz-reservierung.html\" data-url-mobile=\"https://parking.koeln-bonn-airport.de/iPCP/?mobile=true&loc=de\"><i class=\"arr arr-normal-e\"></i>&nbsp;Parkplatz reservieren</a></li><li><a href=\"/am-airport/aktuelles.html\" data-url-mobile=\"\"><i class=\"arr arr-normal-e\"></i>&nbsp;Aktuelles</a></li><li><a href=\"/karriere/jobangebote.html\" data-url-mobile=\"\"><i class=\"arr arr-normal-e\"></i>&nbsp;Karriere</a></li><li><a href=\"/presse/pressemitteilungen.html\" data-url-mobile=\"\"><i class=\"arr arr-normal-e\"></i>&nbsp;Presse</a></li><li><a href=\"/unternehmen/umwelt-und-laermschutz.html\" data-url-mobile=\"\"><i class=\"arr arr-normal-e\"></i>&nbsp;Umwelt</a></li><li><a href=\"/b2b/marketing-und-vertrieb.html\" data-url-mobile=\"\"><i class=\"arr arr-normal-e\"></i>&nbsp;B2B</a></li><li><a href=\"/serviceseiten/kontakte.html\" data-url-mobile=\"\"><i class=\"arr arr-normal-e\"></i>&nbsp;Kontakte</a></li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t\t<div class=\"navbar-language-right\">\n" +
                "\t\t\t\t\t<ul class=\"nav navbar-nav navbar-right\">\n" +
                "\t\t\t\t\t\t<li><a class=\"big zoom-out\" href=\"#\">A-</a></li>\n" +
                "\t\t\t\t\t\t<li><a class=\"big zoom-in\" href=\"#\">A+</a></li>\n" +
                "\t\t\t\t\t\t<li><a class=\"big\" href=\"https://www.cologne-bonn-airport.com/en/index.html\" ><i class=\"sym sym-blocksatz\"></i>EN</a></li><li><a class=\"big\" href=\"https://www.keulen-bonn-airport.nl/nl/index.html\"><i class=\"sym sym-blocksatz\"></i>NL</a></a></li>\n" +
                "\t\t\t\t\t</ul>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</nav>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t<div class=\"naviwrapper intropagewrapper\">\n" +
                "\t\t<div class=\"container\">\n" +
                "\t\t\t<div class=\"row brandrow\">\n" +
                "\t\t\t\t<div class=\"col-lg-12 col-md-12 col-md-12 col-sm-12\">\n" +
                "\t\t\t\t\t<div class=\"pull-left brand\"><div class=\"hidden-sm hidden-md hidden-lg\"><a href=\"/index.html\">Köln Bonn Airport <span class=\"cgn_grey\">so&nbsp;simple</span></a></div></div>\n" +
                "\t\t\t\t\t<div class=\"pull-right hidden-xs\">\n" +
                "\t\t\t\t\t\t\n" +
                "\t\t\t\t\t\t<div class=\"header-nav-searchform\"><form method=\"post\" name=\"fulltextsearch\" action=\"/serviceseiten/suche.html\"><div>    \t<input type=\"hidden\" name=\"id\" value=\"175\" />\n" +
                "    \t<input type=\"hidden\" name=\"tx_indexedsearch_pi2[action]\" value=\"search\" />\n" +
                "    \t<input type=\"hidden\" name=\"tx_indexedsearch_pi2[controller]\" value=\"search\" />\n" +
                "<input type=\"hidden\" name=\"tx_indexedsearch_pi2[search][searchType]\" value=\"20\" /><input type=\"hidden\" name=\"tx_indexedsearch_pi2[search][languageUid]\" value=\"0\" /><input type=\"hidden\" name=\"tx_indexedsearch_pi2[search][_freeIndexUid]\" value=\"0\"><div class=\"search-form\"><label for=\"headerSearchInput\">Suche nach:</label><input type=\"text\" name=\"tx_indexedsearch_pi2[search][sword]\" id=\"headerSearchInput\" class=\"form-control search-query\" placeholder=\"Suche nach...\" value=\"\" class=\"ac_input\" autocomplete=\"off\" /><i class=\"fa fa-search\" aria-hidden=\"true\"></i></div></div></form></div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<!-- - - - - - - - customHiddenNav - - - - - - - - - -->\n" +
                "\t\t\t\t\t<div class=\"customHiddenNav\">\n" +
                "\t\t\t\t\t    <p><a href=\"#navigation_local\">Abschnittsende: Rücksprung zur seiteninternen Navigation</a></p>\n" +
                "\t\t\t\t\t    <hr /><hr />\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<!-- - - - - - - - customHiddenNav - End - - - - - - - - - -->\t\t\t\t\t\t\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "\t<div class=\"container intropagecontainer\" >\n" +
                "\n" +
                "\t\t<div class=\"row\">\n" +
                "\t\t\t<div id=\"content\" class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12 -vertical-center-\">\n" +
                "\t\t\t\t<div class=\"row\">\n" +
                "\t\t\t\t\t<!-- - - - - - - - customHiddenNav - - - - - - - - - -->\n" +
                "\t\t\t\t\t<div class=\"customHiddenNav\">\t\t\t\n" +
                "\t\t\t\t\t\t<h2><a id=\"areanavigation_global\">Bereichsnavigation des Internetauftritts</a></h2>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<!-- - - - - - - - customHiddenNav - End - - - - - - - - - -->\n" +
                "\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t\t\t<div class=\"col-lg-12 brand brand-lang-0 hidden-xs\"><a href=\"/index.html\"><span class=\"int-logo\">Köln Bonn Airport</span> <span class=\"cgn_grey\">so&nbsp;simple</span></a></div>\n" +
                "\t\t\t\t\t<div class=\"intronavi\"><a href=\"/fluege/abflug-ankunft.html\"><div class=\"col-lg-15 col-md-15 col-sm-4 col-xs-6 text-center introbullet introbullet-1\"><img alt=\"Flüge\" src=\"/fileadmin/cgn/theme/img/navigation/Icon_01.png\"><div class=\"text-center element\">Flüge</div></div></a><a href=\"/fluege/flug-buchen.html\"><div class=\"col-lg-15 col-md-15 col-sm-4 col-xs-6 text-center introbullet introbullet-2\"><img alt=\"Flug buchen\" src=\"/fileadmin/cgn/theme/img/navigation/Icon_02.png\"><div class=\"text-center element\">Flug buchen</div></div></a><a href=\"/fluege/gesund-ans-ziel.html\"><div class=\"col-lg-15 col-md-15 col-sm-4 col-xs-6 text-center introbullet introbullet-3\"><img alt=\"Gesund ans Ziel\" src=\"/fileadmin/cgn/theme/img/navigation/Icon_03.png\"><div class=\"text-center element\">Gesund ans Ziel</div></div></a><a href=\"/parken-anreise/parkplatz-reservierung.html\"><div class=\"col-lg-15 col-md-15 col-sm-4 col-xs-6 text-center introbullet introbullet-4\"><img alt=\"Parken & Anreise\" src=\"/fileadmin/cgn/theme/img/navigation/Icon_04.png\"><div class=\"text-center element\">Parken & Anreise</div></div></a><a href=\"/am-airport/shoppen-und-essen.html\"><div class=\"col-lg-15 col-md-15 col-sm-4 col-xs-6 text-center introbullet introbullet-5\"><img alt=\"Am Airport\" src=\"/fileadmin/cgn/theme/img/navigation/Icon_05.png\"><div class=\"text-center element\">Am Airport</div></div></a><div class=\"clearLeft\"></div></div>\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t\t<div class=\"customHiddenNav\">\n" +
                "\t\t\t\t\t    <p><a href=\"#navigation_local\">Abschnittsende: Rücksprung zur seiteninternen Navigation</a></p>\n" +
                "\t\t\t\t\t    <hr /><hr />\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t<!-- - - - - - - - customHiddenNav - End - - - - - - - - - -->  \t\t\t\t\t\n" +
                "</div>\n" +
                "<form id=\"CenterdpFrameForm\" action=\"https://parking.koeln-bonn-airport.de/iPCP/reservation/create\"\n" +
                "\tmethod=\"get\" target=\"_blank\">\n" +
                "\t<div class=\"row\" style=\"margin-bottom: 10px\">\n" +
                "\t\t<div class=\"col-xs-12\">\n" +
                "\t\t\t<div id=\"_con5f897c6be6381\" class=\"panel panel-default cgnContainer container-default  \">\n" +
                "<div class=\"panel-body\">\n" +
                "\t \n" +
                "<div class=\"row\">\n" +
                "\t\t\t\t<div class=\"col-md-4\">\n" +
                "\t\t\t\t\t<div class=\"row\">\n" +
                "\t\t\t\t\t\t<div class=\"col-lg-3\">\n" +
                "\t\t\t\t\t\t\t<label for=\"Centerdatefrom\">Einfahrt:</label>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"form-group form-group-lg col-lg-9\">\n" +
                "\t\t\t\t\t\t\t<div class=\\'input-group date\\' id=\\'CenterdatetimepickerFrom\\'>\n" +
                "\t\t\t\t\t\t\t\t<input type=\\'text\\' id=\"Centerdatefrom\" name=\"datefrom\"\n" +
                "\t\t\t\t\t\t\t\t\tclass=\"form-control\" /> <span class=\"input-group-addon\">\n" +
                "\t\t\t\t\t\t\t\t\t<span class=\"fa fa-calendar\"></span>\n" +
                "\t\t\t\t\t\t\t\t</span>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t<div class=\"col-md-4\">\n" +
                "\t\t\t\t\t<div class=\"row\">\n" +
                "\t\t\t\t\t\t<div class=\"col-lg-3\">\n" +
                "\t\t\t\t\t\t\t<label for=\"Centerdateto\">Ausfahrt:</label>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"form-group form-group-lg col-lg-9\">\n" +
                "\t\t\t\t\t\t\t<div class=\\'input-group date\\' id=\\'CenterdatetimepickerTo\\'>\n" +
                "\t\t\t\t\t\t\t\t<input type=\\'text\\' id=\"Centerdateto\" name=\"dateto\"\n" +
                "\t\t\t\t\t\t\t\t\tclass=\"form-control\" /> <span class=\"input-group-addon\">\n" +
                "\t\t\t\t\t\t\t\t\t<span class=\"fa fa-calendar\"></span>\n" +
                "\t\t\t\t\t\t\t\t</span>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t<div class=\"col-md-4\">\n" +
                "\t\t\t\t\t<div class=\"row\">\n" +
                "\t\t\t\t\t\t<div class=\"col-lg-2 hidden-xs hidden-sm\">\n" +
                "\t\t\t\t\t\t\t<label for=\"Centerdateto\">&nbsp;</label>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"form-group form-group-lg col-lg-10 col-md-12\">\n" +
                "\t\t\t\t\t\t\t<button type=\"button\"\n" +
                "\t\t\t\t\t\t\t\tclass=\"btn btn-block btn-lg bg_cgn_orange cgn_white\"\n" +
                "\t\t\t\t\t\t\t\tonClick=\"$(\\'#CenterdpFrameForm\\').submit();\"><i class=\"sym sym-pkw\"></i>\n" +
                "\t\t\t\t\t\t\t\tWunschparkplatz finden\n" +
                "\t\t\t\t\t\t\t\t<i class=\"arr arr-normal-e\"></i>\n" +
                "\t\t\t\t\t\t\t</button>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\n" +
                "\t\t\t</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "\t<input\n" +
                "\t\ttype=\"hidden\" name=\"loc\" value=\"de\" /> <input type=\"hidden\"\n" +
                "\t\tid=\"CenterDatepickerFromData\" name=\"begin_date\"\n" +
                "\t\tvalue=\"2020-10-17\" /> <input\n" +
                "\t\ttype=\"hidden\" id=\"CenterDatepickerToData\" name=\"end_date\"\n" +
                "\t\tvalue=\"2020-10-18\" /> <input\n" +
                "\t\ttype=\"hidden\" id=\"CenterDatepickerFromTimeData\" name=\"begin_time\"\n" +
                "\t\tvalue=\"14:00\" /> <input\n" +
                "\t\ttype=\"hidden\" id=\"CenterDatepickerToTimeData\" name=\"end_time\"\n" +
                "\t\tvalue=\"18:00\" />\n" +
                "\t\n" +
                "</form>\n" +
                "<script type=\"text/javascript\">\n" +
                "    $(function() {\n" +
                "\tvar locDP = \\'de\\';\n" +
                "\n" +
                "\t$(\\'#CenterdatetimepickerFrom\\').datetimepicker({\n" +
                "\t    locale : locDP,\n" +
                "\t    stepping : 15,\n" +
                "\t    format : \"DD.MM.YYYY HH:mm\",\n" +
                "\t    defaultDate : \"2020-10-17 14:00\",\n" +
                "\t    sideBySide : true,\n" +
                "\t    debug:false,\n" +
                "\t    icons : {\n" +
                "\t\ttime : \"fa fa-fw fa-clock-o\",\n" +
                "\t\tdate : \"fa fa-calendar\",\n" +
                "\t\tup : \"fa fa-arrow-up\",\n" +
                "\t\tdown : \"fa fa-arrow-down\",\n" +
                "\t\tnext : \"fa fa-arrow-right\",\n" +
                "\t\tprevious : \"fa fa-arrow-left\"\n" +
                "\t    }\n" +
                "\t});\n" +
                "\t$(\\'#CenterdatetimepickerTo\\').datetimepicker({\n" +
                "\t    locale : locDP,\n" +
                "\t    stepping : 15,\n" +
                "\t    format : \"DD.MM.YYYY HH:mm\",\n" +
                "\t    defaultDate : \"2020-10-18 18:00\",\n" +
                "\t    debug:false,\n" +
                "\t    sideBySide : true,\n" +
                "\t    icons : {\n" +
                "\t\ttime : \"fa fa-fw fa-clock-o\",\n" +
                "\t\tdate : \"fa fa-calendar\",\n" +
                "\t\tup : \"fa fa-arrow-up\",\n" +
                "\t\tdown : \"fa fa-arrow-down\",\n" +
                "\t\tnext : \"fa fa-arrow-right\",\n" +
                "\t\tprevious : \"fa fa-arrow-left\"\n" +
                "\t    }\n" +
                "\t});\n" +
                "\n" +
                "\t$(\"#CenterdatetimepickerFrom\").on(\"dp.change\", function(e) {\n" +
                "\t    var tmpDateTimeFrom = e.date;\n" +
                "\t    if (!tmpDateTimeFrom) {\n" +
                "\t\t$(\\'#CenterdatetimepickerFrom\\').data(\"DateTimePicker\").date(\"2020-10-17 14:00\");\n" +
                "\t\t$(\\'#Centerdatefrom\\').val(\"17.10.2020 14:00\");\n" +
                "\t\t$(\\'#CenterDatepickerFromData\\').val(\"2020-10-17\");\n" +
                "\t\t$(\\'#CenterDatepickerFromTimeData\\').val(\"14:00\");\n" +
                "\t    } else {\n" +
                "\t\tif ($(\\'#Centerdatefrom\\').length > 0) {\n" +
                "\t\t    var tmpDF = $(\\'#Centerdatefrom\\').val().split(\" \");\n" +
                "\t\t    var tmpDFDate = tmpDF[0];\n" +
                "\t\t    var tmpDFTime = tmpDF[1];\n" +
                "\t\t    tmpDFDate = tmpDFDate.split(\".\");\n" +
                "\t\t    tmpDFDate = tmpDFDate[2] + \"-\" + tmpDFDate[1] + \"-\" + tmpDFDate[0];\n" +
                "\t\t    //console.log(tmpDFDate);\n" +
                "\t\t    //console.log(tmpDFTime);\n" +
                "\t\t    $(\\'#CenterDatepickerFromData\\').val(tmpDFDate);\n" +
                "\t\t    $(\\'#CenterDatepickerFromTimeData\\').val(tmpDFTime);\n" +
                "\t\t}\n" +
                "\t    }\n" +
                "\t    //console.log(\"tmpDateTimeFrom:\" + tmpDateTimeFrom);\n" +
                "\t});\n" +
                "\t$(\"#CenterdatetimepickerTo\").on(\"dp.change\", function(e) {\n" +
                "\t    var tmpDateTimeTo = e.date;\n" +
                "\t    if (!tmpDateTimeTo) {\n" +
                "\t\t$(\\'#CenterdatetimepickerTo\\').data(\"DateTimePicker\").date(\"2020-10-18 18:00\");\n" +
                "\t\t$(\\'#Centerdateto\\').val(\"18.10.2020 18:00\");\n" +
                "\t\t$(\\'#CenterDatepickerToData\\').val(\"2020-10-18\");\n" +
                "\t\t$(\\'#CenterDatepickerToTimeData\\').val(\"18:00\");\n" +
                "\t    } else {\n" +
                "\t\tif ($(\\'#Centerdateto\\').length > 0) {\n" +
                "\t\t    var tmpDF = $(\\'#Centerdateto\\').val().split(\" \");\n" +
                "\t\t    var tmpDFDate = tmpDF[0];\n" +
                "\t\t    var tmpDFTime = tmpDF[1];\n" +
                "\t\t    tmpDFDate = tmpDFDate.split(\".\");\n" +
                "\t\t    tmpDFDate = tmpDFDate[2] + \"-\" + tmpDFDate[1] + \"-\" + tmpDFDate[0];\n" +
                "\n" +
                "\t\t    $(\\'#CenterDatepickerToData\\').val(tmpDFDate);\n" +
                "\t\t    $(\\'#CenterDatepickerToTimeData\\').val(tmpDFTime);\n" +
                "\t\t}\n" +
                "\t    }\n" +
                "\t});\n" +
                "    });\n" +
                "\n" +
                "    $(document).ready(function() {\n" +
                "\n" +
                "\tif ((MOBILE == \"phone\" || MOBILE == \"tablet\") && ($(\"#CenterdpFrameForm\").length > 0)) {\n" +
                "\t    $(\"#CenterdpFrameForm\").attr(\"action\", \"https://parking.koeln-bonn-airport.de/iPCP/reservation/create?skip=false&mobile=true\");\n" +
                "\t    $(\"#CenterdpFrameForm\").attr(\"target\", \"_blank\");\n" +
                "\t}\n" +
                "    });\n" +
                "</script><div class=\"row\">\n" +
                "\n" +
                "\t\t\t\t\t<div class=\"introticker col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n" +
                "\t\t\t\t\t\t<!-- - - - - - - - customHiddenNav - - - - - - - - - -->\n" +
                "\t\t\t\t\t\t<div class=\"customHiddenNav\">\t\t\t\n" +
                "\t\t\t\t\t\t\t<h2><a id=\"content_local\">Inhaltsbereich des Internetauftritts</a></h2>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<!-- - - - - - - - customHiddenNav - End - - - - - - - - - -->\t\t\t\t\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t\t\t\t<!-- CONTENTPARSER_START_CONTENT -->\n" +
                "\t\t  \t\t\t\t\n" +
                "    <!--TYPO3SEARCH_begin-->\n" +
                "    \n" +
                "<div id=\"c2093\" class=\"frame frame-default frame-type-list frame-layout-0\"><div class=\"tx-cgn-modules\"><script type=\"text/javascript\"\n" +
                "\t\tsrc=\"/fileadmin/cgn/theme/js/jquery.vticker.min.js\"></script><div id=\"_con5f897c6bac9db\" class=\"panel panel-default cgnContainer container-default  cgnContainerNewswidget\"><div class=\"panel-body\"><div id=\"newswidget\" style=\"display:none\"><ul><li><div><a\n" +
                "\t\t\t\thref=\"/am-airport/aktuelles/default-4bf5154851.html\"><i class=\"arr arr-direction1-e\"></i>Abschlussarbeiten an Startbahnoberfläche </div></a></li><li><div><a\n" +
                "\t\t\t\thref=\"/am-airport/aktuelles/default-c25dfc5f06.html\"><i class=\"arr arr-direction1-e\"></i>Tipps rund ums Fliegen in den Ferien </div></a></li><li><div><a\n" +
                "\t\t\t\thref=\"/am-airport/aktuelles/art-invest-real-estate-feiert-richtfest-des-moxy-hotels-am-flughafen-koelnbonn.html\"><i class=\"arr arr-direction1-e\"></i>Art-Invest Real Estate feiert Richtfest des Moxy Hotels am Flughafen Köln/Bonn</div></a></li><li><div><a\n" +
                "\t\t\t\thref=\"/am-airport/aktuelles/h2r-wasserstoff-rheinland-praesentiert-umfangreiche-plaene.html\"><i class=\"arr arr-direction1-e\"></i>&quot;H2R Wasserstoff Rheinland&quot; präsentiert umfangreiche Pläne</div></a></li><li><div><a\n" +
                "\t\t\t\thref=\"/am-airport/aktuelles/abschlussarbeiten-an-startbahnoberflaeche.html\"><i class=\"arr arr-direction1-e\"></i>Abschlussarbeiten an Startbahnoberfläche </div></a></li></ul></div></div></div><div class=\"pull-right\"><a href=\"/am-airport/aktuelles.html\"><i class=\"arr arr-direction1-e\"></i> Alle News</a></div><script type=\"text/javascript\">\n" +
                "\t\t $(document).ready(function() { \t\n" +
                "\t\t \t$(\"#newswidget\").vTicker({\n" +
                "\t\t\t\tspeed: 500,\n" +
                "\t\t\t\tpause: 4000,\n" +
                "\t\t\t\tanimation: \"fade\",\n" +
                "\t\t\t\tshowItems: 1,\n" +
                "\t\t\t\theight:100\n" +
                "\t\t\t});\n" +
                "\t\t \t$(\"#newswidget\").show();\n" +
                "\t\t });\n" +
                "\t\t\n" +
                "\t\t</script></div></div>\n" +
                "\n" +
                "\n" +
                "    <!--TYPO3SEARCH_end-->\n" +
                "\n" +
                "\t  \t\t\t\t\t<!-- CONTENTPARSER_END_CONTENT -->\n" +
                "  \t\t\t\t\t\n" +
                "\t\t\t\t\t\t<div class=\"customHiddenNav\">\n" +
                "\t\t\t\t\t\t    <p><a href=\"#navigation_local\">Abschnittsende: Rücksprung zur seiteninternen Navigation</a></p>\n" +
                "\t\t\t\t\t\t    <hr /><hr />\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<!-- - - - - - - - customHiddenNav - End - - - - - - - - - -->  \t\t\t\t\t\n" +
                "  \t\t\t\t\t</div>\n" +
                "  \t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "\t\n" +
                "\t<!-- - - - - - - - customHiddenNav - - - - - - - - - -->\n" +
                "\t<div class=\"customHiddenNav\">\t\t\t\n" +
                "\t\t<h2><a id=\"service_global\">Servicebereich des Internetauftritts</a></h2>\n" +
                "\t</div>\n" +
                "\t<!-- - - - - - - - customHiddenNav - End - - - - - - - - - -->\t\t\n" +
                "\t\n" +
                "\t<div class=\"footerhead\"><div class=\"container\"><div class=\"row\"><div class=\"col-lg-12\"><img class=\"\" alt=\"Skyline\" src=\"/fileadmin/cgn/theme/img/footer_skyline.png\" srcset=\"/fileadmin/cgn/theme/img/footer_skyline@2x.png 2x\"></div></div></div></div>\n" +
                "\t\n" +
                "\t<footer>\n" +
                "\t\t<div class=\"footerWrapper\">\n" +
                "\t\t    <div class=\"footerlinks\">\n" +
                "\t\t\t    <div class=\"container\"><div class=\"row\"><div class=\"footer-col-1 col-lg-3 col-md-3 col-sm-3 col-xs-12\">\n" +
                "<div id=\"c1\" class=\"frame frame-default frame-type-text frame-layout-0\"><header><h3 class=\"\">\n" +
                "\t\t\t\tParken\n" +
                "\t\t\t</h3></header><p>Direkt&nbsp;am Köln Bonn Airport parken, ohne Umwege. Jetzt reservieren!</p></div>\n" +
                "\n" +
                "\n" +
                "<div id=\"c2064\" class=\"frame frame-default frame-type-list frame-layout-0\"><div class=\"row\"><form id=\"dpFrameForm\" action=\"https://parking.koeln-bonn-airport.de/iPCP/reservation/create\" method=\"get\" target=\"_blank\"><div class=\"col-lg-4\" style=\"padding-right:0px;\"><p><label for=\"datefrom\">Parken von:</label></p></div><div class=\"form-group form-group-sm col-lg-8\"><div class=\\'input-group date\\' id=\\'datetimepickerFrom\\'><input type=\\'text\\' id=\"datefrom\" name=\"datefrom\" class=\"form-control\" /><span class=\"input-group-addon\"><span class=\"fa fa-calendar\"></span></span></div></div><div class=\"col-lg-4\" style=\"padding-right:0px;\"><p><label for=\"dateto\">bis:</label></p></div><div class=\"form-group form-group-sm col-lg-8\"><div class=\\'input-group date\\' id=\\'datetimepickerTo\\'><input type=\\'text\\' id=\"dateto\" name=\"dateto\" class=\"form-control\" /><span class=\"input-group-addon\"><span class=\"fa fa-calendar\"></span></span></div></div><div class=\"col-lg-12\"><button type=\"button\" class=\"btn btn-block\" onClick=\"$(\\'#dpFrameForm\\').submit();\">Wunschparkplatz finden<i class=\"arr arr-normal-e\"></i></button></div><input type=\"hidden\" name=\"loc\" value=\"de\" /><input type=\"hidden\" id=\"DatepickerFromData\" name=\"begin_date\" value=\"2020-10-17\" /><input type=\"hidden\" id=\"DatepickerToData\" name=\"end_date\" value=\"2020-10-18\" /><input type=\"hidden\" id=\"DatepickerFromTimeData\" name=\"begin_time\" value=\"14:00\" /><input type=\"hidden\" id=\"DatepickerToTimeData\" name=\"end_time\" value=\"18:00\" /><script type=\"text/javascript\">\n" +
                "            $(function () {\n" +
                "            \tvar locDP = \\'de\\';\n" +
                "            \t\n" +
                "                $(\\'#datetimepickerFrom\\').datetimepicker({\n" +
                "              \t\tlocale: locDP,\n" +
                "              \t\tstepping: 15,\n" +
                "              \t\tformat: \"DD.MM.YYYY HH:mm\",\n" +
                "                \tdefaultDate: \"2020-10-17 14:00\",\n" +
                "                \tsideBySide: true,\n" +
                "                \ticons: {\n" +
                "                    \ttime: \"fa fa-fw fa-clock-o\",\n" +
                "                    \tdate: \"fa fa-calendar\",\n" +
                "                    \tup: \"fa fa-arrow-up\",\n" +
                "                    \tdown: \"fa fa-arrow-down\",\n" +
                "\t                    next: \"fa fa-arrow-right\",\n" +
                "\t                    previous: \"fa fa-arrow-left\"\n" +
                "                \t}\n" +
                "            \t});\n" +
                "                $(\\'#datetimepickerTo\\').datetimepicker({\n" +
                "\t              \tlocale: locDP,\n" +
                "\t              \tstepping: 15,\n" +
                "\t                format: \"DD.MM.YYYY HH:mm\",\n" +
                "\t              \tdefaultDate: \"2020-10-18 18:00\",\n" +
                "\t                sideBySide: true,\n" +
                "\t                icons: {\n" +
                "\t                    time: \"fa fa-fw fa-clock-o\",\n" +
                "\t                    date: \"fa fa-calendar\",\n" +
                "\t                    up: \"fa fa-arrow-up\",\n" +
                "\t                    down: \"fa fa-arrow-down\",\n" +
                "\t                    next: \"fa fa-arrow-right\",\n" +
                "\t                    previous: \"fa fa-arrow-left\"\n" +
                "\t                }\n" +
                "            \t});\n" +
                "                \n" +
                "                $(\"#datetimepickerFrom\").on(\"dp.change\", function (e) {\n" +
                "                    var tmpDateTimeFrom = e.date;\n" +
                "                    if (!tmpDateTimeFrom) {\n" +
                "                    \t$(\\'#datetimepickerFrom\\').data(\"DateTimePicker\").date(\"2020-10-17 14:00\");\n" +
                "                    \t$(\\'#datefrom\\').val(\"17.10.2020 14:00\");\n" +
                "                    \t$(\\'#DatepickerFromData\\').val(\"2020-10-17\");\n" +
                "                    \t$(\\'#DatepickerFromTimeData\\').val(\"14:00\");\n" +
                "                    } else {\n" +
                "                    \tif ($(\\'#datefrom\\').length > 0) {\n" +
                "                    \t\tvar tmpDF = $(\\'#datefrom\\').val().split(\" \");\n" +
                "                    \t\tvar tmpDFDate = tmpDF[0];\n" +
                "                    \t\tvar tmpDFTime = tmpDF[1];\n" +
                "                    \t\ttmpDFDate = tmpDFDate.split(\".\");\n" +
                "                    \t\ttmpDFDate = tmpDFDate[2]+\"-\"+tmpDFDate[1]+\"-\"+tmpDFDate[0];\n" +
                "                    \t\t//console.log(tmpDFDate);\n" +
                "                    \t\t//console.log(tmpDFTime);\n" +
                "                        \t$(\\'#DatepickerFromData\\').val(tmpDFDate);\n" +
                "                        \t$(\\'#DatepickerFromTimeData\\').val(tmpDFTime);                    \t\t\n" +
                "                    \t}\n" +
                "                    }\n" +
                "                    //console.log(\"tmpDateTimeFrom:\" + tmpDateTimeFrom);\n" +
                "                });\n" +
                "                $(\"#datetimepickerTo\").on(\"dp.change\", function (e) {\n" +
                "                    var tmpDateTimeTo = e.date;\n" +
                "                    if (!tmpDateTimeTo) {\n" +
                "                    \t$(\\'#datetimepickerTo\\').data(\"DateTimePicker\").date(\"2020-10-18 18:00\");\n" +
                "                    \t$(\\'#dateto\\').val(\"18.10.2020 18:00\");\n" +
                "                    \t$(\\'#DatepickerToData\\').val(\"2020-10-18\");\n" +
                "                    \t$(\\'#DatepickerToTimeData\\').val(\"18:00\");\n" +
                "                    } else {\n" +
                "                    \tif ($(\\'#dateto\\').length > 0) {\n" +
                "                    \t\tvar tmpDF = $(\\'#dateto\\').val().split(\" \");\n" +
                "                    \t\tvar tmpDFDate = tmpDF[0];\n" +
                "                    \t\tvar tmpDFTime = tmpDF[1];\n" +
                "                    \t\ttmpDFDate = tmpDFDate.split(\".\");\n" +
                "                    \t\ttmpDFDate = tmpDFDate[2]+\"-\"+tmpDFDate[1]+\"-\"+tmpDFDate[0];\n" +
                "                    \t\t//console.log(tmpDFDate);\n" +
                "                    \t\t//console.log(tmpDFTime);\n" +
                "                        \t$(\\'#DatepickerToData\\').val(tmpDFDate);\n" +
                "                        \t$(\\'#DatepickerToTimeData\\').val(tmpDFTime);                    \t\t\n" +
                "                    \t}                    \t\n" +
                "                    }\n" +
                "                    //console.log(\"tmpDateTimeTo: \" + tmpDateTimeTo);\n" +
                "                });                \n" +
                "\t\t\t});\n" +
                "\n" +
                "            $(document).ready(function() {\n" +
                "\n" +
                "\t\t\t\tif ((MOBILE == \"phone\" || MOBILE == \"tablet\") && ($(\"#dpFrameForm\").length > 0)) {\n" +
                "\t\t\t\t\t$(\"#dpFrameForm\").attr(\"action\", \"https://parking.koeln-bonn-airport.de/iPCP/reservation/create?skip=false&mobile=true\");\n" +
                "\t\t\t\t\t$(\"#dpFrameForm\").attr(\"target\", \"_blank\");\n" +
                "\t\t\t\t}\n" +
                "            });\n" +
                "\n" +
                "            \n" +
                "        </script></form></div></div>\n" +
                "\n" +
                "</div><div class=\"footer-col-2 col-lg-3 col-md-3 col-sm-3 col-xs-12\">\n" +
                "<div id=\"c2\" class=\"frame frame-default frame-type-text frame-layout-0\"><header><h3 class=\"\">\n" +
                "\t\t\t\tAktuelles\n" +
                "\t\t\t</h3></header></div>\n" +
                "\n" +
                "<div class=\"tx-cgn-modules\">\n" +
                "\t\n" +
                "\t\n" +
                "\t\n" +
                "\t<ul>\n" +
                "\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t\t<li><a href=\"/am-airport/aktuelles/default-4bf5154851.html\">Abschlussarbeiten an Startbahnoberfläche </a></li>\n" +
                "\t\t\t\n" +
                "\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t\t<li><a href=\"/am-airport/aktuelles/default-c25dfc5f06.html\">Tipps rund ums Fliegen in den Ferien </a></li>\n" +
                "\t\t\t\n" +
                "\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t\t<li><a href=\"/am-airport/aktuelles/art-invest-real-estate-feiert-richtfest-des-moxy-hotels-am-flughafen-koelnbonn.html\">Art-Invest Real Estate feiert Richtfest des Moxy Hotels am Flughafen Köln/Bonn</a></li>\n" +
                "\t\t\t\n" +
                "\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t\t<li><a href=\"/am-airport/aktuelles/h2r-wasserstoff-rheinland-praesentiert-umfangreiche-plaene.html\">&quot;H2R Wasserstoff Rheinland&quot; präsentiert umfangreiche Pläne</a></li>\n" +
                "\t\t\t\n" +
                "\t\t\n" +
                "\t\t\t\n" +
                "\t\t\t\t<li><a href=\"/am-airport/aktuelles/abschlussarbeiten-an-startbahnoberflaeche.html\">Abschlussarbeiten an Startbahnoberfläche </a></li>\n" +
                "\t\t\t\n" +
                "\t\t\n" +
                "\n" +
                "\t</ul>\n" +
                "\t<p>\n" +
                "\t\t<a href=\"/am-airport/aktuelles.html\">Alle News</a>\n" +
                "\t</p>\n" +
                "\n" +
                "\n" +
                "</div></div><div class=\"footer-col-3 col-lg-3 col-md-3 col-sm-3 col-xs-12\">\n" +
                "<div id=\"c3\" class=\"frame frame-default frame-type-text frame-layout-0\"><header><h3 class=\"\">\n" +
                "\t\t\t\tAnfahrt\n" +
                "\t\t\t</h3></header><p>Anreise mit dem Navigationsgerät:<br />Köln Bonn Airport, Kennedystraße,<br />51147 Köln\n" +
                "</p><p><a href=\"/parken-anreise/anreise-mit-dem-pkw.html\" class=\"internal-link\">Ausführliche Beschreibung</a></p></div>\n" +
                "\n" +
                "</div><div class=\"footer-col-4 col-lg-3 col-md-3 col-sm-3 col-xs-12\">\n" +
                "<div id=\"c9\" class=\"frame frame-default frame-type-text frame-layout-0\"><header><h3 class=\"\">\n" +
                "\t\t\t\tHilfreiche Links\n" +
                "\t\t\t</h3></header><ul><li><a href=\"/fluege/flug-buchen.html\" class=\"internal-link\">Flug buchen</a></li><li><a href=\"/serviceseiten/hilfe-faq.html\" class=\"internal-link\">Hilfe &amp; FAQ</a></li><li><a href=\"/serviceseiten/sitemap.html\" class=\"internal-link\">Sitemap</a></li><li><a href=\"/serviceseiten/impressum.html\" class=\"internal-link\">Impressum</a></li><li><a href=\"/serviceseiten/datenschutz.html\" class=\"internal-link\">Hinweise zum Datenschutz</a></li><li><a href=\"/serviceseiten/portal.html\" class=\"internal-link\">Portal</a></li><li><a href=\"/b2b/vertragsbedingungen-entgelte.html\" class=\"internal-link\">AGBs</a></li></ul></div>\n" +
                "\n" +
                "</div></div></div>\n" +
                "\t\t    </div>\n" +
                "\t\t\t\n" +
                "\t\t\t<div class=\"social\">\n" +
                "\t\t\t\t<div class=\"container\">\n" +
                "\t\t\t\t\t<div class=\"row \">\n" +
                "\t\t\t\t\t\t<div class=\"col-lg-5 col-md-5 col-sm-5 col-xs-8\">\n" +
                "\t\t\t\t\t\t\t<a class=\"sm-link\" target=\"_blank\" href=\"https://www.facebook.com/KoelnBonnAirport\"><i class=\"fa fa-facebook\"></i><span>Facebook</span></a> \n" +
                "\t\t\t\t\t\t\t<a class=\"sm-link\" target=\"_blank\" href=\"https://www.instagram.com/airportcgn/\"><i class=\"fa fa-instagram\"></i><span>Instagram</span></a>\n" +
                "\t\t\t\t\t\t\t<a class=\"sm-link\" target=\"_blank\" href=\"https://twitter.com/AirportCGN\"><i class=\"fa fa-twitter\"></i><span>Twitter</span></a>\n" +
                "\t\t\t\t\t\t\t<a class=\"sm-link\" target=\"_blank\" href=\"https://www.youtube.com/channel/UCfC3r29U6ueu-AfOE0fSEfg\"><i class=\"fa fa-youtube\"></i><span>Youtube</span></a>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t\t<div class=\"col-lg-7 col-md-7 col-sm-7 col-xs-4\"  style=\"padding:5px\">\n" +
                "\t\t\t\t\t\t<div class=\"pull-right\"><!--<a href=\"/fluege/flug-buchen.html\"><img class=\"img-responsive\" src=\"/fileadmin/cgn/theme/img/navigation/cgnhubbing.png\"></a>--></div>\n" +
                "\t\t\t\t\t\t</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div class=\"copyright\">\n" +
                "\t\t\t\t<div class=\"container\">\n" +
                "\t\t\t\t\t<div class=\"row \">\n" +
                "\t\t\t\t\t\t<div class=\"col-lg-12 col-md-12 col-md-12 col-sm-12 \">\n" +
                "\t\t\t\t\t\t\tCopyright 2020 Köln Bonn Airport</div>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t</footer>\n" +
                "\t\n" +
                "\t<!-- - - - - - - - customHiddenNav - - - - - - - - - -->\n" +
                "\t<div class=\"customHiddenNav\">\n" +
                "\t    <p><a href=\"#navigation_local\">Abschnittsende: Rücksprung zur seiteninternen Navigation</a></p>\n" +
                "\t    <hr /><hr />\n" +
                "\t</div>\n" +
                "\t<!-- - - - - - - - customHiddenNav - End - - - - - - - - - -->\t\t\n" +
                "</div>\n" +
                "\n" +
                "<script>\n" +
                "\tfunction respositingFooterAndContentContainer() {\n" +
                "\t\tvh=$(window).height();\n" +
                "\t\tnh=$(\\'.navbar\\').height() + $(\\'.naviwrapper\\').height() + $(\\'.footerhead\\').height() +90;\n" +
                "\t\t//console.log(\"windowHeight: \"+vh);\n" +
                "\t\t//console.log(\"naviHeight: \"+nh);\t\t\n" +
                "\t\tif($(\\'#content\\').height() < vh-nh && $(window).width()>768) {\n" +
                "\t\t\ttmpContentHeight = $(\\'#content\\').height();\n" +
                "\t\t\ttmpHeightToUse = vh-nh-tmpContentHeight;\n" +
                "\t\t\ttmpPadding = tmpHeightToUse / 2;\n" +
                "\t\t\t//console.log(\"initial - content height: \" + tmpContentHeight);\n" +
                "\t\t\t//console.log(\"tmpHeightToUse: \" + tmpHeightToUse);\n" +
                "\t\t\t//console.log(\"tmpPadding: \"+tmpPadding);\n" +
                "\t\t\t\n" +
                "\t\t\t$(\\'#content\\').css({\\'paddingTop\\':tmpPadding+\\'px\\', paddingBottom:tmpPadding+\\'px\\'});\n" +
                "\t\t\t//console.log(\"after CSS adjustment - content height: \" + tmpContentHeight);\n" +
                "\t\t\t\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\t\t\n" +
                "\t$(window).load(function() {\n" +
                "\t\trespositingFooterAndContentContainer();\n" +
                "\t});\n" +
                "\n" +
                "\t$(window).on(\"resize\", function() {\n" +
                "\t\trespositingFooterAndContentContainer();\n" +
                "\t});\n" +
                "\t\n" +
                "</script>\n" +
                "<script src=\"/typo3temp/assets/compressed/merged-63b5e55ffee7675722613246c4624e84.js?1583325621\" type=\"text/javascript\"></script>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div class=\"tx-cgn-modules\">\n" +
                "\t\n" +
                "\t\n" +
                "\t\n" +
                "\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        assertFalse(hc.containsFlightplan(html));
    }

}