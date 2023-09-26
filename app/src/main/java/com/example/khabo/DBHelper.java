package com.example.khabo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.khabo.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME= "khabo.db";
    final static int DBVERSION=1;

    public DBHelper(Context context) {
        super(context,"khabo.db",null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT, useremail TEXT primary key, userpassword TEXT,userphone TEXT)");
        MyDB.execSQL("create table orders" + "(id integer primary key autoincrement,"+"name text,"+"phone text,"+"price int,"+"image int,"+"quantity int,"+"foodname text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");
        MyDB.execSQL("drop Table if exists orders");
        onCreate(MyDB);

    }
    public boolean inserdata(String username,String useremail,String userpassword,String userphone){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("useremail",useremail);
        contentValues.put("userpassword",userpassword);
        contentValues.put("userphone",userphone);
        long result=MyDB.insert("users",null,contentValues);
        if (result==-1) return false;
        else
            return true;
    }
    public boolean checkuseremail(String useremail){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from users where useremail = ?",new String[]{useremail});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkusernamepassword(String username,String userpassword){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from users where username = ? and userpassword = ?",new String[]{username,userpassword});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean insertOrder(String name,String phone,int price,int image,String foodname){
        SQLiteDatabase database=this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("foodname",foodname);

        long id=database.insert("orders",null,values);
        if(id<=0){
            return false;
        }else {
            return true;
        }
    }
    public ArrayList<OrdersModel> getOrders(){
        ArrayList<OrdersModel> orders=new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor=database.rawQuery("Select id,foodname,image,price from orders",null);

        if(cursor.moveToFirst()){
            // This means the query has returned some results
            do {
                OrdersModel model =new OrdersModel();
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                orders.add(model);

            }while(cursor.moveToNext());
        }
        else{
            // The query returned an empty list
        }
        cursor.close();;
        database.close();
        return orders;
    }
    public int deleteOrder(String id){
        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete("orders","id="+id,null);
    }
    public Cursor getUser(){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from users", null);
        return  cursor;
    }
}
