package org.pat.howell.tes.alchemyreference.activities;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.data.loading.DatabaseLoadingService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

/**
 * Main menu activity of the application and the first activity loaded.
 * This activity is also the one which all home buttons lead to.
 */
public class MenuActivity extends Activity {
	private static MenuActivity instance;
	private static Handler databaseSetupHander = new Handler()
	{
		public void handleMessage( Message message  ) {
			if( message.arg1 != RESULT_OK ) {
				String toastMessage = "An error occured setting up the application database";
				Toast.makeText( instance.getApplication(), toastMessage, Toast.LENGTH_SHORT ).show();
			}
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        checkDatabaseStatus();
        instance = this;
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
    
    private void checkDatabaseStatus() {
    	Intent intent = new Intent( "tes.alchemyreference.DATABASELOADING" );
    	intent.putExtra( DatabaseLoadingService.MESSENGER_EXTRA,
    					 new Messenger( databaseSetupHander ) );
    	startService( intent );
    }
}