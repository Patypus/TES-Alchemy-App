package org.pat.howell.tes.alchemyreferencetests.data;

import org.pat.howell.tes.alchemyreference.data.AlchemyDataProvider;
import org.pat.howell.tes.alchemyreference.data.ContentConstants;

import android.test.ProviderTestCase2;

/**
 * Unit tests for the AlchemyDataProvider class
 */
public class AlchemyDataProviderTest extends ProviderTestCase2<AlchemyDataProvider> {

	/** The instance of AlchemyDataProvider under test */
	private AlchemyDataProvider instance;
	
	public AlchemyDataProviderTest() {
		super(AlchemyDataProvider.class, null);
		instance = new AlchemyDataProvider();
	}
	
	public void testCallToDeleteThrowsUnsupportedOperationExcption() {
		UnsupportedOperationException expecetedException = null;
		try {
			instance.delete( ContentConstants.INGREDIENT_URI,
							 "Where",
							 null );
		} catch ( UnsupportedOperationException exception ) {
			expecetedException = exception;
		}
		assertNotNull( expecetedException );
	}
	
	public void testCallToInsertThrowsUnsupportedOperationException() {
		UnsupportedOperationException expecetedException = null;
		try {
			instance.insert( ContentConstants.INGREDIENT_URI,
							 null );
		} catch ( UnsupportedOperationException exception ) {
			expecetedException = exception;
		}
		assertNotNull( expecetedException );
	}
	
	public void testCallToUpdateThrowsUnsupportedOperationException() {
		UnsupportedOperationException expecetedException = null;
		try {
			instance.update( ContentConstants.INGREDIENT_URI,
							 null,
							 null,
							 null );
		} catch ( UnsupportedOperationException exception ) {
			expecetedException = exception;
		}
		assertNotNull( expecetedException );
	}
}