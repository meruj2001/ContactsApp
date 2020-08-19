package com.contacts.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.contacts.Contact;
import com.contacts.R;

public class ContactAdapter extends BaseAdapter {

    Activity myActivity;
    Contacts myContact;

    public ContactAdapter(Activity myActivity, Contacts myContact) {
        this.myActivity = myActivity;
        this.myContact = myContact;
    }

    @Override
    public int getCount() {
        return myContact.getContactList().size();
    }

    @Override
    public Object getItem(int i) {
        return myContact.getContactList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View contactLine;
        LayoutInflater inflater = (LayoutInflater) myActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contactLine = inflater.inflate(R.layout.contact_one_line, viewGroup, false);
        TextView tvName = contactLine.findViewById(R.id.tvName);
        TextView tvSurname = contactLine.findViewById(R.id.tvSurname);
        TextView tvPhoneNumber = contactLine.findViewById(R.id.tvPhoneNumber);
        TextView tvEmail = contactLine.findViewById(R.id.tvEmail);

        Contact contact = (Contact) this.getItem(i);

        tvName.setText(contact.getName());
        tvSurname.setText(contact.getSurname());
        tvPhoneNumber.setText(Integer.toString(contact.getPhoneNumber()));
        tvEmail.setText(contact.getEmail());

        return contactLine;
    }
}
