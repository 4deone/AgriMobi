package com.bydeone.agrimobi.Models;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bydeone.agrimobi.Controllers.OneFragment;

public class AllFragmentAdapter extends FragmentStatePagerAdapter {

    public AllFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        OneFragment oneFragment = new OneFragment();
        Bundle bundle = new Bundle();
        position = position +1;
        bundle.putString("message", "Hello from page " + position);
        oneFragment.setArguments(bundle);

        return oneFragment;
    }

    @Override
    public int getCount() {
        return 100;
    }
}
