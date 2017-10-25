package co.edu.eafit.numericalanalysis.onevariable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import co.edu.eafit.numericalanalysis.MainActivity;
import co.edu.eafit.numericalanalysis.R;
import co.edu.eafit.numericalanalysis.onevariable.AdapterTabsOV.PagerAdapterBisection;


public class Bisection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_bisection);
        tabLayout.addTab(tabLayout.newTab().setText("Incremental Searches"));
        tabLayout.addTab(tabLayout.newTab().setText("Bisection Algorithm"));
        tabLayout.addTab(tabLayout.newTab().setText("Result Iterations"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager_bisection);
        final PagerAdapterBisection adapter = new PagerAdapterBisection
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }
/*
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_Calculate:
                Intent inte = new Intent(Bisection.this,Bisection.class);
                startActivity(inte);
                break;
        }
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_one_variable, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        if (id == R.id.action_Home_Item) {
            Intent inte = new Intent(Bisection.this,MainActivity.class);
            startActivity(inte);
        }

        if (id == R.id.action_Bisection_Item) {
            return true;
        }

        if (id == R.id.action_FalseRule_Item) {
            Intent inte = new Intent(Bisection.this,FalseRule.class);
            startActivity(inte);
        }

        if (id == R.id.action_FixedPoint_Item) {
            Intent inte = new Intent(Bisection.this,FixedPoint.class);
            startActivity(inte);
        }

        if (id == R.id.action_NewtonRaphson_Item) {
            Intent inte = new Intent(Bisection.this,NewtonRaphson.class);
            startActivity(inte);
        }

        if (id == R.id.action_NewtonRaphsonModified_Item) {
            Intent inte = new Intent(Bisection.this,NewtonRaphsonModified.class);
            startActivity(inte);
        }

        if (id == R.id.action_Secant_Item) {
            Intent inte = new Intent(Bisection.this,Secant.class);
            startActivity(inte);
        }

        return super.onOptionsItemSelected(item);
    }
}