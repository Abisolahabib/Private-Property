package uk.ac.tees.b1592041.privateproperty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }

    public void changePwd(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message,Toast.LENGTH_LONG);
        toast.show();

        Intent intent = new Intent(ChangePasswordActivity.this, SendOTPActivity.class);

        startActivity(intent);

    }
}