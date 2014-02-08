package org.pat.howell.tes.alchemyreference.activities.support;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class IngredientListItemClickHandler implements AdapterView.OnItemClickListener {

	private Activity _activityContext;
	
	public IngredientListItemClickHandler( Activity activityContext ) {
		_activityContext = activityContext;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View selectedItem, int arg2, long arg3) {
		TextView item = (TextView) selectedItem;
		String selectedItemName = item.getText().toString();
		Intent intent = new Intent("tes.alchemyreference.EFFECT");
		_activityContext.startActivity( intent );
	}

}
