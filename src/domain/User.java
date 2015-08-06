package domain;

public class User {

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

    public int getNoClient() {
        return userNo;
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

    

}
