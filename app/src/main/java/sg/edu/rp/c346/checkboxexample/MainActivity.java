package sg.edu.rp.c346.checkboxexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox cbEnabled;
    Button btnCheck;
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbEnabled = findViewById(R.id.checkBoxDiscount);
        btnCheck = findViewById(R.id.buttonCheck);
        tvShow = findViewById(R.id.textView);

        btnCheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                Log.i("MyActivity","Inside onClick()");
                if(cbEnabled.isChecked()){
                    double pay = calcPay(100,20);
                    tvShow.setText("The discount is given. you need to pay " + pay);
                }else{
                    double pay = calcPay(100,0);
                    tvShow.setText("The discount is  not given. you need to pay " + pay);
                }
                Context context = getApplicationContext();
                CharSequence text = "Button Click";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context,text,duration);
                toast.show();
            }

        });







    }
    private double calcPay(double price, double discount){
        double pay = price * (1-discount/100);
        return pay;
    }
}
