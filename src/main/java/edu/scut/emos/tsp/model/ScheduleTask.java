package edu.scut.emos.tsp.model;

public class ScheduleTask {

    private Order order;
    private int action;// 1 means load,-1 means unload;

    public ScheduleTask(Order order, int action) {
        this.order = order;
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public ScheduleTask clone() {
        ScheduleTask newSt = null;
        Order orderTmp = order == null ? null : order.clone();

        newSt = new ScheduleTask(orderTmp, this.action);
        return newSt;
    }

    @Override
    public String toString() {
        return order.getId() + " " + String.valueOf(action) + ";";
    }
}
