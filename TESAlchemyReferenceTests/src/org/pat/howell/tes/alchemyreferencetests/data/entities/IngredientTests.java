package org.pat.howell.tes.alchemyreferencetests.data.entities;

import java.util.ArrayList;
import org.junit.Test;
import org.pat.howell.tes.alchemyreference.data.entities.Ingredient;
import android.os.Parcel;
import junit.framework.TestCase;

public class IngredientTests extends TestCase {
	
	public void testIngredientEquality() {
		Ingredient testIngredient1 = createTestIngredient();
		Ingredient testIngredient2 = createTestIngredient();
		assertTrue( testIngredient1.equals( testIngredient2 ) );
	}
	
	public void testIngredientEqualsRejectsOtherObjects() {
		Ingredient testIngredient = createTestIngredient();
		assertFalse( testIngredient.equals( new ArrayList<String>() ) );
	}
	
	public void testIngredientEqualsFailsDifferentIngredint() {
		Ingredient testIngredient1 = createTestIngredient();
		Ingredient testIngredient2 = createOtherTestIngredient();
		assertFalse( testIngredient1.equals( testIngredient2 ) );
	}
	
	public void testIngredientParcelRoundTrip()
	{
		Ingredient testIngredient = createTestIngredient();
		Parcel parcel = Parcel.obtain();
		testIngredient.writeToParcel( parcel, 0 );
		parcel.setDataPosition( 0 );
		Ingredient afterParcelling = Ingredient.CREATOR.createFromParcel( parcel );
		assertTrue( testIngredient.equals( afterParcelling ) );
	}
	
	private Ingredient createTestIngredient() {
		return new Ingredient( 0,
							   "testroot",
							   "fortify test",
							   "restore faith",
							   "buff confidence",
							   "increase coverage" );
	}
	
	private Ingredient createOtherTestIngredient() {
		return new Ingredient( 12,
				   			   "fake leaf",
				   			   "fortify misery",
				   			   "restore confusion",
				   			   "buff annoyance",
				   			   "increase coverage" );
	}
}