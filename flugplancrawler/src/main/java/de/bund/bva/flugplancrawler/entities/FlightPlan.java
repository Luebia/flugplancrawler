package de.bund.bva.flugplancrawler.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**Diese Klasse stammt von Burak. 
 * 
 * 
 * 
 * 
 * Dies ist Klasse für den Flugplan.
 * Hier wird der Flugplan verarbeitet.
 */
public class FlightPlan extends Website {
	

	
    private Pattern patternFlightPlan;
    private LinkedList<String> flightPlanStructure;
    private ArrayList<Arrival> arrivals;
    private ArrayList<Departure> departures;
    private Element HTMLwholeFlightPlan;
    private String TXTwholeFlightPlan;
    private Elements HTMLtableFlightPlan;
    private String TXTtableFlightPlan;
    private Element HTMLheaderTableFlightPlan;
    private String TXTheaderTableFlightplan;
    private String flightPlanType;
    private int nrOfColumns;
    private int columnNrDestination;
    private int columnNrCheckIn;
    private int columnNrGate;
    private int columnNrFlightNo;
    private int columnNrStatus;
    private int columnNrDepartureTime;
    private int columnNrFrom;
    private int columnNrVIA;
    private int columnNrAirline;
    private int columnNrArrivalTime;
    private int columnNrExpected;
    private int columnNrTerminal;
    private int columnNrDetails;
    private int columnNrCounter;
    private int columnNrCodeshare;
    private int columnNrArrivalAtDestination;
    
  
	


    /**
     * Getter Methode um den Flugplan RegEx Pattern wieder zu geben.
     *
     * @return Gibt den Pattern Objekt zurück.
     */
    public Pattern getPatternFlightPlan() {
        return patternFlightPlan;
    }

    /**
     * Setter Methodel, um den Pattern für den Flugplan du setzen und zu kompilieren.
     * Der Reguläreausdruck wird mit "Multiline" geflagt.
     *
     * @param regEx Reguläreausdurck wird als String in die Methode eingegeben.
     */
    public void setPatternFlightPlan(String regEx) {
        if (regEx.isEmpty()) {
            System.out.println("[ ] Regex Pattern wurde gesetzt.");
        } else {
            this.patternFlightPlan = Pattern.compile(regEx, Pattern.MULTILINE);
            System.out.println("[X] Regex Pattern wurde gesetzt.");
        }
    }

    /**
     * Gibt die Reihenfolge der Überschriften von der Flugplantabelle wieder.
     *
     * @return Gibt die Überschriften in einer LinkedList wieder.
     */
    public LinkedList<String> getFlightPlanStructure() {
        return flightPlanStructure;
    }

    /**
     * Setter Methode um die Reihenfolge der Überschriften festzulegen.
     * In die Methode wird ein String übergeben. Die Buchstaben des String werden zu Kleinbuchstaben.
     * Anschließend werden alle Leerzeichen mit einem Komma ersetzt.
     * Nachder verarbeitung des Strings wird diese in ein Array gespeichert und die Länge des Strings in eine andere Methode weitergegeben.
     *
     * @param headers Überschriften aus dem HTML-Code des Flugplans als String.
     */
    public void setFlightPlanStructure(String headers) {
        if (headers.isEmpty()) {
            System.out.println("[ ] Struktur des Flugplans wurde festgelegt.");
        } else {
            String header = headers.toLowerCase().trim();
            header = header.replaceAll("\\s", ",");
            String[] tmp;
            tmp = header.split(",");
            setNrOfColumns(tmp.length);
            LinkedList<String> structure = new LinkedList<>(Arrays.asList(tmp));
            setColumnsOrder(structure);
            this.flightPlanStructure = structure;
            System.out.println("[X] Struktur des Flugplans wurde festgelegt");
        }
    }


    /**
     * Setter für die Reihenfolge der Spalten in Ankünften.
     *
     * @param structure Die Struktur die erstellt wurden ist wird hier als Parameter übergeben.
     */
    private void setColumnsOrderArrival(LinkedList<String> structure) {
        String topic = "";
        for (int i = 0; i < structure.size(); i++) {
            topic = structure.get(i);
            if (topic.matches("^(flug.?ziel|nach|ziel)$")) {
                this.columnNrDestination = i + 1;
            } else if (topic.matches("^(check.?in|kontrolle)$")) {
                this.columnNrCheckIn = i + 1;
            } else if (topic.matches("^(gates?)$")) {
                this.columnNrGate = i + 1;
            } else if (topic.matches("^(flug.?(nummer|nr).?|flug)$")) {
                this.columnNrFlightNo = i + 1;
            } else if (topic.matches("^status$")) {
                this.columnNrStatus = i + 1;
            } else if (topic.matches("^(aus|von)$")) {
                this.columnNrFrom = i + 1;
            } else if (topic.matches("^(via|\\(über\\))$")) {
                this.columnNrVIA = i + 1;
            } else if (topic.matches("^airline$")) {
                this.columnNrAirline = i + 1;
            } else if (topic.matches("^(ankunft|geplant|plan)$")) {
                this.columnNrArrivalTime = i + 1;
            } else if (topic.matches("^erwartet$")) {
                this.columnNrExpected = i + 1;
            } else if (topic.matches("^terminal$")) {
                this.columnNrTerminal = i + 1;
            } else if (topic.matches("^flugdetails$")) {
                this.columnNrDetails = i + 1;
            } else if (topic.matches("^(counter|schalter)$")) {
                this.columnNrCounter = i + 1;
            } else if (topic.matches("^(codeshare)$")) {
                this.columnNrCodeshare = i + 1;
            } else if (topic.matches("^(buchen|sms|infos?|weitere)$")) {
                //Hier kann man Überschriften ausfiltern die man nicht mit in der weiteren Bearbeitung einbeziehen möchte.
            } else {
                System.out.println("Diese Überschrift konnte nicht zugewiesen werden: " + topic);
            }
        }
    }

    private void setColumnsOrderDeparture(LinkedList<String> structure) {
        String topic = "";
        for (int i = 0; i < structure.size(); i++) {
            topic = structure.get(i);
            if (topic.matches("^(flug.?ziel|nach|reiseziel|flughafen|von|ziel)$")) {
                this.columnNrDestination = i + 1;
            } else if (topic.matches("^(check.?in|kontrolle)$")) {
                this.columnNrCheckIn = i + 1;
            } else if (topic.matches("^(gates?)$")) {
                this.columnNrGate = i + 1;
            } else if (topic.matches("^(flug.?(nummer|nr).?|flug)$")) {
                this.columnNrFlightNo = i + 1;
            } else if (topic.matches("^status$")) {
                this.columnNrStatus = i + 1;
            } else if (topic.matches("^(ab\\W*flug\\W*zeit$|abflug|geplant|tag|zeit)")) {
                this.columnNrDepartureTime = i + 1;
            } else if (topic.matches("^(ankunft)")) {
                this.columnNrArrivalAtDestination = i + 1;
            }
            /* else if (topic.matches("^(aus|von)$")) {
                this.columnNrFrom = i + 1;
            } */
            else if (topic.matches("^(via|über)$")) {
                this.columnNrVIA = i + 1;
            } else if (topic.matches("^airline$")) {
                this.columnNrAirline = i + 1;
            } else if (topic.matches("^erwartet$")) {
                this.columnNrExpected = i + 1;
            } else if (topic.matches("^terminal$")) {
                this.columnNrTerminal = i + 1;
            } else if (topic.matches("^(flugdetails|zeitraum)$")) {
                this.columnNrDetails = i + 1;
            } else if (topic.matches("^(counter|schalter)$")) {
                this.columnNrCounter = i + 1;
            } else if (topic.matches("^(codeshare)$")) {
                this.columnNrCodeshare = i + 1;
            } else if (topic.matches("^(buchen|sms|info||iata-code|ankunft|verkehrstage| )$")) {
                //Hier kann man Überschriften ausfiltern die man nicht mit in der weiteren Bearbeitung einbeziehen möchte.
            } else {
                System.out.println("Diese Überschrift konnte nicht zugewiesen werden: " + topic);
            }
        }
    }

    private void setColumnsOrder(LinkedList<String> structure) {
        if (structure.isEmpty()) {
            System.out.println("[ ] Reihenfolge der Spalten wurde festgelegt");
        } else {
            switch (this.flightPlanType) {
                case "ankunft":
                    setColumnsOrderArrival(structure);
                    break;
                case "abflug":
                    setColumnsOrderDeparture(structure);
                    break;
                default:
                    System.out.println("[ ] Reihenfolge der Spalten wurde festgelegt");
            }
            System.out.println("[X] Reihenfolge der Spalten wurde festgelegt");
        }
    }

    public ArrayList<Arrival> getArrivals() {
        return arrivals;
    }


    private void updateRegEx() {
        if (patternFlightPlan.matcher(TXTtableFlightPlan).groupCount() < nrOfColumns) {
            StringBuilder regEx = new StringBuilder(getPatternFlightPlan().pattern());
            for (int i = patternFlightPlan.matcher(TXTtableFlightPlan).groupCount(); i < nrOfColumns; i++) {
                regEx.append("()");
            }
            setPatternFlightPlan(regEx.toString());
        }
    }

    public void setArrivals() {
        ArrayList<Arrival> arrivals = new ArrayList<>();
        Arrival arrival;
        updateRegEx();
        Matcher matcher = this.patternFlightPlan.matcher(TXTtableFlightPlan);
        while (matcher.find()) {
            arrival = new Arrival();
            if (this.columnNrArrivalTime != 0) {
                arrival.setArrivalTime(matcher.group(this.columnNrArrivalTime));
            }
            if (this.columnNrFlightNo != 0) {
                arrival.setFlightNo(matcher.group(this.columnNrFlightNo));
                arrival.setAirline(matcher.group(this.columnNrFlightNo));
            }
            if (this.columnNrStatus != 0) {
                arrival.setStatus(matcher.group(this.columnNrStatus));
            }
            if (this.columnNrTerminal != 0) {
                arrival.setTerminal(matcher.group(this.columnNrTerminal));
            }
            if (this.columnNrExpected != 0) {
                arrival.setExpectedTime(matcher.group(this.columnNrExpected));
            }
            if (this.columnNrFrom != 0) {
                arrival.setFrom(matcher.group(this.columnNrFrom));
            }
            if (this.columnNrVIA != 0) {
                arrival.setVia(matcher.group(this.columnNrVIA));
            }
            if (this.columnNrCodeshare != 0) {
                arrival.setCodeshare(matcher.group(this.columnNrCodeshare));
            }
            if (this.columnNrDetails != 0) {
                arrival.setDetails(matcher.group(this.columnNrDetails));
            }
            if (this.columnNrFlightNo == 0 && this.columnNrAirline != 0) {
                arrival.setAirline(matcher.group(this.columnNrAirline));
            }
            arrivals.add(arrival);
            arrival = null;
        }
        this.arrivals = arrivals;
    }

    public ArrayList<Departure> getDepartures() {
        return departures;
    }

    public void setDepartures() {
    	
    	
        ArrayList<Departure> departures = new ArrayList<>();
        Departure departure;
        updateRegEx();
        Matcher matcher = this.patternFlightPlan.matcher(TXTtableFlightPlan);
        while (matcher.find()) {
            departure = new Departure();
            if (columnNrDepartureTime != 0) {
                departure.setDepartureTime(matcher.group(columnNrDepartureTime));
            }
            if (columnNrGate != 0) {
                departure.setGate(matcher.group(columnNrGate));
            }
            if (columnNrDestination != 0) {
                departure.setFlightDestination(matcher.group(columnNrDestination));
            }
            if (columnNrFlightNo != 0) {
                departure.setFlightNo(matcher.group(columnNrFlightNo));
                departure.setAirline(matcher.group(columnNrFlightNo));
            }
            if (columnNrCheckIn != 0) {
                departure.setCheckIn(matcher.group(columnNrCheckIn));
            }
            if (columnNrTerminal != 0) {
                departure.setTerminal(matcher.group(columnNrTerminal));
            }
            if (columnNrVIA != 0) {
                departure.setVia(matcher.group(columnNrVIA));
            }
            if (columnNrExpected != 0) {
                departure.setExpectedTime(matcher.group(columnNrExpected));
            }
            if (columnNrCodeshare != 0) {
                departure.setCodeshare(matcher.group(columnNrCodeshare));
            }
            if (columnNrStatus != 0) {
                departure.setStatus(matcher.group(columnNrStatus));
            }
            if (columnNrDetails != 0) {
                departure.setDetails(matcher.group(columnNrDetails));
            }
            if (columnNrArrivalAtDestination != 0) {
                departure.setArrivalAtDestination(matcher.group(columnNrArrivalAtDestination));
            }
            
            departures.add(departure);
            

            
         // Sinnfrei, da oben ein neues Objekt pro Schleifendurchlauf erzeugt wird
//            departure = null;
        }
        this.departures = departures;
    }

    public Element getHTMLwholeFlightPlan() {
        return HTMLwholeFlightPlan;
    }


    public void setHTMLwholeFlightPlan(String id) {
        if (id == null || id.isEmpty()) {
            System.out.println("[ ] Flugplan wurde gefunden und gespeichert. Der HTML-Code der kompletten Flugplantabelle konnte nicht gesetzt werden, ");
            System.out.println("da der übergabeparameter leer oder null ist. Übergabeparameter: " + id);
        } else {
            try {
                if (super.getWholeHTML().body().getElementById(id) == null) {
                    this.HTMLwholeFlightPlan = super.getWholeHTML().body().getElementsByClass(id).first();
                } else {
                    this.HTMLwholeFlightPlan = super.getWholeHTML().body().getElementById(id);
                }
                setTXTwholeFlightPlan(this.HTMLwholeFlightPlan.text());
                System.out.println("[X] Flugplan wurde gefunden und gespeichert.");
            } catch (Exception e) {
                System.out.println("[ ] Flugplan wurde gefunden und gespeichert.");
            }
        }
    }

    public Elements getHTMLtableFlightPlan() {
        return HTMLtableFlightPlan;
    }

    public void setHTMLtableFlightPlan(String attributeValue) {
        if (attributeValue == null || attributeValue.isEmpty()) {
            System.out.println("[ ] Flugplantabelle gesetzt. Der HTML-Code der inneren Flugplantabelle konnte nicht gesetzt werden, ");
            System.out.println("da der Übergabeparameter leer oder null ist.");
        } else {
            try {
                if (attributeValue.matches("(ul|tbody)")) {
                    this.HTMLtableFlightPlan = HTMLwholeFlightPlan.getElementsByTag(attributeValue);
                } else if (HTMLwholeFlightPlan.getElementsByClass(attributeValue).isEmpty()) {
                    this.HTMLtableFlightPlan = HTMLwholeFlightPlan.getElementById(attributeValue).children();
                } else {
                    this.HTMLtableFlightPlan = HTMLwholeFlightPlan.getElementsByClass(attributeValue);
                }
                setTXTtableFlightPlan(this.HTMLtableFlightPlan.text());
                System.out.println("[X] Flugplantabelle gesetzt.");
            } catch (Exception e) {
                System.out.println("[ ] Flugplantabelle gesetzt.");
            }
        }
    }

    public Element getHTMLheaderTableFlightPlan() {
        return HTMLheaderTableFlightPlan;
    }

    public void setHTMLheaderTableFlightPlan(String attributeValue) {
        if (attributeValue == null || attributeValue.isEmpty()) {
            System.out.println("[ ] Tabellen Überschriften gesetzt. Der HTML-Code der Überschriften der Flugplantabelle konnte nicht gesetzt werden, ");
            System.out.println("da der Übergabeparameter leer oder null ist. Übergabeparameter: " + attributeValue);
        } else {
            try {
                if (attributeValue.matches("^(header|thead)$")) {
                    this.HTMLheaderTableFlightPlan = HTMLwholeFlightPlan.getElementsByTag(attributeValue).first();
                } else if (attributeValue.matches("^tr$")) {
                    this.HTMLheaderTableFlightPlan = HTMLwholeFlightPlan.getElementsByTag(attributeValue).first();
                } else {
                    this.HTMLheaderTableFlightPlan = HTMLwholeFlightPlan.getElementsByClass(attributeValue).first();
                }
                setTXTheaderTableFlightplan(this.HTMLheaderTableFlightPlan.text());
                System.out.println("[X] Tabellen Überschriften gesetzt.");
            } catch (Exception e) {
                System.out.println("[ ] Tabellen Überschriften gesetzt.");
            }
        }
    }

    public void flightPlanHandler(String idWholeFlightPlan, String idTableheaderFlightplan, String idInnerFlightTable, String flightPlanType) {
        if (idWholeFlightPlan.isEmpty() || idTableheaderFlightplan.isEmpty() || flightPlanType.isEmpty()) {
            System.out.println("Übergabe Parameter sind leer.");
        } else {
            setFlightPlanType(flightPlanType);
            setHTMLwholeFlightPlan(idWholeFlightPlan);
            setHTMLheaderTableFlightPlan(idTableheaderFlightplan);
            try {
                setFlightPlanStructure(TXTheaderTableFlightplan);
            } catch (Exception e) {
                System.out.println("[ ] Struktur des Flugplans wurde festgelegt.");
            }
            setHTMLtableFlightPlan(idInnerFlightTable);
            setFlightPlanContent();
        }
    }

    private void setFlightPlanContent() {
        switch (this.flightPlanType) {
            case "ankunft":
                try {
                    setArrivals();
                    System.out.println("[X] Ankünfte wurden gefiltert. So viele Ankünfte wurden gefunden: " + arrivals.size());
                } catch (Exception e) {
                    System.out.println("[ ] Ankünfte wurden gefiltert.");
                }
                break;
            case "abflug":
                try {
                    setDepartures();
                    System.out.println("[X] Abflüge wurden gefiltert. So viele Abflüge wurden gefunden: " + departures.size());
                } catch (Exception e) {
                    System.out.println("[ ] Abflüge wurden gefiltert.");
                }
                break;
            default:
                System.out.println("Flugplan konnte keiner Art eingeordnetwerden.");
                System.out.println("Default wird aufgerufen.");
                break;
        }
    }

    public String getFlightPlanType() {
        return flightPlanType;
    }

    public void setFlightPlanType(String flightPlanType) {
        if (flightPlanType == null || flightPlanType.isEmpty()) {
            System.out.println("[ ] Flugplan Typ gesetzt.");
        } else {
            this.flightPlanType = flightPlanType.trim().toLowerCase(Locale.ROOT);
            System.out.println("[X] Flugplan Typ gesetzt.");
        }
    }

    public void setNrOfColumns(int nrOfColumns) {
        if (nrOfColumns <= 0) {
            System.out.println("[ ] Spaltenanzahl gesetzt (" + nrOfColumns + ").");
        } else {
            this.nrOfColumns = nrOfColumns;
            System.out.println("[X] Spaltenanzahl gesetzt (" + nrOfColumns + ").");
        }
    }

    public String getColumnNumbers() {
        return "Spalten{" +
                "nrOfColumns=" + nrOfColumns +
                ", columnNrDestination=" + columnNrDestination +
                ", columnNrCheckIn=" + columnNrCheckIn +
                ", columnNrGate=" + columnNrGate +
                ", columnNrFlightNo=" + columnNrFlightNo +
                ", columnNrStatus=" + columnNrStatus +
                ", columnNrDepartureTime=" + columnNrDepartureTime +
                ", columnNrFrom=" + columnNrFrom +
                ", columnNrVIA=" + columnNrVIA +
                ", columnNrAirline=" + columnNrAirline +
                ", columnNrArrivalTime=" + columnNrArrivalTime +
                ", columnNrExpected=" + columnNrExpected +
                ", columnNrTerminal=" + columnNrTerminal +
                ", columnNrDetails=" + columnNrDetails +
                ", columnNrCounter=" + columnNrCounter +
                ", columnNrArrivalAtDestination=" + columnNrArrivalAtDestination +
                '}';
    }

    public String getTXTwholeFlightPlan() {
        return TXTwholeFlightPlan;
    }

    public void setTXTwholeFlightPlan(String TXTwholeFlightPlan) {
        this.TXTwholeFlightPlan = TXTwholeFlightPlan;
    }

    public String getTXTtableFlightPlan() {
        return TXTtableFlightPlan;
    }

    public void setTXTtableFlightPlan(String TXTtableFlightPlan) {
        this.TXTtableFlightPlan = TXTtableFlightPlan;
    }

    public String getTXTheaderTableFlightplan() {
        return TXTheaderTableFlightplan;
    }

    public void setTXTheaderTableFlightplan(String TXTheaderTableFlightplan) {
        this.TXTheaderTableFlightplan = TXTheaderTableFlightplan;
    }

    public String getTXT() {
        return "FlightPlan{" +
                "\nTXTwholeFlightPlan='" + TXTwholeFlightPlan + '\'' +
                "\nTXTtableFlightPlan='" + TXTtableFlightPlan + '\'' +
                "\nTXTheaderTableFlightplan='" + TXTheaderTableFlightplan + '\'' +
                '}';
    }
}
