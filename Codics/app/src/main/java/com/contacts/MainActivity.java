package com.contacts;

import android.content.Intent;
import android.os.Bundle;

import com.contacts.adapter.ContactAdapter;
import com.contacts.adapter.Contacts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ListView lvContacts;

    Contacts contacts;

    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvContacts = findViewById(R.id.lvContactList);

        updateListView();

        FloatingActionButton fab = findViewById(R.id.addContact);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(view.getContext(), AddContactActivity.class);
                startActivity(intent);
            }
        });

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = (Contact)  adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, EditOrDeleteContactActivity.class);
                intent.putExtra("keyContact", contact);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Sort) {
            Collections.sort(contacts.getContactList());
            contactAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateListView() {
        DBHelper dbHelper = new DBHelper(MainActivity.this);
            contacts = new Contacts(dbHelper.getContacts());

            contactAdapter = new ContactAdapter(MainActivity.this, contacts);

            lvContacts.setAdapter(contactAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListView();
    }
}