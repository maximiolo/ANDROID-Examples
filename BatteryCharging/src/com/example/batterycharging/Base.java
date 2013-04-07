package com.example.batterycharging;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

public class Base {

	Context context;	
	DBAdapter dbAdapter;

	public Base(Context context) {
		super();
		this.context = context;
		dbAdapter = new DBAdapter(context);
		dbAdapter.open();
	}

	public void Add(Boolean bStart, String pDate) {

		 dbAdapter.insertLog(bStart, pDate);
	//	Toast.makeText(context, "ID: " + i, 1);
		dbAdapter.close();
		/*
		 * if(bStart) Clear(); SharedPreferences sp=
		 * context.getSharedPreferences("AppData", context.MODE_PRIVATE);
		 * SharedPreferences.Editor editor=sp.edit();
		 * editor.putString(bStart?AppDateStart:AppDateStop, pDate);
		 * editor.commit();
		 */
	}

	public void Clear() {
		dbAdapter.deleteCharging();
		dbAdapter.close();
		/*
		 * SharedPreferences sp= context.getSharedPreferences("AppData",
		 * context.MODE_PRIVATE); SharedPreferences.Editor editor=sp.edit();
		 * editor.clear(); editor.commit();
		 */
	}

	public Cursor GetData() {
		/*
		 * SharedPreferences sp= context.getSharedPreferences("AppData",
		 * context.MODE_PRIVATE); SharedPreferences.Editor editor=sp.edit();
		 * String pReturn="Start: "+ sp.getString(AppDateStart,
		 * "--")+" stop: "+sp.getString(AppDateStop, "--"); return pReturn;
		 */
		Cursor c = dbAdapter.getAllCharging();
		return c;
	}

	/*public String Test() {

		String s= dbAdapter.GetLastInsertId();
		dbAdapter.close();
		return s;

	}*/
}
