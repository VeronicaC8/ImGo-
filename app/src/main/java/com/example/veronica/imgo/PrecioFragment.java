package com.example.veronica.imgo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/*
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PrecioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrecioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrecioFragment extends Fragment {

    public PrecioFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_precio, null);
    }
}
