package com.example.myapplication;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

public class PremiumFragment extends CurrFragment1  {
    public PremiumFragment() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_premium;
    }

    private void swap() {
        ArrayAdapter adapter1 = (ArrayAdapter) spinner1.getAdapter();
        ArrayAdapter adapter2 = (ArrayAdapter) spinner2.getAdapter();
        int pos1 = adapter1.getPosition(spinner1.getSelectedItem().toString());
        int pos2 = adapter2.getPosition(spinner2.getSelectedItem().toString());
        spinner1.setSelection(pos2);
        spinner2.setSelection(pos1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        content(view);
        ImageButton button = view.findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swap();
            }
        });
        ImageButton button1 = view.findViewById(R.id.imageButton3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) Objects.requireNonNull(getContext()).getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("tag_input", editText.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Text copied successfully!", Toast.LENGTH_SHORT).show();
            }
        });
        ImageButton button2 = view.findViewById(R.id.imageButton4);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) Objects.requireNonNull(getContext()).getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("tag_output", textView.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Text copied successfully!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}