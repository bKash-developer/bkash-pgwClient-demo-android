package com.bkash.rnd.pgwwebview.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bkash.rnd.pgwwebview.R;
import com.bkash.rnd.pgwwebview.model.Checkout;


import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    private EditText amount;
    private RadioButton sale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = (EditText) findViewById(R.id.checkout_amount);
        sale = (RadioButton) findViewById(R.id.intent_sale);

        sale.setChecked(true);

        Button buttonCheckOut = (Button) findViewById(R.id.buttonUrlCheckout);

        buttonCheckOut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!amount.getText().toString().isEmpty()) {
                    Checkout checkout = new Checkout();
                    checkout.setAmount(amount.getText().toString());

                    checkout.setVersion("two");

                    if (sale.isChecked()) {
                        checkout.setIntent("sale");
                    } else {
                        checkout.setIntent("authorization");
                    }

                    Intent intent = new Intent(context, WebViewCheckoutActivity.class);
                    intent.putExtra("values", checkout);

                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter the field values", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
