package sunil.project2;

/**
 * Created by sunil on 7/28/16.
 */
public class Pet implements Sellable.purchaseable {

    private String name;
    private String description;
    private String price;
    private String quantity;
    private String imgResId;

    public Pet(String n, String d, String r, String p, String q) {
        name = n;
        description = d;
        imgResId = r;
        price = p;
        quantity = q;
    }

    public String getImgResId() {return imgResId;}

    public void setImgResId(String imgResId) {this.imgResId = imgResId;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public void setPrice(String p) {price = p;}

    public String getPrice() {return price;}

    public void setQuantity(String q) {quantity = q;}

    public String getQuantity() {return quantity;}
}
