package com.example.dndmanager;


import android.content.Intent;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ShowLog extends ActionBarActivity implements
        View.OnClickListener{

    private Button clearLog;
    private ListView showLogListView;
    private TextView totallog;
    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_log);

        clearLog = (Button)findViewById(R.id.clearlog);
        showLogListView = (ListView) findViewById(R.id.showlog);
        totallog = (TextView) findViewById(R.id.totallog);

        clearLog.setOnClickListener(this);

        String[] columnNames = {BaseColumns._ID,"date", "number"};

        int[] targetLayoutIDs = {0,R.id.date,R.id.number};

        Cursor getLog = db.getLog();

        if(getLog.getCount() > 0)
        {
            CursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.show_log_field,
                                    getLog, columnNames, targetLayoutIDs, 0);

            totallog.setText("Logs found: "+getLog.getCount());
            showLogListView.setAdapter(adapter);
        }
        else
        {
            //If totallog is zero
            totallog.setText("No New Log Found....");
            clearLog.setVisibility(View.INVISIBLE);
        }
    }

    public void onClick(View v)
    {
        if(v == clearLog)
        {
                boolean status = db.clearLog();

                if(status)
                {
                    db.clearLog();

                    Intent obj = new Intent(this,ShowLog.class);
                    startActivity(obj);
                    Toast.makeText(getApplicationContext(), "Clear Log Successfully:", Toast.LENGTH_SHORT).show();
                }
        }
   }
}