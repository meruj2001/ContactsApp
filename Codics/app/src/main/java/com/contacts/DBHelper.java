package com.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String CONTACT_TABLE = "contact_table";

    public static final String COLUMN_CONTACT_ID = "id";
    public static final String COLUMN_CONTACT_NAME = "name";
    public static final String COLUMN_CONTACT_SURNAME = "surname";
    public static final String COLUMN_CONTACT_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_CONTACT_EMAIL = "email";

    DBHelper(Context context) {
        super(context, CONTACT_TABLE, null, 1);
    }

    //This function is creating a new table in database;
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Database table fields
        db.execSQL("create table " + CONTACT_TABLE + "("
                + COLUMN_CONTACT_ID + " integer primary key autoincrement not null,"
                + COLUMN_CONTACT_NAME + " text not null,"
                + COLUMN_CONTACT_SURNAME + " text not null,"
                + COLUMN_CONTACT_PHONE_NUMBER + " integer not null,"
                + COLUMN_CONTACT_EMAIL + " text not null"
                + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CONTACT_NAME, contact.getName());
        cv.put(COLUMN_CONTACT_SURNAME, contact.getSurname());
        cv.put(COLUMN_CONTACT_PHONE_NUMBER, contact.getPhoneNumber());
        cv.put(COLUMN_CONTACT_EMAIL, contact.getEmail());

        db.insert(CONTACT_TABLE, null, cv);
    }

    public List<Contact> getContacts() {
        List<Contact> returnList = new ArrayList<>();

        // get data from the DB
        String queryString = "SELECT * FROM " + CONTACT_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(queryString, null);
            if (cursor.moveToFirst()) {
                //loop through the cursor and create new contact objects. Put them into the return list
                do {
                    int contactID = cursor.getInt(0);
                    String contactName = cursor.getString(1);
                    String contactSurname = cursor.getString(2);
                    int contactPhoneNumber = cursor.getInt(3);
                    String contactEmail = cursor.getString(4);

                    Contact newContact = new Contact(contactID, contactName, contactSurname, contactPhoneNumber, contactEmail);
                    returnList.add(newContact);
                } while (cursor.moveToNext());
            }

        //closing the cursor and db
        cursor.close();
        sqLiteDatabase.close();
        return  returnList;
    }

    public void deleteContact(Contact contact) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(CONTACT_TABLE, COLUMN_CONTACT_ID + " = " + contact.getId(), null);
    }

    public void editContact(Contact contact) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CONTACT_NAME, contact.getName());
        cv.put(COLUMN_CONTACT_SURNAME, contact.getSurname());
        cv.put(COLUMN_CONTACT_PHONE_NUMBER, contact.getPhoneNumber());
        cv.put(COLUMN_CONTACT_EMAIL, contact.getEmail());

        sqLiteDatabase.update(CONTACT_TABLE, cv, COLUMN_CONTACT_ID + " = " + contact.getId(), null);
    }
}
