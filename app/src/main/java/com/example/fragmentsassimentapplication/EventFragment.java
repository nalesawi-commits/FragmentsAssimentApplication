package com.example.fragmentsassimentapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class EventFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private String[] tabTitles = {"All Events", "Party", "Programming"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        // تعيين ال adapter للـ ViewPager
        viewPager.setAdapter(new EventPagerAdapter(this));

        // ربط الـ TabLayout مع ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabTitles[position])
        ).attach();

        return view;
    }
}
 class EventPagerAdapter extends FragmentStateAdapter {
 public EventPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AllEventsFragment();   // All Events
            case 1:
                return new PartyFragment();         // Party
            case 2:
                return new ProgrammingFragment();  // Programming
            default:
                return new AllEventsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;  // عدد التبويبات
    }
}