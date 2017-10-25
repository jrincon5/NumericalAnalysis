package co.edu.eafit.numericalanalysis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import co.edu.eafit.numericalanalysis.interpolation.LagrangeInterpolation;
import co.edu.eafit.numericalanalysis.interpolation.NewtonInterpolation;
import co.edu.eafit.numericalanalysis.interpolation.ReductionSystems;

public class Interpolation extends AppCompatActivity implements View.OnClickListener{
    Button btn_Reduction_Systems, btn_Lagrange_Interpolation, btn_Newton_Interpolation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interpolation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_Reduction_Systems=(Button) findViewById(R.id.button_Reduction_Systems);
        btn_Lagrange_Interpolation=(Button) findViewById(R.id.button_Lagrange_Interpolation);
        btn_Newton_Interpolation=(Button) findViewById(R.id.button_Newton_Interpolation);

        btn_Reduction_Systems.setOnClickListener(this);
        btn_Lagrange_Interpolation.setOnClickListener(this);
        btn_Newton_Interpolation.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Home) {
            Intent inte = new Intent(Interpolation.this,MainActivity.class);
            startActivity(inte);
        }

        if (id == R.id.action_OneVariable) {
            Intent inte = new Intent(Interpolation.this,OneVariable.class);
            startActivity(inte);
        }

        if (id == R.id.action_EquationsSystems) {
            Intent inte = new Intent(Interpolation.this,EquiationsSystems.class);
            startActivity(inte);
        }

        if (id == R.id.action_Interpolation) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_Reduction_Systems:
                Intent inte = new Intent(Interpolation.this, ReductionSystems.class);
                startActivity(inte);
                break;

            case R.id.button_Lagrange_Interpolation:
                Intent inte2= new Intent(Interpolation.this, LagrangeInterpolation.class);
                startActivity(inte2);
                break;

            case R.id.button_Newton_Interpolation:
                Intent inte3= new Intent(Interpolation.this, NewtonInterpolation.class);
                startActivity(inte3);
                break;
        }
    }

}
