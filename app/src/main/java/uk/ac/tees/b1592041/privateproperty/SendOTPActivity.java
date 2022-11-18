package uk.ac.tees.b1592041.privateproperty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendOTPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otpactivity);
         EditText number = findViewById(R.id.number);
               Button otpbutton = findViewById(R.id.otpbutton);

               otpbutton.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       if(number.toString().isEmpty()){
                            
                       }
                   }
               });


    }
}