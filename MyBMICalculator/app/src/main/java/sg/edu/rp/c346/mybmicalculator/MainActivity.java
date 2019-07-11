package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText eHeight;
    EditText eWeight;
    Button calculate, reset;
    TextView Date, BMI, outcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eHeight = findViewById(R.id.editTextHeight);
        eWeight = findViewById(R.id.editTextWeight);
        calculate = findViewById(R.id.button);
        reset = findViewById(R.id.button2);
        Date = findViewById(R.id.textView);
        BMI = findViewById(R.id.textView2);
        outcome = findViewById(R.id.textView3);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);

                Date.setText("Last Calculated Date: " + datetime);
                float weight = Float.parseFloat(eWeight.getText().toString());
                float height = Float.parseFloat(eHeight.getText().toString());

                float bmi = weight / (height*height);
                String result = " ";

                if (bmi < 18.5){
                    result = "You are underweight";
                }else if(bmi < 24.9){
                    result = "Your BMI is normal";
                }else if(bmi < 29.9){
                    result = " You are overweight";
                }else{
                    result = "You are obese";
                }

                BMI.setText("Last BMI Calculate: "+bmi);
                outcome.setText(result);


            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date.setText("Last Calculated Date: ");
                BMI.setText("Last Calculated BMI: ");
                eWeight.setText("");
                eHeight.setText("");

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String date = prefs.getString("date","");
        String bmi = prefs.getString("bmi","");


        Date.setText(date);
        BMI.setText(bmi);

    }

    @Override
    protected void onPause() {
        super.onPause();

        String date = Date.getText().toString();
        String bmi = BMI.getText().toString();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor preEdit = prefs.edit();

        preEdit.putString("date",date);
        preEdit.putString("bmi", bmi);

        preEdit.commit();
    }
}
