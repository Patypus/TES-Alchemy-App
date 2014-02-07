package org.pat.howell.tes.alchemyreferencetests.activities;

import junit.framework.Assert;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.activities.IngredientSearchActivity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Unit tests for the IngredientSearchActivity
 */
public class IngredientSearchActivityTests extends ActivityUnitTestCase<IngredientSearchActivity> {

	/** Instance of the activity under test*/
	private IngredientSearchActivity instance;
	
	public IngredientSearchActivityTests()
	{
		super( IngredientSearchActivity.class );
	}
	
	@Override
	public void setUp () throws Exception {
		super.setUp();
		Intent intent = new Intent( getInstrumentation().getTargetContext(), IngredientSearchActivity.class );
		startActivity( intent, null, null );
		instance = getActivity();
	}
	
	public void testClicksOnListItemDisparchEffectIntent() {
		getInstrumentation().callActivityOnCreate( instance, null );
		ListView ingredientList = (ListView) instance.findViewById( R.id.ingredient_list );	
		ListAdapter adapter = ingredientList.getAdapter();
		View firstItem = adapter.getView( 0, null, null );
		ingredientList.performItemClick( firstItem, 0, adapter.getItemId(0) );
		Intent dispatedIntent = getStartedActivityIntent();
		Assert.assertEquals( "tes.alchemyreference.EFFECT", dispatedIntent.getAction() );
	}
	
	@MediumTest
	public void testBackButtonFinishesActivity() {
		ImageView backButton = (ImageView) instance.findViewById( R.id.ingredient_search_back_button );
		backButton.performClick();
		assertTrue( isFinishCalled() );
	}
}
