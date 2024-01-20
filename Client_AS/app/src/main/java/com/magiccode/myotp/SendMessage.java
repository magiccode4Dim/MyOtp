package com.magiccode.myotp;

import android.content.pm.PackageManager;
import android.telephony.SmsManager;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SendMessage {


    public static void sendDirectSms(String phoneNumber, String msg)  throws  Exception{

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, msg, null, null);

    }
}
