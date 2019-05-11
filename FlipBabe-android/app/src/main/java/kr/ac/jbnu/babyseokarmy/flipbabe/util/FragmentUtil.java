package kr.ac.jbnu.babyseokarmy.flipbabe.util;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import kr.ac.jbnu.babyseokarmy.flipbabe.R;

/**
 * Copyright 2018 BongO Moon All rights reserved.
 *
 * @author BongO Moon(http://github.com/bongOmoon)
 * @since 2018.10.09
 */
public class FragmentUtil {

    private static final String TAG = FragmentUtil.class.getSimpleName();
    private FragmentManager mFragmentManager;
    private int mContainerId;

    public FragmentUtil(FragmentManager fm) {
        this.mFragmentManager = fm;
    }

    public FragmentUtil(FragmentManager fm, int containerId) {
        this.mFragmentManager = fm;
        this.mContainerId = containerId;
    }

    public void add(Fragment fragment, Bundle b) {
        if (mContainerId == 0) {
            Log.e(TAG, "Fragment Container Layout Id is zero.");
            return;
        }

        if (mFragmentManager == null) {
            Log.e(TAG, "FragmentManager fm is null.");
            return;
        }

        if(b != null){
            fragment.setArguments(b);
        }

        if(mFragmentManager.getFragments().size() != 0){
            mFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fragment_in, R.anim.fragment_out, R.anim.fragment_in_backstack, R.anim.fragment_out_backstack)
                    .replace(mContainerId, fragment)
                    .addToBackStack(fragment.toString())
                    .commitAllowingStateLoss();
        }else{
            mFragmentManager.beginTransaction()
                    .add(mContainerId, fragment)
                    .commitAllowingStateLoss();
        }
    }

    public void addFadeInOut(Fragment fragment, Bundle b) {
        if (mContainerId == 0) {
            Log.e(TAG, "Fragment Container Layout Id is zero.");
            return;
        }

        if (mFragmentManager == null) {
            Log.e(TAG, "FragmentManager fm is null.");
            return;
        }

        if(b != null){
            fragment.setArguments(b);
        }

        if(mFragmentManager.getFragments().size() != 0){
            mFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                    .replace(mContainerId, fragment)
                    .addToBackStack(fragment.toString())
                    .commitAllowingStateLoss();
        } else{
            mFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .add(mContainerId, fragment)
                    .commitAllowingStateLoss();
        }
    }

    public Fragment getCurrentFragment() {
        return mFragmentManager.getFragments().get(mFragmentManager.getFragments().size()-1);
    }

    public void back() {
        if (mFragmentManager == null) {
            Log.e(TAG, "FragmentManager fm is null.");
            return;
        }

        mFragmentManager.popBackStack();
    }

    public void clear() {
        if (mFragmentManager == null) {
            Log.e(TAG, "FragmentManager fm is null.");
            return;
        }

        try {
            mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (IllegalStateException ise) {
            ise.printStackTrace();
        }
    }
//
//    public void showDialog(DialogFragment dialogFragment) {
//        if (mFragmentManager == null) {
//            Log.e(TAG, "FragmentManager fm is null.");
//            return;
//        }
//
//        dialogFragment.show(mFragmentManager, "dialog");
//    }
//
//    public void dismissDialog() {
//        if (mFragmentManager == null) {
//            Log.e(TAG, "FragmentManager fm is null.");
//            return;
//        }
//
//        Fragment fragment = mFragmentManager.findFragmentByTag("dialog");
//        if (fragment != null) {
//            mFragmentManager.beginTransaction().remove(fragment)
//                    .commitAllowingStateLoss();
//        }
//
//    }
}
