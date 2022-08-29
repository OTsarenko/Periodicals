package app.entity;

/**
 * Class that represents Event entity in system.
 */
public class Event {

    private Periodical periodical;
    private final int eventType;
    private String alertUkr;
    private String alertEng;

    public Event(Periodical periodical) {
        this.periodical = periodical;
        eventType = periodical.getId();
        alertUkr = "Вас чекає новий випуск: " + periodical.getUkrTitle();
        alertEng = "You are checked for a new issue: " + periodical.getEngTitle();
    }

    public Periodical getPeriodical() {
        return periodical;
    }

    public int getEventType() {return eventType;}

    public String getAlertUkr() {
        return alertUkr;
    }

    public String getAlertEng() {
        return alertEng;
    }

    @Override
    public String toString() {
        return "Event{" +
                "periodical=" + periodical +
                ", eventType=" + eventType +
                ", alertUkr='" + alertUkr + '\'' +
                ", alertEng='" + alertEng + '\'' +
                '}';
    }
}
