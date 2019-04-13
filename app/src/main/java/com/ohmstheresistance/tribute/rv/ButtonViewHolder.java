package com.ohmstheresistance.tribute.rv;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.activities.AboutTheCreator;
import com.ohmstheresistance.tribute.activities.CreateListActivity;
import com.ohmstheresistance.tribute.activities.ViewFellowListActivity;
import com.ohmstheresistance.tribute.model.Buttons;


public class ButtonViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private TextView buttonNameTextView;
    private Intent navigationIntent;
    private long lastButtonClickTime = 0;

    public ButtonViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();

        buttonNameTextView = itemView.findViewById(R.id.button_name_recycler_text);
    }

    public void onBind(final Buttons buttons){

        buttonNameTextView.setText(buttons.getButton());

        itemView.setOnClickListener(v -> {

            navigationIntent = new Intent();
            switch (getAdapterPosition()) {
                case 0:
                    if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                        return;
                    }
                    lastButtonClickTime = SystemClock.elapsedRealtime();
                    navigationIntent = new Intent(context, ViewFellowListActivity.class);
                    context.startActivity(navigationIntent);
                    break;

                case 1:
                    if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                        return;
                    }
                    lastButtonClickTime = SystemClock.elapsedRealtime();
                    navigationIntent = new Intent(context, CreateListActivity.class);
                    context.startActivity(navigationIntent);
                    break;

                case 2:
                    if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                        return;
                    }
                    lastButtonClickTime = SystemClock.elapsedRealtime();
                    navigationIntent = new Intent(context, AboutTheCreator.class);
                    context.startActivity(navigationIntent);
                    break;
            }

        });
    }
}
