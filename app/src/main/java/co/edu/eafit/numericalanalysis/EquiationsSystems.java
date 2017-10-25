package co.edu.eafit.numericalanalysis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import co.edu.eafit.numericalanalysis.equiationssystems.FactoringLU;
import co.edu.eafit.numericalanalysis.equiationssystems.GaussElimination;
import co.edu.eafit.numericalanalysis.equiationssystems.GaussSeidel;
import co.edu.eafit.numericalanalysis.equiationssystems.Jacobi;
import co.edu.eafit.numericalanalysis.equiationssystems.Richardson;
import co.edu.eafit.numericalanalysis.equiationssystems.SOR;

public class EquiationsSystems extends AppCompatActivity implements View.OnClickListener{
    Button btn_Gauss_Elimination, btn_Factoring_Lu, btn_Jacobi, btn_Gauss_Seidel, btn_Richardson, btn_SOR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equiations_systems);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_Gauss_Elimination = (Button) findViewById(R.id.button_Gauss_Eliminatiom);
        btn_Factoring_Lu = (Button) findViewById(R.id.button_Factoring_LU);
        btn_Jacobi = (Button) findViewById(R.id.button_Jacobi);
        btn_Gauss_Seidel = (Button) findViewById(R.id.button_Gauss_Seidel);
        btn_Richardson = (Button) findViewById(R.id.button_Richardson);
        btn_SOR = (Button) findViewById(R.id.button_SOR);

        btn_Gauss_Elimination.setOnClickListener(this);
        btn_Factoring_Lu.setOnClickListener(this);
        btn_Jacobi.setOnClickListener(this);
        btn_Gauss_Seidel.setOnClickListener(this);
        btn_Richardson.setOnClickListener(this);
        btn_SOR.setOnClickListener(this);
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
            Intent inte = new Intent(EquiationsSystems.this,MainActivity.class);
            startActivity(inte);
        }

        if (id == R.id.action_OneVariable) {
            Intent inte = new Intent(EquiationsSystems.this,OneVariable.class);
            startActivity(inte);
        }

        if (id == R.id.action_EquationsSystems) {
            return true;
        }

        if (id == R.id.action_Interpolation) {
            Intent inte = new Intent(EquiationsSystems.this,Interpolation.class);
            startActivity(inte);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_Gauss_Eliminatiom:
                Intent inte = new Intent(EquiationsSystems.this,GaussElimination.class);
                startActivity(inte);
                break;

            case R.id.button_Factoring_LU:
                Intent inte2 = new Intent(EquiationsSystems.this,FactoringLU.class);
                startActivity(inte2);
                break;

            case R.id.button_Jacobi:
                Intent inte3 = new Intent(EquiationsSystems.this,Jacobi.class);
                startActivity(inte3);
                break;

            case R.id.button_Gauss_Seidel:
                Intent inte4 = new Intent(EquiationsSystems.this,GaussSeidel.class);
                startActivity(inte4);
                break;

            case R.id.button_Richardson:
                Intent inte5 = new Intent(EquiationsSystems.this,Richardson.class);
                startActivity(inte5);
                break;

            case R.id.button_SOR:
                Intent inte6 = new Intent(EquiationsSystems.this,SOR.class);
                startActivity(inte6);
                break;
        }
    }
}