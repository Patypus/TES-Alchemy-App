package org.pat.howell.tes.alchemyreference.activities;

import java.util.ArrayList;
import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.activities.adapters.EffectListAdapter;
import org.pat.howell.tes.alchemyreference.activities.adapters.IngredientListAdapter;
import org.pat.howell.tes.alchemyreference.activities.support.IngredientListItemClickHandler;
import org.pat.howell.tes.alchemyreference.data.AlchemyDataService;
import org.pat.howell.tes.alchemyreference.data.ContentConstants;
import org.pat.howell.tes.alchemyreference.data.entities.Ingredient;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Activity for displaying information about a specific ingredient
 */
public class IngredientActivity extends Activity {
	/** The list of ingredients matching a chosen effect */
	private ListView matchingIngredients;
	/** The list of the ingredient's effects */
	private ListView ingredientsEffects;
	/** The TextView for displaying the selected effect */
	private TextView selectedEffectDisplay;
	private static IngredientActivity instance;
	/** The ingredient that is being displayed in this activity */
	private Ingredient displayedIngredient;
	/** Progress dialogue to cover the loading of monitors */
	private ProgressDialog progress;
	/** Handler for responses loading ingredients with an effect from the database */
	private static Handler ingredientResponseHandler = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage( Message message ) {
			ArrayList<Ingredient> response = (ArrayList<Ingredient>) message.obj;
			Ingredient[] ingredients = new Ingredient[response.size()];
			ingredients = response.toArray( ingredients );
			instance.populateMatchingIngredientsList( ingredients );
		}
	};
	
	@Override
    protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		instance = this;
		displayedIngredient = getIntent().getParcelableExtra( getResources().getString( R.string.ingredient_extra_key ) );
        setContentView( R.layout.ingredient_activity );
        matchingIngredients = (ListView) findViewById( R.id.matching_ingredients_list );
        ingredientsEffects = (ListView) findViewById( R.id.effects_of_ingredient_list );
        selectedEffectDisplay = (TextView) findViewById( R.id.choosen_effect_display );
        setComponentsWithActivityDetails();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }
    
    private void setComponentsWithActivityDetails() {
    	TextView title = (TextView) findViewById( R.id.ingredient_title );
    	title.setText( displayedIngredient.getName() );
    	String[] effects = new String[] 
    							{ 
    								displayedIngredient.getEffectOne(),
    								displayedIngredient.getEffectTwo(),
    								displayedIngredient.getEffectThree(),
    								displayedIngredient.getEffectFour()
								};
    	ArrayAdapter<String> adapter = new EffectListAdapter( this, effects );
    	ingredientsEffects.setAdapter( adapter );
    	setEffectsListClickHandler( adapter );
    	dismissProgress();
    }
    
    private void setEffectsListClickHandler( final ArrayAdapter<String> effects ) {
    	ingredientsEffects.setOnItemClickListener( new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick( AdapterView<?> parent, View slectedItem, int position, long id ) {
				String selectedEffect = effects.getItem( position );
				selectedEffectDisplay.setText( selectedEffect );
				requestMatchingIngredientsFromDatabase( selectedEffect );
			}
    	});
    }
    
    private void requestMatchingIngredientsFromDatabase(String effectName) {
    	Intent intent = new Intent( this, AlchemyDataService.class );
    	intent.putExtra( AlchemyDataService.URI_KEY, ContentConstants.GET_INGREDIENTS_WITH_EFFECT_URI.toString() );
    	intent.putExtra( AlchemyDataService.EFFECT_KEY_NAME, effectName );
    	intent.putExtra( AlchemyDataService.MESSENGER_KEY, new Messenger( ingredientResponseHandler ) );
    	showProgress();
    	startService( intent );
    }
    
    private void populateMatchingIngredientsList( Ingredient[] ingredients ) {
    	IngredientListAdapter adapter = new IngredientListAdapter( getApplicationContext(), ingredients );
    	setOnChildClickHandlerForIngredientsList( adapter );
    	matchingIngredients.setAdapter( adapter );
    	dismissProgress();
    }
    
    private void setOnChildClickHandlerForIngredientsList( IngredientListAdapter adapter ) {
    	matchingIngredients.setOnItemClickListener( new IngredientListItemClickHandler( this, adapter ) );
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
    
    /**
	 * Click handler for the back button
	 */
	public void onBackClicked( View clickedButton ) {
		finish();
	}
}