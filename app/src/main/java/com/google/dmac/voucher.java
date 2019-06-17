package com.google.dmac;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

public class voucher extends AppCompatActivity {
private TabLayout tblayout;
private AppBarLayout applayout;
private ViewPager viewpager1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        applayout=(AppBarLayout)findViewById(R.id.appbar);
        tblayout=(TabLayout)findViewById(R.id.tablay);
        viewpager1=(ViewPager)findViewById(R.id.bigfragment);
        viewPageAdapter vpa= new viewPageAdapter(getSupportFragmentManager());
        vpa.AddFragment(new fragmentredeemed(),"Redeemed Vouchers");
        vpa.AddFragment(new fragmentmyvoucher(),"My Vouchers");
        viewpager1.setAdapter(vpa);
        tblayout.setupWithViewPager(viewpager1);
    }
}
