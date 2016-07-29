package sunil.project2;

import java.util.ArrayList;

/**
 * Created by sunil on 7/29/16.
 */
public class ShopLists {
        private static ShopLists sInstance = null;

    private ArrayList<Pet> mInventoryList = new ArrayList<>();
    private ArrayList<Pet>cart = new ArrayList<>();

    public static ShopLists getInstance(){
        if(sInstance==null){
            sInstance = new ShopLists();
        }
        return sInstance;
    }

    //Let's keep this constructor empty for now
    private ShopLists(){}

    public void addToInventory(Pet prod){
        mInventoryList.add(prod);
    }

    public ArrayList<Pet> getInventoryList(){
        return mInventoryList;
    }

    public void addToCart(Pet p){
        cart.add(p);
    }
}
