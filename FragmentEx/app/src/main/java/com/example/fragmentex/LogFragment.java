package com.example.fragmentex;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by madsoliy on 1/28/2015.
 */
public class LogFragment extends Fragment {

    public LogFragment() {}

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.log_fragment, container, false);
        return rootView;
    }


}

