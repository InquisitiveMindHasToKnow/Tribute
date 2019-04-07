package com.ohmstheresistance.tribute.rv;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.database.Person;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.getInstance;


public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private List<Person> personList = new ArrayList<>();
    private OnItemClickListener itemClickListener;

    private final LayoutInflater layoutInflater;
    private Context layoutContext;

    private Calendar calendar = getInstance();


    public PersonAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        layoutContext = context;

    }


    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_item, parent, false);
        return new PersonViewHolder(itemView, calendar);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person currentPerson = personList.get(position);

        holder.personNameTextView.setText(currentPerson.getPersonName());
        holder.personPhoneNumberTextView.setText(currentPerson.getPersonPhoneNumber());
        holder.personEmailTextview.setText(currentPerson.getPersonEmail());
        holder.timeAndDateMade.setText(currentPerson.getDateTime());

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void setPersons(List<Person> persons) {
        this.personList = persons;
        notifyDataSetChanged();
    }

    public void deletePerson(int position) {
        personList.remove(position);
        notifyDataSetChanged();
    }

    public Person getPersonAtPosition(int position) {
        return personList.get(position);
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {
        private TextView personNameTextView;
        private TextView personPhoneNumberTextView;
        private TextView personEmailTextview;
        private TextView timeAndDateMade;

        private long lastButtonClickTime = 0;


        @SuppressLint("StringFormatMatches")
        public PersonViewHolder(View itemView, Calendar calendar) {
            super(itemView);

            personNameTextView = itemView.findViewById(R.id.person_name_textview);
            personPhoneNumberTextView = itemView.findViewById(R.id.person_phone_number_textview);
            personEmailTextview = itemView.findViewById(R.id.person_email_textview);
            timeAndDateMade = itemView.findViewById(R.id.date_made_textview);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SystemClock.elapsedRealtime() - lastButtonClickTime < 1000) {
                        return;
                    }
                    lastButtonClickTime = SystemClock.elapsedRealtime();
                    int position = getAdapterPosition();
                    if (itemClickListener != null && position != RecyclerView.NO_POSITION) {
                        itemClickListener.onItemClicked(personList.get(position));
                    }
                }
            });
        }

    }


    public interface OnItemClickListener {
        void onItemClicked(Person person);
    }

    public void SetItemClickListener(PersonAdapter.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }

    public void setData(List<Person> newPersonList) {
        this.personList = newPersonList;
        notifyDataSetChanged();
    }
}