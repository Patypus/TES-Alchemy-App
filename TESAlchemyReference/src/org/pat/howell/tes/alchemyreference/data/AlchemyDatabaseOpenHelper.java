package org.pat.howell.tes.alchemyreference.data;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.data.loading.DatabaseLoadingService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class AlchemyDatabaseOpenHelper extends SQLiteOpenHelper {

	public AlchemyDatabaseOpenHelper( Context context, String databaseName, int dbVersion ) {
		super( context, databaseName, null, dbVersion );
	}

	@Override
	public void onCreate( SQLiteDatabase arg0 ) {
		/* Not implemented as the database is pre-created */
	}

	@Override
	public void onUpgrade( SQLiteDatabase arg0, int arg1, int arg2 ) {
		onCreate( arg0 );
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