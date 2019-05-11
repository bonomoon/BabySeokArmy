package kr.ac.jbnu.babyseokarmy.flipbabe.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import kr.ac.jbnu.babyseokarmy.flipbabe.R;
import kr.ac.jbnu.babyseokarmy.flipbabe.view.base.BaseFragment;

public class HomeCareFragment extends BaseFragment {

    public HomeCareFragment() {
        super(R.layout.fragment_home_care);
    }

    public static HomeCareFragment newInstance() {
        HomeCareFragment fragment = new HomeCareFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public void onCreateView(LayoutInflater inflate, View v) {

    }
}
