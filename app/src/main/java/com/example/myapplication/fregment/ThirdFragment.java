package com.example.myapplication.fregment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

public class ThirdFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        Log.i("On CreateView", "On CreateView Is Running for Fragment3");

/*
        Button text = view.findViewById(R.id.thirdBtn);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                */
/*FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_container_view, FirstFragment.class, null);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*//*

            }
        });
*/


        return view;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("On Attach", "On Attach is Running for Fragment3");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("On Create", "On Create is Running for Fragment3");
    }


    @Override
    public void onStart() {
        super.onStart();

        Log.i("On Start", "On Start Is Running for Fragment3");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("On Resume", "On Resume is Running for Fragment3");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("On Pause", "On Pause is Running for Fragment3");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("On Stop", "On Stop is Running for Fragment3");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("On DestroyView", "On DestroyView is Running for Fragment3");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("On Destroy", "On Destroy is Running for Fragment3");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("On Detach", "On Detach is Running for Fragment3");
    }
}