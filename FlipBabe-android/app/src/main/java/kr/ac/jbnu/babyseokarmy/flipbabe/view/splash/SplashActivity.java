package kr.ac.jbnu.babyseokarmy.flipbabe.view.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import kr.ac.jbnu.babyseokarmy.flipbabe.view.init.InitActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, InitActivity.class));
        finish();
    }
}