package com.tuandm.codeme.view.home.tab_youtube;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuandm.codeme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YoutubeFragment extends Fragment {


    public YoutubeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_youtube, container, false);
    }

}
