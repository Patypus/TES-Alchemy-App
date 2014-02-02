package org.pat.howell.tes.alchemyreference.activities;

import org.pat.howell.tes.alchemyreference.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

/**
 * Activity containing a list of effects loaded from the database
 * for the user to search for
 */
public class EffectSearchActivity extends Activity {
	
	@Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView( R.layout.effect_search_activity );
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }
}
