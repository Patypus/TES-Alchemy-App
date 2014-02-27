package org.pat.howell.tes.alchemyreference.data;

import java.util.ArrayList;

import org.pat.howell.tes.alchemyreference.data.entities.Ingredient;
import org.pat.howell.tes.alchemyreference.data.proxy.AlchemyDatabaseOpenHelper;
import org.pat.howell.tes.alchemyreference.data.proxy.DatabaseProxy;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Messenger;

/**
 * Service to access the Alchemy database
 */
public class AlchemyDataService extends IntentService {
	/** Uri manager to identify  */
	private UriManager _uriManager;
	/** Database proxy for loading accessing the database */
	private DatabaseProxy _proxy;
	/** Key to identify Uri's sent to the database service to identify the required service */
	public static final String URI_KEY = "URI"; 
	/** Key to identify messenger to handle result returns to intent senders */
	public static final String MESSENGER_KEY = "MESSENGER";
	
	
	
	public AlchemyDataService() {
		super( AlchemyDataService.class.toString() );
		_uriManager = new UriManager();
		AlchemyDatabaseOpenHelper openHandler = new AlchemyDatabaseOpenHelper( getApplicationContext(),
																			   DatabaseConstants.DATABASE_NAME,
																			   DatabaseConstants.DATABASE_VERSION );
		_proxy = new DatabaseProxy( openHandler );
	}
	
	@Override
	protected void onHandleIntent( Intent intent ) {
		Uri operationUri = Uri.parse( intent.getStringExtra( URI_KEY ) );
		Object returnable = null;
		switch( _uriManager.match( operationUri ) ) {
			case ContentConstants.ALL_INGREDIENTS:
				returnable = loadAllIngredients();
			case ContentConstants.ALL_EFFECTS:
				//TODO - handle this
			case ContentConstants.INGREDIENT_BY_NAME:
				//TODO - handle this
			case ContentConstants.INGREDIENTS_WITH_EFFECT:
				//TODO - handle this
		}
		// TODO send back results
		Messenger messenger = (Messenger) intent.getParcelableExtra( MESSENGER_KEY );
	}
	
	/**
	 * Load all ingredients from the database.
	 */
	private ArrayList<Ingredient> loadAllIngredients() {
		return _proxy.getAllIngredients();
	}
}