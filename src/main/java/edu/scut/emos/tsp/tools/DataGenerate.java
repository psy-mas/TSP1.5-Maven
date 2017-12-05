package edu.scut.emos.tsp.tools;

import edu.scut.emos.tsp.model.Order;
import edu.scut.emos.tsp.model.Position;
import edu.scut.emos.tsp.model.Route;
import edu.scut.emos.tsp.model.Vehicle;

import java.util.Date;

public class DataGenerate {

    public static Order generateOrder(String orderId) {
        TimeWindow[] pdt = generateTimeWindow();
        return new Order(orderId, generateStochasticPosition(), generateStochasticPosition(), null, Math.random() * 20, Math.random() * 20, pdt[0].getEarlyTime(), pdt[0].getEndTime()
                , pdt[1].getEarlyTime(), pdt[1].getEndTime(), ((int) (5 + Math.random() * 6)) * 0.1, ((int) (5 + Math.random() * 6)) * 0.1, null, null, null);
    }

    private static TimeWindow[] generateTimeWindow() {

        int gap = 1 + (int) (Math.random() * 2);//1-2

        TimeWindow[] pdTimeWindow = new TimeWindow[2];
        //获取当前时间的日期
        Date now = new Date();
        int year = now.getYear();
        int month = now.getMonth();
        int day = now.getDate();

        int[] startHours = new int[]{9, 10, 11, 12, 13, 14, 15, 16, 17, 18};//可允许取货时间
        int[] endHours = new int[]{9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};//可允许送货时间
        int pickupStartHour = 0;
        int pickupEndHour = 0;
        int deliveryStartHour = 0;
        int deliveryEndHour = 0;

        int startRand = (int) (Math.random() * 9);//0-8之间的随机整数
        pickupStartHour = startHours[startRand];

        if (pickupStartHour == 17) {
            pickupEndHour = 18;
        } else {
            pickupEndHour = pickupStartHour + gap;
        }

        if (pickupEndHour == 18) {
            deliveryStartHour = pickupEndHour + (int) (Math.random());//18-19
            if (deliveryStartHour == 19) {
                deliveryEndHour = 20;
            } else {
                deliveryEndHour = deliveryStartHour + gap;
            }
        } else {
            int endRand = (pickupEndHour - 9) + (int) (Math.random() * (endHours.length - (pickupEndHour - 9) - 1));
            deliveryStartHour = endHours[endRand];
            if (deliveryStartHour == 19) {
                deliveryEndHour = 20;
            } else {
                deliveryEndHour = deliveryStartHour + gap;
            }
        }


        pdTimeWindow[0] = new TimeWindow(new Date(year, month, day, pickupStartHour, 0), new Date(year, month, day, pickupEndHour, 0));
        pdTimeWindow[1] = new TimeWindow(new Date(year, month, day, deliveryStartHour, 0), new Date(year, month, day, deliveryEndHour, 0));

        return pdTimeWindow;
    }

    public static Position generateStochasticPosition() {

        int minLatitude = 22922741;
        int maxLatitude = 23214805;
        int randLatitude = minLatitude + (int) (Math.random() * (maxLatitude - minLatitude));
        int minLongitude = 113203591;
        int maxLongitude = 113512321;
        int randLongitude = minLongitude + (int) (Math.random() * (maxLongitude - minLongitude));
        return new Position((randLatitude / 1000000) + ((randLatitude % 1000000) * 0.000001), (randLongitude / 1000000) + ((randLongitude % 1000000) * 0.000001));

    }

    public static Vehicle generateVehicle(String vehicleId) {

        int[] weight = new int[]{20, 40, 60};
        int random = (int) (Math.random() * 3);// 生成种子
        int randWeight = weight[random];
        Date gpsUpdateTime = new Date();
        gpsUpdateTime.setHours(8);
        gpsUpdateTime.setMinutes(0);
        gpsUpdateTime.setSeconds(0);
        return new Vehicle(vehicleId, 10, 4, 3, null, randWeight, 100, generateStochasticPosition(), gpsUpdateTime, 0, null
                , 0, 0, false, new Route());
    }

}
