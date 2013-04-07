package com.example.notification;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	int notificationID=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn = (Button)findViewById(R.id.btn_notification);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				displayNotification();
			}
		});
	}
	protected void displayNotification()
	{
		Intent i=new Intent(this, NotificationView.class);
		i.putExtra("notificationID", notificationID);
		PendingIntent pendingIntent=
				PendingIntent.getActivity(this, 0,i,0);
		
		NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification notif=new Notification(R.drawable.ic_launcher,"Notification: This is notification",System.currentTimeMillis() );
		CharSequence from ="Notification Test";
		CharSequence message="Message to diplay";
		
		notif.setLatestEventInfo(this,from,message, pendingIntent);
		notif.vibrate=new long[]{100,250,100,500};
		nm.notify(notificationID, notif);
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
