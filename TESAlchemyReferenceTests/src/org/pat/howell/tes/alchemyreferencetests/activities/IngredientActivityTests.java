package org.pat.howell.tes.alchemyreferencetests.activities;

import junit.framework.Assert;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.activities.IngredientActivity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Unit test class for the ingredient activity
 */
public class IngredientActivityTests extends ActivityUnitTestCase<IngredientActivity> {

	/** Instance of the IngredientActivity under test */
	private IngredientActivity instance;
	
	public IngredientActivityTests()
	{
		super( IngredientActivity.class );
	}
	
	@Override
	public void setUp () throws Exception {
		super.setUp();
		Intent intent = new Intent( getInstrumentation().getTargetContext(), IngredientActivity.class );
		startActivity( intent, null, null );
		instance = getActivity();
	}
	
	@MediumTest
	public void testBackButtonFinishesActivity()
	{
		ImageView backButton = (ImageView) instance.findViewById( R.id.ingredient_back_button );
		backButton.performClick();
		assertTrue( isFinishCalled() );
	}
	
	public void testClickOnIngredientItemDispatchesRequiredEvent() {
		getInstrumentation().callActivityOnCreate( instance, null );
		ListView ingredientList = (ListView) instance.findViewById( R.id.matching_ingredients_list );	
		ListAdapter adapter = ingredientList.getAdapter();
		View firstItem = adapter.getView( 0, null, null );
		ingredientList.performItemClick( firstItem, 0, adapter.getItemId(0) );
		Intent dispatedIntent = getStartedActivityIntent();
		Assert.assertEquals( "tes.alchemyreference.EFFECT", dispatedIntent.getAction() );
	}
}
