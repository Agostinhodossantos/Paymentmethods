package ads.com.co.paymentmethods.paypal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

import ads.com.co.paymentmethods.R;

public class PaypalActivity extends AppCompatActivity {

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId("YOUR CLIENT ID");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        final Button button = (Button) findViewById(R.id.paypal_button);

    }

    public void beginPayment(View view) {
        Intent serviceConfig = new Intent(this, PayPalService.class);
        serviceConfig.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(serviceConfig);

        PayPalPayment payment = new PayPalPayment(new BigDecimal("5.65"),
                "USD", "My Awesome Item", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent paymentConfig = new Intent(this, PaymentActivity.class);
        paymentConfig.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        paymentConfig.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(paymentConfig, 0);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            PaymentConfirmation confirm = data.getParcelableExtra(
                    PaymentActivity.EXTRA_RESULT_CONFIRMATION);
            if (confirm != null) {
                try {
                    Log.i("sampleapp", confirm.toJSONObject().toString(4));
// TODO: send 'confirm' to your server for verification
                } catch (JSONException e) {
                    Log.e("sampleapp", "no confirmation data: ", e);
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("sampleapp", "The user canceled.");
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Log.i("sampleapp", "Invalid payment / config set");

        }

    }

    @Override
    public void onDestroy(){
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }
}
