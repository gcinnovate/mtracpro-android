package com.example.eq62roket.mtracpro.Activities;

/**
 * Created by eq62roket on 1/11/18.
 */

public class History {
    private String RawMsg, Detail, Date;

    public History( String RawMsg, String Detail, String Date){
        this.setRawMsg(RawMsg);
        this.setDetail(Detail);
        this.setDate(Date);
    }


    public String getRawMsg() {
        return RawMsg;
    }

    public void setRawMsg(String rawMsg) {
        RawMsg = rawMsg;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
