package domain;

public class Product {

    private int productNo;
    private String barcode;
    private String name;
    private String desc;
    private float price;
    private int quantity;
    private String pic;
    private String location;
    private String flagDel;

    private int shoppingNum = 1;

    public int getNoProduit() {
        return productNo;
    }

    public String getDescription() {
        return desc;
    }

    public float getPrix() {
        return price;
    }

    public int getQuantite() {
        return quantity;
    }

    public String getPhoto() {
        return pic;
    }

    public String getStatut() {
        return flagDel;
    }

    public void setNoProduit(int noProduit) {
        this.productNo = noProduit;
    }

    public void setDescription(String descriptionProduit) {
        this.desc = descriptionProduit;
    }

    public void setQuantite(int quantite) {
        this.quantity = quantite;
    }

    public void setPhoto(String photoProduit) {
        this.pic = photoProduit;
    }

    public void setStatut(String flagSupprime) {
        this.flagDel = flagSupprime;
    }

    public int getShoppingNum() {
        return shoppingNum;
    }

    public void setPrix(float prix) {
        this.price = prix;
    }

    public void setShoppingNum(int shoppingNum) {
        this.shoppingNum = shoppingNum;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFlagDel() {
        return flagDel;
    }

    public void setFlagDel(String flagDel) {
        this.flagDel = flagDel;
    }

}
