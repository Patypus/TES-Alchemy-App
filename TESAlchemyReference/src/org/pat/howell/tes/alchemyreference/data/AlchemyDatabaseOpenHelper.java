package org.pat.howell.tes.alchemyreference.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AlchemyDatabaseOpenHelper extends SQLiteOpenHelper {

	public AlchemyDatabaseOpenHelper( Context context, String databaseName, int dbVersion ) {
		super( context, databaseName, null, dbVersion );
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	/** This method opens and closes the writeable database to ensure that
	 * the databases directory in the application is created */
	public void createDatabasesDirectory() {
		open();
		close();
	}
	
	/** Returns the writeable database */
	public SQLiteDatabase open() {
		try {
			return getWritableDatabase();
		} catch( SQLiteException sqle ) {
			Log.e( getClass().getName(), "Database was not able to be opened" );
			return null;
		}
	}
}