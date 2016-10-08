package com.example.aaron.customviewhomework;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by Oscar Aaron Revilla Escalona on 10/6/2016.
 */

public class UnitContainer extends LinearLayout {

    private TextView title;
    private EditText unitNumber;
    private Spinner unit;
    private int getConversionId;

    public UnitContainer(Context context) {
        super(context);
        initContainer(context);
    }

    public UnitContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initContainer(context);
    }

    public UnitContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initContainer(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public UnitContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initContainer(context);
    }

    public void initContainer(Context contex){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.unit_container, this);
    }

    public void setContainerTitle(String t){
        this.title.setText(t);
    }

    public double getUnitNumber(){
        String aux = unitNumber.getText().toString();
        if(aux.isEmpty()){
            return 0.0;
        }
        else{
            return Double.parseDouble(aux);
        }
    }

    public EditText getNumericField(){
        return this.unitNumber;
    }

    public int getGetConversionId() {
        return getConversionId;
    }

    public void setGetConversionId(int getConversionId) {
        this.getConversionId = getConversionId;
    }

    public void setResultValue(String value){
        unitNumber.setText(value);
    }

    public Spinner getUnits(){
        return unit;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        this.setOrientation(VERTICAL);

        title = ((TextView) this.findViewById(R.id.title));
        unitNumber = ((EditText) this.findViewById(R.id.numberUnits));
        unit = ((Spinner) this.findViewById(R.id.units));
        //set units
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.mass_units, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        unit.setAdapter(adapter);

    }
}
