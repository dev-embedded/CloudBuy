package com.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderDetail implements Parcelable {

	private int orderNo;
    private int productNo;
    private String productName;
    private int quantity;


    public OrderDetail() {
    }
	
	public OrderDetail(int orderNo) {
		super();
		this.orderNo = orderNo;
	}
	
	public OrderDetail(int orderNo, int productNo, String productName,
			int quantity) {
		super();
		this.orderNo = orderNo;
		this.productNo = productNo;
		this.productName = productName;
		this.quantity = quantity;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Override
	public int describeContents() {

		return 0;

	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(orderNo);
		dest.writeInt(productNo);
		dest.writeString(productName);
		dest.writeInt(quantity);
	}

	public static final Parcelable.Creator<OrderDetail> CREATOR = new Parcelable.Creator<OrderDetail>() {

		@Override
		public OrderDetail createFromParcel(Parcel source) {

			return new OrderDetail(source.readInt(), source.readInt(), source.readString(),source.readInt());

		}

		@Override
		public OrderDetail[] newArray(int size) {

			return new OrderDetail[size];

		}

	};
}
