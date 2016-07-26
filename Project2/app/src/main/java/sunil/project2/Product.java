package sunil.project2;

public class Product {
    private String itemName;
    private String description;
    private double itemPrice;
    private boolean inStock;
    private int stock;
    private int imgID;

    public Product(String n, String desc, double p, boolean is, int no, int id){
        itemName = n;
        description = desc;
        itemPrice = p;
        inStock = is;
        stock = no;
        imgID = id;
    }

    public String getItemName(){ return itemName;}
    public void setItemName(String n){itemName = n;}

    public String getDescription(){return description;}
    public void setDescription(String d){description = d;}

    public double getItemPrice(){return itemPrice;}
    public void setItemPrice(double p){itemPrice = p;}

    public boolean isInStock(){return inStock;}
    public void setInStock(boolean is){inStock = is;}

    public int getStock(){return stock;}
    public void setStocNo(int n){stock = n;}

    public void incrementStock(){stock+=1;}
    public void decrementStock(){stock-=1;}

    public void checkStock(Product p){
        if(p.getStock()==0){
            p.setInStock(false);
        }
    }

    public int getImgID(){return imgID;}
    public void setImgID(int i){imgID =i;}
}
