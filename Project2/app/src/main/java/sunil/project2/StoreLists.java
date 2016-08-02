package sunil.project2;

import java.util.ArrayList;

import sunil.project2.Products.Product;

/**
 * Created by sunil on 7/26/16.
 */

 /** Singleton class for inventory & cart **/
public class StoreLists {
    private static StoreLists sInstance = null;

    private ArrayList<Product> mInventoryList = new ArrayList<>();

    private ArrayList<Product>cart = new ArrayList<>();

    public static StoreLists getInstance(){
        if(sInstance==null){
            sInstance = new StoreLists();
        }
        return sInstance;
    }

    //Let's keep this constructor empty for now
    private StoreLists(){}

    public void addToInventory(Product prod){
        mInventoryList.add(prod);
    }

    public ArrayList<Product> getInventoryList(){
        return mInventoryList;
    }

     public void addToCart(Product prod){cart.add(prod);}

     public ArrayList<Product>getCart(){return cart;}

}
