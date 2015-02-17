package com.example.dndmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by madsoliy on 1/29/2015.
 */
public class EditRemoveContact  extends ActionBarActivity implements OnItemClickListener {

    private EditText result;
    private long contactId;
    private View promptView;
    private ListView listView;
    private TextView title;

    DBHelper db = new DBHelper(this);

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contacts);

        listView = (ListView) findViewById(R.id.listView);

        Cursor getContacts = db.getContactsByCursor();

        if(getContacts.getCount() > 0)
        {
            createListView(getContacts);
        }

        else
        {
            title = (TextView) findViewById(R.id.listcontacttitle);
            title.setText("No Contacts added yet!!");
        }
    }

    public boolean createListView(Cursor getContacts)
    {
        String[] columnNames = {BaseColumns._ID,"number"};

        int[] targetLayoutIDs = {0,R.id.contact};

        CursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_contacts_field,
                getContacts, columnNames, targetLayoutIDs, 0);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                EditRemoveContact.this);
        alertDialogBuilder.setTitle("Edit or Remove Contact");
        alertDialogBuilder.setMessage("Enter New Number");

        LayoutInflater layoutInflater = LayoutInflater.from(EditRemoveContact.this);
        promptView = layoutInflater.inflate(R.layout.input_dialog, null);

        result = (EditText)promptView.findViewById(R.id.result);

        String contactSelected =  db.getContactById(id);

        result.setText(contactSelected);
        contactId = id;

        alertDialogBuilder.setView(promptView);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                      String updatedContact = result.getText().toString();
                      db.updateContact(contactId,updatedContact);

                      createListView(db.getContactsByCursor());
                    }
                })
                .setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        db.removeContactById(contactId);
                        Toast.makeText(getApplicationContext(), "Contact Deleted Successfully", Toast.LENGTH_SHORT).show();

                            createListView(db.getContactsByCursor());

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
     }
}
