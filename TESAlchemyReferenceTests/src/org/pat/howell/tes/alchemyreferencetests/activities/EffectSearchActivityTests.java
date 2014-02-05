package org.pat.howell.tes.alchemyreferencetests.activities;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.activities.EffectSearchActivity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Unit tests for the EffectSearchActivity
 */
public class EffectSearchActivityTests extends ActivityUnitTestCase<EffectSearchActivity> {

	/** Instance of the activity under test */
	private EffectSearchActivity effectSearchActivity;
	
	public EffectSearchActivityTests() {
		super(EffectSearchActivity.class);
	}
	
	@Override
	public void setUp () throws Exception {
		super.setUp();
		Intent intent = new Intent(getInstrumentation().getTargetContext(), EffectSearchActivity.class);
		startActivity(intent, null, null);
		effectSearchActivity = getActivity();
	}

	@MediumTest
	public void testBackButtonFinishesActivity() {
		ImageView backButton = (ImageView) effectSearchActivity.findViewById(R.id.effect_search_back_button);
		backButton.performClick();
		assertTrue(isFinishCalled());
	}
}