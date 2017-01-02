package com.agora.main.drawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agora.R;


public class DrawerItemAdapter extends ArrayAdapter<DrawerItem> {

	private final Context context;
	private final int rowResourceId;
	private DrawerItem[] items;
	private SharedPreferences settings;

	public void setItems(DrawerItem[] i) {
		this.items = i;
	}

	public DrawerItemAdapter(Context context, int textViewResourceId, DrawerItem[] items) {
		super(context, textViewResourceId, items);
		this.context = context;
		this.rowResourceId = textViewResourceId;
		this.items = items;
		settings=  PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(rowResourceId, parent, false);
		ViewGroup.LayoutParams param2;

		DrawerItem item = items[position];
		TextView textView = (TextView) rowView.findViewById(R.id.drawer_item_text);
		TextView text2 = (TextView) rowView.findViewById(R.id.drawer_item_text2);

		if (item.getName()!=-1) {
			textView.setText(context.getResources().getString(item.getName()));
			textView.setVisibility(View.VISIBLE);
		}

		if (item.getText2()!=-1) {
			text2.setText(context.getResources().getString(item.getText2()));
			text2.setVisibility(View.VISIBLE);
		}

		ImageView image = (ImageView) rowView.findViewById(R.id.drawer_item_img);
		if (item.getImage()!=-1) {
			image.setImageResource(item.getImage());
			image.setVisibility(View.VISIBLE);
		}

		ImageView icon = (ImageView) rowView.findViewById(R.id.drawer_item_icon);
		if (item.getIcon()!=-1) {
			icon.setImageResource(item.getIcon());
			icon.setVisibility(View.VISIBLE);
		}

		ImageView image2 = (ImageView) rowView.findViewById(R.id.drawer_item_img2);
		if (item.getImage2()!=-1) {
			image2.setImageResource(item.getImage2());
			image2.setVisibility(View.VISIBLE);
		}

		switch (position){
			case 0:
				rowView.setBackgroundColor(context.getResources().getColor(R.color.primary));
				textView.setTextColor(context.getResources().getColor(R.color.white));
				ViewGroup.LayoutParams param = rowView.getLayoutParams();
				param.height=context.getResources().getDimensionPixelSize(R.dimen.drawer_header_height);
				image.setBackgroundColor(rowView.getResources().getColor(R.color.primary));
				textView.setText(null);
				break;
			case 1:

				String name= settings.getString("userName","");
				String lastName=settings.getString("lastName","");
				textView.setText (name+" "+ lastName);
				rowView.setBackgroundColor(context.getResources().getColor(R.color.primary));
				textView.setTextColor(context.getResources().getColor(R.color.white));
				text2.setTextColor(context.getResources().getColor(R.color.white));
				textView.setTextSize(context.getResources().getDimension(
						R.dimen.drawer_text_item_size_10));
			//	param2 = rowView.getLayoutParams();
			//	param2.height=context.getResources().getDimensionPixelSize(R.dimen.drawer_title_height);
				break;
			case 3:
				rowView.setBackgroundColor(context.getResources().getColor(R.color.primary));
				textView.setTextColor(context.getResources().getColor(R.color.white));
				param2 = rowView.getLayoutParams();
				param2.height=context.getResources().getDimensionPixelSize(R.dimen.drawer_title_height);
				break;
			default:

				textView.setTextSize(context.getResources().getDimension(
						R.dimen.drawer_text_item_size_8));

		}
		return rowView;

	}

}
