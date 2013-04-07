package com.example.batterycharging;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {

	BroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// jeœli chcemy by aktywnoœc siê pojawia³a to receiver musi byc tworzony
		// w on create
		/*
		 * receiver = new BroadcastReceiver() {
		 * 
		 * @Override public void onReceive(Context context, Intent intent) { //
		 * TODO Auto-generated method stub
		 * 
		 * //if (intent.getAction().equals("Test")) { LoadData(); //} }
		 * 
		 * }; registerReceiver(receiver, new IntentFilter("Test"));
		 */

		// sprawdzanie czy s¹ notyfikacje i usuwanie
		Bundle b = getIntent().getExtras();

		if (b != null && b.containsKey("notificationID")) {
			NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			nm.cancel(getIntent().getExtras().getInt("notificationID"));
		}
		LoadData();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);

		// jeœli chcemy by aktywnoœc siê pojawia³a
		/*
		 * Bundle b = intent.getExtras();
		 * 
		 * if (b != null && b.containsKey("notificationID")) {
		 * NotificationManager nm = (NotificationManager)
		 * getSystemService(NOTIFICATION_SERVICE);
		 * nm.cancel(intent.getExtras().getInt("notificationID")); }
		 */

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		LoadData();

		receiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub

				if (intent.getAction().equals("Test")) {
					LoadData();
				}
			}

		};
		registerReceiver(receiver, new IntentFilter("Test"));
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(receiver);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		// jeœli chcemy by aktywnoœc siê pojawia³a tp receiver musi byc
		// wy³¹czony w onDestroy
		// unregisterReceiver(receiver);
	}

	private void LoadData() {
		ListView l = (ListView) findViewById(R.id.listView);

		Base b = new Base(this);

		Cursor c = b.GetData();
		startManagingCursor(c);

		SimpleCursorAdapter sa = new SimpleCursorAdapter(this,
				R.layout.grid_layout, c, new String[] { "_id", "start_date",
						"stop_date" }, new int[] { R.id.txId, R.id.txStart,
						R.id.txStop });

		l.setAdapter(sa);

	}

	private void CursorDisplay(Cursor c) {
		Toast.makeText(
				this,
				"id: " + c.getString(0) + " Start: " + c.getString(1)
						+ " Stop: " + c.getString(2), 1).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub

		if (item.getItemId() == R.id.menu_clear) {
			Base b = new Base(this);
			b.Clear();
			LoadData();
		}
		if (item.getItemId() == R.id.menu_Show) {
			LoadData();
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
