package org.pat.howell.tes.alchemyreference.data;

import android.content.UriMatcher;
import android.net.Uri;

/**
 * Class to manage the Uri's used by the content provider
 */
public class UriManager {

	private UriMatcher _matcher;
	
	public UriManager()
	{
		_matcher = new UriMatcher( UriMatcher.NO_MATCH );
		_matcher.addURI( ContentConstants.PROVIDER_NAME,
						 ContentConstants.ALL_EFFECTS_KEY,
						 ContentConstants.ALL_EFFECTS );
		_matcher.addURI( ContentConstants.PROVIDER_NAME,
					     ContentConstants.ALL_INGREDIENTS_KEY,
					     ContentConstants.ALL_INGREDIENTS );
		_matcher.addURI( ContentConstants.PROVIDER_NAME,
			     		 ContentConstants.INGREDIENT_BY_NAME_KEY,
			     		 ContentConstants.INGREDIENT_BY_NAME );
		_matcher.addURI( ContentConstants.PROVIDER_NAME,
			     	 	 ContentConstants.INGREDIENTS_WITH_EFFECT_KEY,
			     	 	 ContentConstants.INGREDIENTS_WITH_EFFECT );
	}
	
	/**
	 * Method to match Uri's know to the system to their action keys. Throws an illegal 
	 * argument exception if the URI is not recognised.
	 * @param uri - The Uri to match
	 * @return - int action key that the Uri corresponds to
	 */
	public int match( Uri uri ) {
		return _matcher.match( uri );
	}
}
