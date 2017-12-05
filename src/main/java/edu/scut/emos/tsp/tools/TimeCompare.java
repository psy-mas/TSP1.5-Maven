package edu.scut.emos.tsp.tools;

import java.util.Date;

public class TimeCompare {

    public static double earlyTimeSubArriveTime(Date early, Date arrive) {

        return (double) (early.getTime() - arrive.getTime()) / (1000.0 * 3600.0);
    }

    public static double arriveTimeSubLastTime(Date arrive, Date last) {
        return (double) (arrive.getTime() - last.getTime()) / (1000.0 * 3600.0);
    }

    public static Date addHour(Date now, double hour) {
        return new Date((long) (now.getTime() + hour * 3600.0 * 1000.0));
    }
}
