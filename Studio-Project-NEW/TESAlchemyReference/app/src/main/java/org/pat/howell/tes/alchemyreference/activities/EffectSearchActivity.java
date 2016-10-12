package org.pat.howell.tes.alchemyreference.activities;

import java.util.ArrayList;
import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.activities.adapters.IngredientListAdapter;
import org.pat.howell.tes.alchemyreference.activities.support.IngredientListItemClickHandler;
import org.pat.howell.tes.alchemyreference.data.AlchemyDataService;
import org.pat.howell.tes.alchemyreference.data.ContentConstants;
import org.pat.howell.tes.alchemyreference.data.entities.Ingredient;
import android.widget.AdapterView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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
	/** Progress dialogue to cover the loading of monitors */
	private ProgressDialog progress;
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
	/** Handler for responses loading ingredients with an effect from the database */
	private static Handler ingredientResponseHandler = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage( Message message ) {
			ArrayList<Ingredient> response = (ArrayList<Ingredient>) message.obj;
			Ingredient[] ingredients = new Ingredient[response.size()];
			ingredients = response.toArray( ingredients );
			instance.populateIngredientsList( ingredients );
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
        setEffectSpinnerOnClickHandler();
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
		ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, 
																 R.layout.effect_spinner_item, 
																 effects );
		dismissProgress();
		effectSpinner.setAdapter( adapter );
	}
    
    private void setOnChildClickHandlerForIngredientsList( Ingredient[] ingredients ) {
    	ingredientsList.setOnItemClickListener( 
    			new IngredientListItemClickHandler( this, 
    					 						    new IngredientListAdapter( getApplicationContext(), ingredients ) ) 
    			);
    }
    
    private void requestEffectData() {
    	Intent intent = new Intent( "tes.alchemyreference.DATABASESERVICE" );
    	intent.putExtra( AlchemyDataService.URI_KEY, ContentConstants.GET_ALL_EFFECTS_URI.toString() );
    	intent.putExtra( AlchemyDataService.MESSENGER_KEY, new Messenger( effectResponseHandler ) );
    	showProgress();
    	startService( intent );
    }
    
    private void setEffectSpinnerOnClickHandler() {
    	effectSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
    		public void onItemSelected( AdapterView<?> parentView, View selectedItemView, int possition, long id ) {
    			String selectedEffect = ( (TextView) selectedItemView ).getText().toString();
    			requestIngredientsWithEffect( selectedEffect );
    		}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				Log.w( getClass().getName(), "Nothing selected in spinner");
			}
		} );
    }
    
    private void requestIngredientsWithEffect( String effectName ) {
    	Intent intent = new Intent( "tes.alchemyreference.DATABASESERVICE" );
    	intent.putExtra( AlchemyDataService.URI_KEY, ContentConstants.GET_INGREDIENTS_WITH_EFFECT_URI.toString() );
    	intent.putExtra( AlchemyDataService.EFFECT_KEY_NAME, effectName );
    	intent.putExtra( AlchemyDataService.MESSENGER_KEY, new Messenger( ingredientResponseHandler ) );
    	showProgress();
    	startService( intent );
    }
    
    private void populateIngredientsList( Ingredient[] ingredients ) {
    	IngredientListAdapter adapter = new IngredientListAdapter( getApplicationContext(), ingredients );
    	setOnChildClickHandlerForIngredientsList( ingredients );
    	ingredientsList.setAdapter( adapter );
    	dismissProgress();
    }
    
    /** Show a progress dialogue to tell the user that the monitors are being loaded */
    private void showProgress() {
    	String title = getResources().getString( R.string.loading_title );
    	String message = getResources().getString( R.string.loading_message );
    	progress = new ProgressDialog( this, AlertDialog.THEME_HOLO_DARK );
    	progress.setTitle( title );
    	progress.setMessage( message );
    	progress.show();
    }
    
    /** Remove the loading monitors progress dialogue */
    private void dismissProgress() {
    	if( progress != null ) {
    		progress.dismiss();
    	}
    }
}