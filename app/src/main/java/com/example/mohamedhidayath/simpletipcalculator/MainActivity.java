package com.example.mohamedhidayath.simpletipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spinnertips;
    private List<String> tips;
    private ArrayAdapter<String> adapter;
    private String prcentage;

    private EditText editTextNumber_of_people;
    private EditText editTextBill_amount;
    private EditText editTextTip_amount;

    private Button buttonCalculate;
    private Button buttonReset;

    private TextView textView_No_of_people;
    private TextView textViewBill_Amount;
    private TextView textViewTip_Amount;
    private TextView textViewTotal_Bill;
    private TextView textViewIndividual_Bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        intiListners();
    }

    private void intiListners() {
        buttonCalculate.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
    }


    private void initViews() {
        editTextNumber_of_people=(EditText)findViewById(R.id.edit_text_numberofpeople);
        editTextBill_amount=(EditText) findViewById(R.id.edit_text_billamount);
        editTextTip_amount=(EditText)findViewById(R.id.edit_text_tip_perecent);

        buttonCalculate=(Button)findViewById(R.id.button);
        buttonReset= (Button) findViewById(R.id.button2);

        textView_No_of_people = (TextView) findViewById(R.id.number_of_people);
        textViewBill_Amount = (TextView) findViewById(R.id.bill_amount);
        textViewTip_Amount=(TextView) findViewById(R.id.tip_amount);
        textViewTotal_Bill = (TextView) findViewById(R.id.total_bill);
        textViewIndividual_Bill = (TextView) findViewById(R.id.individual_bill);

        spinnertips = (Spinner) findViewById(R.id.spinner);
        tips = new ArrayList<String>();
        tips.add("select");
        tips.add("10%");
        tips.add("15%");
        tips.add("20%");
        tips.add("25%");

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, tips);
        spinnertips.setAdapter(adapter);

        spinnertips.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if(position!=0){
                    prcentage=spinnertips.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                //reading that edittext boxes
               String ed1=editTextNumber_of_people.getText().toString().trim();
                String ed2 =editTextBill_amount.getText().toString().trim();
                String ed3 = editTextTip_amount.getText().toString().trim();

                if(TextUtils.isEmpty(ed1)){
                    Toast.makeText(getApplicationContext(),"Input the Number of People",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(ed2)){
                    Toast.makeText(getApplicationContext(),"Enter the bill amount",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(ed3)){
                    Toast.makeText(getApplicationContext(),"Enter the Tip Percentage",Toast.LENGTH_SHORT).show();
                }
                int numberofpeople= 0;
                Float billamount=0.0f;
                int tippercentage=0;
                Float tipamount=0.0f;
                Float totalBill=0.0f;
                Float individualpay=0.0f;

                numberofpeople = Integer.parseInt(ed1);
                billamount = Float.parseFloat(ed2);
                tippercentage = Integer.parseInt(ed3);
                tipamount = billamount * tippercentage/100;
                totalBill = billamount+tipamount;
                individualpay = totalBill/numberofpeople;

                textView_No_of_people.setText(""+numberofpeople);
                textViewBill_Amount.setText(""+billamount);
                textViewTip_Amount.setText(""+tipamount);
                textViewTotal_Bill.setText(""+totalBill);
                textViewIndividual_Bill.setText(""+individualpay);
                break;

            case R.id.button2:
                editTextNumber_of_people.setText("");
                editTextBill_amount.setText("");
                editTextTip_amount.setText("");
                textView_No_of_people.setText("");
                textViewBill_Amount.setText("");
                textViewTip_Amount.setText("");
                textViewTotal_Bill.setText("");
                textViewIndividual_Bill.setText("");
                break;
        }

    }
}
