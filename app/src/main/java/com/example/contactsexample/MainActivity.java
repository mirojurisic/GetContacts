package com.example.contactsexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.CursorLoader;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Cursor mData;
    List<SimpleContact> contact_list;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_item);
        contact_list = new ArrayList<>();

        new ReadContactAsync().execute();
    }

    class ReadContactAsync extends AsyncTask<Void,Void, Cursor> {

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            mData = cursor;
            printStuff();
            fillList();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            Cursor contactsCursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                    new String[] {ContactsContract.Contacts.DISPLAY_NAME , ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Email.ADDRESS },null,null);
            return  contactsCursor;
        }
    }
    public void printStuff(){
        for(String s : mData.getColumnNames())
        Log.v("PRINTING", s);
    }
    public void fillList() {
        contact_list = new ArrayList<>();

        if (mData.moveToFirst()) {
            do {

                String displayName = mData
                        .getString(mData
                                .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME ));
                String phone = mData.getString(mData.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String email = mData.getString(mData.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));

               // if(phone!=null&&phone.length()>0 && phone.charAt(0) =='+')
                {
                    contact_list.add(new SimpleContact(displayName, phone,email));

                }

            } while (mData.moveToNext());

        }
        mData.close();
        ContactAdapter contactAdapter = new ContactAdapter(this, 0, contact_list);
        listView.setAdapter(contactAdapter);
    }


}
