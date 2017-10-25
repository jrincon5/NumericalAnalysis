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
public class AlgorithmSecant extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    View rootView;
    Button btn_secant_calcular;
    Spinner cb_resultados;
    double X0, X1, delta, tolerance, a, b;
    TextView salida;
    static String funcion;
    EditText txt_iterations, txt_tolerance, txt_delta, txt_Xa, txt_Xb;
    int iterations;
    static List<String> Xvalue = new ArrayList<String>();
    static List<String> Nvalue = new ArrayList<String>();
    static List<String> Avalue = new ArrayList<String>();
    static List<String> Bvalue = new ArrayList<String>();
    static List<String> FXvalue = new ArrayList<String>();
    static List<String> ERRORvalue = new ArrayList<String>();
    IterationsResults iter=new IterationsResults();
    StopCriteria sc = new StopCriteria();
    public static List<String> results = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        results.add("");
        rootView = inflater.inflate(R.layout.tab_algorithm_secant, container, false);
        btn_secant_calcular=(Button) rootView.findViewById(R.id.button_Secant_Calculate);
        txt_Xa = (EditText) rootView.findViewById(R.id.inpt_Secant_intervalA);
        txt_Xb = (EditText) rootView.findViewById(R.id.inpt_Secant_intervalB);
        txt_iterations = (EditText) rootView.findViewById(R.id.inpt_Secant_iterations);
        txt_tolerance = (EditText) rootView.findViewById(R.id.inpt_Secant_tolerance);
        txt_delta = (EditText) rootView.findViewById(R.id.inpt_Secant_delta);
        cb_resultados=(Spinner) rootView.findViewById(R.id.spinner_Secant_resultados);
        btn_secant_calcular.setOnClickListener(this);
        cb_resultados.setOnItemSelectedListener(this);
        salida=(TextView) rootView.findViewById(R.id.txt_salida_push);
        ArrayAdapter<String> dataAdapter= new ArrayAdapter<String>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item,results);
        cb_resultados.setAdapter(dataAdapter);
        makingSpinner(results);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_Secant_Calculate:
                boolean empty=true;
                try {
                    iterations=Integer.parseInt(txt_iterations.getText().toString());
                    delta=Double.parseDouble(txt_delta.getText().toString());
                    tolerance=Double.parseDouble(txt_tolerance.getText().toString());
                    a = Double.parseDouble(txt_Xa.getText().toString());
                    b = Double.parseDouble(txt_Xb.getText().toString());
                    empty=false;
                }catch (Exception e){
                    Toast.makeText(getContext(),"All fields must have values",Toast.LENGTH_SHORT).show();
                }
                if(!empty)SecantMethod(a, b,iterations, tolerance, delta);
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

    void SecantMethod(double a, double b, int iterations, double tolerance, double delta) { // terminar
        try {
            double ValueDoubleFa, ValueDoubleFb, x=0;
            String r="";
            SimpleEvaluationContext context = new SimpleEvaluationContext();
            for (int i = 0; sc.c1(iterations, i); i++) {
                Expr Fa = ExprParser.parse(funcion.replaceAll("x", "(" + a + ")"));
                Expr Fb = ExprParser.parse(funcion.replaceAll("x", "(" + b + ")"));
                Exprs.toUpperCase(Fa);
                Exprs.toUpperCase(Fb);
                Nvalue.add(i + ""); // Columna N
                Avalue.add(a + ""); // Columna A
                Bvalue.add(b + ""); // Columna B
                if (Fa instanceof ExprEvaluatable && Fb instanceof ExprEvaluatable) {
                    Fa = ((ExprEvaluatable) Fa).evaluate(context);
                    Fb = ((ExprEvaluatable) Fb).evaluate(context);
                    ValueDoubleFa = Double.parseDouble(Fa + "");
                    ValueDoubleFb = Double.parseDouble(Fb + "");
                    if (sc.c2(Double.parseDouble(Fb.toString()), delta)) {
                        Toast.makeText(getContext(), "Method finished by delta value", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if (sc.c3(b, a, tolerance)) {
                        Toast.makeText(getContext(), "Method finished by tolerance value", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if (!sc.c1(iterations, i + 1)) {
                        Toast.makeText(getContext(), "Method finished by iterations", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    x= b - ((ValueDoubleFb * (b - a)) / (ValueDoubleFb - ValueDoubleFa));
                    a=b;
                    b=x;
                }
                Xvalue.add(x + ""); // ColumnaX
                FXvalue.add((Fb.toString())); // Columna F(X)
                ERRORvalue.add(Math.abs(b - a) + ""); // Columna Error
            }
            salida.setText("X = " + a);
            iter.setTypeMethod(true);
            iter.makingList(Nvalue, Avalue, Bvalue, Xvalue, FXvalue, ERRORvalue);
            Nvalue.clear();Avalue.clear();Bvalue.clear();FXvalue.clear();ERRORvalue.clear();
            Xvalue.clear();
        } catch (Exception e) {
        }
    }
}