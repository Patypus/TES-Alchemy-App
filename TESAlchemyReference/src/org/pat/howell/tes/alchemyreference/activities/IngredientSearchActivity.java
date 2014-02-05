package org.pat.howell.tes.alchemyreference.activities;

import java.util.ArrayList;

import org.pat.howell.tes.alchemyreference.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Activity to display all ingredients from the database
 * to allow choosing from the list for more information on 
 * each ingredient.
 */
public class IngredientSearchActivity extends Activity {

	/** The list of ingredients for searching in the activity */
	private ListView ingredientsList;
	
	@Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView( R.layout.ingredient_search_activity );
        ingredientsList = (ListView) findViewById(R.id.ingredient_list );
        populateIngredientsList( getResources().getStringArray( R.array.ingredients_dummy_data ) );
        setOnItemClickHandlerForList();
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
	
	private void populateIngredientsList(String[] ingredients)
	{
		ingredientsList.setAdapter( new ArrayAdapter<String>( this, 
															  android.R.layout.simple_list_item_1, 
															  ingredients ) );
	}
	
	private void setOnItemClickHandlerForList()
	{
		ingredientsList.setOnItemClickListener( new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View selectedItem, int arg2, long arg3) {
				TextView item = (TextView) selectedItem;
				String selectedItemName = item.getText().toString();
				Intent intent = new Intent("thing");
			}
		});
	}
}
