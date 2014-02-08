package org.pat.howell.tes.alchemyreference.activities;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.activities.support.IngredientListItemClickHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

/**
 * Activity containing a list of effects loaded from the database
 * for the user to search for
 */
public class EffectSearchActivity extends Activity {

	/** Spinner containing list of available effects */
	private Spinner effectSpinner;
	
	/** Ingredients list to be populated with ingredients matching chosen effect */
	private ListView ingredientsList;
	
	@Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.effect_search_activity );
        ingredientsList = (ListView) findViewById( R.id.ingredients_with_choosen_effect );
        effectSpinner = (Spinner) findViewById( R.id.effect_choice_spinner );
        setDummyData();
        setOnChildClickHandlerForIngredientsList();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
    
    private void setDummyData() {
    	populateIngredientsList( getResources().getStringArray( R.array.ingredients_dummy_data ) );
    	populateEffectSpinner( getResources().getStringArray( R.array.effects_dummy_array ) );
    }
    
    private void populateIngredientsList( String[] ingredientNames ) {
    	ingredientsList.setAdapter( new ArrayAdapter<String>( this, 
				  					android.R.layout.simple_list_item_1, 
				  					ingredientNames ) );
    }
    
    private void populateEffectSpinner( String[] effects ) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, 
																 android.R.layout.simple_spinner_item, 
																 effects );
		effectSpinner.setAdapter( adapter );
	}
    
    private void setOnChildClickHandlerForIngredientsList() {
    	ingredientsList.setOnItemClickListener( new IngredientListItemClickHandler( this ) );
    }
}
