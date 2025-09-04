package com.example.leyan_hw2_fragmentsnavigationbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabsPagerAdapter extends FragmentStateAdapter {

    public TabsPagerAdapter(@NonNull Fragment hostFragment) {
        super(hostFragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new TabOneFragment();
            case 1: return new TabTwoFragment();
            default: return new TabThreeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean containsItem(long itemId) {
        return itemId >= 0 && itemId < getItemCount();
    }
}
