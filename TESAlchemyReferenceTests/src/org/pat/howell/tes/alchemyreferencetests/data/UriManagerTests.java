package org.pat.howell.tes.alchemyreferencetests.data;

import org.pat.howell.tes.alchemyreference.data.ContentConstants;
import org.pat.howell.tes.alchemyreference.data.UriManager;

import android.net.Uri;
import junit.framework.TestCase;

/**
 * Unit tests for the UriManager class
 */
public class UriManagerTests extends TestCase {

	private UriManager _manager;
	
	protected void setUp() throws Exception {
		_manager = new UriManager();
		super.setUp();
	}

	public void testEffectUriIsResolvedCorrectly() {
		int result = _manager.match( ContentConstants.EFFECT_URI );
		assertEquals( ContentConstants.EFFECTS, result );
	}

	public void testIngredientUriIsReolvedCorrectly() {
		int result = _manager.match( ContentConstants.INGREDIENT_URI );
		assertEquals( ContentConstants.INGREDIENTS, result );
	}
	
	public void testUnknownUriIsNotResolved() {
		IllegalArgumentException expectedException = null;
		try {
			_manager.match( Uri.parse( "content://" + ContentConstants.PROVIDER_NAME + "/" + "NeedleNardleNoo" ) );
		} catch (IllegalArgumentException exception) {
			expectedException = exception;
		}
		assertNotNull( expectedException );
	}
}
