package com.devshiv.paypaltestjava.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.devshiv.paypaltestjava.R;
import com.devshiv.paypaltestjava.databinding.FragmentNotificationsBinding;
import com.devshiv.paypaltestjava.map.Map;
import com.devshiv.paypaltestjava.ui.login.LoginActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    FirebaseAuth auth;
    Button button, buttonLocation;
    FirebaseUser user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        auth = FirebaseAuth.getInstance();
        button = root.findViewById(R.id.logout); // Assuming you have a logout button in your fragment layout
        buttonLocation = root.findViewById(R.id.location);
        user = auth.getCurrentUser();

        if (user == null){
            Intent intent = new Intent(root.getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        else {
            String uid = user.getUid();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(root.getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), Map.class);
                startActivity(intent);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
