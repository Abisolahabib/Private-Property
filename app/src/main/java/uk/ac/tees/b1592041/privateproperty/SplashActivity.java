package uk.ac.tees.b1592041.privateproperty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.content.Intent;

public class SplashActivity extends AppCompatActivity {

    Animation animation;
    ImageView logo_image;

//    delay for splash screen
    private static int splash_delay = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo_image = findViewById(R.id.logo_image);

        animation = AnimationUtils.loadAnimation(this,R.anim.zoom_animation);
        logo_image.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, OnboardingActivity.class);

                startActivity(intent);
                finish();
            }
        },splash_delay);
    }
}