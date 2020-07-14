package com.tuandm.codeme.base;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
