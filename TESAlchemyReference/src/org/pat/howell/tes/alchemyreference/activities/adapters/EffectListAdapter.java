package org.pat.howell.tes.alchemyreference.activities.adapters;

import org.pat.howell.tes.alchemyreference.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EffectListAdapter extends ArrayAdapter<String> {
	/** Array of displayed effects */
	private String[] _effects;
	/** Inflater for list item views */
	private LayoutInflater _inflator; 
	
	public EffectListAdapter( Context context, String[] effects ) {
		super( context, R.layout.ingredient_list_item, effects );
		_effects = effects;
		_inflator = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
	}
	
	@Override
	public View getView( int position, View convertView, ViewGroup parent ) {
		View view = convertView;
		if ( view == null ) {
			view = _inflator.inflate( R.layout.effect_list_item, null );
		}
		String effect = _effects[position];
		if ( effect != null ) {
			TextView nameView = (TextView) view.findViewById( R.id.effect_list_item_name_field );
			nameView.setText( effect );
		}
		return view;
	}
	
	@Override
	public String getItem( int index ) {
		return _effects[index];
	}
}
