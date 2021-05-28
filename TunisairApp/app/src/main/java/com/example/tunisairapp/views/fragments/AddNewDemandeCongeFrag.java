package com.example.tunisairapp.views.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.media.Session2Command;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.DocumentsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tunisairapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddNewDemandeCongeFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewDemandeCongeFrag extends Fragment {
    Spinner dropdown;
    Button btnUploadFile, btnValidateLeaveReq;
    Intent myFileIntent;
    TextView txtPathFile;
    EditText edtDateDebut, edtDateFin, edtDescription, edtNbrDays;
    LinearLayout layoutFile;

    // For validators
    private Pattern pattern;
    private Matcher matcher;

    private static final String DATE_PATTERN =
            "(0?[1-9]|1[012]) [/.-] (0?[1-9]|[12][0-9]|3[01]) [/.-] ((19|20)\\d\\d)";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddNewDemandeCongeFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNewDemandeCongeFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewDemandeCongeFrag newInstance(String param1, String param2) {
        AddNewDemandeCongeFrag fragment = new AddNewDemandeCongeFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_new_demande_conge, container, false);

        // spinner or dropdown (Liste deroulante)
        dropdown = rootView.findViewById(R.id.spinnerType);

        // buttons
        btnUploadFile = rootView.findViewById(R.id.btnUploadFile);
        btnValidateLeaveReq = rootView.findViewById(R.id.btn_ValidLeaveReq);

        // Text path file
        txtPathFile = rootView.findViewById(R.id.txtfichier);

        // Edit Texts variables
        edtDateDebut = rootView.findViewById(R.id.date_debut);
        edtDateFin = rootView.findViewById(R.id.date_fin);
        edtDescription = rootView.findViewById(R.id.description);
        edtNbrDays = rootView.findViewById(R.id.nbrDays);

        // Layout
        layoutFile = rootView.findViewById(R.id.layout_file);

        // Listeners
        btnUploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("*/*");
                startActivityForResult(myFileIntent, 10);

            }
        });

        btnValidateLeaveReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*matcher = Pattern.compile(DATE_PATTERN).matcher(edtDateDebut.getText());
                matcher = Pattern.compile(DATE_PATTERN).matcher(edtDateFin.getText());
                if (ValidDate(edtDateDebut.toString()) && ValidDate(edtDateFin.toString()) && ValidDescription(edtDescription.toString())){
                    Toast.makeText(getContext(), "Valid Form", Toast.LENGTH_LONG).show();
                }*/
            }
        });


        layoutFile.setVisibility(View.INVISIBLE);
        edtNbrDays.setVisibility(View.INVISIBLE);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("Medical leave")){
                    layoutFile.setVisibility(View.VISIBLE);
                    edtNbrDays.setVisibility(View.INVISIBLE);
                }else if(parent.getItemAtPosition(position).toString().equals("Annual leave")){
                    edtNbrDays.setVisibility(View.VISIBLE);
                }else{
                    layoutFile.setVisibility(View.INVISIBLE);
                    edtNbrDays.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing happen
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 10 : if (resultCode == RESULT_OK){
                String path = data.getData().getPath();
                txtPathFile.setText("File : "+ path);
                break;
            }
        }
    }

    public boolean ValidDate(final String date){
        matcher = pattern.matcher(date);
        if(matcher.matches()){
            matcher.reset();

            if(matcher.find()){
                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                if (day.equals("31") &&
                        (month.equals("4") || month .equals("6") || month.equals("9") ||
                                month.equals("11") || month.equals("04") || month .equals("06") ||
                                month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                }

                else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if(year % 4==0){
                        if(day.equals("30") || day.equals("31")){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    else{
                        if(day.equals("29")||day.equals("30")||day.equals("31")){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                }

                else{
                    return true;
                }
            }

            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public boolean ValidDescription(final String desc){
        if(desc.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean ValidNbrDays(final String nbr){
        //if(nbr)
        return true;
    }
}