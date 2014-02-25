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
		int result = _manager.match( ContentConstants.GET_ALL_EFFECTS_URI );
		assertEquals( ContentConstants.ALL_EFFECTS, result );
	}

	public void testIngredientUriIsReolvedCorrectly() {
		int result = _manager.match( ContentConstants.GET_ALL_INGREDIENTS_URI );
		assertEquals( ContentConstants.ALL_INGREDIENTS, result );
	}
	
	public void testGetIngredientByNameUriIsResolvedCorrectly() {
		int result = _manager.match( ContentConstants.GET_INGREDIENTS_BY_NAME_URI );
		assertEquals( ContentConstants.INGREDIENT_BY_NAME, result );
	}
	
	public void testGetIngredientsWithEffectUriIsResolvedCorrectly() {
		int result = _manager.match( ContentConstants.GET_INGREDIENTS_WITH_EFFECT_URI );
		assertEquals( ContentConstants.INGREDIENTS_WITH_EFFECT, result );
	}
}