package org.pat.howell.tes.alchemyreference.data;

import android.net.Uri;

/**
 * Constant keys used by the content provider AlchemyDataProvider and it's
 * UriManager in resolving requests
 */
public class ContentConstants {
	/** Name of the content provider */
	public static final String PROVIDER_NAME = "org.pat.howell.tes.alchemyreference.data.AlchemyDataProvider";
	/** String key for getting all effects */
	public static final String ALL_EFFECTS_KEY = "effect";
	/** String key for getting all ingredients */
	public static final String ALL_INGREDIENTS_KEY = "ingredient";
	/** String key for getting ingredient by name */
	public static final String INGREDIENT_BY_NAME_KEY = ALL_INGREDIENTS_KEY + "byName";
	/** String key for getting ingredients with an effect */
	public static final String INGREDIENTS_WITH_EFFECT_KEY = ALL_INGREDIENTS_KEY + "withEffect";
	
	/** String structure of the Uri for the ingredient table */
	private static final String INGREDIENT_URL = "content://" + PROVIDER_NAME + "/" + ALL_INGREDIENTS_KEY;
	/** Uri to get all ingredients from the ingredient table */
	public static final Uri GET_ALL_INGREDIENTS_URI = Uri.parse( INGREDIENT_URL );
	
	/** String structure of the Uri for the effect table */
	private static final String EFFECT_URL = "content://" + PROVIDER_NAME + "/" + ALL_EFFECTS_KEY;
	/** Uri to get all effects from the effect table */
	public static final Uri GET_ALL_EFFECTS_URI = Uri.parse( EFFECT_URL );
	
	/** String structure of the Uri for getting ingredients by name */
	private static final String INGREDIENT_BY_NAME_URL = "content://" + PROVIDER_NAME + "/" + INGREDIENT_BY_NAME_KEY;
	/** Uri to get ingredient from the ingredient table which matches a given name */
	public static final Uri GET_INGREDIENTS_BY_NAME_URI = Uri.parse( INGREDIENT_BY_NAME_URL );
	
	/** String structure of the Uri for getting ingredients by name */
	private static final String INGREDIENT_WITH_EFFECT_URL = "content://" + PROVIDER_NAME + "/" + INGREDIENTS_WITH_EFFECT_KEY;
	/** Uri to get ingredient from the ingredient table which matches a given name */
	public static final Uri GET_INGREDIENTS_WITH_EFFECT_URI = Uri.parse( INGREDIENT_WITH_EFFECT_URL );
	
	/** Key for all ingredients requests*/
	public static final int ALL_INGREDIENTS = 10;
	/** Key for all effects requests */
	public static final int ALL_EFFECTS = 20;
	/** Key for getting ingredient by name */
	public static final int INGREDIENT_BY_NAME = 30;
	/** Key for getting all ingredients with effects */
	public static final int INGREDIENTS_WITH_EFFECT = 40;
}