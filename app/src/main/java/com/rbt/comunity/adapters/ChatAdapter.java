package com.rbt.comunity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rbt.comunity.R;
import com.rbt.comunity.model.ModelChat;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private ArrayList<ModelChat> datachat;

    public ChatAdapter(ArrayList<ModelChat> datachat) {
        this.datachat = datachat;
    }

    public interface OnItemClickCallback {
        void onItemClicked(ModelChat data);
    }

    private OnItemClickCallback callback;

    public ChatAdapter(OnItemClickCallback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_chat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position) {
        ModelChat chat = datachat.get(position);
        holder.ivprofil.setImageResource(chat.getProfil());
        holder.tvnama.setText(chat.getNama());
        holder.tvisichat.setText(chat.getisichat());
    }

    @Override
    public int getItemCount() {
        return datachat.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView ivprofil;
        TextView tvnama,tvisichat;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            ivprofil = itemView.findViewById(R.id.iv_profil);
            tvnama = itemView.findViewById(R.id.tv_nama);
            tvisichat = itemView.findViewById(R.id.tv_isi);
        }
    }
}
