package edu.scut.emos.tsp.time_windows;

public class FailedOrder {
    private String orderId;
    private int status;         // 0表示无车匹配的订单，1表示匹配成功但被争抢资源的订单

    /**
     * 构造方法
     *
     * @param orderId 匹配失败的订单ID
     * @param status  0表示无车匹配的订单，1表示匹配成功但被争抢资源的订单
     */
    public FailedOrder(String orderId, int status) {
        this.orderId = orderId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FailedOrder{" +
                "orderId: '" + orderId + '\'' +
                ", status: " + (status == 0 ? "no matching" : "recomputation") +
                '}';
    }
}
