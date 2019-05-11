package kr.ac.jbnu.babyseokarmy.flipbabe.view.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import kr.ac.jbnu.babyseokarmy.flipbabe.R;
import kr.ac.jbnu.babyseokarmy.flipbabe.view.base.BaseFragment;

public class HomeDiaryFragment extends BaseFragment {

    public HomeDiaryFragment() {
        super(R.layout.fragment_home_diary);
    }

    public static HomeDiaryFragment newInstance() {
        HomeDiaryFragment fragment = new HomeDiaryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateView(LayoutInflater inflate, View v) {

    }

}
