package com.example.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends Activity {

    private BluetoothAdapter bluetoothAdapter;
    private ArrayAdapter adapter;
    private static final int ENABLE_BT_REQUEST_CODE = 1;
    private static final int DISCOVERABLE_BT_REQUEST_CODE = 2;
    private static final int DISCOVERABLE_DURATION = 300;

    String editText;
    String displayText;
    EditText username;
    EditText password;
    TextView statusTV;
    Boolean isUserAvailable;
    ProgressBar pg;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isUserAvailable = false;

        username = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.editText2);
        statusTV = (TextView) findViewById(R.id.tv_result);
        pg = (ProgressBar) findViewById(R.id.progressBar1);

        Intent intename = getIntent();

        if(intename.hasExtra("logoutmsg"))
        {
            String loggedOut = intename.getSerializableExtra("logoutmsg").toString();

            statusTV.setText(loggedOut);
        }


    }

    public void onClick(View v)
    {


        if (username.getText().length() != 0 && username.getText().toString() != "")
        {
            //Web-service
           /* AsyncCallWS task = new AsyncCallWS();
            task.execute();
             status = task.getStatus().toString();*/

            WebService ws = new WebService();
            isUserAvailable = ws.softLogin(username.getText().toString(),password.getText().toString());

            //Mongo
          //  MongoService ms = new MongoService();
            //isUserAvailable = ms.softLogin(username.getText().toString(),password.getText().toString());
        }

        if(isUserAvailable)
        {
            Intent intObj = new Intent(this,WelcomeActivity.class);

            intObj.putExtra("US",username.getText().toString());

            startActivity(intObj);
        }

        else
        {
            statusTV.setText("Invalid username or password!!");
        }
    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice
            WebService ws = new WebService();
            isUserAvailable = ws.softLogin(username.getText().toString(),password.getText().toString());
            statusTV.setText("");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response
            statusTV.setText("Loading");
            //Make ProgressBar invisible
         //   pg.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
        //    pg.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

    public boolean softLogin(String us,String pass)
    {
        AsyncCallWS task = new AsyncCallWS();
        //Call execute
        task.execute();

        WebService ws = new WebService();

         return ws.softLogin(us,pass);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onToggleClicked(View view) {

        setContentView(R.layout.bluetoothactivity);
    }



}
