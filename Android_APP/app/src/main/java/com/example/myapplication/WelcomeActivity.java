package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by madsoliy on 1/22/2015.
 */
public class WelcomeActivity  extends Activity {

    TextView greetMsg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        greetMsg = (TextView) findViewById(R.id.loginuser);
        Intent intename = getIntent();

        String userName = intename.getSerializableExtra("US").toString();

        greetMsg.setText("Welcome:"+userName);
    }

    public void logout(View view)
    {
        Intent intObj = new Intent(this,MainActivity.class);

        intObj.putExtra("logoutmsg","Successfully Logged out!!");

        startActivity(intObj);
    }
}