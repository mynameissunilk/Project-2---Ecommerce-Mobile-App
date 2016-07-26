package sunil.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunil on 7/26/16.
 */
public class InventoryHelper extends SQLiteOpenHelper {

    public static List<Product>InventoryInstance;

    //create a list that extends arraylist that checks for an instance and keepsg
    //the list from being duplicated
    //then figure out a way to check if the list has been populated, and
    //how to avoid storing duplicate product objects

    public static final String DB_NAME = "Illicit Animal Exchange";
    public static final int DB_VER = 1;

    /** Singleton Stuff **/
    private static InventoryHelper sInstance;

    private InventoryHelper(Context context){
        super(context,DB_NAME,null,DB_VER);
    }

    public static InventoryHelper getInstance(Context context){
        if(sInstance==null){
            sInstance = new InventoryHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Schemas.Inventory.INV_CREATE_TABLE);
        sqLiteDatabase.execSQL(Schemas.Incidents.INC_CREATE_TABLE);
        // consider joining with SQLiteQueryBuilder instead of in INC_CREATE_TABLE
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Schemas.Inventory.INV_DELETE_TABLE);
        sqLiteDatabase.execSQL((Schemas.Incidents.INC_DELETE_TABLE));
        onCreate(sqLiteDatabase);
    }

    public void addInvRow(Product p){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Schemas.Inventory.ITEM_NAME,p.getItemName());
        cv.put(Schemas.Inventory.ITEM_DESC,p.getDescription());
        cv.put(Schemas.Inventory.ITEM_PRICE,p.getItemPrice());
        cv.put(Schemas.Inventory.ITEM_QUANT,p.getStock());
        cv.put(Schemas.Inventory.ITEM_THUMB,p.getImgID());
        db.insert(Schemas.Inventory.INV_TABLE_NAME,null,cv);
    }
    // list is created
    // arraylist is instantiated
    // add method isn't working

    // can't assign this to anything,so what am I doing with this.
    // InventoryList.getInstance().populateInventory();




}
