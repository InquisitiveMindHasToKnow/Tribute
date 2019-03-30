package com.ohmstheresistance.tribute.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;

public class PersonViewHolder extends RecyclerView.ViewHolder {

    private TextView nameTextView;
    private TextView numberTextView;
    private TextView emailTextView;

    public PersonViewHolder(@NonNull View itemView) {
        super(itemView);

        nameTextView = itemView.findViewById(R.id.person_name_textview);
        numberTextView = itemView.findViewById(R.id.person_phone_number_textview);
        emailTextView = itemView.findViewById(R.id.person_email_textview);

    }


}
