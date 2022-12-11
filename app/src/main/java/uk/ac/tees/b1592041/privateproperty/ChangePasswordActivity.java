package uk.ac.tees.b1592041.privateproperty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ChangePasswordActivity extends AppCompatActivity {
    TextInputLayout txtEmail;
    Button btnSubmit, btnPlease_Login;
    private String email;

    //validate email
    private Boolean validateEmail() {
        String val = txtEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            txtEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            txtEmail.setError("invalid email");
            return false;
        } else {
            txtEmail.setError(null);

            return true;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        btnSubmit = findViewById(R.id.Submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnPlease_Login = findViewById(R.id.Please_Login);
        btnSubmit = findViewById(R.id.Submit);
        txtEmail = findViewById(R.id.sendEmail);
        btnPlease_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getEditText().getText().toString();


                if (email.isEmpty()) {
                    Toast.makeText(ChangePasswordActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                } else {
                    btnSubmit();
                }
            }
        });
    }

    private void btnSubmit() {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChangePasswordActivity.this, "Email has been sent", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ChangePasswordActivity.this, Log.class));
                            finish();
                        } else {
                            Toast.makeText(ChangePasswordActivity.this, "Error :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
