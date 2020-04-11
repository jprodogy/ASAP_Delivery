package com.example.asap_delivery.ui.closeout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.asap_delivery.R;

public class CloseOutFragment extends Fragment {

    private CloseOutModel closeOutModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        closeOutModel =
                ViewModelProviders.of(this).get(CloseOutModel.class);
        View root = inflater.inflate(R.layout.fragment_close_out, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        closeOutModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
