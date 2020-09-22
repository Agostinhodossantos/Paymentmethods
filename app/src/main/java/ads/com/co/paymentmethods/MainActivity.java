package ads.com.co.paymentmethods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ads.com.co.paymentmethods.paypal.PaypalActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnPaypal , btnGooglePay , btnMpesa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPaypal  = findViewById(R.id.btnPayPal);
        btnGooglePay = findViewById(R.id.btnGooglePay);
        btnMpesa = findViewById(R.id.btnMpesa);

        btnPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , PaypalActivity.class);
                startActivity(intent);
            }
        });
        btnMpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnGooglePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
