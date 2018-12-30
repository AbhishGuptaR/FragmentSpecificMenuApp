package com.example.android.fragmentspecificmenuapp;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    String[] menuTitle={"CALLS","CHAT","CONTACTS"};
    int[] notificationCount={0,10,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("WhatsApp");
        getSupportActionBar().setElevation(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        try{
            setupNotification();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        CallsFragment callsFragment = new CallsFragment();
        ChatsFragment chatsFragment = new ChatsFragment();
        ContactsFragment contactsFragment = new ContactsFragment();
        viewPagerAdapter.addFragment(callsFragment,"Calls");
        viewPagerAdapter.addFragment(chatsFragment,"Chats");
        viewPagerAdapter.addFragment(contactsFragment,"Contacts");
        viewPager.setAdapter(viewPagerAdapter);
    }

    private View setBadge(int i){
        View view = getLayoutInflater().inflate(R.layout.menu_layout,null);
        TextView title = (TextView) findViewById(R.id.menutitle);
        TextView badge = (TextView) findViewById(R.id.menubadge);
        title.setText(menuTitle[i]);
        if(notificationCount[i]>0){
            badge.setVisibility(View.VISIBLE);
            badge.setText(""+notificationCount[i]);
        }
        else{
            badge.setVisibility(View.GONE);
        }
        return view;
    }

    private void setupNotification(){
        for(int i=0;i<notificationCount.length;i++){
            Log.e("Hello","Inside For Loop");
            //TabLayout.Tab tab = tabLayout.newTab();
            //tab.setCustomView(setBadge(i));
            //tabLayout.addTab(tab);
            tabLayout.getTabAt(i).setCustomView(setBadge(i));

        }
    }

}
