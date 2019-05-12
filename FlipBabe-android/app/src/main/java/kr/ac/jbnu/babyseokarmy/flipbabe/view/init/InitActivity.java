package kr.ac.jbnu.babyseokarmy.flipbabe.view.init;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import kr.ac.jbnu.babyseokarmy.flipbabe.R;
import kr.ac.jbnu.babyseokarmy.flipbabe.util.FragmentUtil;
import kr.ac.jbnu.babyseokarmy.flipbabe.view.home.HomeActivity;

public class InitActivity extends AppCompatActivity {
    private FragmentUtil fragmentUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        fragmentUtil = new FragmentUtil(getSupportFragmentManager(), R.id.content);
        updateUI(FirebaseAuth.getInstance().getCurrentUser());
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser == null) {
            fragmentUtil.add(SignInFragment.newInstance(), null);
        } else {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        System.runFinalization();
    }
}
