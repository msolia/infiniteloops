package com.example.dndmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.Calendar;



public class SchedulerTask extends ActionBarActivity implements
        View.OnClickListener {

    // Widget GUI
    private Button onDate, onTime, offDate, offTime, setbutton,setdefaultmsgbutton;
    private EditText ontxtDate, ontxtTime ,offtxtDate, offtxtTime;
    private TextView ontextView,offtextView,defaultmsg;
    private ToggleButton toggleButton;
    private CheckBox checkBox;

    private int mYear, mMonth, mDay, mHour, mMinute;

    int i = 0;

    DBHelper db =new DBHelper(this);

     @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduler_task);

         onDate = (Button) findViewById(R.id.onDate);
         onTime = (Button) findViewById(R.id.onTime);
         offDate = (Button) findViewById(R.id.offdate);
         offTime = (Button) findViewById(R.id.offTime);
         setbutton = (Button) findViewById(R.id.setbutton);
         setdefaultmsgbutton = (Button) findViewById(R.id.setdefaultmsgbutton);

         ontxtDate = (EditText) findViewById(R.id.ontxtDate);
         ontxtTime = (EditText) findViewById(R.id.ontxtTime);
         offtxtDate = (EditText) findViewById(R.id.offtxtDate);
         offtxtTime = (EditText) findViewById(R.id.offtxtTime);

         ontextView = (TextView) findViewById(R.id.ontextView);
         offtextView = (TextView) findViewById(R.id.offtextView);
         defaultmsg = (TextView) findViewById(R.id.defaultmsg);

         checkBox = (CheckBox) findViewById(R.id.checkBox);

         onDate.setOnClickListener(this);
         onTime.setOnClickListener(this);
         offDate.setOnClickListener(this);
         offTime.setOnClickListener(this);
         checkBox.setOnClickListener(this);
         setdefaultmsgbutton.setOnClickListener(this);

         toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
         toggleButton.setOnClickListener(this);

         if(db.getScheduleStatus())
         {
            toggleButton.setChecked(true);
            changeLayout(true);
         }
         else
         {
            toggleButton.setChecked(false);
            changeLayout(false);
         }
    }

    public void changeLayout(boolean status)
    {
        //Toggle is On
        if(status)
        {

            if(db.getDefaultMsgStatus())
            {
                checkBox.setChecked(true);
                defaultmsg.setVisibility(View.VISIBLE);
                setdefaultmsgbutton.setVisibility(View.VISIBLE);
            }
            else
            {
                defaultmsg.setVisibility(View.INVISIBLE);
                checkBox.setVisibility(View.VISIBLE);
                setdefaultmsgbutton.setVisibility(View.INVISIBLE);
            }
            onDate.setVisibility(View.VISIBLE);
            onTime.setVisibility(View.VISIBLE);
            offDate.setVisibility(View.VISIBLE);
            offTime.setVisibility(View.VISIBLE);
            ontxtDate.setVisibility(View.VISIBLE);
            ontxtTime.setVisibility(View.VISIBLE);
            offtxtDate.setVisibility(View.VISIBLE);
            offtxtTime.setVisibility(View.VISIBLE);
            ontextView.setVisibility(View.VISIBLE);
            offtextView.setVisibility(View.VISIBLE);
            setbutton.setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.VISIBLE);

        }
        else
        {

            onDate.setVisibility(View.INVISIBLE);
            onTime.setVisibility(View.INVISIBLE);
            offDate.setVisibility(View.INVISIBLE);
            offTime.setVisibility(View.INVISIBLE);
            ontxtDate.setVisibility(View.INVISIBLE);
            ontxtTime.setVisibility(View.INVISIBLE);
            offtxtDate.setVisibility(View.INVISIBLE);
            offtxtTime.setVisibility(View.INVISIBLE);
            ontextView.setVisibility(View.INVISIBLE);
            offtextView.setVisibility(View.INVISIBLE);
            setbutton.setVisibility(View.INVISIBLE);
            defaultmsg.setVisibility(View.INVISIBLE);
            checkBox.setVisibility(View.INVISIBLE);
            setdefaultmsgbutton.setVisibility(View.INVISIBLE);

        }

    }
    @Override
    public void onClick(View v) {


        if (toggleButton.getText().equals("On"))
        {
            if(i==0)
            {
                boolean status = db.setScheduleStatus("true");

                if (status)
                {
                    Toast.makeText(getApplicationContext(), "Scheduler Task:Enabled", Toast.LENGTH_SHORT).show();
                }
            }

            changeLayout(true);
           // setdefaultmsgbutton.setVisibility(View.INVISIBLE);


            if (v == onDate)
            {

                // Process to get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                ontxtDate.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
            if (v == onTime)
            {

                // Process to get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog tpd = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // Display Selected time in textbox
                                ontxtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                tpd.show();
            }

            if (v == offDate)
            {

                // Process to get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                offtxtDate.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
            if (v == offTime)
            {

                // Process to get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog tpd = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // Display Selected time in textbox
                                offtxtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                tpd.show();
            }

            i++;
        }
        if (toggleButton.getText().equals("Off"))
        {
            boolean status = db.setScheduleStatus("false");

            if (status)
            {
                changeLayout(false);
                setdefaultmsgbutton.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Scheduler Task:Disabled", Toast.LENGTH_SHORT).show();
            }

            i=0;
        }

        if(v == checkBox || checkBox.isChecked())
        {
           defaultmsg.setVisibility(View.INVISIBLE);
           if(checkBox.isChecked() && toggleButton.getText().equals("On") )
            {
                defaultmsg.setVisibility(View.VISIBLE);
                setdefaultmsgbutton.setVisibility(View.VISIBLE);

                if(v == setdefaultmsgbutton)
                {
                    db.addDefaultMsg("true",defaultmsg.getText().toString());
                    Toast.makeText(getApplicationContext(), "Default Message Set Successfully", Toast.LENGTH_SHORT).show();
                }
            }
            else
           {
               defaultmsg.setVisibility(View.INVISIBLE);
               setdefaultmsgbutton.setVisibility(View.INVISIBLE);
               db.changeDefaultMsgStatus("false");

               Toast.makeText(getApplicationContext(), "Default Message Disabled", Toast.LENGTH_SHORT).show();
           }
        }
    }

    public void set(View view)
    {
      boolean setScheduler = db.setSchedule("true",ontxtDate.getText().toString(),ontxtTime.getText().toString(),
                             offtxtDate.getText().toString(),offtxtTime.getText().toString());

        if(setScheduler)
        {
            Toast.makeText(getApplicationContext(), "Scheduler Set Successfully:", Toast.LENGTH_LONG).show();
        }
    }
}

