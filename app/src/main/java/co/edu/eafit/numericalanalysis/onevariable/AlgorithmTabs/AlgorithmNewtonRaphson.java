package co.edu.eafit.numericalanalysis.onevariable.AlgorithmTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.boris.expr.Expr;
import org.boris.expr.ExprEvaluatable;
import org.boris.expr.parser.ExprParser;
import org.boris.expr.util.Exprs;
import org.boris.expr.util.SimpleEvaluationContext;

import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.numericalanalysis.R;
import co.edu.eafit.numericalanalysis.onevariable.IterationsResults;
import co.edu.eafit.numericalanalysis.onevariable.StopCriteria;

/**
 * Created by Jrincon on 17/03/2016.
 */
public class AlgorithmNewtonRaphson extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    View rootView;
    Button btn_newton_calcular;
    Spinner cb_resultados;
    double X0, X1, delta, tolerance, Xin;
    String functionGx;
    TextView salida;
    static String funcion;
    EditText txt_iterations, txt_tolerance, txt_delta, txt_Gx, txt_Xnum;
    int iterations;
    static List<String> Xvalue = new ArrayList<String>();
    static List<String> Nvalue = new ArrayList<String>();
    static List<String> FXvalue = new ArrayList<String>();
    static List<String> ERRORvalue = new ArrayList<String>();
    IterationsResults iter=new IterationsResults();
    StopCriteria sc = new StopCriteria();
    public static List<String> results = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        results.add("");
        rootView = inflater.inflate(R.layout.tab_algorithm_newton_raphson, container, false);
        btn_newton_calcular = (Button) rootView.findViewById(R.id.button_NewtonRaphson_Calculate);
        txt_Gx = (EditText) rootView.findViewById(R.id.inpt_NewtonRaphson_function);
        txt_Xnum = (EditText) rootView.findViewById(R.id.inpt_NewtonRaphson_value);
        txt_iterations = (EditText) rootView.findViewById(R.id.inpt_NewtonRaphson_iterations);
        txt_tolerance = (EditText) rootView.findViewById(R.id.inpt_NewtonRaphson_tolerance);
        txt_delta = (EditText) rootView.findViewById(R.id.inpt_NewtonRaphson_delta);
        cb_resultados = (Spinner) rootView.findViewById(R.id.spinner_NewtonRaphson_resultados);
        btn_newton_calcular.setOnClickListener(this);
        cb_resultados.setOnItemSelectedListener(this);
        salida = (TextView) rootView.findViewById(R.id.txt_salida_push);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item, results);
        cb_resultados.setAdapter(dataAdapter);
        makingSpinner(results);
        return rootView;
    }

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button_NewtonRaphson_Calculate:
                    boolean empty=true;
                    try {
                        iterations=Integer.parseInt(txt_iterations.getText().toString());
                        delta=Double.parseDouble(txt_delta.getText().toString());
                        tolerance=Double.parseDouble(txt_tolerance.getText().toString());
                        Xin=Double.parseDouble(txt_Xnum.getText().toString());
                        functionGx=txt_Gx.getText().toString();
                        empty=false;
                    }catch (Exception e){
                        Toast.makeText(getContext(), "All fields must have values", Toast.LENGTH_SHORT).show();
                    }
                    if(!empty)NewtonRaphsonMethod(iterations, tolerance, delta, functionGx.replaceAll("exp", "eXp"), Xin);
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // On selecting a spinner item
            String item = parent.getItemAtPosition(position).toString();
            // Showing selected spinner item
            try {
                X0=value1(item);
                X1=value2(item);
            }catch (Exception e){
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    public void makingSpinner(List<String> values) {
        if(!values.isEmpty()){
            results.clear();
            results.add("Intervals");
            results.addAll(values);
        }
    }

    double value1(String valor){
        String concat="";
        for(int i=1;true;i++){
            if(valor.charAt(i)==' ')break;
            concat+=valor.charAt(i);
        }
        return Double.parseDouble(concat);
    }

    double value2(String valor){
        String concat="";
        for(int i=valor.length()-1;true;i--){
            if(valor.charAt(i)==' '){
                concat=valor.substring(i,valor.length()-1);
                break;
            }
        }
        return Double.parseDouble(concat);
    }

    public void setFuncion(String f){
        this.funcion=f;
    }

    void NewtonRaphsonMethod(int iterations, double tolerance, double delta, String functionGx, double Xin){
        try {
            String function=funcion.replaceAll("exp","eXp");
            double a=X0,b=X1,xprev;  // Hacer validaciones con los intervalos
            SimpleEvaluationContext context = new SimpleEvaluationContext();
            for(int i=0;sc.c1(iterations,i); i++){
                xprev=Xin;
                Expr Gx = ExprParser.parse(functionGx.replaceAll("x", "(" + xprev + ")"));
                Expr Fx = ExprParser.parse(function.replaceAll("x", "(" + xprev + ")"));
                Exprs.toUpperCase(Gx);
                Exprs.toUpperCase(Fx);
                Xvalue.add(Xin + ""); // ColumnaX
                Nvalue.add(i + ""); // Columna N
                if (Gx instanceof ExprEvaluatable && Fx instanceof ExprEvaluatable) {
                    Gx = ((ExprEvaluatable) Gx).evaluate(context);
                    Fx = ((ExprEvaluatable) Fx).evaluate(context);
                    Xin=xprev-(Double.parseDouble(Fx.toString())/Double.parseDouble(Gx.toString()));
                }
                if(sc.c2(Double.parseDouble(Fx.toString()),delta)){
                    Toast.makeText(getContext(), "Method finished by delta value", Toast.LENGTH_SHORT).show();
                    break;
                }
                if(sc.c3(Xin, xprev, tolerance)){
                    Toast.makeText(getContext(), "Method finished by tolerance value", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (!sc.c1(iterations,i+1)){
                    Toast.makeText(getContext(), "Method finished by iterations", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (sc.c4(a,b,Xin)){
                    Toast.makeText(getContext(), "Method invalid", Toast.LENGTH_SHORT).show();
                    break;
                }
                FXvalue.add((Xin+"")); // Columna F(X)
                ERRORvalue.add(Math.abs(Xin - xprev) + ""); // Columna Error
            }
            salida.setText(Xin+"");
            iter.setTypeMethod(false);
            iter.makingList(Nvalue, null, null, Xvalue, FXvalue, ERRORvalue);
            Xvalue.clear();Nvalue.clear();FXvalue.clear();ERRORvalue.clear();
        }catch (Exception e){
        }
    }
}