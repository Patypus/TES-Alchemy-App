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
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * Service run on application startup to check that the pre-built database
 * has been loaded to the correct location
 */
public class DatabaseLoadingService extends IntentService {
	/** This key should be used in Intents to this Service to identify the 
	 * extra containing the messenger to report a result back to */
	public static final String MESSENGER_EXTRA = "MESSENGER";
	
	private String DatabaseFileLocation;
	
	public DatabaseLoadingService() {
		super( "DatabaseLoadingService" );
	}

	@Override
	protected void onHandleIntent( Intent intent ) {
		DatabaseFileLocation = getApplicationContext().getFilesDir().getPath() + "data/" + getPackageName() + "/databases/";
		Messenger messenger = getReturnMessengerFromIntent( intent.getExtras() );
		int result = Activity.RESULT_OK;
		if( checkFileHasNotYetBeenCopied() ) {
			createDatabasesDirectory();
			result = copyFile();
		}
		sendOperationCompleteRespose( messenger, result );
	}

	private boolean checkFileHasNotYetBeenCopied() {
		File databaseFile = new File( DatabaseFileLocation + DatabaseConstants.DATABASE_NAME );
		return !databaseFile.exists();
	}
	
	private Messenger getReturnMessengerFromIntent( Bundle extras ) {
		return (Messenger) extras.get( MESSENGER_EXTRA );
	}
	
	private void createDatabasesDirectory() {
		(new AlchemyDatabaseOpenHelper( getApplicationContext(), 
										DatabaseConstants.DATABASE_NAME, 
										DatabaseConstants.DATABASE_VERSION )).createDatabasesDirectory();
	}
	
	private int copyFile() {
		try {
			InputStream prebuiltDatabase = getApplicationContext().getAssets().open( DatabaseConstants.DATABASE_NAME );
			OutputStream destination = new FileOutputStream( DatabaseFileLocation + DatabaseConstants.DATABASE_NAME ); 
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
				   getResources().getString( R.string.database_copying_error ) + exception.getMessage() );
			return Activity.RESULT_CANCELED;
		}
	}
	
	private void sendOperationCompleteRespose( Messenger messenger, int result ) {
		Message message = Message.obtain();
		message.arg1 = result;
		sendMessage( messenger, message );
	}
	
	private void sendMessage( Messenger messenger, Message msg ) {
		try {
			messenger.send( msg );
		} catch( RemoteException re ) {
			Log.e( this.getClass().getName(), 
				   getResources().getString( R.string.message_sedning_error ) + re.getMessage() );
		}
	}
}