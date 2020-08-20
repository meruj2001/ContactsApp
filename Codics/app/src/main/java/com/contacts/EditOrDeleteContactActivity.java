package com.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditOrDeleteContactActivity extends AppCompatActivity {

    EditText etName, etSurname, etPhoneNumber, etEmail;

    FloatingActionButton fabEditContact, fabDeleteContact;

    Contact contact;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_or_delete_contact);

        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etEmail = findViewById(R.id.etEmail);

        fabEditContact = findViewById(R.id.fabEditContact);
        fabDeleteContact = findViewById(R.id.fabDeleteContact);

        intent = new Intent(this, MainActivity.class);

        contact = (Contact) getIntent().getSerializableExtra("keyContact");

        etName.setText(contact.getName());
        etSurname.setText(contact.getSurname());
        etPhoneNumber.setText((String)Integer.toString(contact.getPhoneNumber()));
        etEmail.setText(contact.getEmail());

        fabDeleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(EditOrDeleteContactActivity.this);
                dbHelper.deleteContact(contact);
                startActivity(intent);
            }
        });

        fabEditContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact editedContact = new Contact(contact.getId(), etName.getText().toString(), etSurname.getText().toString(),
                        Integer.parseInt(etPhoneNumber.getText().toString()), etEmail.getText().toString());

                DBHelper dbHelper = new DBHelper(EditOrDeleteContactActivity.this);
                dbHelper.editContact(editedContact);

                Intent intent = new Intent(EditOrDeleteContactActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
