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
import co.edu.eafit.numericalanalysis.onevariable.AdapterTabsOV.PagerAdapterFalseRule;

public class FalseRule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_false_rule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_FalseRule);
        tabLayout.addTab(tabLayout.newTab().setText("Incremental Searches"));
        tabLayout.addTab(tabLayout.newTab().setText("False Rule Algorithm"));
        tabLayout.addTab(tabLayout.newTab().setText("Iterations Result"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager_FalseRule);
        final PagerAdapterFalseRule adapter = new PagerAdapterFalseRule
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

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

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
            Intent inte = new Intent(FalseRule.this,MainActivity.class);
            startActivity(inte);
        }

        if (id == R.id.action_Bisection_Item) {
            Intent inte = new Intent(FalseRule.this,Bisection.class);
            startActivity(inte);
        }

        if (id == R.id.action_FalseRule_Item) {
            return true;
        }

        if (id == R.id.action_FixedPoint_Item) {
            Intent inte = new Intent(FalseRule.this,FixedPoint.class);
            startActivity(inte);
        }

        if (id == R.id.action_NewtonRaphson_Item) {
            Intent inte = new Intent(FalseRule.this,NewtonRaphson.class);
            startActivity(inte);
        }

        if (id == R.id.action_NewtonRaphsonModified_Item) {
            Intent inte = new Intent(FalseRule.this,NewtonRaphsonModified.class);
            startActivity(inte);
        }

        if (id == R.id.action_Secant_Item) {
            Intent inte = new Intent(FalseRule.this,Secant.class);
            startActivity(inte);
        }

        return super.onOptionsItemSelected(item);
    }
}
