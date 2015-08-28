package com.domain;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {

	private int orderNo;
	private int userNo;
	private String barcode;
	private double orderSum;
	private String flagPay;
	private String flagDel;
	private ArrayList<OrderDetail> orderDetail;

	public Order(int orderNo) {
		this.orderNo = orderNo;
	}

	public Order() {
	}

	public Order(int orderNo, String barcode, int userNo, double orderSum, String flagPay,
			String flagDel) {
		this.orderNo = orderNo;
		this.barcode = barcode;
		this.userNo = userNo;
		this.orderSum = orderSum;
		this.flagPay = flagPay;
		this.flagDel = flagDel;
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

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public ArrayList<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(ArrayList<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	@Override
	public int describeContents() {

		return 0;

	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(orderNo);
		dest.writeString(barcode);
		dest.writeInt(userNo);
		dest.writeDouble(orderSum);
		dest.writeString(flagPay);
		dest.writeString(flagDel);
		//dest.writeBinderArray(orderDetail);

	}

	public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {

		@Override
		public Order createFromParcel(Parcel source) {

			return new Order(source.readInt(), source.readString(), source.readInt(),
					source.readDouble(), source.readString(),
					source.readString());

		}

		@Override
		public Order[] newArray(int size) {

			return new Order[size];

		}

	};
}
