package sunil.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunil on 7/26/16.
 */
public class StoreDBHelper extends SQLiteOpenHelper {



    public static final String DB_NAME = "Illicit Animal Exchange";
    public static final int DB_VER = 1;

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
        sqLiteDatabase.execSQL(Schemas.Incidents.INC_CREATE_TABLE);
        // consider joining with SQLiteQueryBuilder instead of in INC_CREATE_TABLE
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Schemas.Inventory.INV_DELETE_TABLE);
        sqLiteDatabase.execSQL((Schemas.Incidents.INC_DELETE_TABLE));
        onCreate(sqLiteDatabase);
    }

    // Inventory Projection: _ID, NAME, DESC, PRICE, QUANTITY
    String[] invprojection = new String[]{Schemas.Inventory._ID, Schemas.Inventory.ITEM_NAME,Schemas.Inventory.ITEM_DESC,Schemas.Inventory.ITEM_PRICE,Schemas.Inventory.ITEM_QUANT, Schemas.Inventory.ITEM_THUMB};

    /**
    public String doSomething(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Schemas.Inventory.INV_TABLE_NAME,
                invprojection,
                Schemas.Inventory.ITEM_NAME,
                null,
                null,
                null,
                null);
        String testoutput="";

        cursor.moveToFirst();
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                testoutput+=cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_NAME))+
                cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_DESC))+
                cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_PRICE))+
                cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_QUANT));
                cursor.moveToNext();
            }
        }
        return testoutput;
    }
    **/

    // use a rawquery to check if a table is empty... well this doesn't work anyways so @#$%
    public boolean checkPopulated(String tablequery){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM "+tablequery,null);
        if(cursor !=null) return false;
        else return true;
    }

    public Cursor getallInventory(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Schemas.Inventory.INV_TABLE_NAME,
                invprojection,
                Schemas.Inventory.ITEM_NAME,
                null,null,null,null);
        cursor.moveToFirst();
        return cursor;
    }

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

    public void insertIncidentRow(Incidents in){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Schemas.Incidents.INC_REPORT,in.getReport());
        cv.put(Schemas.Incidents.INC_LOCATION,in.getLocation());
        cv.put(Schemas.Incidents.INC_OWNER,in.getOwnerStatus());
        cv.put(Schemas.Incidents.INC_FATAL,in.isFatal());
        db.insert(Schemas.Incidents.INC_TABLE_NAME,null,cv);
    }

    public void insertIncidentList(ArrayList<Incidents> list){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        for(int i = 0; i <list.size(); i++){
            values.put(Schemas.Incidents.INC_REPORT,list.get(i).getReport());
            values.put(Schemas.Incidents.INC_LOCATION,list.get(i).getLocation());
            values.put(Schemas.Incidents.INC_OWNER,list.get(i).getOwnerStatus());
            values.put(Schemas.Incidents.INC_FATAL,list.get(i).isFatal());
            db.insert(Schemas.Incidents.INC_TABLE_NAME,null,values);
        }
    }

    public Cursor createInventoryFromTable(){
        ArrayList<Product> listFromTable;
        StoreLists storeinvs = StoreLists.getInstance();
        SQLiteDatabase  db = getReadableDatabase();

        // get all columns, to be passed to mainactivity and written into objects
        Cursor cursor = db.query(Schemas.Inventory.INV_TABLE_NAME,
                invprojection, null,null,null,null,null);

        cursor.moveToFirst();
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){

                String name = cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_NAME));
                String desc = cursor.getString(cursor.getColumnIndex(Schemas.Inventory.ITEM_DESC));
                double price = cursor.getDouble(cursor.getColumnIndex(Schemas.Inventory.ITEM_PRICE));
                int stockno = cursor.getInt(cursor.getColumnIndex(Schemas.Inventory.ITEM_QUANT));
                int imgid = cursor.getInt(cursor.getColumnIndex(Schemas.Inventory.ITEM_THUMB));
                storeinvs.getInstance().addToInventory(new Product(name,desc,price,true,stockno,imgid));
                cursor.moveToNext();
            }
        }
        return cursor;
    }
}

