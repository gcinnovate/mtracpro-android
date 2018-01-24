package com.example.eq62roket.mtracpro.Helpers;

/**
 * Created by eq62roket on 1/11/18.
 */

public class History {
    private String id, rawMsg, details, date, period;

    public History( String id, String rawMsg, String details, String date, String period){
        this.setId(id);
        this.setRawMsg(rawMsg);
        this.setDetails(details);
        this.setDate(date);
        this.setPeriod(period);
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRawMsg() {
        return rawMsg;
    }

    public void setRawMsg(String rawMsg) {
        this.rawMsg = rawMsg;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
