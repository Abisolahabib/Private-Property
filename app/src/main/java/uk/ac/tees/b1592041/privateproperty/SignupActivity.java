package uk.ac.tees.b1592041.privateproperty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    Button submit;
    ImageView back_button;
    TextView titleText;
    TextInputLayout FirstName, LastName, Username, Email, Password, phonenumber,
            confirmpassword;
    String TAG = SignupActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);


        mAuth = FirebaseAuth.getInstance();

        titleText = findViewById(R.id.signup);
        back_button = findViewById(R.id.back_button);

        FirstName = findViewById(R.id.firstname);
        LastName = findViewById(R.id.lastname);
        Email = findViewById(R.id.email);
        phonenumber = findViewById(R.id.phonenumber);
        Password = findViewById(R.id.createpassword);
        confirmpassword = findViewById(R.id.confirmpassword);
        Username = findViewById(R.id.username);
        signIn = findViewById(R.id.sigin_up);


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
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

        String val = Email.getEditText().getText().toString().trim();
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
        if (!validateFirstName() || !validateLastName() || !validateUsername() || !validateEmail() || !validatePassword() || !validatecomfirmpassword()) {
            return;
        } else {

            FirebaseFirestore database = FirebaseFirestore.getInstance();
            HashMap<String, Object> user = new HashMap<>();

            String userEmail = Email.getEditText().getText().toString().trim();
            String userPwd = Password.getEditText().getText().toString().trim();


            user.put("firstname", FirstName.getEditText().getText().toString().trim());
            user.put("lastname", LastName.getEditText().getText().toString().trim());
            user.put("username", Username.getEditText().getText().toString().trim());
            user.put("phonenumber", phonenumber.getEditText().getText().toString().trim());
            user.put("email", userEmail);


            Log.d(TAG, userEmail);
            mAuth.createUserWithEmailAndPassword(userEmail, userPwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        String uid = task.getResult().getUser().getUid();
                        database.collection("users")
                                .document(uid)
                                .set(user)
                                .addOnSuccessListener(documentReference -> {
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                });
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        check to see if the user is already signed
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }

    private void reload() {
    }

    private void updateUI(FirebaseUser user) {

    }
}










