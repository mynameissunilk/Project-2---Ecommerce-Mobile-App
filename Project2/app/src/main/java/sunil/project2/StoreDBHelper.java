package sunil.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import sunil.project2.Products.Product;

/**
 * Created by sunil on 7/26/16.
 */
public class StoreDBHelper extends SQLiteOpenHelper {



    public static final String DB_NAME = "Illicit Animal Exchange";
    public static final int DB_VER = 2;

    /** Singleton Stuff **/
    private static StoreDBHelper sInstance;

    private StoreDBHelper(Context context){
        super(context,DB_NAME,null,DB_VER);
    }

    public static StoreDBHelper getInstance(Context context){
        if(sInstance==null){
            sInstance = new StoreDBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Schemas.Inventory.INV_CREATE_TABLE);
        sqLiteDatabase.execSQL(Schemas.Cart.CREATE_CART_TABLE);
        // consider joining with SQLiteQueryBuilder instead of in INC_CREATE_TABLE
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Schemas.Inventory.INV_DELETE_TABLE);
        sqLiteDatabase.execSQL(Schemas.Cart.CART_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    // Inventory Projection: _ID, NAME, DESC, PRICE, QUANTITY, IMGID
    String[] invprojection = new String[]{Schemas.Inventory._ID, Schemas.Inventory.ITEM_NAME,Schemas.Inventory.ITEM_DESC,Schemas.Inventory.ITEM_PRICE,Schemas.Inventory.ITEM_QUANT, Schemas.Inventory.ITEM_THUMB};
    // Projection for cart: NAME, PRICE, QUANTITY
    String[] cartprojection = new String[]{Schemas.Inventory.ITEM_NAME, Schemas.Inventory.ITEM_PRICE, Schemas.Inventory.ITEM_QUANT};

    // use a rawquery to check if a table is empty... well this doesn't work anyways so @#$%
    public boolean checkPopulated(String tablequery){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM "+tablequery,null);
        if(cursor !=null) return false;
        else return true;
    }

    // SELECT all cols FROM Table
    public Cursor getallInventory(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Schemas.Inventory.INV_TABLE_NAME,
                invprojection,
                Schemas.Inventory.ITEM_NAME,
                null,null,null,null);
        cursor.moveToFirst();
        return cursor;
    }
    // SELECT all cols FROM table WHERE col_name LIKE query
    public Cursor getRowFromName(String name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                Schemas.Inventory.INV_TABLE_NAME,
                invprojection,
                Schemas.Inventory.ITEM_NAME+" =?",
                new String[]{name},
                null,null,null);
        cursor.moveToFirst();
        return cursor;
    }

    // Take in the cursor that called getRowFromName
    // in ProductDetailActivity, and use it to add to cart
    public void addToCartByCursor(Cursor c){
        SQLiteDatabase db = getWritableDatabase();
        c.moveToFirst();
        ContentValues cv = new ContentValues();
        cv.put(Schemas.Cart.CART_ITEM,c.getString(
                c.getColumnIndex(Schemas.Inventory.ITEM_NAME)));
        cv.put(Schemas.Cart.CART_PRICE,c.getString(
                c.getColumnIndex(Schemas.Inventory.ITEM_PRICE)));
        cv.put(Schemas.Cart.CART_QUANT,c.getString(
                c.getColumnIndex(Schemas.Inventory.ITEM_QUANT)));
        db.insert(Schemas.Cart.CART_TABLE_NAME,null,cv);
    }

    // add a Product object to the inventory table
    public void insertInventoryRow(Product p){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Schemas.Inventory.ITEM_NAME,p.getItemName());
        cv.put(Schemas.Inventory.ITEM_DESC,p.getDescription());
        cv.put(Schemas.Inventory.ITEM_PRICE,p.getItemPrice());
        cv.put(Schemas.Inventory.ITEM_QUANT,p.getStock());
        cv.put(Schemas.Inventory.ITEM_THUMB,p.getImgID());
        db.insert(Schemas.Inventory.INV_TABLE_NAME,null,cv);
    }

    // add an ArrayList of Products to the inventory table
    public void insertInventoryList(ArrayList<Product> list){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        for(int i = 0; i <list.size();i++){
            values.put(Schemas.Inventory.ITEM_NAME,list.get(i).getItemName());
            values.put(Schemas.Inventory.ITEM_DESC,list.get(i).getDescription());
            values.put(Schemas.Inventory.ITEM_PRICE,list.get(i).getItemPrice());
            values.put(Schemas.Inventory.ITEM_QUANT,list.get(i).getStock());
            values.put(Schemas.Inventory.ITEM_THUMB,list.get(i).getImgID());
            db.insert(Schemas.Inventory.INV_TABLE_NAME,null,values);
        }
    }


    // create an inventory object from an inventory row
    // incomplete, and unnecessary!
    public Cursor createInventoryFromTable(){
        ArrayList<Product> listFromTable;
        StoreLists storeinvs = StoreLists.getInstance();
        SQLiteDatabase  db = getReadableDatabase();

        // get values from tables to create a product object
        Cursor cursor = db.query(Schemas.Inventory.INV_TABLE_NAME,
                invprojection, null,null,null,null,null);

        cursor.moveToFirst();
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                String name = cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_NAME));
                String desc = cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_DESC));
                String price = cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_PRICE));
                String stockno = cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_QUANT));
                String imgid = cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_THUMB));
                storeinvs.getInstance().addToInventory(new Product(name,desc,price,stockno,imgid));
                cursor.moveToNext();
            }
        }
        return cursor;
    }
}

