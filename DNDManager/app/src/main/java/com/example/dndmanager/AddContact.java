package com.example.dndmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by madsoliy on 1/29/2015.
 */
public class AddContact extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private Button addFromCallLog;
    private EditText inputNumber;
    private ListView listview;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        addFromCallLog = (Button)findViewById(R.id.addfromcalllog);
        inputNumber = (EditText) findViewById(R.id.number);

    }

    public void createContact(View view)
    {

        String contactFromCallLog = ((TextView) view).getText().toString();
        String contact = inputNumber.getText().toString();

        DBHelper db = new DBHelper(this);

        if(!contactFromCallLog.equals("Submit"))//If it is not contactfromcalllog it will return submit
        {
            db.setContact(contactFromCallLog);
            Toast.makeText(getApplicationContext(), "Contact No:" + contactFromCallLog + "Added Successfully", Toast.LENGTH_LONG).show();
        }
        else
        {
            db.setContact(contact);
            Toast.makeText(getApplicationContext(), "Contact No:" + contact + "Added Successfully", Toast.LENGTH_LONG).show();
        }


    }

    public void addFromCallLog(View view)
    {
        ArrayList<String> getContactsFromCallLog = contactAddFromCallLog();
        setContentView(R.layout.contact_from_call_log);
        listview = (ListView) findViewById(R.id.callloglistview);
        ArrayAdapter adapter = new ArrayAdapter(this,
                               android.R.layout.simple_list_item_1, getContactsFromCallLog);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);

    }
    public ArrayList<String> contactAddFromCallLog()
    {
        Cursor managedCursor = managedQuery(CallLog.Calls.CONTENT_URI, null,null, null, null);
        ArrayList array_list = new ArrayList();
        while (managedCursor.moveToNext()) {

            if(!array_list.contains(managedCursor.getString(managedCursor.getColumnIndex(CallLog.Calls.NUMBER))))
            {
                array_list.add(managedCursor.getString(managedCursor.getColumnIndex(CallLog.Calls.NUMBER)));
            }

        }
        return array_list;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        createContact(view);
    }
}
