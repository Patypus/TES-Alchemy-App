package org.pat.howell.tes.alchemyreference.activities;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.R.layout;
import org.pat.howell.tes.alchemyreference.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;

/**
 * Main menu activity of the application and the first activity loaded.
 * This activity is also the one which all home buttons lead to.
 */
public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * Click handler for the search by effect button
     * @param view - The clicked button
     */
    public void onEffectClicked( View view ) {
    	Intent intent = new Intent( "tes.alchemyreference.EFFECTSEARCH" );
    	startActivity( intent );
    }
    
    /**
     * Click handler for the search by ingredient button
     * @param view - The clicked button
     */
    public void onIngredientClicked ( View view ) {
    	Intent intent = new Intent( "tes.alchemyreference.INGREDIENTSEARCH" );
    	startActivity( intent );
    }
}