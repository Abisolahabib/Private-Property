package uk.ac.tees.b1592041.privateproperty;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {


    Button submit;
    ImageView backbutton;
    TextView titleText;
    TextInputLayout FirstName, LastName, Username, Email, Password, phonenumber,
            confirmpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);


        submit = findViewById(R.id.Submit);
        titleText = findViewById(R.id.signup);
        backbutton = findViewById(R.id.back_button);

        FirstName = findViewById(R.id.firstname);
        LastName = findViewById(R.id.lastname);
        Email = findViewById(R.id.email);
        phonenumber = findViewById(R.id.phonenumber);
        Password = findViewById(R.id.createpassword);
        confirmpassword = findViewById(R.id.confirmpassword);
        Username = findViewById(R.id.username);


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private Boolean validateFirstName() {
        String val = FirstName.getEditText().getText().toString();
        if (val.isEmpty()) {
            FirstName.setError("Field cannot be empty");
            return false;
        } else {
            FirstName.setError(null);
            FirstName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateLastName() {

        String val = LastName.getEditText().getText().toString();

        if (val.isEmpty()) {
            LastName.setError("Field cannot be empty");

            return false;
        } else {
            LastName.setError(null);
            LastName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = Username.getEditText().getText().toString();
        boolean hasWhiteSpace = Pattern.matches("\\s+", val);

        if (val.isEmpty()) {
            Username.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 10) {
            Username.setError("Too long");
            return false;
        } else if (val.contains(" ")) {
            Username.setError("Empty space");
            return false;
        } else {
            Username.setError(null);
            return true;
        }
    }

    private Boolean validateEmail() {

        String val = Email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            Email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            Email.setError("Email incorrect");
            return false;
        } else {
            Email.setError(null);

            return true;
        }
    }

    private Boolean validatePassword() {

        String val = Password.getEditText().getText().toString();
        String passwordValue = "^" +
                "(?=.*[a-zA-Z])" + // letters only
                "(?=.+?[0-9])" + //at least 1 digit
                "(?=.*[@#$%^&+=])" + // at least 1 special character
                "(?=\\S+$)" +  //No empty spaces
                ".{8,}" +
                "$"; // atleast 8 characters;

        if (val.isEmpty()) {
            Password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordValue)) {
            Password.setError("Too easy");
            return false;
        } else {
            Password.setError(null);
            return true;
        }
    }

    private Boolean validatecomfirmpassword() {

        String val = confirmpassword.getEditText().getText().toString();

        if
        (val.isEmpty()) {
            confirmpassword.setError("Field cannot be empty");
            return false;
        } else {
            confirmpassword.setError(null);
            return true;
        }
    }

    public void btnRegisterUser(View view) {


    }


}










