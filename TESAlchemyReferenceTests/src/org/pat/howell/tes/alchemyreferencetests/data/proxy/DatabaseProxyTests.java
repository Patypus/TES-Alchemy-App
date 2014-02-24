package org.pat.howell.tes.alchemyreferencetests.data.proxy;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.pat.howell.tes.alchemyreference.data.DatabaseConstants;
import org.pat.howell.tes.alchemyreference.data.entities.Ingredient;
import org.pat.howell.tes.alchemyreference.data.proxy.AlchemyDatabaseOpenHelper;
import org.pat.howell.tes.alchemyreference.data.proxy.DatabaseProxy;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

import junit.framework.TestCase;
/**
 * Unit tests for the database proxy to test the basic interaction with the database schema
 */
public class DatabaseProxyTests extends AndroidTestCase {

	private String _testDatabaseName = "testAlchemyDatabase";
	private AlchemyDatabaseOpenHelper testOpenHandler;
	private DatabaseProxy proxy;
	
	protected void setUp() throws Exception {
		super.setUp();
		removeTestDatabaseFile();
		Context context = waitForContext();
		testOpenHandler = new AlchemyDatabaseOpenHelper( context, _testDatabaseName, 1 );
		proxy = new DatabaseProxy( testOpenHandler );
	}
	
	private Context waitForContext() {
		int wait = 0;
		Context context = null;
		while ( wait <= 30 ) {
			wait++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {}
			context = getContext();
			if (context != null) {
				wait = 50;
			}
		}
		return context;
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		removeTestDatabaseFile();
	}
	
	private void removeTestDatabaseFile() {
		File database = new File( "/data/data/org.pat.howell.tes.alchemyreference/databases/" + 
			      				  _testDatabaseName );
		database.delete();
	}
	
	private void ensureDatabaseIsCopied() {
		testOpenHandler.onCreate( null );
	}
	
	public void testGetAllIngredientsReturnsExpectedCount() {
		ensureDatabaseIsCopied();
		ArrayList<Ingredient> returnedIngredients = proxy.getAllIngredients();
		assertEquals( returnedIngredients.size(), 3 );
	}
	
	
	public void testGetAllEffectdReturnsExpectedItems() {
		ensureDatabaseIsCopied();
		ArrayList<String> returnedEffects = proxy.getAllEffects();
		Collections.sort( returnedEffects );
		ArrayList<String> sortedExpectedEffects = buildExpectedEffectsArray();
		compareArraysOfEffects( sortedExpectedEffects, returnedEffects );
	}
	
	private ArrayList<String> buildExpectedEffectsArray() {
		ArrayList<String> expected = 
					new ArrayList<String>( Arrays.asList( "Resist Magic", 
														  "Fortify Stamina",
														  "Ravage Magicka",
														  "Fortify Conjuration",
														  "Invisibility",
														  "Damage Stamina",
														  "Damage Health",
														  "Restore Magicka",
														  "Regenerate Health",
														  "Cure Disease"
										  ));
		 Collections.sort( expected );
		 return expected;
	}
	
	private void compareArraysOfEffects( ArrayList<String> expected, ArrayList<String> actual ) {
		for ( int i = 0; i < expected.size(); i++ ) {
			assertTrue( expected.get( i ).equals( actual.get( i ) ) );
		}
	}
	
	//TODO - test database operations with test database
	
}
