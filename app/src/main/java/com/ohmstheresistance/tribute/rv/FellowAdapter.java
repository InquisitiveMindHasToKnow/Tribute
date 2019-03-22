package com.ohmstheresistance.tribute.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ohmstheresistance.tribute.R;
import com.ohmstheresistance.tribute.model.Fellows;

import java.util.List;

public class FellowAdapter extends RecyclerView.Adapter<FellowViewHolder> {

    private List<Fellows> fellowList;

    public FellowAdapter(List<Fellows> fellowList){
        this.fellowList = fellowList;
    }

    @NonNull
    @Override
    public FellowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fellow_item_view, viewGroup, false);
        return new FellowViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull FellowViewHolder fellowViewHolder, int i) {

        Fellows fellows = fellowList.get(i);
        fellowViewHolder.onBind(fellows);

    }

    @Override
    public int getItemCount() {
        return fellowList.size();
    }
}
