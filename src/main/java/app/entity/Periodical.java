package app.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class that represents Periodical entity in system.
 */
public class Periodical implements Serializable {

    private int id;
    private String engTitle;
    private String ukrTitle;
    private String engDescription;
    private String ukrDescription;
    private int issue;
    private BigDecimal price;

    /**
     * The constructor for Periodical without parameters.
     */
    public Periodical() {
    }

    /**
     * Constructor to create new Periodical.
     *
     * @param id                periodical id
     * @param engTitle          title of periodical in english language
     * @param ukrTitle          title of periodical in ukrainian language
     * @param engDescription    description of periodical in english language
     * @param ukrDescription    description of periodical in ukrainian language
     * @param issue             number of current periodical issue
     * @param price             cost of a periodical
     */
    public Periodical(int id, String engTitle, String ukrTitle, String engDescription, String ukrDescription, int issue, BigDecimal price) {
        this.id = id;
        this.engTitle = engTitle;
        this.ukrTitle = ukrTitle;
        this.engDescription = engDescription;
        this.ukrDescription = ukrDescription;
        this.issue = issue;
        this.price = price;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getEngTitle() {return engTitle;}

    public void setEngTitle(String engTitle) {this.engTitle = engTitle;}

    public String getUkrTitle() {return ukrTitle;}

    public void setUkrTitle(String ukrTitle) {this.ukrTitle = ukrTitle;}

    public String getEngDescription() {return engDescription;}

    public void setEngDescription(String engDescription) {this.engDescription = engDescription;}

    public String getUkrDescription() {return ukrDescription;}

    public void setUkrDescription(String ukrDescription) {this.ukrDescription = ukrDescription;}

    public int getIssue() {return issue;}

    public void setIssue(int issue) {this.issue = issue;}

    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {this.price = price;}

    @Override
    public String toString() {
        return "Periodical{" +
                "id=" + id +
                ", engTitle='" + engTitle + '\'' +
                ", ukrTitle='" + ukrTitle + '\'' +
                ", engDescription='" + engDescription + '\'' +
                ", ukrDescription='" + ukrDescription + '\'' +
                ", issue=" + issue +
                ", price=" + price +
                '}';
    }
}
