package com.lsy.vehicle.log.dto;

import java.io.Serializable;
import java.util.Date;

public class LogEntry implements Serializable{

    private static final long serialVersionUID = 1L;

    private String message;
    private Date timeStamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "LOG> [" + timeStamp + "]: " + message ;
    }

    
    
    
}
