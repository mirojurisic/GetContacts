package com.example.contactsexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.CursorLoader;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    Cursor mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ReadContactAsync().execute();
    }

    class ReadContactAsync extends AsyncTask<Void,Void, Cursor> {

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            mData = cursor;
            printStuff();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            ContentResolver resolver = getContentResolver();
            return  resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null,
                    null, ContactsContract.Contacts.DISPLAY_NAME + " ASC ");
        }
    }
    public void printStuff(){
        for(String s : mData.getColumnNames())
        Log.v("PRINTING", s);
    }

}
