package co.edu.eafit.numericalanalysis;

import android.content.Intent;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import co.edu.eafit.numericalanalysis.onevariable.Bisection;
import co.edu.eafit.numericalanalysis.onevariable.FalseRule;
import co.edu.eafit.numericalanalysis.onevariable.FixedPoint;
import co.edu.eafit.numericalanalysis.onevariable.NewtonRaphson;
import co.edu.eafit.numericalanalysis.onevariable.NewtonRaphsonModified;
import co.edu.eafit.numericalanalysis.onevariable.Secant;

public class OneVariable extends AppCompatActivity implements View.OnClickListener{
    Button btn_bisection, btn_FalseRulse, btn_FixedPoint, btn_NewtonRaphson, btn_Secant, btn_NewtonRaphsonModified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_variable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_bisection = (Button) findViewById(R.id.button_Bisection);
        btn_FalseRulse = (Button) findViewById(R.id.button_FalseRule);
        btn_FixedPoint = (Button) findViewById(R.id.button_FixedPoint);
        btn_NewtonRaphson = (Button) findViewById(R.id.button_NewtonRaphosn);
        btn_NewtonRaphsonModified=(Button) findViewById(R.id.button_NewtonModified);
        btn_Secant = (Button) findViewById(R.id.button_Secant);

        btn_bisection.setOnClickListener(this);
        btn_FalseRulse.setOnClickListener(this);
        btn_FixedPoint.setOnClickListener(this);
        btn_NewtonRaphson.setOnClickListener(this);
        btn_NewtonRaphsonModified.setOnClickListener(this);
        btn_Secant.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_Bisection:
                Intent inte = new Intent(OneVariable.this,Bisection.class);
                startActivity(inte);
                break;

            case R.id.button_FalseRule:
                Intent inte2 = new Intent(OneVariable.this,FalseRule.class);
                startActivity(inte2);
                break;

            case R.id.button_FixedPoint:
                Intent inte3 = new Intent(OneVariable.this,FixedPoint.class);
                startActivity(inte3);
                break;
            case R.id.button_NewtonRaphosn:
                Intent inte4 = new Intent(OneVariable.this,NewtonRaphson.class);
                startActivity(inte4);
                break;
            case R.id.button_NewtonModified:
                Intent inte6 = new Intent(OneVariable.this,NewtonRaphsonModified.class);
                startActivity(inte6);
                break;
            case R.id.button_Secant:
                Intent inte5 = new Intent(OneVariable.this,Secant.class);
                startActivity(inte5);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Home) {
            Intent inte = new Intent(OneVariable.this,MainActivity.class);
            startActivity(inte);
        }

        if (id == R.id.action_OneVariable) {
            return true;
        }

        if (id == R.id.action_EquationsSystems) {
            Intent inte = new Intent(OneVariable.this,EquiationsSystems.class);
            startActivity(inte);
        }

        if (id == R.id.action_Interpolation) {
            Intent inte = new Intent(OneVariable.this,Interpolation.class);
            startActivity(inte);
        }

        return super.onOptionsItemSelected(item);
    }
}