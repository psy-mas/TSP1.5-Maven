package edu.scut.emos.tsp.tools;

import edu.scut.emos.tsp.model.ScheduleTask;

import java.util.LinkedList;
import java.util.List;

public class RouteTools {

    /**
     * 计算车辆完成任务路线后的载货列表
     *
     * @param tasks 车辆已完成任务列表
     * @return 完成任务路线后，车辆的装载列表
     */
    public static LinkedList<ScheduleTask> loadedTaskInVehicle(List<ScheduleTask> tasks) {
        if (tasks != null) {
            LinkedList<ScheduleTask> doneTask = new LinkedList<>();
            for (ScheduleTask scheduleTask : tasks) {
                if (scheduleTask.getAction() == 1) {
                    doneTask.add(scheduleTask);
                } else {
                    int pIndex = getUpIndex(doneTask, scheduleTask.getOrder().getId());
                    if (pIndex != -1) {
                        doneTask.remove(pIndex);
                    }
                }
            }
            return doneTask;
        } else {
            return null;
        }
    }

    /**
     * 根据订单ID，获取任务顺序列表中的取货顺序号
     *
     * @param task    车辆的任务列表
     * @param orderId 订单ID
     * @return 任务顺序列表中的取货顺序号
     */
    public static int getUpIndex(LinkedList<ScheduleTask> task, String orderId) {
        if (task != null) {
            for (int i = 0; i < task.size(); i++) {
                ScheduleTask st = task.get(i);
                if (st.getOrder().getId().equals(orderId) && st.getAction() == 1) {
                    return i;
                }
            }
            return -1;
        } else {
            return -1;
        }
    }

    /**
     * 根据订单ID，获取任务顺序列表中的送货顺序号
     *
     * @param task    车辆的任务列表
     * @param orderId 订单ID
     * @return 任务顺序列表中的送货顺序号
     */
    public static int getDownIndex(LinkedList<ScheduleTask> task, String orderId) {
        if (task != null) {
            for (int i = 0; i < task.size(); i++) {
                ScheduleTask st = task.get(i);
                if (st.getOrder().getId().equals(orderId) && st.getAction() == -1) {
                    return i;
                }
            }
            return -1;
        } else {
            return -1;
        }
    }
}
