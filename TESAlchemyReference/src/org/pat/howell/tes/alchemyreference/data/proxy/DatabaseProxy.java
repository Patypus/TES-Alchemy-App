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
	
	public Cursor getAllEffects( String[] columnNames, String sort ) {
		return null;
	}
	
	public Cursor getIngredientsWithEffect( String[] columnNames, String where, String[] values, String sort ) {
		return null;
	}
	
	private ArrayList<String> formatEffects( Cursor effectsCursor ) {
		ArrayList<String> effects = new ArrayList<String>();
		for( effectsCursor.moveToFirst(); !effectsCursor.isAfterLast(); effectsCursor.moveToNext() ) {
			//TODO - format effects from cursor
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
