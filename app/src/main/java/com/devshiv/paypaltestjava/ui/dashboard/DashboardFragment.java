package com.devshiv.paypaltestjava.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.devshiv.paypaltestjava.MainActivity;
import com.devshiv.paypaltestjava.R;
import com.devshiv.paypaltestjava.databinding.FragmentDashboardBinding;
import com.devshiv.paypaltestjava.ui.cartDetail.CartDetailActivity;
import com.devshiv.paypaltestjava.ui.historyTransaction.HistoryTransaction;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    ImageView viewBack, viewShop;

    CardView cartDetailList,historyTransactionPart;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //anh xa
        viewBack = root.findViewById(R.id.pharmImage);
        viewShop = root.findViewById(R.id.clothingImage);
        cartDetailList = root.findViewById(R.id.cartDetail);
        historyTransactionPart = root.findViewById(R.id.historyTransactionPart);


        historyTransactionPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), HistoryTransaction.class);
                startActivity(intent);
            }
        });

        viewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), MainActivity.class);
                startActivity(intent);
            }
        });

//        viewShop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create a new instance of the HomeFragment
//                HomeFragment homeFragment = new HomeFragment();
//
//                // Replace the current fragment with the HomeFragment
//                getParentFragmentManager().beginTransaction()
//                        .replace(R.id.constraintLayout, homeFragment)
//                        .addToBackStack(null) // Optional: adds the transaction to the back stack
//                        .commit();
//            }
//        });


        cartDetailList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), CartDetailActivity.class);
                startActivity(intent);

            }
        });


//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}