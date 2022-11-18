package uk.ac.tees.b1592041.privateproperty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgotpasswordActivity extends AppCompatActivity {
    Button forgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        forgotpassword = findViewById(R.id.forgot_password);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ForgotpasswordActivity.this, SendOTPActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onBtnSignup(View view) {
    }
}









