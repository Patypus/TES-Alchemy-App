package org.pat.howell.tes.alchemyreference.activities;

import java.util.ArrayList;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.activities.adapters.IngredientListAdapter;
import org.pat.howell.tes.alchemyreference.activities.support.IngredientListItemClickHandler;
import org.pat.howell.tes.alchemyreference.data.AlchemyDataService;
import org.pat.howell.tes.alchemyreference.data.ContentConstants;
import org.pat.howell.tes.alchemyreference.data.entities.Ingredient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

/**
 * Activity containing a list of effects loaded from the database
 * for the user to search for
 */
public class EffectSearchActivity extends Activity {
	/** static instance of this class for use by handlers */
	private static EffectSearchActivity instance;
	/** Spinner containing list of available effects */
	private Spinner effectSpinner;
	/** Ingredients list to be populated with ingredients matching chosen effect */
	private ListView ingredientsList;
	/** Handler for responses loading effects from the database */
	private static Handler effectResponseHandler = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage( Message message  ) {
			ArrayList<String> response = (ArrayList<String>) message.obj;
			String[] stringEffects = new String[response.size()];
			stringEffects = response.toArray(stringEffects);
			instance.populateEffectSpinner( stringEffects );
		}
	};
	
	@Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        instance = this;
        setContentView( R.layout.effect_search_activity );
        ingredientsList = (ListView) findViewById( R.id.ingredients_with_choosen_effect );
        effectSpinner = (Spinner) findViewById( R.id.effect_choice_spinner );
        requestEffectData();
        setOnChildClickHandlerForIngredientsList();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }
    
    /**
     * Click handler for the back button
     * @param view - the clicked button
     */
    public void onBackClicked( View view ) {
    	finish();
    }
    
    private void populateEffectSpinner( String[] effects ) {
    	//android.R.layout.simple_spinner_item
		ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, 
																 R.layout.effect_spinner_item, 
																 effects );
		effectSpinner.setAdapter( adapter );
	}
    
    private void setOnChildClickHandlerForIngredientsList() {
    	ingredientsList.setOnItemClickListener( new IngredientListItemClickHandler( this, new IngredientListAdapter(getApplicationContext(), new Ingredient[0]) ) );
    }
    
    private void requestEffectData() {
    	Intent intent = new Intent( "tes.alchemyreference.DATABASESERVICE" );
    	intent.putExtra( AlchemyDataService.URI_KEY, ContentConstants.GET_ALL_EFFECTS_URI.toString() );
    	intent.putExtra( AlchemyDataService.MESSENGER_KEY, new Messenger( effectResponseHandler ) );
    	startService( intent );
    }
}
