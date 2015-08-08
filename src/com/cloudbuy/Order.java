package com.cloudbuy;

import java.io.Serializable;


public class Order {

	private int OrderNo;
	private int UserNo;
	private double OrderSum;

	public Order(int OrderNo, int UserNo, double OrderSum) {
		this.OrderNo = OrderNo;
		this.UserNo = UserNo;
		this.OrderSum = OrderSum;
	}

	public int getOrderNo() {
		return OrderNo;
	}

	public int getUserNo() {
		return UserNo;
	}

	public double getOrderSum() {
		return OrderSum;
	}
}