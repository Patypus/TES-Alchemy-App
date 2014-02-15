package org.pat.howell.tes.alchemyreference.data;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

/**
 * Content provider for the alchemy database in the application
 * Used to control access to the database.
 */
public class AlchemyDataProvider extends ContentProvider {

	private UriManager _uriManager;
	
	@Override
	public boolean onCreate() {
		_uriManager = new UriManager();
		return false;
	}
	
	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cursor query( Uri uri, String[] columnNames, String where, String[] values, String sort ) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		throw new UnsupportedOperationException( "Insert is not supported on this database" );
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		throw new UnsupportedOperationException( "Update is not supported on this database" );
	}
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		throw new UnsupportedOperationException( "Delete is not supported on this database" );
	}
}