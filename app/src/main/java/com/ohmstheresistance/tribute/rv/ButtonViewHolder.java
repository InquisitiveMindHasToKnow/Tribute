package com.ohmstheresistance.tribute.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.model.Buttons;


public class ButtonViewHolder extends RecyclerView.ViewHolder {

    private TextView buttonNameTextView;

    public ButtonViewHolder(@NonNull View itemView) {
        super(itemView);

        buttonNameTextView = itemView.findViewById(R.id.button_name_recycler_text);
    }

    public void onBind(final Buttons buttons){

        buttonNameTextView.setText(buttons.getButton());
    }
}
