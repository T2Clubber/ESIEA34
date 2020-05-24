package com.example.esiea34.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.esiea34.R;
import com.example.esiea34.presentation.model.Pokemon;

import java.util.List;

    public class ListAdapteur extends RecyclerView.Adapter<ListAdapteur.ViewHolder> {

        private List<Pokemon> values;
        private OnItemClickListener listener;

        public interface OnItemClickListener {
            void onItemClick(Pokemon item);
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView txtHeader;
            public TextView txtFooter;
            public View layout;

            public ViewHolder(View v) {
                super(v);
                layout = v;
                txtHeader = (TextView) v.findViewById(R.id.firstLine);
                txtFooter = (TextView) v.findViewById(R.id.secondLine);
            }
        }

        public void add(int position, Pokemon item) {
            values.add(position, item);
            notifyItemInserted(position);
        }

        public void remove(int position) {
            values.remove(position);
            notifyItemRemoved(position);
        }

        public  ListAdapteur(List<Pokemon> myDataset, OnItemClickListener listener) {
            this.values = myDataset;
            this.listener = listener;
        }

        @Override
        public ListAdapteur.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.row_layout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            final Pokemon currentPokemon = values.get(position);
            holder.txtHeader.setText(currentPokemon.getName());
            holder.txtFooter.setText(currentPokemon.getUrl());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(currentPokemon);
                }
            });
        }

        @Override
        public int getItemCount() {
            return values.size();
        }

    }
