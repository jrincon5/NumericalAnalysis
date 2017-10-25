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
import co.edu.eafit.numericalanalysis.equiationssystems.AdapterTabsES.PagerAdapterFactoringLU;
import co.edu.eafit.numericalanalysis.equiationssystems.AdapterTabsES.PagerAdapterJacobi;

public class Jacobi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jacobi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_jacobi);
        tabLayout.addTab(tabLayout.newTab().setText("Jacobi Algorithm"));
        tabLayout.addTab(tabLayout.newTab().setText("Jacobi Solution"));
        tabLayout.addTab(tabLayout.newTab().setText("Result Iterations"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager_jacobi);
        final PagerAdapterJacobi adapter = new PagerAdapterJacobi
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
            Intent inte = new Intent(Jacobi.this,MainActivity.class);
            startActivity(inte);
        }

        if (id == R.id.action_GaussElimination_Item) {
            Intent inte = new Intent(Jacobi.this,GaussElimination.class);
            startActivity(inte);
        }

        if (id == R.id.action_FactoringLU) {
            Intent inte = new Intent(Jacobi.this,FactoringLU.class);
            startActivity(inte);
        }

        if (id == R.id.action_Jacobi) {
            return true;
        }

        if (id == R.id.action_GaussSeidel_Item) {
            Intent inte = new Intent(Jacobi.this,GaussSeidel.class);
            startActivity(inte);
        }

        if (id == R.id.action_Richardson_Item) {
            Intent inte = new Intent(Jacobi.this,Richardson.class);
            startActivity(inte);
        }

        if (id == R.id.action_SOR_Item) {
            Intent inte = new Intent(Jacobi.this,SOR.class);
            startActivity(inte);
        }

        return super.onOptionsItemSelected(item);
    }

}
