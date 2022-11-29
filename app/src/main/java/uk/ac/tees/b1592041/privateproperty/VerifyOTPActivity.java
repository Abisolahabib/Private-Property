package uk.ac.tees.b1592041.privateproperty;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerifyOTPActivity extends AppCompatActivity {
    //    ImageView backButton;
    String log_tag = VerifyOTPActivity.class.getSimpleName();
    //    String firstname, lastname, username, phoneno, email, password, confirmpassword;
    private EditText code, code1, code2, code3, code4, code5;
    private String verificationId;
    private TextView resendOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otpactivity);
        TextView mobile = findViewById(R.id.mobile);
        resendOtp = findViewById(R.id.resend_otp);
        Intent intent = getIntent();


//        firstname = intent.getStringExtra("firstname");
//        lastname = intent.getStringExtra("lastname");
//        username = intent.getStringExtra("username");
//        email = intent.getStringExtra("email");
//        confirmpassword = intent.getStringExtra("confirmpassword");
//        password = intent.getStringExtra("password");
        String number = intent.getStringExtra("mobile");

        Log.d(log_tag, number);
        mobile.setText(number);

        code = findViewById(R.id.code);
        code1 = findViewById(R.id.code1);
        code2 = findViewById(R.id.code2);
        code3 = findViewById(R.id.code3);
        code4 = findViewById(R.id.code4);
        code5 = findViewById(R.id.code5);

        setupOTPInputs();
        final ProgressBar progressBar = findViewById(R.id.progressbar);
        verificationId = intent.getStringExtra("VerificationId");
        final Button btnVerify = findViewById(R.id.otpbuttonverify);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (code.getText().toString().trim().isEmpty()
                        || code1.getText().toString().trim().isEmpty()
                        || code2.getText().toString().trim().isEmpty()
                        || code3.getText().toString().trim().isEmpty()
                        || code4.getText().toString().trim().isEmpty()
                        || code5.getText().toString().trim().isEmpty()) {
                    Toast.makeText(VerifyOTPActivity.this, "Please enter valid code", Toast.LENGTH_LONG).show();
                    return;
                }
                String OTPcode =
                        code.getText().toString() +
                                code1.getText().toString() +
                                code2.getText().toString() +
                                code3.getText().toString() +
                                code4.getText().toString() +
                                code5.getText().toString();


                if (verificationId != null) {
                    progressBar.setVisibility(View.VISIBLE);
                    btnVerify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationId,
                            OTPcode
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.VISIBLE);
                            btnVerify.setVisibility(View.INVISIBLE);
                            if (task.isSuccessful()) {

//                                storeNewUserData();
                                Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(VerifyOTPActivity.this, "The Verification code is invalid", Toast.LENGTH_LONG).show();


                            }

                        }
                    });
                    resendOtp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            PhoneAuthProvider.getInstance().verifyPhoneNumber(getIntent().getStringExtra("mobile"), 60,// user should be able to get an new code in 60 seconds
                                    TimeUnit.SECONDS,
                                    VerifyOTPActivity.this,
                                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {
                                            Toast.makeText(VerifyOTPActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();


                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                            verificationId = newVerificationId;
                                            Toast.makeText(VerifyOTPActivity.this, "OTP Sent", Toast.LENGTH_LONG).show();
                                        }


                                    }
                            );
                        }
                    });
                }


            }

            private void storeNewUserData() {

                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                DatabaseReference reference = rootNode.getReference("users");

                reference.setValue("First record!");

            }
        });
    }

    private void setupOTPInputs() {
        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    code.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    code1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    code2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    code3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    code4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        code5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    code5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}

