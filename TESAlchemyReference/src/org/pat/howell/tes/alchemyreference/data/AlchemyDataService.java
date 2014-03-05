package org.pat.howell.tes.alchemyreference.data;

import java.util.ArrayList;

import org.pat.howell.tes.alchemyreference.data.entities.Ingredient;
import org.pat.howell.tes.alchemyreference.data.proxy.AlchemyDatabaseOpenHelper;
import org.pat.howell.tes.alchemyreference.data.proxy.DatabaseProxy;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

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
	/** Key to identify the name of the ingredient from intent senders */
	public static final String INGREDIENT_NAME_KEY = "INGREDIENTNAME";
	/** Key to identify the name of the effect from intent senders */
	public static final String EFFECT_KEY_NAME = "EFFECTNAME";
	
	public AlchemyDataService() {
		super( AlchemyDataService.class.toString() );
		_uriManager = new UriManager();
	}
	
	@Override
	protected void onHandleIntent( Intent intent ) {
		AlchemyDatabaseOpenHelper openHandler = new AlchemyDatabaseOpenHelper( getApplicationContext(),
				   DatabaseConstants.DATABASE_NAME,
				   DatabaseConstants.DATABASE_VERSION );
		_proxy = new DatabaseProxy( openHandler );
		Uri operationUri = Uri.parse( intent.getStringExtra( URI_KEY ) );
		Object returnable = null;
		switch( _uriManager.match( operationUri ) ) {
			case ContentConstants.ALL_INGREDIENTS:
				returnable = loadAllIngredients();
				break;
			case ContentConstants.ALL_EFFECTS:
				returnable = loadAllEffects();
				break;
			case ContentConstants.INGREDIENT_BY_NAME:
				String ingredientName = intent.getStringExtra( INGREDIENT_NAME_KEY );
				returnable = loadIngredientByName( ingredientName );
				break;
			case ContentConstants.INGREDIENTS_WITH_EFFECT:
				String effectName = intent.getStringExtra( EFFECT_KEY_NAME );
				returnable = loadAllIngredientsWithEffect( effectName );
				break;
		}
		Messenger messenger = (Messenger) intent.getParcelableExtra( MESSENGER_KEY );
		replyToCallingActivity( messenger, returnable );
	}
	
	/**
	 * Load all ingredients from the database.
	 */
	private ArrayList<Ingredient> loadAllIngredients() {
		return _proxy.getAllIngredients();
	}
	
	/**
	 * Load all effects from the database
	 */
	private ArrayList<String> loadAllEffects() {
		return _proxy.getAllEffects();
	}
	
	/**
	 * Load all ingredients with the effect specified by the parameter effectName
	 */
	private ArrayList<Ingredient> loadAllIngredientsWithEffect( String effectName ) {
		return _proxy.getIngredientsWithEffect( effectName );
	}
	
	/**
	 * Load ingredient by name as specified by the parameter ingredientName
	 */
	private Ingredient loadIngredientByName( String ingredientName ) {
		return _proxy.getIngredientByName( ingredientName );
	}
	
 	/**
	 * Method to send back the response from the database to the calling activity
	 * @param messenger - Messenger to respond through
	 * @param response - Object to return to the caller
	 */
	private void replyToCallingActivity( Messenger messenger, Object response ) {
		Message message = Message.obtain();
		message.arg1 = Activity.RESULT_OK;
		message.obj = response;
		sendMessage( messenger, message );
	}
	
	/**
	 * Method to send a message through a messenger
	 * @param messenger - Messenger to send the message through
	 * @param msg - The message to send
	 */
	private void sendMessage( Messenger messenger, Message msg ) {
		try {
			messenger.send( msg );
		} catch( RemoteException re ) {
			Log.w( getClass().getName(), "Error sending message" );
		}
	}
}