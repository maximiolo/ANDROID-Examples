package com.example.batterycharging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Charging extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Base b = new Base(context);

		if (intent.getAction().equals(
				"android.intent.action.ACTION_POWER_CONNECTED")) {
			// Toast.makeText(context, "Charging - start", 1).show();
			String sDate = GetCurrentDate();
			b.Add(true, sDate);
			NotifyCharging n = new NotifyCharging(context);
			n.showNotify("Operation date: " + sDate, "Charging",
					"Charging is started");

		} else if (intent.getAction().equals(
				"android.intent.action.ACTION_POWER_DISCONNECTED")) {
			// Toast.makeText(context, "Charging - stop", 1).show();
			String sDate = GetCurrentDate();
			b.Add(false, sDate);
			NotifyCharging n = new NotifyCharging(context);
			n.showNotify("Operation date: " + sDate, "Charging",
					"Charging is stoped");
		}

	}

	private String GetCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
