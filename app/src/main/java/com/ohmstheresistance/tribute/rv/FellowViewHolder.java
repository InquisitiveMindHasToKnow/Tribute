package com.ohmstheresistance.tribute.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.model.Fellows;

public class FellowViewHolder extends RecyclerView.ViewHolder {

    private TextView fellowNameTextView;

    public FellowViewHolder(@NonNull View itemView) {
        super(itemView);

        fellowNameTextView = itemView.findViewById(R.id.fellow_name_recycler_text);
    }

    public void onBind(final Fellows fellow) {

        fellowNameTextView.setText(fellow.getFellow());

    }
}