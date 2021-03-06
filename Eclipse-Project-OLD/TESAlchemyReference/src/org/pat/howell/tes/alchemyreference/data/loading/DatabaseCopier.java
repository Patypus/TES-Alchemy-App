package org.pat.howell.tes.alchemyreference.data.loading;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.data.proxy.AlchemyDatabaseOpenHelper;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class DatabaseCopier {

	private Context _context;
	private AlchemyDatabaseOpenHelper _openHandler; 
	private String _databaseFileLocation;
	private String _databaseName;
	
	public DatabaseCopier( Context applicationContext, String databaseName, AlchemyDatabaseOpenHelper handler ) {
		_context = applicationContext;
		_databaseName = databaseName;
		_openHandler = handler;
	}
	
	public int copyDatabase() {
		_databaseFileLocation = "/data/data/" + _context.getPackageName() + "/databases/";
		int result = Activity.RESULT_OK;
		if( checkFileHasNotYetBeenCopied() ) {
			_openHandler.createDatabasesDirectory();
			result = copyFile();
		}
		return result;
	}
	
	private boolean checkFileHasNotYetBeenCopied() {
		File databaseFile = new File( _databaseFileLocation + _databaseName );
		return !databaseFile.exists();
	}
	
	private int copyFile() {
		try {
			InputStream prebuiltDatabase = _context.getAssets().open( _databaseName );
			OutputStream destination = new FileOutputStream( _databaseFileLocation + _databaseName ); 
			byte[] buffer = new byte[1024];
			int length;
			while( ( length = prebuiltDatabase.read( buffer ) ) > 0 ) {
				destination.write( buffer, 0, length );
			}
			destination.flush();
			prebuiltDatabase.close();
			destination.close();
			return Activity.RESULT_OK;
		} catch ( IOException exception ) {
			Log.e( this.getClass().getName(), 
					_context.getString( R.string.database_copying_error ) + exception.getMessage() );
			return Activity.RESULT_CANCELED;
		}
	}	
}