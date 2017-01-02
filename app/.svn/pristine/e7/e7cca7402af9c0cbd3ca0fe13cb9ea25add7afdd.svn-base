package com.agora.msg;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.entity.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {

	private TextView msg;
	private ImageView msgStatus;
	private ArrayList<Message> messages = new ArrayList<>();
	private LinearLayout wrapper;
	private RelativeLayout bubble;
	private final Context context;
	private final int rowResourceId;

	@Override
	public void add(Message object) {
		messages.add(object);
		super.add(object);
	}

	public MessageAdapter(Context context, int textViewResourceId, ArrayList<Message> messages) {
		super(context, textViewResourceId);
		this.context = context;
		this.rowResourceId = textViewResourceId;
		this.messages=messages;
	}

	public int getCount() {
		return messages.size();
	}

	public Message getItem(int index) {
		return messages.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(rowResourceId, parent, false);
		msg = (TextView) rowView.findViewById(R.id.comment);
		msgStatus = (ImageView) rowView.findViewById(R.id.iv_status);
		wrapper= (LinearLayout) rowView.findViewById(R.id.wrapper);
		bubble= (RelativeLayout) rowView.findViewById(R.id.bubble);
		msg.setText(getItem(position).getText());
		boolean isIncoming=getItem(position).getIncoming();
		if (isIncoming){
			msgStatus.setVisibility(View.GONE);
		}else {
			msgStatus.setAlpha(0.5f);
			if (AppConfig.MSG_READ.equals(getItem(position).getStatus())) {
				msgStatus.setImageResource(R.drawable.ic_drafts_black_18dp);
			} else if (AppConfig.MSG_RECEIVED.equals(getItem(position).getStatus())) {
				msgStatus.setImageResource(R.drawable.ic_markunread_black_18dp);
			}else{
				msgStatus.setImageResource(R.drawable.ic_schedule_black_18dp);
			}

		}
		bubble.setBackgroundResource(isIncoming ? R.drawable.bubble_in : R.drawable.bubble_out);
		wrapper.setGravity(isIncoming ? Gravity.LEFT : Gravity.RIGHT);
		return rowView;
	}

	public Bitmap decodeToBitmap(byte[] decodedByte) {
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}