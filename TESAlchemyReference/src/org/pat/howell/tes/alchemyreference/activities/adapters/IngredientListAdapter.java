package org.pat.howell.tes.alchemyreference.activities.adapters;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.data.entities.Ingredient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class IngredientListAdapter extends ArrayAdapter<Ingredient> {
	/** Array of displayed ingredients */
	private Ingredient[] _ingredients;
	/** Inflater for list item views */
	private LayoutInflater _inflator;
	
	public IngredientListAdapter( Context context, Ingredient[] ingredients ) {
		super( context, R.layout.ingredient_list_item, ingredients );
		_ingredients = ingredients;
		_inflator = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
	}
	
	@Override
	public View getView( int position, View convertView, ViewGroup parent ) {
		View view = convertView;
		if ( view == null ) {
			view = _inflator.inflate( R.layout.ingredient_list_item, null );
		}
		Ingredient ingredient = _ingredients[position];
		if ( ingredient != null ) {
			TextView nameView = (TextView) view.findViewById( R.id.ingredient_list_item_name_field );
			nameView.setText( ingredient.getName() );
		}
		return view;
	}
	
	@Override
	public Ingredient getItem( int index ) {
		return _ingredients[index];
	}
}
