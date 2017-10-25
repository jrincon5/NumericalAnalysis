package co.edu.eafit.numericalanalysis.interpolation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import co.edu.eafit.numericalanalysis.MainActivity;
import co.edu.eafit.numericalanalysis.R;
import co.edu.eafit.numericalanalysis.equiationssystems.FactoringLU;
import co.edu.eafit.numericalanalysis.equiationssystems.GaussElimination;
import co.edu.eafit.numericalanalysis.equiationssystems.Jacobi;
import co.edu.eafit.numericalanalysis.equiationssystems.Richardson;
import co.edu.eafit.numericalanalysis.interpolation.AdapterTabsIP.PagerAdapterReductionSystems;

public class ReductionSystems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reduction_systems);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_reduction_systems);
        tabLayout.addTab(tabLayout.newTab().setText("Reduction Systems Algorithm"));
        tabLayout.addTab(tabLayout.newTab().setText("Reduction Systems Solution"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager_reduction_systems);
        final PagerAdapterReductionSystems adapter = new PagerAdapterReductionSystems
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_interpolation, menu);

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
            Intent inte = new Intent(ReductionSystems.this, MainActivity.class);
            startActivity(inte);
        }

        if (id == R.id.action_ReductionSystems_Item) {
            return true;
        }

        if (id == R.id.action_LagrangeInterpolation_Item) {
            Intent inte = new Intent(ReductionSystems.this, LagrangeInterpolation.class);
            startActivity(inte);
        }

        if (id == R.id.action_NewtonInterpolation_Item) {
            Intent inte = new Intent(ReductionSystems.this, NewtonInterpolation.class);
            startActivity(inte);
        }
        return super.onOptionsItemSelected(item);
    }

}
