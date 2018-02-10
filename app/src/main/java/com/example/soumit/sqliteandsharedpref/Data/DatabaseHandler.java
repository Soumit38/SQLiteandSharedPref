package com.example.soumit.sqliteandsharedpref.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.soumit.sqliteandsharedpref.Model.Contact;
import com.example.soumit.sqliteandsharedpref.Utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Soumit on 2/10/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" +
                Util.KEY_ID + " INTEGER PRIMARY KEY, " + Util.KEY_NAME + " TEXT, " +
                Util.KEY_PHONE_NUMBER + " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);

        onCreate(db);
    }

    /**
     * add contact info
     * @param contact
     */
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, contact.getName());
        contentValues.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //row insertion
        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }

    /**
     * get single contact
     * @param id
     * @return contact
     */
    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER},
                Util.KEY_ID + "=?" , new String[]{String.valueOf(id)}, null, null, null);

        if(cursor!=null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                                        cursor.getString(1),
                                        cursor.getString(2));

        return contact;
    }

    /**
     * get all contacts
     * @return contactList
     */
    public List<Contact> getAllContacts(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Contact> contactList = new ArrayList<>();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                contactList.add(contact);
            }while (cursor.moveToNext());
        }

        return contactList;
    }

    /**
     * updateContact
     * @param contact
     * @return int rowNo
     */
    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //update row
        return db.update(Util.TABLE_NAME, values,
                Util.KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
    }

    /**
     * delete single contact
     * @param contact
     */
    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?" , new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    /**
     * getContactsCount
     * @return noOfContacts
     */
    public int getContactsCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }


}





















