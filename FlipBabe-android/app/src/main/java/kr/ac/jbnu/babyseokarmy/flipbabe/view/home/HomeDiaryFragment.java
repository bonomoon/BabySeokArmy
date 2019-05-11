package kr.ac.jbnu.babyseokarmy.flipbabe.view.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import kr.ac.jbnu.babyseokarmy.flipbabe.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeDiaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeDiaryFragment extends Fragment {

    public HomeDiaryFragment() {
        // Required empty public constructor
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_diary, container, false);
    }

}
