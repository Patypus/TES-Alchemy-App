package org.pat.howell.tes.alchemyreferencetests.activities;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.activities.IngredientSearchActivity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.ImageView;

/**
 * Unit tests for the IngredientSearchActivity
 */
public class IngredientSearchActivityTests extends ActivityUnitTestCase<IngredientSearchActivity> {

	/** Instance of the activity under test*/
	private IngredientSearchActivity instance;
	
	public IngredientSearchActivityTests()
	{
		super(IngredientSearchActivity.class);
	}
	
	@Override
	public void setUp () throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(), IngredientSearchActivity.class);
		startActivity(intent, null, null);
		instance = getActivity();
	}
	
	@MediumTest
	public void testBackButtonFinishesActivity() {
		ImageView backButton = (ImageView) instance.findViewById(R.id.ingredient_search_back_button);
		backButton.performClick();
		assertTrue(isFinishCalled());
	}
}
