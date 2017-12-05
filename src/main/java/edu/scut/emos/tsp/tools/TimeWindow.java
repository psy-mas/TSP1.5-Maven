package edu.scut.emos.tsp.tools;

import java.util.Date;

public class TimeWindow {

    private Date earlyTime;
    private Date endTime;

    public TimeWindow(Date earlyTime, Date endTime) {
        this.earlyTime = earlyTime;
        this.endTime = endTime;
    }

    public Date getEarlyTime() {
        return earlyTime;
    }

    public Date getEndTime() {
        return endTime;
    }

}
