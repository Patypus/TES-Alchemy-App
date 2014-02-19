package org.pat.howell.tes.alchemyreferencetests.activities;

import java.io.File;

import junit.framework.Assert;

import org.pat.howell.tes.alchemyreference.activities.MenuActivity;
import org.pat.howell.tes.alchemyreference.data.DatabaseConstants;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

import org.pat.howell.tes.alchemyreference.R;

/**
 * Unit tests for the MenuActivity
 */
public class MenuActivityTests extends ActivityUnitTestCase<MenuActivity> {

	/** Instance of the activity under test */
	private MenuActivity menuActivity;
	
	public MenuActivityTests() {
		super(MenuActivity.class);
	}

	@Override
	public void setUp () throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
		        MenuActivity.class);
		startActivity(intent, null, null);
		menuActivity = getActivity();
	}
	
	public void testSearchByEffectButtonIsPresentInActivity() {
		int effectButtonId = R.id.search_by_effect_button;
		assertNotNull(menuActivity.findViewById(effectButtonId));
	}
	
	public void testSearchByIngredientButtonIsPresentInActivity() {
		int ingredientButtonId = R.id.search_ingredient_button;
		assertNotNull(menuActivity.findViewById(ingredientButtonId));
	}
	
	public void testSearchByEffectButtonSendsCorrectIntent() {
		Button button = (Button) menuActivity.findViewById(R.id.search_by_effect_button);
		button.performClick();
		Intent triggeredIntent = getStartedActivityIntent();
		String actionText = triggeredIntent.getAction();
		Assert.assertEquals("tes.alchemyreference.EFFECTSEARCH", actionText);
	}
	
	public void testSearchForIngredientButtonSendsCorrectIntent() {
		Button button = (Button) menuActivity.findViewById(R.id.search_ingredient_button);
		button.performClick();
		Intent triggeredIntent = getStartedActivityIntent();
		String actionText = triggeredIntent.getAction(); 
		Assert.assertEquals("tes.alchemyreference.INGREDIENTSEARCH", actionText);
	}
	
	public void testCreatingMainActivityChecksDatabaseExistence() {
		removeDatabaseFile();
		getInstrumentation().callActivityOnCreate( menuActivity, null);
		try {
			Thread.sleep( 10000 );
		} catch( InterruptedException e ) {
		}
		File database = new File( "/data/data/org.pat.howell.tes.alchemyreference/databases/" + 
				  DatabaseConstants.DATABASE_NAME );
		assertTrue( database.exists() );
		removeDatabaseFile();
	}
	
	private void removeDatabaseFile() {
		File database = new File( "/data/data/org.pat.howell.tes.alchemyreference/databases/" + 
			      				  DatabaseConstants.DATABASE_NAME );
		database.delete();
	}
}