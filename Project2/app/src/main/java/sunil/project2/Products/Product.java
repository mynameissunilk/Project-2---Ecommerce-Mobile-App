package sunil.project2.Products;

// superclass for all animal-products
// I could have used a sellable interface, I wanted to, but having a
// superclass reference is useful here

public class Product {
    private String itemName;
    private String description;
    private String itemPrice;
    private String stock;
    private String imgID;

    // will make creating subclass objects way less time-consuming
    public Product(){}

    // get rid of the overloaded constructor, keep getters & setters
    public Product(String n, String desc, String p, String no, String id){
        itemName = n;
        description = desc;
        itemPrice = p;
        stock = no;
        imgID = id;
    }

    public String getItemName(){ return itemName;}
    public void setItemName(String n){itemName = n;}

    public String getDescription(){return description;}
    public void setDescription(String d){description = d;}

    public String getItemPrice(){return itemPrice;}
    public void setItemPrice(String p){itemPrice = p;}

    public String getStock(){return stock;}
    public void setStocNo(String n){stock = n;}

    public String getImgID(){return imgID;}
    public void setImgID(String i){imgID =i;}
}
