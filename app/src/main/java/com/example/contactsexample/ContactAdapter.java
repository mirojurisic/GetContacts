package com.example.contactsexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<SimpleContact> {
    List<SimpleContact> list;
    Context context;
    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<SimpleContact> objects) {
        super(context, resource, objects);
        list=objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        convertView =(View) LayoutInflater.from(container.getContext())
                .inflate(R.layout.single_contact, container, false);
        TextView name = convertView.findViewById(R.id.name);
        TextView phone = convertView.findViewById(R.id.phone);
        name.setText(list.get(position).getName());
        phone.setText(list.get(position).getPhone());
        return convertView;
    }
}
