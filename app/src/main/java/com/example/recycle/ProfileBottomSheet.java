package com.example.recycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ProfileBottomSheet extends BottomSheetDialogFragment {

    private String userName;
    private String userEmail;

    // Método "fábrica" para passar dados (nome e email) com segurança
    public static ProfileBottomSheet newInstance(String name, String email) {
        ProfileBottomSheet fragment = new ProfileBottomSheet();
        Bundle args = new Bundle();
        args.putString("USER_NAME", name);
        args.putString("USER_EMAIL", email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Recupera os argumentos
        if (getArguments() != null) {
            userName = getArguments().getString("USER_NAME");
            userEmail = getArguments().getString("USER_EMAIL");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla (carrega) o layout XML que acabamos de criar
        return inflater.inflate(R.layout.bottom_sheet_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Encontra os TextViews no layout
        TextView tvName = view.findViewById(R.id.tv_profile_name);
        TextView tvEmail = view.findViewById(R.id.tv_profile_email);

        // Define o texto com os dados do usuário
        tvName.setText(userName);
        tvEmail.setText(userEmail);
    }
}