package com.example.batterycharging;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

	Context context;
	static final String Base_Name = "BatteryDB";
	static final int Base_Version = 2;
	DatabaseHelper dbHelper;
	// static final String
	// CreateBase=" create table battery("+ID+" integer primary key autoincrement,"+Start+" text,"+Stop+"text)";
	static final String DropBase = " Drop table if exists battery";
	static final String ID = "_id";
	static final String Start = "start_date";
	static final String Stop = "stop_date";
	static final String BaseTable = "battery";
	SQLiteDatabase db;

	public DBAdapter(Context context) {
		super();
		this.context = context;
		dbHelper = new DatabaseHelper(context);
	}

	public DBAdapter open() {
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public long insertLog(Boolean bIsStart, String pDate) {

		if (bIsStart) {
			ContentValues initialValues = new ContentValues();
			initialValues.put(Start, pDate);
			return db.insert(BaseTable, null, initialValues);
		} else {
			// initialValues.put(Stop, pDate);
			updateCharging(GetLastInsertId(), pDate);
			return 1;
		}

	}

	public boolean deleteCharging() {
		return db.delete(BaseTable, ID + ">0", null) > 0;
	}

	public Cursor getAllCharging() {
		return db.query(BaseTable, new String[] { ID, Start, Stop }, null,
				null, null, null, ID+" desc");
	}

	public boolean updateCharging(long rowid, String pDate) {
		if (rowid > 0) {
			ContentValues val = new ContentValues();
			val.put(Stop, pDate);

			return db.update(BaseTable, val, ID + " = " + rowid, null) > 0;
		}
		return true;
	}

	public long GetLastInsertId() {
		// Stop +" is null and "+Start+" is not null"
		Cursor c = db.query(BaseTable, new String[] { ID }, Stop
				+ " is null and " + Start + " is not null", null, null, null,
				ID + " desc");
		if (c != null) {
			if(c.getCount()>0)
			{
			c.moveToFirst();
			return Long.parseLong(c.getString(0));
			}
			return -1;
		}
		return -1;
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, Base_Name, null, Base_Version);

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("create table " + BaseTable + "(" + ID
					+ " integer primary key autoincrement," + Start + " text,"
					+ Stop + " text)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL(DropBase);
			onCreate(db);
		}

	}

}
