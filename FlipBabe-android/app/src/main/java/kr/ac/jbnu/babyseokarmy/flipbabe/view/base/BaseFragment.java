package kr.ac.jbnu.babyseokarmy.flipbabe.view.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import kr.ac.jbnu.babyseokarmy.flipbabe.R;

public abstract class BaseFragment extends Fragment {

//    protected Bundle args;
    protected Context context;
//    private Activity activity;
    private String TAG = BaseFragment.class.getSimpleName();
    private View v;
    private int layoutId;

    public BaseFragment() {
        TAG = getClass().getSimpleName();
    }

    public BaseFragment(@LayoutRes int resId) {
        TAG = getClass().getSimpleName();
        this.layoutId = resId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (layoutId == 0) {
            Log.e(TAG, "layoutId is zero.");
            return null;
        }

        if (v == null) {
            v = inflater.inflate(layoutId, container, false);
        }

        onCreateView(inflater, v);

        if(v.getBackground() == null)
            v.setBackgroundColor(context.getResources().getColor(R.color.white));
        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        onFocusFragment();
    }

    public abstract void onCreateView(LayoutInflater inflate, View v);


    private void onFocusFragment() {

    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDestroyView() {
        ViewGroup parentViewGroup = (ViewGroup) v.getParent();
        if (null != parentViewGroup) {
            parentViewGroup.removeView(v);
        }

        super.onDestroyView();
    }
}