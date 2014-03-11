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
import android.widget.ListView;

/**
 * Activity for displaying information about a specific ingredient
 */
public class IngredientActivity extends Activity {
	/** The list of ingredients matching a chosen effect */
	private ListView matchingIngredients;
	private static IngredientActivity instance;
	
	
	@Override
    protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		instance = this;
        setContentView( R.layout.ingredient_activity );
        matchingIngredients = (ListView) findViewById( R.id.matching_ingredients_list );
        //TODO - fix
        //requestDataFromDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }
    
    
    
    
    
    private void setClickHandlerOnListOfIngredientsMatchingEffect( IngredientListAdapter adapter ) {
    	matchingIngredients.setOnItemClickListener( new IngredientListItemClickHandler( this, adapter ) );
    }
    
    /**
	 * Click handler for the back button
	 */
	public void onBackClicked( View clickedButton ) {
		finish();
	}
}