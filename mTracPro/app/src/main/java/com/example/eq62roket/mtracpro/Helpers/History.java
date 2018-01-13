package com.example.eq62roket.mtracpro.Helpers;

/**
 * Created by eq62roket on 1/11/18.
 */

public class History {
    private String Id, RawMsg, Detail, Date;

    public History( String Id, String RawMsg, String Detail, String Date){
        this.setId(Id);
        this.setRawMsg(RawMsg);
        this.setDetail(Detail);
        this.setDate(Date);
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
