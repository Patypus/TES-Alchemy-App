package org.pat.howell.tes.alchemyreference.data;

/**
 * Static strings for the database
 */
public class DatabaseConstants {
	/** Name of the database file */
	public static final String DATABASE_NAME = "alchemyDatabase";
	/** Int database version */
	public static final int DATABASE_VERSION = 1;
	
	/** Strings relating to the Ingredients table */
	/** Table name */
	public static final String INGREDIENTS_TABLE = "Ingredients";
    /** Column titles*/
    public static final String INGREDIENT_ID_COLUMN = "IngredientId";
    public static final String INGREDIENT_NAME_COLUMN = "IngredientName";
    public static final String EFFECT_ONE_COLUMN = "EffectOne";
    public static final String EFFECT_TWO_COLUMN = "EffectTwo";
    public static final String EFFECT_THREE_COLUMN = "EffectThree";
    public static final String EFFECT_FOUR_COLUMN = "EffectFour";
    
    /** Strings relating to the Effects table */
    /** Table name */
    public static final String EFFECTS_TABLE = "Effects";
    /** Column titles */ 
    public static final String EFFECT_ID_COLUMN = "EffectId";
    public static final String EFFECT_TITLE_COLUMN = "EffectTitle";
}
