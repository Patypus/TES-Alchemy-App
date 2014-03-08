package org.pat.howell.tes.alchemyreference.activities.support;

import org.pat.howell.tes.alchemyreference.R;
import org.pat.howell.tes.alchemyreference.activities.adapters.IngredientListAdapter;
import org.pat.howell.tes.alchemyreference.data.entities.Ingredient;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

public class IngredientListItemClickHandler implements AdapterView.OnItemClickListener {

	private Activity _activityContext;
	private IngredientListAdapter _adapter;
	
	public IngredientListItemClickHandler( Activity activityContext, IngredientListAdapter adapter ) {
		_activityContext = activityContext;
		_adapter = adapter;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View selectedItem, int arg2, long arg3) {
		int index = arg0.getSelectedItemPosition();
		Ingredient selectedIngredient = _adapter.getItem( index );
		String key = _activityContext.getString( R.string.ingredient_extra_key );
		Intent intent = new Intent("tes.alchemyreference.EFFECT");
		intent.putExtra(key, selectedIngredient );
		_activityContext.startActivity( intent );
	}
}