package com.agora.listener;

import android.view.View;

/**
 * Interface for the touch events in each item
 */
public interface OnItemTouchListener {
    /**
     * Callback invoked when the user Taps one of the RecyclerView items
     *
     * @param view     the CardView touched
     * @param position the index of the item touched in the RecyclerView
     * @param action    action
     */
    public void onCardViewTap(View view, int position, int action);

    /**
     * Callback invoked when the Button1 of an item is touched
     *
     * @param view     the Button touched
     * @param position the index of the item touched in the RecyclerView
     */
    public void onButton1Click(View view, int position);

    /**
     * Callback invoked when the Button2 of an item is touched
     *
     * @param view     the Button touched
     * @param position the index of the item touched in the RecyclerView
     */
    public void onButton2Click(View view, int position);
}
