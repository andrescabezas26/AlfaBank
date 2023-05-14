package com.example.financialmovementmanager.model;


import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FinancialMovement {

    private String description;

    private double value;

    private String type;

    private Date date;

    private  String dateString;

    public FinancialMovement(String description, double value, String type, Date date) {
        this.description= description;
        this.value= value;
        this.type= type;
        this.date= date;
        Calendar dateWithHour = Calendar.getInstance();

        dateWithHour.clear(Calendar.HOUR_OF_DAY);
        dateWithHour.clear(Calendar.MINUTE);
        dateWithHour.clear(Calendar.SECOND);
        dateWithHour.clear(Calendar.MILLISECOND);
        SimpleDateFormat formatedDate = new SimpleDateFormat("dd/MM/yyyy");
        this.dateString = formatedDate.format(date) +"";
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}


