package com.ohmstheresistance.tribute.rv;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.database.Person;


public class PersonAdapter extends ListAdapter<Person, PersonAdapter.PersonViewHolder> {
    private OnItemClickListener itemClickListener;

    public PersonAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Person> DIFF_CALLBACK = new DiffUtil.ItemCallback<Person>() {
        @Override
        public boolean areItemsTheSame(@NonNull Person currentPerson, @NonNull Person newPerson) {
            return currentPerson.getPersonID() == newPerson.getPersonID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Person currentPerson, @NonNull Person newPerson) {
            return currentPerson.getPersonName().equals(newPerson.getPersonName()) && currentPerson.getPersonPhoneNumber().equals(newPerson.getPersonPhoneNumber())
                    && currentPerson.getPersonEmail().equals(newPerson.getPersonEmail()) && currentPerson.getPersonNotes().equals(newPerson.getPersonNotes());
        }
    };

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person currentPerson = getItem(position);

        holder.personNameTextView.setText(currentPerson.getPersonName());
        holder.personPhoneNumberTextView.setText(currentPerson.getPersonPhoneNumber());
        holder.personEmailTextView.setText(currentPerson.getPersonEmail());
        holder.personNotesTextView.setText(currentPerson.getPersonNotes());

    }

    public Person getPersonAtPosition(int position) {

        return getItem(position);
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {
        private TextView personNameTextView;
        private TextView personPhoneNumberTextView;
        private TextView personEmailTextView;
        private TextView personNotesTextView;


        private long lastButtonClickTime = 0;


        @SuppressLint("StringFormatMatches")
        public PersonViewHolder(View itemView) {
            super(itemView);

            personNameTextView = itemView.findViewById(R.id.person_name_textview);
            personPhoneNumberTextView = itemView.findViewById(R.id.person_phone_number_textview);
            personEmailTextView = itemView.findViewById(R.id.person_email_textview);
            personNotesTextView = itemView.findViewById(R.id.person_notes_textview);

            itemView.setOnClickListener(v -> {
                if (SystemClock.elapsedRealtime() - lastButtonClickTime < 3000) {
                    return;
                }
                lastButtonClickTime = SystemClock.elapsedRealtime();
                int position = getAdapterPosition();
                if (itemClickListener != null && position != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClicked(getItem(position));
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

}