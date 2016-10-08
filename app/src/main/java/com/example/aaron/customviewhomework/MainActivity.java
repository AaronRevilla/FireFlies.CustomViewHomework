package com.example.aaron.customviewhomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    public UnitContainer uC1;
    public UnitContainer uC2;
    public static double[] conversion_array = {1000000.0, 1000.0, 1.0, 0.001, 35.273962, 2.20462};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uC1 = ((UnitContainer) findViewById(R.id.container1));
        uC2 = ((UnitContainer) findViewById(R.id.container2));

        uC1.setContainerTitle("From Compund View 1:");
        uC2.setContainerTitle("To Compund View 2:");


        uC1.getUnits().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                uC1.setGetConversionId(position);
                uC1.getNumericField().setFocusable(true);
                manageConversion();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        uC1.getNumericField().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.d("DEBUGER", String.valueOf(uC1.getUnitNumber()));
                if(uC1.getNumericField().hasFocus()){
                    manageConversion();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        uC2.getUnits().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                uC2.setGetConversionId(position);
                uC2.getNumericField().setFocusable(true);
                manageConversion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        uC2.getNumericField().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Log.d("DEBUGER", String.valueOf(uC2.getUnitNumber()));
                if(uC2.getNumericField().hasFocus()){
                    manageConversion();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void manageConversion(){
        double n1 = uC1.getUnitNumber();
        double n2 = uC2.getUnitNumber();

        //Log.d("NUM1", String.valueOf(n1));
        //Log.d("NUM2", String.valueOf(n2));

        String aux;

        if(uC1.getNumericField().hasFocus()){
            aux = String.valueOf(makeConversion(n1, uC1.getGetConversionId(), n2, uC2.getGetConversionId()));

            if(aux.length()>10){
                uC2.getNumericField().setText(aux.substring(0,10));
            }
            else{
                uC2.getNumericField().setText(aux);
            }
        }
        else{
            aux = String.valueOf(makeConversion(n2, uC2.getGetConversionId(), n1, uC1.getGetConversionId()));

            if(aux.length()>10){
                uC1.getNumericField().setText(aux.substring(0,10));
            }
            else{
                uC1.getNumericField().setText(aux);
            }
        }
        //Log.d("RES", aux);

    }

    public BigDecimal makeConversion(double n1, int positionN1, double n2, int positionN2){
        BigDecimal result;
        //convertir a kilos
        result  = new BigDecimal((n1 / conversion_array[positionN1]) * conversion_array[positionN2]);
        return result;
    }
}
