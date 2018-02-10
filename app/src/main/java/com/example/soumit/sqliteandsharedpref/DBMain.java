package com.example.soumit.sqliteandsharedpref;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.soumit.sqliteandsharedpref.Data.DatabaseHandler;
import com.example.soumit.sqliteandsharedpref.Model.Contact;

import java.util.List;

/**
 * Created by Soumit on 2/11/2018.
 */

public class DBMain extends AppCompatActivity {

    private static final String TAG = "DBMain";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_main);

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d(TAG, "No of Contacts: " + String.valueOf(db.getContactsCount()));

        //value insertion
        Log.d(TAG, "onCreate: Inserting values : \n");
        db.addContact(new Contact("Jackson", "875498549"));
        db.addContact(new Contact("Shohol", "7343847"));
        db.addContact(new Contact("Bahadur", "2324354"));
        db.addContact(new Contact("Swajan", "948954958"));

//        Contact oneContact = db.getContact(1);
        /*
        oneContact.setName("paulooooo");
        oneContact.setPhoneNumber("999999999999");


        //update contact
        int newContact = db.updateContact(oneContact);

        Log.d(TAG, "Updated row: " + String.valueOf(newContact)
                +" Name : " + oneContact.getName() + " Phone : " + oneContact.getPhoneNumber());*/

        //delete contact
//        db.deleteContact(oneContact);

        //Reading values
        Log.d(TAG, "onCreate: Reading contacts : \n");
        List<Contact> contactList = db.getAllContacts();
        for(Contact contact : contactList){
            String contactDetails = "Id: " + contact.getId() + " Name: " + contact.getName() + " Phone: " + contact.getPhoneNumber();
            Log.d(TAG, "onCreate: contact details : \n" + contactDetails);
        }

    }
}
