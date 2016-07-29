package sunil.project2;

import android.provider.BaseColumns;

/**
 * Created by sunil on 7/28/16.
 */
public class Schema {

    public Schema(){}

    public static class Inventory implements BaseColumns {

        public static final String INVENTORY_T_NAME="inventory";
        public static final String INV_NAME="name";
        public static final String INV_DESC="description";
        public static final String INV_PRICE="price";
        public static final String INV_QUANT="quantity";
        public static final String INV_IMGID="imageid";

        public static final String CREATE_INV_TABLE=
                "CREATE TABLE "+INVENTORY_T_NAME+" ("+
                        _ID+" INTEGER PRIMARY KEY,"+
                        INV_NAME+" TEXT,"+
                        INV_DESC+" TEXT,"+
                        INV_PRICE+" INT,"+
                        INV_QUANT+" INT,"+
                        INV_IMGID+" TEXT"+
                        ");";

        public static final String DELETE_INV_TABLE=
                "DROP TABLE IF EXISTS "+INVENTORY_T_NAME;
    }

    public static class Cart implements BaseColumns {
        public static final String CART_T_NAME="cart";
        public static final String CART_NAME="name";
        public static final String CART_PRICE="price";

        public static final String CREATE_CART_TABLE=
                "CREATE TABLE "+CART_T_NAME + " ("+
                         _ID+" INT,"+
                        CART_NAME+" TEXT,"+
                        CART_PRICE+" INT,"+
                        "FOREIGN KEY("+Cart._ID+") REFERENCES "+Inventory.INVENTORY_T_NAME+"("+Inventory._ID+"));";

        public static final String DELETE_CART_TABLE=
                "DROP TABLE IF EXISTS "+CART_T_NAME;

    }

}
