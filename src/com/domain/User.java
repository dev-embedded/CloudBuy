package com.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private int userNo;
    private String lastName;
    private String firstName;
    private String password;
    private int age;
    private String sex;
    private String address;
    private String postalCode;
    private String telephone;
    private String email;
    private String flagUser;
    private String flagDel;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int userNo) {
        this.userNo = userNo;
    }

    public User() {
    }

    public User(int userNo, String lastName, String firstName, String password, int age, String sex, String address, String postalCode, String telephone, String email, String flagUser, String flagDel){
    	this.userNo = userNo;
    	this.lastName = lastName;
    	this.firstName = firstName;
    	this.password = password;
    	this.age = age;
    	this.sex = sex;
    	this.address = address;
    	this.postalCode = postalCode;
    	this.telephone = telephone;
    	this.email = email;
    	this.flagUser = flagUser;
    	this.flagDel = flagDel;
    	
    }
    
    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFlagUser() {
        return flagUser;
    }

    public void setFlagUser(String flagUser) {
        this.flagUser = flagUser;
    }

    public String getFlagDel() {
        return flagDel;
    }

    public void setFlagDel(String flagDel) {
        this.flagDel = flagDel;
    }

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postCode) {
		this.postalCode = postCode;
	}

	@Override public int describeContents() {  

		return 0;  

		}  

	@Override  
	public void writeToParcel(Parcel dest, int flags) {  
		dest.writeInt(userNo); 
		dest.writeString(lastName); 
		dest.writeString(firstName);
		dest.writeString(password);
		dest.writeInt(age); 
		dest.writeString(sex);
		dest.writeString(address); 
		dest.writeString(postalCode); 
		dest.writeString(telephone); 
		dest.writeString(email); 
		dest.writeString(flagUser); 
		dest.writeString(flagDel); 


	}  
	
	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {  

		@Override  

		public User createFromParcel(Parcel source) {  

		return new User(source.readInt(), source.readString(), source.readString(), source.readString(), source.readInt(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString());  

		}  

		@Override  

		public User[] newArray(int size) {  


		return new User[size];  

		}  

		 

		};  

}
