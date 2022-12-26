package com.rbt.comunity.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rbt.comunity.R;
import com.rbt.comunity.adapters.ChatAdapter;
import com.rbt.comunity.adapters.GridAdapter;
import com.rbt.comunity.model.DataChat;
import com.rbt.comunity.model.DataCom;
import com.rbt.comunity.model.ModelChat;
import com.rbt.comunity.model.ModelPost;

import java.nio.file.Files;
import java.util.ArrayList;


public class ChatFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<ModelChat> chats = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.rv_chat);
        recyclerView.setHasFixedSize(true);

        chats.addAll(DataChat.getData());

        displayGrid();

        return view;
    }

    private void displayGrid() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ChatAdapter adapter = new ChatAdapter(chats);
        recyclerView.setAdapter(adapter);

    }
}