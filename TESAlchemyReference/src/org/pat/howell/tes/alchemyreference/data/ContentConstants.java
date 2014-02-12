package org.pat.howell.tes.alchemyreference.data;

import android.net.Uri;

/**
 * Constant keys used by the content provider AlchemyDataProvider and it's
 * UriManager in resolving requests
 */
public class ContentConstants {
	/** Name of the content provider */
	public static final String PROVIDER_NAME = "org.pat.howell.tes.alchemyreference.data.AlchemyDataProvider";
	/** String key for the effect table*/
	public static final String EFFECT_TABLE_KEY = "effect";
	/** String key for the ingredient table */
	public static final String INGREDIENT_TABLE_KEY = "ingredient";
	
	/** String structure of the Uri for the ingredient table */
	private static final String INGREDIENT_URL = "content://" + PROVIDER_NAME + "/" + INGREDIENT_TABLE_KEY;
	/** Uri to access the ingredient table */
	public static final Uri INGREDIENT_URI = Uri.parse( INGREDIENT_URL );
	
	/** String structure of the Uri for the effect table */
	private static final String EFFECT_URL = "content://" + PROVIDER_NAME + "/" + EFFECT_TABLE_KEY;
	/** Uri to access the effect table */
	public static final Uri EFFECT_URI = Uri.parse( EFFECT_URL );
	
	/** Key for all ingredients requests*/
	public static final int ALL_INGREDIENTS = 10;
	/** Key for all effects requests */
	public static final int ALL_EFFECTS = 20;
	/** Key for a query using a specific effect name */
	public static final int EFFECT_NAME = 30;
	/** Key for a query using a specific ingredient name */
	public static final int INGREDIENT_NAME = 40;
}