package uk.ac.tees.b1592041.privateproperty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends AppCompatActivity {

    String log_tag = SendOTPActivity.class.getSimpleName();
    private CountryCodePicker countryCodePicker;
    private TextInputLayout phoneNum;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otpactivity);

        countryCodePicker = findViewById(R.id.country_code);
        phoneNum = findViewById(R.id.phonenumber);
        Button otpbutton = findViewById(R.id.otpbutton);

        backBtn = findViewById(R.id.go_back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        otpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //        get user input
                String country = countryCodePicker.getSelectedCountryCodeWithPlus();
                String phoneNumber = phoneNum.getEditText().getText().toString().trim();

                String fullNum = country + phoneNumber;

                if (!fullNum.isEmpty()) {

                    Log.d(log_tag, fullNum);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            fullNum, // country code + phone number
                            60,// user should be able to get an new code in 60 seconds
                            TimeUnit.SECONDS,
                            SendOTPActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Toast.makeText(SendOTPActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                    Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);
                                    intent.putExtra("mobile", fullNum);
                                    intent.putExtra("VerificationId", verificationId);
                                    startActivity(intent);
                                }

                            });


                }
            }

        });
    }
}