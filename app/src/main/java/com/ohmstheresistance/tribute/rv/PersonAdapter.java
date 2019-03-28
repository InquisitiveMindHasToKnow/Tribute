package com.ohmstheresistance.tribute.rv;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.database.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonHolder>{
    private List<Person> personList = new ArrayList<>();

    private final LayoutInflater layoutInflater;
    private Context layoutContext;

    public PersonAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        layoutContext = context;

    }


    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_item, parent, false);
        return new PersonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder holder, int position) {
        Person currentPerson = personList.get(position);
        holder.personIDTextView.setText(String.valueOf(currentPerson.getPersonID()));
        holder.personNameTextView.setText(currentPerson.getPersonName());
        holder.personPhoneNumberTextView.setText(currentPerson.getPersonPhoneNumber());
        holder.personEmailTextview.setText(currentPerson.getPersonEmail());
    }

    @Override
    public int getItemCount() {

        return personList.size();
    }

    public void setPersons(List<Person> persons) {
        this.personList = persons;
        notifyDataSetChanged();
    }

    class PersonHolder extends RecyclerView.ViewHolder {
        private TextView personNameTextView;
        private TextView personPhoneNumberTextView;
        private TextView personEmailTextview;
        private TextView personIDTextView;

        public PersonHolder(View itemView) {
            super(itemView);
            personNameTextView = itemView.findViewById(R.id.person_name_textview);
            personPhoneNumberTextView = itemView.findViewById(R.id.person_phone_number_textview);
            personEmailTextview = itemView.findViewById(R.id.person_email_textview);
            personIDTextView = itemView.findViewById(R.id.person_idtext_view);
        }
    }
}