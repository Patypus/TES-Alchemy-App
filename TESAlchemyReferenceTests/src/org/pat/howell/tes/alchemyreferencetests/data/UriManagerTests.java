package org.pat.howell.tes.alchemyreferencetests.data;

import org.pat.howell.tes.alchemyreference.data.ContentConstants;
import org.pat.howell.tes.alchemyreference.data.UriManager;

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
		assertEquals( ContentConstants.ALL_EFFECTS, result );
	}
}