package org.pat.howell.tes.alchemyreference.data.proxy;

import java.util.ArrayList;

import org.pat.howell.tes.alchemyreference.data.DatabaseConstants;
import org.pat.howell.tes.alchemyreference.data.entities.Ingredient;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseProxy {
	private AlchemyDatabaseOpenHelper _database;
	
	public DatabaseProxy ( AlchemyDatabaseOpenHelper openHandler ) {
		_database = openHandler;
	}
	
	public ArrayList<Ingredient> getAllIngredients() {
		SQLiteDatabase database = _database.open();
		Cursor queryResult = database.query( DatabaseConstants.INGREDIENTS_TABLE,
											 new String[] {
												DatabaseConstants.INGREDIENT_ID_COLUMN,
												DatabaseConstants.INGREDIENT_NAME_COLUMN,
												DatabaseConstants.EFFECT_ONE_COLUMN,
												DatabaseConstants.EFFECT_TWO_COLUMN,
												DatabaseConstants.EFFECT_THREE_COLUMN,
												DatabaseConstants.EFFECT_FOUR_COLUMN
											 },
											 null, null, null, null, null );
		ArrayList<Ingredient> allIngredients = formatIngredients( queryResult );
		database.close();
		return allIngredients;
				
	}
	
	public ArrayList<String> getAllEffects() {
		SQLiteDatabase database = _database.open();
		Cursor queryResult = database.query( DatabaseConstants.EFFECTS_TABLE,
											 new String[] { DatabaseConstants.EFFECT_TITLE_COLUMN },
											 null, null, null, null, null );
		ArrayList<String> allEffects = formatEffects( queryResult );
		database.close();
		return allEffects;
	}
	
	public Ingredient getIngredientByName( String name ) {
		SQLiteDatabase database = _database.open();
		Cursor queryResult = database.query( DatabaseConstants.INGREDIENTS_TABLE,
											 new String[] {
												DatabaseConstants.INGREDIENT_ID_COLUMN,
												DatabaseConstants.INGREDIENT_NAME_COLUMN,
												DatabaseConstants.EFFECT_ONE_COLUMN,
												DatabaseConstants.EFFECT_TWO_COLUMN,
												DatabaseConstants.EFFECT_THREE_COLUMN,
												DatabaseConstants.EFFECT_FOUR_COLUMN
											 },
											 DatabaseConstants.INGREDIENT_NAME_COLUMN + "=\"" + name + "\"", null, null, null, null );
		ArrayList<Ingredient> allMatchingIngredients = formatIngredients( queryResult );
		database.close();
		return allMatchingIngredients.get( 0 );
	}
	
	public ArrayList<Ingredient> getIngredientsWithEffect( String effectName ) {
		SQLiteDatabase database = _database.open();
		Cursor queryResult = database.query( DatabaseConstants.INGREDIENTS_TABLE,
											 new String[] {
												DatabaseConstants.INGREDIENT_ID_COLUMN,
												DatabaseConstants.INGREDIENT_NAME_COLUMN,
												DatabaseConstants.EFFECT_ONE_COLUMN,
												DatabaseConstants.EFFECT_TWO_COLUMN,
												DatabaseConstants.EFFECT_THREE_COLUMN,
												DatabaseConstants.EFFECT_FOUR_COLUMN
											 },
											 DatabaseConstants.EFFECT_ONE_COLUMN + "=\"" + effectName + "\" OR " +
											 DatabaseConstants.EFFECT_TWO_COLUMN + "=\"" + effectName + "\" OR " +
											 DatabaseConstants.EFFECT_THREE_COLUMN + "=\"" + effectName + "\" OR " +
											 DatabaseConstants.EFFECT_FOUR_COLUMN + "=\"" + effectName +"\"",
											 null, null, null, null );
		ArrayList<Ingredient> allMatchingIngredients = formatIngredients( queryResult );
		database.close();
		return allMatchingIngredients;
	}
	
	private ArrayList<String> formatEffects( Cursor effectsCursor ) {
		ArrayList<String> effects = new ArrayList<String>();
		for( effectsCursor.moveToFirst(); !effectsCursor.isAfterLast(); effectsCursor.moveToNext() ) {
			String effect = effectsCursor.getString( effectsCursor.getColumnIndex( DatabaseConstants.EFFECT_TITLE_COLUMN ) );
			effects.add( effect );
		}
		return effects;
	}
	
	private ArrayList<Ingredient> formatIngredients( Cursor ingredientsCursor ) {
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		for( ingredientsCursor.moveToFirst(); !ingredientsCursor.isAfterLast(); ingredientsCursor.moveToNext() ) {
			int id = ingredientsCursor.getInt( ingredientsCursor.getColumnIndex( DatabaseConstants.INGREDIENT_ID_COLUMN ) );
			String name = ingredientsCursor.getString( ingredientsCursor.getColumnIndex( DatabaseConstants.INGREDIENT_NAME_COLUMN ) );
			String effect1 = ingredientsCursor.getString( ingredientsCursor.getColumnIndex( DatabaseConstants.EFFECT_ONE_COLUMN ) );
			String effect2 = ingredientsCursor.getString( ingredientsCursor.getColumnIndex( DatabaseConstants.EFFECT_TWO_COLUMN ) );
			String effect3 = ingredientsCursor.getString( ingredientsCursor.getColumnIndex( DatabaseConstants.EFFECT_THREE_COLUMN ) );
			String effect4 = ingredientsCursor.getString( ingredientsCursor.getColumnIndex( DatabaseConstants.EFFECT_FOUR_COLUMN ) );
			ingredients.add( new Ingredient( id, name, effect1, effect2, effect3, effect4 ) );
		}
		return ingredients;
	}
}
