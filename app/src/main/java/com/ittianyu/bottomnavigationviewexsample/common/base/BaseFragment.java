package com.ittianyu.bottomnavigationviewexsample.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.ittianyu.bottomnavigationviewexsample.R;
import com.ittianyu.bottomnavigationviewexsample.databinding.FragBaseBinding;

/**
 * Created by yu on 2016/11/11.
 */

public class BaseFragment extends Fragment {
    private String title;
    FragBaseBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get title
        title = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.frag_base, null);
        // bind view
        binding = DataBindingUtil.inflate(inflater, R.layout.frag_base, container, false);
        binding.tvTitle.setText(title);
        return view;
    }
}
