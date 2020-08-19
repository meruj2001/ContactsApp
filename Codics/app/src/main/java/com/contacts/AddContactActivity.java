package com.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddContactActivity extends AppCompatActivity {

    EditText etName, etSurname, etPhoneNumber, etEmail;

    FloatingActionButton fabAddContact;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etEmail = findViewById(R.id.etEmail);

        fabAddContact = findViewById(R.id.addContact);

        dbHelper = new DBHelper(this);

        fabAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (etName.length() == 0 || etSurname.length() == 0 || etPhoneNumber.length() == 0 || etEmail.length() == 0) {
                        Toast.makeText(AddContactActivity.this, "Your name, surname, phone number or email field is empty",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Contact contact = new Contact(-1, etName.getText().toString(), etSurname.getText().toString(),
                                Integer.parseInt(etPhoneNumber.getText().toString()), etEmail.getText().toString());
                        dbHelper.addContact(contact);

                        Toast.makeText(AddContactActivity.this, "You have created a new contact", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(AddContactActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                } catch (SQLException e) {
                    Toast.makeText(AddContactActivity.this, "Something is incorrect",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
