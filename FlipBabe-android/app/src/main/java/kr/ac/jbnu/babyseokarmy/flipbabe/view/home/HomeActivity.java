package kr.ac.jbnu.babyseokarmy.flipbabe.view.home;

import android.os.Bundle;
import android.widget.Button;

import kr.ac.jbnu.babyseokarmy.flipbabe.R;
import kr.ac.jbnu.babyseokarmy.flipbabe.util.FragmentUtil;
import kr.ac.jbnu.babyseokarmy.flipbabe.view.base.BluServiceActivity;

public class HomeActivity extends BluServiceActivity {
    //    private static final String TAG = HomeActivity.class.getSimpleName();
    private FragmentUtil fragmentUtil;

    private Button careBtn, diaryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        careBtn = findViewById(R.id.care_btn);
        diaryBtn = findViewById(R.id.diary_btn);

        fragmentUtil = new FragmentUtil(getSupportFragmentManager(), R.id.content);
        fragmentUtil.addFadeInOut(HomeCareFragment.newInstance(), null);

        careBtn.setOnClickListener(view -> {
            if (careBtn.getCurrentTextColor() == getResources().getColor(R.color.black)) {
                careBtn.setTextColor(getResources().getColor(R.color.orangeKcal));
                diaryBtn.setTextColor(getResources().getColor(R.color.black));
                fragmentUtil.addFadeInOut(HomeCareFragment.newInstance(), null);
                fragmentUtil.clear();
            }
        });

        diaryBtn.setOnClickListener(view -> {
            if (diaryBtn.getCurrentTextColor() == getResources().getColor(R.color.black)) {
                careBtn.setTextColor(getResources().getColor(R.color.black));
                diaryBtn.setTextColor(getResources().getColor(R.color.orangeKcal));
                fragmentUtil.addFadeInOut(HomeDiaryFragment.newInstance(), null);
                fragmentUtil.clear();
            }
        });
    }
}