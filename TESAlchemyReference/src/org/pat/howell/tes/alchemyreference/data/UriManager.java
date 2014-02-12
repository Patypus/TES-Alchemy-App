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
						 ContentConstants.EFFECT_TABLE_KEY,
						 ContentConstants.ALL_EFFECTS );
	}
	
	public int match( Uri uri ) {
		return _matcher.match( uri );
	}
}
