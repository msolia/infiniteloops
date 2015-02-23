package com.example.dndmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import com.android.internal.telephony.*;
import java.lang.reflect.Method;
import java.util.*;
import java.text.SimpleDateFormat;


/**
 * Created by madsoliy on 1/30/2015.
 */
public class IncomingCallReciever  extends BroadcastReceiver {

    Date date = new Date();
    Date currentDateTime = null;
    ITelephony telephonyService;

    @Override
    public void onReceive(Context context, Intent intent) {

        TelephonyManager telephony = (TelephonyManager)
                         context.getSystemService(Context.TELEPHONY_SERVICE);
        AudioManager audioManager =(AudioManager)
                     context.getSystemService(Context.AUDIO_SERVICE);
        SmsManager smsManager = SmsManager.getDefault();

        DBHelper db = new DBHelper(context);
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        //Retrieve incoming number
        String incomingNumber = intent.getStringExtra("incoming_number");

        //It will check whither it is incoming call or not return boolean according
        boolean isIncomingCall =(intent.hasExtra("state")) ? intent.getStringExtra("state").equals("RINGING") : false;

        //Get current status of Scheduler
        boolean ScheduleTaskStatus = db.getScheduleStatus();

        //Get current status of defaultMessage
        boolean DefaultMsgStatus = db.getDefaultMsgStatus();

        //Gets all blocked contacts add by user
        ArrayList<String> getStoredContacts = db.getAllContacts();

        if(isIncomingCall)//isIncomingCall
        {
            try
            {
                currentDateTime = datetimeFormat.parse(datetimeFormat.format(date) + " " + datetimeFormat.format(Calendar.getInstance().getTime()));
                Class c = Class.forName(telephony.getClass().getName());
                Method m = c.getDeclaredMethod("getITelephony");
                m.setAccessible(true);
                telephonyService = (ITelephony) m.invoke(telephony);

                //It will check incoming number with stored blocked number
                if (getStoredContacts.contains(incomingNumber.substring(1))) //if (getStoredContacts.contains(incomingNumber))
                {
                    audioManager.setRingerMode(0);
                    telephonyService.endCall();

                    db.setLog(datetimeFormat.format(date), incomingNumber);//Generate log of incoming number with respect to Date-Time
                }
                //If Schedule Task is On
                if (ScheduleTaskStatus)
                {
                    ArrayList<String> getSchedule = db.getSchedule();

                    Date onDateTime = datetimeFormat.parse(getSchedule.get(1) + " " + getSchedule.get(2));
                    Date offDateTime = datetimeFormat.parse(getSchedule.get(3) + " " + getSchedule.get(4));

                    if (currentDateTime.after(onDateTime) && currentDateTime.before(offDateTime))
                    {
                        telephonyService.endCall();

                        db.setLog(datetimeFormat.format(date), incomingNumber);//Generate log of incoming number with respect to Date-Time

                        //If default msg status is true it will send Input message to incoming number
                        if(DefaultMsgStatus)
                        {
                            smsManager.sendTextMessage(incomingNumber,null,db.getDefaultMsg(),null,null);
                        }
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
     }
}
