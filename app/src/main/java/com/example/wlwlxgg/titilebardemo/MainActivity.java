package com.example.wlwlxgg.titilebardemo;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private HorizontalScrollView horizontalScrollView;
    private LinearLayout ll, ll1, ll2;
    private Button add, delete;
    private GridView grid1, grid2;
    private GridViewAdapter adapter1, adapter2;
    private RadioGroup radioGroup;
    private ViewPager viewPager;
    private ImageView cursor;
    private ArrayList<String> title, addList, deleteList;
    private ArrayList<Fragment> fragmentList;
    private int baseId = 1000;
    private Context mContext;
    private float lastLeft;
    private MyFragmentAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initTitle();
        initList();
        initView();
        initRadioGroup();
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new MyPageChangedListener());
        viewPager.setAdapter(adapter);
    }

    private void initView(){
        horizontalScrollView = (HorizontalScrollView)findViewById(R.id.scrollView);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new MyFragmentAdapter(getSupportFragmentManager(), fragmentList);
        cursor = (ImageView) findViewById(R.id.cursor);
        ll = (LinearLayout) findViewById(R.id.ll);
        add = (Button) findViewById(R.id.add);
        delete = (Button) findViewById(R.id.delete);
        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll1.setVisibility(View.VISIBLE);
        ll2.setVisibility(View.GONE);
        grid1 = (GridView) findViewById(R.id.grid1);
        adapter1 = new GridViewAdapter(mContext, addList);
        grid1.setAdapter(adapter1);
        grid2 = (GridView) findViewById(R.id.grid2);
        adapter2 = new GridViewAdapter(mContext, deleteList);
        grid2.setAdapter(adapter2);
        radioGroup = new RadioGroup(this);
        radioGroup.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
        ll.addView(radioGroup);
        grid1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteList.add(addList.get(position));
                addList.remove(position);
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });
        grid2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addList.add(deleteList.get(position));
                deleteList.remove(position);
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });
    }

    private void initList() {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < addList.size(); i++) {
            Fragment fragment = MyFragment.newInstance(addList.get(i));
            fragmentList.add(fragment);
        }
    }

    private void initTitle() {
        title = new ArrayList<>();
        addList = new ArrayList<>();
        deleteList = new ArrayList<>();
        title.add("头条");title.add("娱乐");title.add("要闻");title.add("热点");
        title.add("体育");title.add("北京");title.add("财经");title.add("视频");
        title.add("网易号");title.add("科技");title.add("段子");title.add("图片");
        title.add("直播");title.add("汽车");title.add("时尚");title.add("轻松一刻");
        title.add("军事");title.add("股票");title.add("历史");title.add("房产");
        title.add("游戏");title.add("独家");title.add("家居");title.add("财务");
        for (int i = 0; i < 10; i ++) {
            addList.add(title.get(i));
        }
        for (int i = 10; i < title.size(); i++) {
            deleteList.add(title.get(i));
        }
    }

    private void initRadioGroup() {
        radioGroup.removeAllViews();
        for (int i = 0; i < addList.size(); i ++) {
            RadioButton button = new RadioButton(mContext);
            button.setText(addList.get(i));
            if (i == 0) button.setTextColor(ContextCompat.getColor(this, R.color.red));
            else button.setTextColor(ContextCompat.getColor(this, R.color.gray));
            button.setButtonDrawable(android.R.color.transparent);
            button.setId(baseId + i);
            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
            button.setLayoutParams(lp);
            button.setPadding(20, 15, 20, 15);
            button.setGravity(Gravity.CENTER);
            button.setTextSize(20);
            if (i == 0) {
                int itemWidth = (int) button.getPaint().measureText(addList.get(i));
                cursor.setLayoutParams(new LinearLayout.LayoutParams(itemWidth + button.getPaddingLeft() + button.getPaddingRight(), 4));
            }
            radioGroup.addView(button);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //有个疑问，记录一下，button.getchildcheckedid ?= checkedid
                RadioButton button = (RadioButton) findViewById(checkedId);
                for (int i = 0; i < addList.size(); i++) {
                    RadioButton button1 = (RadioButton)findViewById(baseId + i);
                    if (i == checkedId - baseId)
                        button1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                    else button1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
                }

                LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(button.getRight() - button.getLeft(), 4);
                ll.setMargins(button.getLeft(), 0, 0, 0);
                cursor.setLayoutParams(ll);

                AnimationSet animationSet = new AnimationSet(true);
                TranslateAnimation translateAnimation = new TranslateAnimation(lastLeft, button.getLeft(), 0f, 0f);
                animationSet.addAnimation(translateAnimation);
                animationSet.setFillBefore(true);
                animationSet.setFillAfter(true);
                animationSet.setDuration(300);
                cursor.startAnimation(translateAnimation);
                cursor.clearAnimation();
                lastLeft = button.getLeft();
                viewPager.setCurrentItem(checkedId - baseId);
                horizontalScrollView.smoothScrollTo((int)lastLeft - (int)getResources().getDimension(R.dimen.left), 0);

            }
        });
    }

    public class MyFragmentAdapter extends FragmentPagerAdapter{
        private ArrayList<Fragment> fragments;
        private FragmentManager fm;
        public MyFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fm = fm;
            this.fragments = fragments;
        }

        public void setFragments(ArrayList<Fragment> fragments) {
            if(this.fragments != null){
                FragmentTransaction ft = fm.beginTransaction();
                for(Fragment f:this.fragments){
                    ft.remove(f);
                }
                ft.commit();
                ft = null;
                fm.executePendingTransactions();
            }
            this.fragments = fragments;
            notifyDataSetChanged();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragments.get(arg0);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public class MyPageChangedListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            RadioButton button = (RadioButton) findViewById(baseId + position);
            button.performClick();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                ll1.setVisibility(View.GONE);
                ll2.setVisibility(View.VISIBLE);
                break;
            case R.id.delete:
                ll1.setVisibility(View.VISIBLE);
                ll2.setVisibility(View.GONE);
                initList();
                initRadioGroup();
                viewPager.setCurrentItem(0);
                adapter.setFragments(fragmentList);
                break;
        }
    }
}
