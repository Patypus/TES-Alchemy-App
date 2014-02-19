package org.pat.howell.tes.alchemyreference.data.loading;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.data.AlchemyDatabaseOpenHelper;
import org.pat.howell.tes.alchemyreference.data.DatabaseConstants;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class DatabaseCopier {

	private Context _context;
	private String _databaseFileLocation;
	
	public DatabaseCopier(Context applicationContext) {
		_context = applicationContext;
	}
	
	public int copyDatabase() {
		_databaseFileLocation = "/data/data/" + _context.getApplicationContext().getPackageName() + "/databases/";
		int result = Activity.RESULT_OK;
		if( checkFileHasNotYetBeenCopied() ) {
			result = copyFile();
		}
		return result;
	}
	
	private boolean checkFileHasNotYetBeenCopied() {
		File databaseFile = new File( _databaseFileLocation + DatabaseConstants.DATABASE_NAME );
		return !databaseFile.exists();
	}
	
	private void createDatabasesDirectory() {
		(new AlchemyDatabaseOpenHelper( _context, 
										DatabaseConstants.DATABASE_NAME, 
										DatabaseConstants.DATABASE_VERSION )).createDatabasesDirectory();
	}
	
	private int copyFile() {
		try {
			InputStream prebuiltDatabase = _context.getAssets().open( DatabaseConstants.DATABASE_NAME );
			OutputStream destination = new FileOutputStream( _databaseFileLocation + DatabaseConstants.DATABASE_NAME ); 
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