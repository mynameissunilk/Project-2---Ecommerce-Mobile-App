package sunil.project2;

import android.provider.BaseColumns;

/**
 * Created by sunil on 7/25/16.
 */
public class Schemas {

    /** Inventory table  **/
    public static abstract class Inventory implements BaseColumns {
        public static final String INV_TABLE_NAME = "inventory";
        public static final String ITEM_NAME ="item_name";
        public static final String ITEM_DESC ="description";
        public static final String ITEM_PRICE ="price";
        public static final String ITEM_QUANT ="quantity";
        public static final String ITEM_THUMB="image_id";

        public static final String INV_CREATE_TABLE=
                "CREATE TABLE "+Inventory.INV_TABLE_NAME+" ("+
                        Inventory._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        Inventory.ITEM_NAME+" TEXT,"+
                        Inventory.ITEM_DESC+" TEXT,"+
                        Inventory.ITEM_PRICE+" INTEGER,"+
                        Inventory.ITEM_QUANT+" INTEGER,"+
                        Inventory.ITEM_THUMB+" INT"+
                        ");";

        public static final String INV_DELETE_TABLE=
                "DROP TABLE IF EXISTS "+Inventory.INV_TABLE_NAME;
    }

    /*    * Incident Table, a-la "When Animals Attack" *
    public static abstract class Incidents implements BaseColumns{
        public static final String INC_TABLE_NAME="Incidents";
        public static final String INC_REPORT="report";
        public static final String INC_LOCATION="location";
        public static final String INC_OWNER="owner_status"; //killed,arrested,wounded,mia
        public static final String INC_FATAL="fatalities";

        public static final String INC_CREATE_TABLE=
                "CREATE TABLE "+Incidents.INC_TABLE_NAME+" ("+
                        Incidents._ID+" INTEGER,"+
                        Incidents.INC_REPORT+" TEXT,"+
                        Incidents.INC_LOCATION+" TEXT,"+
                        Incidents.INC_OWNER+" TEXT,"+
                        Incidents.INC_FATAL+" TEXT,"+
                        "FOREIGN KEY("+Incidents._ID+") REFERENCES "+Inventory.INV_TABLE_NAME+"("+Inventory._ID+"));";

        public static final String INC_DELETE_TABLE=
                        "DROP TABLE IF EXISTS"+Incidents.INC_TABLE_NAME;

    }*/


    public static abstract class Cart implements BaseColumns{
        public static final String CART_TABLE_NAME="cart";
        public static final String CART_ITEM="item";
        public static final String CART_PRICE="price";
        public static final String CART_QUANT="quantity";

        public static final String CREATE_CART_TABLE=
                "CREATE TABLE "+Cart.CART_TABLE_NAME+" ("+
                        Cart._ID+" INT,"+
                        Cart.CART_ITEM+" TEXT,"+
                        Cart.CART_PRICE+" INT,"+
                        Cart.CART_QUANT+" INT"+
                        ");";

        public static final String CART_DELETE_TABLE=
                "DROP TABLE IF EXISTS "+Cart.CART_TABLE_NAME;
    }
}
