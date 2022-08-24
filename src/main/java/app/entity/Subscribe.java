package app.entity;

import java.io.Serializable;

/**
 * The class that represents user`s subscribe.
 */
public class Subscribe implements Serializable {

    private int id;
    private int periodicalID;
    private int userID;
    private int finalIssue;

    /**
     * The constructor for Subscribe without parameters.
     */
    public Subscribe() {}

    /**
     * The constructor to create new Subscribe.
     *
     * @param id              subscribe id
     * @param periodicalID    periodical id
     * @param userID          user id
     * @param finalIssue      last accessible issue for reader
     */
    public Subscribe(int id, int periodicalID, int userID, int finalIssue) {
        this.id = id;
        this.periodicalID = periodicalID;
        this.userID = userID;
        this.finalIssue = finalIssue;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getPeriodicalID() {return periodicalID;}

    public void setPeriodicalID(int periodicalID) {this.periodicalID = periodicalID;}

    public int getUserID() {return userID;}

    public void setUserID(int userID) {this.userID = userID;}

    public int getFinalIssue() {return finalIssue;}

    public void setFinalIssue(int finalIssue) {this.finalIssue = finalIssue;}

    /**
     * The method is used to count the last issue purchased by the reader
     */
    public void countFinalIssue(int amount) {
            setFinalIssue(finalIssue + amount);
    }

    @Override
    public String toString() {
        return "Subscribe{" +
                "id=" + id +
                ", periodicalID=" + periodicalID +
                ", userID=" + userID +
                ", finalIssue=" + finalIssue +
                '}';
    }
}
