package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;
import java.util.ArrayList;

public class CurrFragment1 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Spinner spinner1, spinner2;
    String category;
    String selected1, selected2;
    EditText editText;
    TextView textView;
    DataViewModel model;
    UnitManager unitManager = new UnitManager();
    ArrayList<Unit> units;

    private String mParam1;
    private String mParam2;

    public CurrFragment1() {
        // Required empty public constructor
    }

/*    public static CurrFragment1 newInstance(String param1, String param2) {
        CurrFragment1 fragment = new CurrFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public int getLayout() {
        return R.layout.fragment_curr1;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void content(View view) {
        category = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("unitName");
        spinner1 = view.findViewById(R.id.spinner1);
        spinner2 = view.findViewById(R.id.spinner2);
        editText = view.findViewById(R.id.editTextNumber);
        model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int inType = editText.getInputType();
                editText.setInputType(InputType.TYPE_NULL);
                editText.onTouchEvent(event);
                editText.setInputType(inType);
                return true;
            }
        });
        textView = view.findViewById(R.id.textView3);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                convert(s.toString());
            }
        });
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convert(editText.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinner1.setOnItemSelectedListener(listener);
        spinner2.setOnItemSelectedListener(listener);
        fillSpinner(category);
        final Observer<String> valueInput = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                editText.setText(s);
            }
        };
        model.getInput().observe(getViewLifecycleOwner(), valueInput);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(getLayout(), container, false);
        content(view);
        return view;
    }

    public String[] getUnitNames(ArrayList<Unit> arrayList) {
        String[] names = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            names[i] = arrayList.get(i).name;
        }
        return names;
    }

    public void createSpinner(Spinner spinner, String[] units) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(this.getActivity()), android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
    }

    public void fillSpinner(String category) {
        units = new ArrayList<>(unitManager.getValues(category));
        createSpinner(spinner1, getUnitNames(units));
        createSpinner(spinner2, getUnitNames(units));
    }

    public void algorithm(int n) {
        selected1 = spinner1.getSelectedItem().toString();
        selected2 = spinner2.getSelectedItem().toString();
        float number = Float.parseFloat(editText.getText().toString());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (selected1.equals(units.get(i).name) && selected2.equals(units.get(j).name)) {
                    if (i == j) {
                        textView.setText(String.valueOf(number));
                        continue;
                    }
                    textView.setText(String.valueOf(number * units.get(i).coefficient * (1 / units.get(j).coefficient)));
                }
            }
        }
    }
    public void convert(String s) {
        if (!s.equals("")) {
            if (Objects.equals(category, "DISTANCE") || Objects.equals(category, "WEIGHT")) {
                algorithm(6);
            }
            else {
                algorithm( 4);
            }
        }
        else {
            textView.setText("");
        }
        model.setOutput(textView.getText().toString());
    }
}

