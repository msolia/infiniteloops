package com.example.dndmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.AvoidXfermode;
import android.os.Environment;
import android.provider.CallLog;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.OPEN_READONLY;
import static android.database.sqlite.SQLiteDatabase.openDatabase;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by madsoliy on 1/29/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Dndmanager.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String LOG_TABLE_NAME = "showlog";
    public static final String SCHEDULE_TABLE_NAME = "schedulertask";
    public static final String DEFAULT_MESSAGE_TABLE_NAME = "defaultmsg";

    public DBHelper(Context context)
    {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(
                "create table "+CONTACTS_TABLE_NAME +
                        "(_id integer primary key autoincrement, number string)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+CONTACTS_TABLE_NAME);
        onCreate(db);
    }

    public boolean setContact(String number)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("number",number);

        db.insert(CONTACTS_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<String> getAllContacts()
    {
        ArrayList array_list = new ArrayList();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CONTACTS_TABLE_NAME, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("number")));
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor getContactsByCursor()
    {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("select * from "+CONTACTS_TABLE_NAME,null);

    }

    public String getContactById(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from "+CONTACTS_TABLE_NAME+" where _id="+id,null);

        cursor.moveToFirst();

        String contact = cursor.getString(cursor.getColumnIndex("number"));

        return contact;
    }

    public boolean updateContact(long id,String newNumber)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("number",newNumber);

        db.update(CONTACTS_TABLE_NAME,contentValues,"_id="+id,null);
        return true;
    }

    public boolean removeContactById(long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(CONTACTS_TABLE_NAME,"_id="+id,null);

        return true;
    }


    public ArrayList<String> getSchedule()
    {
        ArrayList<String> array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from "+SCHEDULE_TABLE_NAME,null);
        res.moveToFirst();
        if(res.getCount() > 0)
        {
            array_list.add(res.getString(res.getColumnIndex("scheduled")));
            array_list.add(res.getString(res.getColumnIndex("ondate")));
            array_list.add(res.getString(res.getColumnIndex("ontime")));
            array_list.add(res.getString(res.getColumnIndex("offdate")));
            array_list.add(res.getString(res.getColumnIndex("offtime")));
        }
        else
        {
            array_list.add("");
        }

       return array_list;
    }

    public boolean setSchedule(String scheduled, String onDate,String onTime,String offDate,String offTime)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(
                "create table IF NOT EXISTS "+SCHEDULE_TABLE_NAME+
                        "(scheduled text, ondate text, ontime text, offdate text, offtime text)"
        );

        ContentValues contentValues = new ContentValues();

        contentValues.put("scheduled",scheduled);
        contentValues.put("ondate",onDate);
        contentValues.put("ontime",onTime);
        contentValues.put("offdate",offDate);
        contentValues.put("offtime",offTime);

        db.update(SCHEDULE_TABLE_NAME,contentValues,"", new String[]{});

        return true;
     }

    public boolean setScheduleStatus(String scheduled)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(
                "create table IF NOT EXISTS "+SCHEDULE_TABLE_NAME+
                        "(scheduled text, ondate text, ontime text, offdate text, offtime text)"
        );

        ArrayList<String> s=getSchedule();

        if(s.contains(""))
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("scheduled",scheduled);
            contentValues.put("ondate","");
            contentValues.put("ontime","");
            contentValues.put("offdate","");
            contentValues.put("offtime","");
            db.insert(SCHEDULE_TABLE_NAME, null, contentValues);

            return true;
        }

        else
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("scheduled", scheduled);

            db.update(SCHEDULE_TABLE_NAME, contentValues, "", new String[]{});

            return true;
        }
    }

    public boolean getScheduleStatus()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(
                "create table IF NOT EXISTS "+SCHEDULE_TABLE_NAME+
                        "(scheduled text, ondate text, ontime text, offdate text, offtime text)"
        );
        Cursor res =  db.rawQuery("select * from "+SCHEDULE_TABLE_NAME,null);
        res.moveToFirst();

        if(res.getCount() > 0)
        {
            String status = res.getString(res.getColumnIndex("scheduled"));

                if(status.equals("true"))
                {
                    return true;
                }
        }

        return false;

    }

    public boolean setLog(String date,String number)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(
                "create table IF NOT EXISTS "+LOG_TABLE_NAME +
                        "(_id integer primary key autoincrement, date text, number text)"
        );

        ContentValues contentValues = new ContentValues();

        contentValues.put("date", date);
        contentValues.put("number", number);

        db.insert(LOG_TABLE_NAME,null,contentValues);

        return  true;
    }

    public Cursor getLog()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL(
                "create table IF NOT EXISTS "+LOG_TABLE_NAME +
                        "(_id integer primary key autoincrement, date text, number text)"
        );

        return db.rawQuery("select * from "+LOG_TABLE_NAME,null);
    }
    
    public boolean clearLog()
    {

        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+LOG_TABLE_NAME);

        return true;
    }

    public boolean addDefaultMsg(String status, String text)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(
                "create table IF NOT EXISTS "+DEFAULT_MESSAGE_TABLE_NAME+
                        "(_id integer primary key autoincrement, status text,message text)"
        );

        ContentValues contentValues = new ContentValues();

        contentValues.put("status", status);
        contentValues.put("message", text);

        db.insert(DEFAULT_MESSAGE_TABLE_NAME,null,contentValues);
        return true;
    }

    public boolean changeDefaultMsgStatus(String status)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("status", status);

        db.update(DEFAULT_MESSAGE_TABLE_NAME, contentValues, "", new String[]{});

        db.execSQL("delete from "+DEFAULT_MESSAGE_TABLE_NAME);
        return true;
    }

    public boolean getDefaultMsgStatus()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(
                "create table IF NOT EXISTS "+DEFAULT_MESSAGE_TABLE_NAME+
                        "(_id integer primary key autoincrement, status text,message text)"
        );

        Cursor res =  db.rawQuery("select * from " +DEFAULT_MESSAGE_TABLE_NAME,null);

        res.moveToFirst();

        if(res.getCount() > 0)
        {
            String status = res.getString(res.getColumnIndex("status"));

            if(status.equals("true"))
            {
                return true;
            }
        }

        return false;
    }
    public String getDefaultMsg()
    {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery("select * from "+DEFAULT_MESSAGE_TABLE_NAME,null);
        res.moveToFirst();

        if(res.getCount() > 0)
        {
            return res.getString(res.getColumnIndex("message"));
        }
        return "";
    }

}
