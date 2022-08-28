package app.entity;

import java.io.Serializable;

/**
 * Class that represents Periodical topic in system.
 */
public class Topic implements Serializable {

    private int id;
    private String engTopicName;
    private String ukrTopicName;

    /**
     * The constructor for Topic without parameters.
     */
    public Topic() {
    }

    /**
     * Constructor for Topic.
     * Used to create new Topic.
     *
     * @param engTopicName   name of topic in english language
     * @param ukrTopicName   name of topic in ukrainian language
     */
    public Topic(String engTopicName, String ukrTopicName) {
        this.engTopicName = engTopicName;
        this.ukrTopicName = ukrTopicName;
    }

    /**
     * Constructor for Topic.
     * Used to create new Topic.
     *
     * @param id             topic id
     * @param engTopicName   name of topic in english language
     * @param ukrTopicName   name of topic in ukrainian language
     */
    public Topic(int id, String engTopicName, String ukrTopicName) {
        this.id = id;
        this.engTopicName = engTopicName;
        this.ukrTopicName = ukrTopicName;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getEngTopicName() {return engTopicName;}

    public void setEngTopicName(String engTopicName) {this.engTopicName = engTopicName;}

    public String getUkrTopicName() {return ukrTopicName;}

    public void setUkrTopicName(String ukrTopicName) {this.ukrTopicName = ukrTopicName;}

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", engTopicName='" + engTopicName + '\'' +
                ", ukrTopicName='" + ukrTopicName + '\'' +
                '}';
    }
}
