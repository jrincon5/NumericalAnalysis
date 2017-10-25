package co.edu.eafit.numericalanalysis.equiationssystems;

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
import co.edu.eafit.numericalanalysis.equiationssystems.AdapterTabsES.PagerAdapterJacobi;
import co.edu.eafit.numericalanalysis.equiationssystems.AdapterTabsES.PagerAdapterRichardson;

public class Richardson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richardson);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_richardson);
        tabLayout.addTab(tabLayout.newTab().setText("Richardson Algorithm"));
        tabLayout.addTab(tabLayout.newTab().setText("Richardson Solution"));
        tabLayout.addTab(tabLayout.newTab().setText("Result Iterations"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager_richardson);
        final PagerAdapterRichardson adapter = new PagerAdapterRichardson
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
        getMenuInflater().inflate(R.menu.menu_equation_system, menu);

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
            Intent inte = new Intent(Richardson.this, MainActivity.class);
            startActivity(inte);
        }

        if (id == R.id.action_GaussElimination_Item) {
            Intent inte = new Intent(Richardson.this, GaussElimination.class);
            startActivity(inte);
        }

        if (id == R.id.action_FactoringLU) {
            Intent inte = new Intent(Richardson.this, FactoringLU.class);
            startActivity(inte);
        }

        if (id == R.id.action_Jacobi) {
            Intent inte = new Intent(Richardson.this, Jacobi.class);
            startActivity(inte);
        }

        if (id == R.id.action_GaussSeidel_Item) {
            Intent inte = new Intent(Richardson.this, Richardson.class);
            startActivity(inte);
        }

        if (id == R.id.action_Richardson_Item) {
            return true;
        }

        if (id == R.id.action_SOR_Item) {
            Intent inte = new Intent(Richardson.this, SOR.class);
            startActivity(inte);
        }
        return super.onOptionsItemSelected(item);
    }
}


