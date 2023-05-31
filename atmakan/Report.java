package com.example.atmakan;

public class Report {

    private String feedback, therapistID, userID, date;

    public Report(String feedback, String therapistID, String userID, String date) {
        this.feedback = feedback;
        this.therapistID = therapistID;
        this.userID = userID;
        this.date = date;
    }

    public Report() {
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getTherapistID() {
        return therapistID;
    }

    public void setTherapistID(String therapistID) {
        this.therapistID = therapistID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
