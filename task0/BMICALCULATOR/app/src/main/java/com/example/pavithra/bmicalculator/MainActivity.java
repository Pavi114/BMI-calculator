package com.example.pavithra.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Float heightValue;
    Integer weightValue;
    double bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast
        String text = "Thanks for using the App";
        Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Height = (EditText)findViewById(R.id.heightValue);
                String hei = Height.getText().toString();
                heightValue = Float.parseFloat(hei)/100;

                EditText Weight = (EditText)findViewById(R.id.weightValue);
                String wei = Weight.getText().toString();
                weightValue = Integer.parseInt(wei);

                calcBMI();

                displayBmi();

            }
        });

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heightValue = Float.valueOf(0);
                weightValue = 0;
                bmi = Float.valueOf(0);

                EditText Height = (EditText)findViewById(R.id.heightValue);
                Height.setText("");
                EditText Weight = (EditText)findViewById(R.id.weightValue);
                Weight.setText("");

                LinearLayout intro = (LinearLayout)findViewById(R.id.intro);
                LinearLayout result = (LinearLayout) findViewById(R.id.result);
                intro.setVisibility(View.VISIBLE);
                result.setVisibility(View.GONE);

            }
        });



    }

    private void displayBmi() {


        TextView bmiR = (TextView)findViewById(R.id.bmi);
        TextView inference = (TextView) findViewById(R.id.inference);
        bmiR.setText(" Your BMI: " + bmi + "." );
        inference.setText(calcInfer());

        LinearLayout intro = (LinearLayout)findViewById(R.id.intro);
        LinearLayout result = (LinearLayout) findViewById(R.id.result);
        intro.setVisibility(View.GONE);
        result.setVisibility(View.VISIBLE);
    }

    private String calcInfer() {

        TextView infer = (TextView)findViewById(R.id.inference);
        if(bmi < 18){
            infer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            return "Underweight!!";
        }
        else if(bmi <= 25){
            infer.setTextColor(getResources().getColor(R.color.pink));
            return "Normal";
        }
        else if(bmi <= 30){
            infer.setTextColor(getResources().getColor(R.color.blue));
            return "Overweight";
        }
        else {
            infer.setTextColor(getResources().getColor(R.color.red));
            return "Obese";
        }
    }

    private void calcBMI() {
        bmi = Math.round(weightValue/(heightValue*heightValue)*100.0)/100.0;
    }
}
