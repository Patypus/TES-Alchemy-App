package org.pat.howell.tes.alchemyreference.activities;

import org.pat.howell.tes.alchemyreference.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * Activity for displaying information about a specific ingredient
 */
public class IngredientActivity extends Activity {

	@Override
    protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
        setContentView( R.layout.ingredient_activity );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }
    
    /**
	 * Click handler for the back button
	 */
	public void onBackClicked( View clickedButton ) {
		finish();
	}
}