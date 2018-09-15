package com.coffee.qrcodescanner.ListActivityPack;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coffee.qrcodescanner.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {

    private Context ctx;
    private List<person> personList;

    public ListAdapter(Context ctx, List<person> personList) {
        this.ctx = ctx;
        this.personList = personList;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.list_row, null);
        ListHolder listHolder = new ListHolder(view);

        return listHolder;
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {

        person person = personList.get(position);
        holder.name.setText(person.getName());

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ListHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);


        }
    }
}

