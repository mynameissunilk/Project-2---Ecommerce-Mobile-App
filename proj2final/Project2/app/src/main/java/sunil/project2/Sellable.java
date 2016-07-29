package sunil.project2;

/**
 * Created by sunil on 7/28/16.
 */
public class Sellable {
    // couldn't think of any super-useful object relationships, so I made an interface for sellables...
    public interface purchaseable{
        String price = "";
        String quantity="";

        public void setPrice(String p);
        public String getPrice();

        public void setQuantity(String q);
        public String getQuantity();
    }
}
