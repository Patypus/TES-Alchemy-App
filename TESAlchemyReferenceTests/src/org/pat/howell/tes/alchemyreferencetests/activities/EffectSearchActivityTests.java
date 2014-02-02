package org.pat.howell.tes.alchemyreferencetests.activities;

import org.pat.howell.tes.alchemyreference.activities.EffectSearchActivity;
import android.content.Intent;
import android.test.ActivityUnitTestCase;

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

}