package org.pat.howell.tes.alchemyreference.activities;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.activities.support.IngredientListItemClickHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Activity for displaying information about a specific ingredient
 */
public class IngredientActivity extends Activity {
	/** The list of ingredients matching a chosen effect */
	private ListView matchingIngredients;
	
	@Override
    protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
        setContentView( R.layout.ingredient_activity );
        matchingIngredients = (ListView) findViewById( R.id.matching_ingredients_list );
        loadDummyData();
        setClickHandlerOnListOfIngredientsMatchingEffect();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }
    
    private void loadDummyData() {
    	String[] dummyData = getResources().getStringArray( R.array.ingredients_dummy_data );
    	matchingIngredients.setAdapter( new ArrayAdapter<String>( this, 
										android.R.layout.simple_list_item_1, 
										dummyData ) );
    }
    
    private void setClickHandlerOnListOfIngredientsMatchingEffect() {
    	//TODO - set new click handler for new custom adapter
    	matchingIngredients.setOnItemClickListener( new IngredientListItemClickHandler( this ) );
    }
    
    /**
	 * Click handler for the back button
	 */
	public void onBackClicked( View clickedButton ) {
		finish();
	}
}