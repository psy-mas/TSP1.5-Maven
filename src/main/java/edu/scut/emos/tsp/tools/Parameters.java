package edu.scut.emos.tsp.tools;

public class Parameters {
    // 路线中两个任务点之间的最大距离(公里)
    public static final double MAX_DISTANCE = 200;
    // 车辆在任务点的等待的最大时间(小时)
    public static final double MAX_WAIT_TIME = 8;
    // 车辆到达任务点延迟的最大时间(小时)
    public static final double MAX_DELAY_TIME = 1;
    // 车辆运输货物时掏货所花费的时间(小时)
    public static final double MAX_DRAW_OUT_TIME = 1;

    public static final double DISTANCE_WEIGHT = 0.1;
    public static final double WAIT_TIME_WEIGHT = 0.1;
    public static final double DELAY_TIME_WEIGHT = 0.5;
    public static final double DRAW_OUT_TIME_WEIGHT = 0.3;

    public static final double MAX_COST = 0.8;

    public static final String AK = "xFXLbDh24OBgnGluD8dqgDV2lvbg4pyL";
//    public static final String AK = "TEFkDzqwAwTgxL9Nf74KHsFrwQhqiIAq";

}
