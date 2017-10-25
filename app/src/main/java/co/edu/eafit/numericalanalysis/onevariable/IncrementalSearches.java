package co.edu.eafit.numericalanalysis.onevariable;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import co.edu.eafit.numericalanalysis.onevariable.AlgorithmTabs.AlgorithmBisection;
import co.edu.eafit.numericalanalysis.onevariable.AlgorithmTabs.AlgorithmFalseRule;
import co.edu.eafit.numericalanalysis.onevariable.AlgorithmTabs.AlgorithmFixedPoint;
import co.edu.eafit.numericalanalysis.onevariable.AlgorithmTabs.AlgorithmNewtonRaphson;
import co.edu.eafit.numericalanalysis.onevariable.AlgorithmTabs.AlgorithmNewtonRaphsonModified;
import co.edu.eafit.numericalanalysis.onevariable.AlgorithmTabs.AlgorithmSecant;

public class IncrementalSearches extends Fragment implements View.OnClickListener {
    AlgorithmBisection globalBisection=new AlgorithmBisection();
    AlgorithmFalseRule globalFalseRule=new AlgorithmFalseRule();
    AlgorithmFixedPoint globalFixedPoint=new AlgorithmFixedPoint();
    AlgorithmNewtonRaphson globalNewtonRaphson = new AlgorithmNewtonRaphson();
    AlgorithmNewtonRaphsonModified globalNewtonRaphsonModified = new AlgorithmNewtonRaphsonModified();
    AlgorithmSecant globalSecant = new AlgorithmSecant();
    IterationsResults iter=new IterationsResults();
    View rootView;
    EditText txt_fx, txt_difer, txt_X0, txt_X1;
    TextView salida;
    Button btn_calcular;
    static String functionX="";
    static double differential, x0, x1;
    List<String> solutions =new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_incremental_searches, container, false);
        txt_fx=(EditText) rootView.findViewById(R.id.inpt_fx);
        txt_difer=(EditText) rootView.findViewById(R.id.inpt_difer);
        txt_X0=(EditText) rootView.findViewById(R.id.inpt_xinit);
        txt_X1=(EditText) rootView.findViewById(R.id.inpt_xfin);
        salida=(TextView) rootView.findViewById(R.id.txt_push);
        btn_calcular=(Button) rootView.findViewById(R.id.button_Calculate);
        btn_calcular.setOnClickListener(this);
        rebuildFragment();
        return rootView;
    }

    private void rebuildFragment(){
        if(!functionX.equals(""))txt_fx.setText(functionX.replace("+0",""));
        if(differential!=0) txt_difer.setText(differential+"");
        if(x0!=0) txt_X0.setText(x0+"");
        if(x1!=0) txt_X1.setText(x1+"");
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_Calculate:
                boolean empty=true;
                try {
                    functionX=txt_fx.getText().toString()+"+0";
                    differential=Double.parseDouble(txt_difer.getText().toString());
                    x0=Double.parseDouble(txt_X0.getText().toString());
                    x1 = Double.parseDouble(txt_X1.getText().toString());
                    empty=false;
                }catch (Exception e){
                    Toast.makeText(getContext(),"All fields must have values",Toast.LENGTH_SHORT).show();
                }
                if (!empty)incrementalSearch(functionX.replaceAll("exp","eXp"),differential,x0,x1);
        }
    }

    private void incrementalSearch(String Function, double dx, double x0, double x1){
        SimpleEvaluationContext context = new SimpleEvaluationContext();
        double ValueFX, ValueFXant;
        String res="";
        for(double i=x0;i<=x1;i+=dx){
            try{
                String Fxi=Function.replaceAll("x","("+i+")").toString();
                String Fxidx=Function.replaceAll("x","("+(i+dx)+")").toString();
                Expr FX = ExprParser.parse(Fxi.toString());
                Expr FXant = ExprParser.parse(Fxidx.toString());
                Exprs.toUpperCase(FX);
                Exprs.toUpperCase(FXant);
                if (FX instanceof ExprEvaluatable && FXant instanceof ExprEvaluatable) {
                    FX = ((ExprEvaluatable) FX).evaluate(context);
                    FXant = ((ExprEvaluatable) FXant).evaluate(context);
                    ValueFX=Double.parseDouble(FX.toString());
                    ValueFXant=Double.parseDouble(FXant.toString());
                    if(ValueFX==0){
                        res+="X= "+i+"\n";
                        Toast.makeText(getContext(),"Solucion\n"+"X = "+i,Toast.LENGTH_SHORT).show();
                    }
                    if(ValueFX*ValueFXant<0){
                        res+="["+i+" , "+(i+dx)+"]"+"\n";
                        solutions.add("["+i+" , "+(i+dx)+"]");
                    }
                    //res+=ValueFX+"\n";
                }
            }catch (Exception e){
                Toast.makeText(getContext(),"Invalid expression",Toast.LENGTH_SHORT).show();
                break;
            }
        }
        globalBisection.makingSpinner(solutions);
        globalBisection.setFuncion(Function);
        globalFalseRule.makingSpinner(solutions);
        globalFalseRule.setFuncion(Function);
        globalFixedPoint.makingSpinner(solutions);
        globalFixedPoint.setFuncion(Function);
        globalNewtonRaphson.makingSpinner(solutions);
        globalNewtonRaphson.setFuncion(Function);
        globalNewtonRaphsonModified.makingSpinner(solutions);
        globalNewtonRaphsonModified.setFuncion(Function);
        globalSecant.makingSpinner(solutions);
        globalSecant.setFuncion(Function);
        salida.setText(res);
        solutions.clear();
    }
}