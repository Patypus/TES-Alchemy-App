package org.pat.howell.tes.alchemyreference.data.proxy;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.data.loading.DatabaseCopier;
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

	private Context _applicationContext;
	private String _databaseName;
	
	public AlchemyDatabaseOpenHelper( Context context, String databaseName, int dbVersion ) {
		super( context, databaseName, null, dbVersion );
		_applicationContext = context;
		_databaseName = databaseName;
	}

	@Override
	public void onCreate( SQLiteDatabase arg0 ) {
		DatabaseCopier copier = new DatabaseCopier( _applicationContext, _databaseName );
		copier.copyDatabase();
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
}