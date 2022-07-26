package com.example.myapplication.apicalling.apicallingcrud;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;

import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ApiCallingBottomSheetFragment extends BottomSheetDialogFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.apicalling_fragment_bottom_sheet, container, false);

        AppCompatButton no = v.findViewById(R.id.tvNoBottomSheet);
        AppCompatButton yes = v.findViewById(R.id.tvYesBottomSheet);

        no.setOnClickListener(view -> dismiss());

        yes.setOnClickListener(view -> {
            ApiCallingShowDataActivity postToGetDataActivity = (ApiCallingShowDataActivity) getActivity();
            if (postToGetDataActivity != null) {
                postToGetDataActivity.deleteList();
            }
            this.dismiss();
        });

        return v;
    }

}