package cn.st.android.femalestar.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import cn.st.android.femalestar.R;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        FragmentPagerAdapter fragmentPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            private String[] titles=getResources().getStringArray(R.array.main_tab_titles);
            @Override
            public Fragment getItem(int position) {
                return new Fragment();
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
