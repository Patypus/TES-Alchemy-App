package org.pat.howell.tes.alchemyreference.data.loading;

import android.app.IntentService;
import android.content.Intent;

/**
 * Service run on application startup to check that the pre-built database
 * has been loaded to the correct location
 */
public class DatabaseLoadingService extends IntentService {

	public DatabaseLoadingService() {
		super( "DatabaseLoadingService" );
	}

	@Override
	protected void onHandleIntent( Intent intent ) {
		// TODO Auto-generated method stub

	}

}