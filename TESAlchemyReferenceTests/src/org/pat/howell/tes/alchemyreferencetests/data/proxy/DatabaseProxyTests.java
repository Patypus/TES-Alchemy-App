package org.pat.howell.tes.alchemyreferencetests.data.proxy;

import java.io.File;
import java.util.ArrayList;

import org.pat.howell.tes.alchemyreference.data.DatabaseConstants;
import org.pat.howell.tes.alchemyreference.data.entities.Ingredient;
import org.pat.howell.tes.alchemyreference.data.proxy.AlchemyDatabaseOpenHelper;
import org.pat.howell.tes.alchemyreference.data.proxy.DatabaseProxy;

import android.test.AndroidTestCase;

import junit.framework.TestCase;
/**
 * Unit tests for the database proxy to test the basic interaction with the database schema
 */
public class DatabaseProxyTests extends AndroidTestCase {

	private String _testDatabaseName = "testAlchemyDatabase";
	private AlchemyDatabaseOpenHelper testOpenHandler = new AlchemyDatabaseOpenHelper( getContext(), _testDatabaseName, 1 );
	private DatabaseProxy proxy;
	
	protected void setUp() throws Exception {
		super.setUp();
		removeTestDatabaseFile();
		proxy = new DatabaseProxy( testOpenHandler );
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
	
	public void testGetAllIngredientsReturnsExpectedCount() {
		//databse npt copied. need to call the copy.
		ArrayList<Ingredient> returnedIngredients = proxy.getAllIngredients();
		assertEquals( returnedIngredients.size(), 3 );
	}
	
	//TODO - test database operations with test database
	
}
