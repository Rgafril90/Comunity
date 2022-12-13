package com.rbt.comunity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rbt.comunity.Activities.MainActivity;
import com.rbt.comunity.R;

public class LoginFragment extends Fragment {

    private EditText email;
    private EditText pw;
    private Button btn_login;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.card_login, container, false);
        email = root.findViewById(R.id.email_login);
        pw = root.findViewById(R.id.password);
        btn_login = root.findViewById(R.id.btn_login);

        pw.addTextChangedListener(loginText);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    private TextWatcher loginText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String user = email.getText().toString().trim();
            String pass = pw.getText().toString().trim();

            btn_login.setEnabled(!user.isEmpty() && !pass.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
