package org.pat.howell.tes.alchemyreferencetests.data.loading;

import java.io.File;

import org.pat.howell.tes.alchemyreference.data.DatabaseConstants;
import org.pat.howell.tes.alchemyreference.data.loading.DatabaseLoadingService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.test.ServiceTestCase;

public class DatabaseLoadingServiceTests extends ServiceTestCase<DatabaseLoadingService> {
	private boolean complete = false;
	private Handler responseHandler = new Handler() {
		public void handleMessage( Message message  ) {
			complete = true;
		}
	};
	
	public DatabaseLoadingServiceTests() {
		super( DatabaseLoadingService.class );
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		//Ensure that database directoy is clean before starting tests.
		removeDatabaseFile();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		removeDatabaseFile();
	}

	public void testDatabaseIsCopiedByService() {
		Intent serviceStart = new Intent( "tes.alchemyreference.DATABASELOADING" );
		serviceStart.putExtra( "MESSENGER", 
							   new Messenger( responseHandler ) );
		startService( serviceStart );
		int count = 0;
		while ( count <= 20 && !complete  ) {
			try {
			Thread.sleep( 1000 );
			} catch( InterruptedException e ) {
			}
		}
		File database = new File( "/data/data/org.pat.howell.tes.alchemyreference/databases/" + 
								  DatabaseConstants.DATABASE_NAME );
		assertTrue( database.exists() );
	}
	
	private void removeDatabaseFile() {
		File database = new File( "/data/data/org.pat.howell.tes.alchemyreference/databases/" + 
			      				  DatabaseConstants.DATABASE_NAME );
		database.delete();
	}
}
