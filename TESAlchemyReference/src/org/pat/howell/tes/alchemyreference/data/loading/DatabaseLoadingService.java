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
	
	public DatabaseLoadingService() {
		super( "DatabaseLoadingService" );
	}

	@Override
	protected void onHandleIntent( Intent intent ) {
		Messenger messenger = getReturnMessengerFromIntent( intent.getExtras() );
		DatabaseCopier copier = new DatabaseCopier( getApplicationContext() );
		int result = copier.copyDatabase();
		sendOperationCompleteRespose( messenger, result );
	}

	private Messenger getReturnMessengerFromIntent( Bundle extras ) {
		return (Messenger) extras.get( MESSENGER_EXTRA );
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