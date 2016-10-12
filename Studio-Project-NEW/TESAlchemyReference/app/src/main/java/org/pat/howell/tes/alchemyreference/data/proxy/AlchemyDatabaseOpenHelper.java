package org.pat.howell.tes.alchemyreference.data.proxy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AlchemyDatabaseOpenHelper extends SQLiteOpenHelper {

	private String _databaseName;
	
	public AlchemyDatabaseOpenHelper( Context context, String databaseName, int dbVersion ) {
		super( context, databaseName, null, dbVersion );
		_databaseName = databaseName;
	}

	@Override
	public void onCreate( SQLiteDatabase arg0 ) {
		/** Not implemented as database is copied */
	}

	@Override
	public void onUpgrade( SQLiteDatabase arg0, int arg1, int arg2 ) {
		onCreate( arg0 );
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
	
	/** Return the name of the database that this class has opened the connection to */
	public String getDatabaseName() {
		return _databaseName;
	}
	
	/** This method opens and closes the writeable database to ensure that
	 * the databases directory in the application is created */
	public void createDatabasesDirectory() {
		open();
		close();
	}
}