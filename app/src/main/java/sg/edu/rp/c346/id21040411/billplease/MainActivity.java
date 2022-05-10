package sg.edu.rp.c346.id21040411.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText Amount;
    EditText Pax;
    EditText Discount;
    ToggleButton SVS;
    ToggleButton GST;
    TextView totalBill;
    TextView eachPays;
    Button btnSplit;
    Button btnReset;
    RadioGroup rgPayment;
    RadioButton rbCash;
    RadioButton rbPayNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Amount = findViewById(R.id.etAmount);
        Pax = findViewById(R.id.etPax);
        Discount = findViewById(R.id.etDiscount);
        SVS = findViewById(R.id.tbSVS);
        GST = findViewById(R.id.tbGST);
        totalBill = findViewById(R.id.tvTotalAmount);
        eachPays = findViewById(R.id.tvPay);
        btnSplit = findViewById(R.id.btnSplit);
        btnReset = findViewById(R.id.btnReset);
        rgPayment = findViewById(R.id.Payment);
        rbCash = findViewById(R.id.rbCash);
        rbPayNow = findViewById(R.id.rbPayNow);

        btnSplit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(Amount.getText().toString().trim().length()!=0 && Pax.getText().toString().trim().length() !=0) {
                    double totAmt = 0.0;
                    if (!SVS.isChecked() && !GST.isChecked()) {
                        totAmt = Double.parseDouble(Amount.getText().toString());
                    } else if (SVS.isChecked() && !GST.isChecked()) {
                        totAmt = Double.parseDouble(Amount.getText().toString()) * 1.1;
                    } else if (!SVS.isChecked() && GST.isChecked()) {
                        totAmt = Double.parseDouble(Amount.getText().toString()) * 1.07;
                    } else {
                        totAmt = Double.parseDouble(Amount.getText().toString()) * 1.17;
                    }

                    if (Discount.getText().toString().trim().length() != 0) {
                        totAmt *= 1 - Double.parseDouble(Discount.getText().toString()) / 100;
                    }

                    totalBill.setText("Total Bill: $" + String.format("%.2f", totAmt));
                    int numPax = Integer.parseInt((Pax.getText().toString()));
                    int checkedRadioID = rgPayment.getCheckedRadioButtonId();
                    if (numPax != 1 && checkedRadioID == R.id.rbCash) {
                        eachPays.setText("Each Pays: $" + String.format("%.2f",totAmt / numPax) + " in cash.");
                    } else if (numPax != 1 && checkedRadioID == R.id.rbPayNow){
                        eachPays.setText("Each Pays: $" + String.format("%.2f",totAmt / numPax) + " via PayNow to 91234567");
                    } else if (numPax == 1 && checkedRadioID == R.id.rbCash) {
                        eachPays.setText("Each Pays: $" + String.format("%.2f",totAmt) + " in cash.");
                    } else {
                        eachPays.setText("Each Pays: $" + String.format("%.2f",totAmt) + " via PayNow to 91234567");
                    }
                }
            }
        });


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amount.setText("");
                Pax.setText("");
                SVS.setChecked(false);
                GST.setChecked(false);
                Discount.setText("");
            }
        });






    }
}