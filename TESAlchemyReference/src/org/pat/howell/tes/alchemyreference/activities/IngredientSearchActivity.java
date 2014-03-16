package org.pat.howell.tes.alchemyreference.activities;

import java.util.ArrayList;
import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.activities.adapters.IngredientListAdapter;
import org.pat.howell.tes.alchemyreference.activities.support.IngredientListItemClickHandler;
import org.pat.howell.tes.alchemyreference.data.AlchemyDataService;
import org.pat.howell.tes.alchemyreference.data.ContentConstants;
import org.pat.howell.tes.alchemyreference.data.entities.Ingredient;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

/**
 * Activity to display all ingredients from the database
 * to allow choosing from the list for more information on 
 * each ingredient.
 */
public class IngredientSearchActivity extends Activity {
	/** Instance of the activity for handler to reference */
	private static IngredientSearchActivity instance;
	/** The list of ingredients for searching in the activity */
	private ListView ingredientsList;
	/** Progress dialogue to cover the loading of monitors */
	private ProgressDialog progress;
	/** Handler for responses loading ingredients from the database */
	private static Handler ingredientResponseHandler = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage( Message message  ) {
			ArrayList<Ingredient> response = (ArrayList<Ingredient>) message.obj;
			Ingredient[] ingredients = new Ingredient[response.size()];
			ingredients = response.toArray( ingredients );
			instance.populateIngredients( ingredients );
		}
	};
	
	@Override
    protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		instance = this;
        setContentView( R.layout.ingredient_search_activity );
        ingredientsList = (ListView) findViewById( R.id.ingredient_list );
        requestDataFromDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }
    
    /**
	 * The button click handler for the back button
	 * @param view - The clicked button
	 */
	public void onBackClicked ( View view ) {
		finish();
	}
	
	/** Show a progress dialogue to tell the user that the monitors are being loaded */
    private void showProgress() {
    	String title = getResources().getString( R.string.loading_title );
    	String message = getResources().getString( R.string.loading_message );
    	progress = ProgressDialog.show( IngredientSearchActivity.this, title, message, true );
    }
    
    /** Remove the loading monitors progress dialogue */
    private void dismissProgress() {
    	if( progress != null ) {
    		progress.dismiss();
    	}
    }
	
	private void requestDataFromDatabase() {
    	Intent intent = new Intent( "tes.alchemyreference.DATABASESERVICE" );
    	intent.putExtra( AlchemyDataService.URI_KEY, ContentConstants.GET_ALL_INGREDIENTS_URI.toString() );
    	intent.putExtra( AlchemyDataService.MESSENGER_KEY, new Messenger( ingredientResponseHandler ) );
    	showProgress();
    	startService( intent );
    }
	
	private void populateIngredients( Ingredient[] ingredients ) {
    	IngredientListAdapter adapter = new IngredientListAdapter( getApplicationContext(), ingredients );
    	ingredientsList.setAdapter( adapter );
    	setClickHandlerOnListOfIngredients(adapter);
    	dismissProgress();
    }
	
	private void setClickHandlerOnListOfIngredients( IngredientListAdapter adapter ) {
		ingredientsList.setOnItemClickListener( new IngredientListItemClickHandler( this, adapter ) );
    }
}