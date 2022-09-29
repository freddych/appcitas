package com.example.retrofitcrud.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofitcrud.R;
import com.example.retrofitcrud.model.User;

import java.util.List;

public class PacienteAdapter extends RecyclerView.Adapter<PacienteAdapter.MyViewHolder> {
        private List<User> usuariosList;

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView apenom, documento, tipodoc;

            public MyViewHolder(View view) {
                super(view);
                apenom = (TextView) view.findViewById(R.id.nameTextView);
                documento = (TextView) view.findViewById(R.id.docuTextView);
                tipodoc = (TextView) view.findViewById(R.id.tdocTextView);
            }
        }


        public PacienteAdapter(List<User> usuariosList) {
            this.usuariosList = usuariosList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            User user = usuariosList.get(position);
            holder.apenom.setText(user.getApenom());
            holder.documento.setText(user.getDocumento());
            holder.tipodoc.setText(user.getTipodoc());
        }

        @Override
        public int getItemCount() {
            return usuariosList.size();
        }
    }
