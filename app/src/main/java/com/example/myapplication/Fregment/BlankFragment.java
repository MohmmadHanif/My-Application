package com.example.myapplication.Fregment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.R;


public class BlankFragment extends Fragment {

        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
            Log.i("On Attach","On Attach is Running");
        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("On Create","On Create is Running");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("On Create View","on CreateView is Running");
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i("On Start","On Start Is Running");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("On Resume","On Resume is Running");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("On Pause","On Pause is Running");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("On Stop","On Stop is Running");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("On DestroyView","On DestroyView is Running");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("On Destroy","On Destroy is Running");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("On Detach","On Detach is Running");
    }
}