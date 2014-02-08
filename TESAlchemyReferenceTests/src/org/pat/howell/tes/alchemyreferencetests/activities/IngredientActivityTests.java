package org.pat.howell.tes.alchemyreferencetests.activities;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.activities.IngredientActivity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.ImageView;

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
}
