package com.ohmstheresistance.tribute.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.database.Person;
import com.ohmstheresistance.tribute.model.Buttons;

import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonViewHolder> {

    private List<Buttons> buttonList;
    private PersonViewHolder personViewHolder;

    public ButtonAdapter (List<Buttons> buttonList){
        this.buttonList = buttonList;
    }

    @NonNull
    @Override
    public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.button_item_view, viewGroup, false);
        return new ButtonViewHolder(childView);
    }


    @Override
    public void onBindViewHolder(@NonNull ButtonViewHolder buttonViewHolder, int i) {
        Buttons buttons = buttonList.get(i);
        buttonViewHolder.onBind(buttons);

    }

    @Override
    public int getItemCount() {

        return buttonList.size();
    }
}
