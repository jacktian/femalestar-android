package cn.st.android.femalestar.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.common.base.Preconditions;

import cn.st.android.femalestar.R;
import cn.st.android.femalestar.menstruation.search.SearchMenstruationFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        getSupportActionBar().hide();
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        MyFragmentPagerAdapter fragmentPagerAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(fragmentPagerAdapter.getTabView(i));
            }
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                TextView tv= (TextView) tab.getCustomView().findViewById(R.id.txt_tab);
                Drawable drawableLeft = ContextCompat.getDrawable(getApplicationContext(),imgs_selected[position]);
                drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
                tv.setCompoundDrawables(drawableLeft,null,null,null);
                tv.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                TextView tv= (TextView) tab.getCustomView().findViewById(R.id.txt_tab);
                Drawable drawableLeft = ContextCompat.getDrawable(getApplicationContext(),imgs_normal[position]);
                drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
                tv.setCompoundDrawables(drawableLeft,null,null,null);
                tv.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private int[] titles=new int[]{R.string.lable_menstruation_assistant,R.string.lable_health_assistant,R.string.lable_storage_assistant};
    private int[] imgs_normal=new int[]{R.drawable.ic_menstruation_normal,R.drawable.ic_health_normal,R.drawable.ic_storage_normal};
    private int[] imgs_selected=new int[]{R.drawable.ic_menstruation_selected,R.drawable.ic_health_selected,R.drawable.ic_storage_selected};

    class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        private Context context;



        public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context= Preconditions.checkNotNull(context);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return new SearchMenstruationFragment();
            }
            return new Fragment();
        }

        @Override
        public int getCount() {
            return titles.length;
        }



        @Override
        public CharSequence getPageTitle(int position) {
            return getString(titles[position]);
        }


        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.main_bottom_tab, null);
            TextView tv = (TextView) v.findViewById(R.id.txt_tab);
            tv.setText(titles[position]);
            if(position==0){
                Drawable drawableLeft = ContextCompat.getDrawable(getApplicationContext(),imgs_selected[position]);
                drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
                tv.setCompoundDrawables(drawableLeft,null,null,null);
                tv.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
            }else{
                Drawable drawableLeft = ContextCompat.getDrawable(getApplicationContext(),imgs_normal[position]);
                drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(), drawableLeft.getMinimumHeight());
                tv.setCompoundDrawables(drawableLeft,null,null,null);
                tv.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
            }

            return v;
        }
    }
}
