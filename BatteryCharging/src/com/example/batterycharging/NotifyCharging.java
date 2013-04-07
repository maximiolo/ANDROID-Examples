package com.example.batterycharging;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotifyCharging {
	public NotifyCharging(Context context) {
		super();
		this.context = context;
	}

	Context context;

	int iNotificationID = -1;

	public void showNotify(String pText, String from, String message) {
		Intent i = new Intent(context, MainActivity.class);
		i.putExtra("notificationID", iNotificationID);

		PendingIntent pending = PendingIntent.getActivity(context, 0, i, 0);

		Notification notif = new Notification(R.drawable.ic_launcher, pText,
				System.currentTimeMillis());

		notif.setLatestEventInfo(context, from, message, pending);

		notif.defaults |= Notification.FLAG_AUTO_CANCEL;// nie dzia³a po stronie
														// Activity jest
														// zaimplementowane
														// usuwanie notif

		NotificationManager nm = (NotificationManager) context
				.getSystemService(context.NOTIFICATION_SERVICE);

		nm.notify(iNotificationID, notif);

		PendingIntent intp = PendingIntent.getBroadcast(context, 0, new Intent(
				context, MainActivity.class), 0);

		// intent jeœli chcemy by aktywnoœæ siê uaktywni³a
		Intent intentBroadcast = new Intent(context, MainActivity.class);
		intentBroadcast.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intentBroadcast);

		// intent do odœwie¿enia widoku w aktywnosci
		Intent intentBroadcast1 = new Intent();
		intentBroadcast1.setAction("Test");
		context.sendBroadcast(intentBroadcast1);

	}
}
