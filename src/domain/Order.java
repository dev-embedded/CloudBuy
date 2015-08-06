package domain;

import java.util.ArrayList;

public class Order {

    private int orderNo;
    private int userNo;
    private double orderSum;
    private String flagPay;
    private String flagDel;
    private ArrayList<OrderDetail> orderDetail;

    public Order(int orderNo) {
        this.orderNo = orderNo;
    }

    public Order() {
    }

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public double getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(double orderSum) {
		this.orderSum = orderSum;
	}

	public String getFlagPay() {
		return flagPay;
	}

	public void setFlagPay(String flagPay) {
		this.flagPay = flagPay;
	}

	public String getFlagDel() {
		return flagDel;
	}

	public void setFlagDel(String flagDel) {
		this.flagDel = flagDel;
	}

	public ArrayList<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(ArrayList<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	

    
}
