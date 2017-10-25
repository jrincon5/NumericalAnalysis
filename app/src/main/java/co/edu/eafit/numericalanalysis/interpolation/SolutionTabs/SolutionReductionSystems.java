package co.edu.eafit.numericalanalysis.interpolation.SolutionTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.boris.expr.Expr;
import org.boris.expr.ExprEvaluatable;
import org.boris.expr.parser.ExprParser;
import org.boris.expr.util.Exprs;
import org.boris.expr.util.SimpleEvaluationContext;

import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;
import co.edu.eafit.numericalanalysis.R;

/**
 * Created by Stiven on 05/06/16.
 */
public class SolutionReductionSystems extends Fragment implements View.OnClickListener {
    View rootView;
    Button btn_showSolution;
    TextView txt_Polynomial, txt_Point_Result;
    static List<String> POINTS = new ArrayList<String>();
    static double Puntico, matrizPuntos[][];
    static int totalPuntos;
    Matrix matrizInterpolation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_solution_reduction_systems, container, false);

        txt_Polynomial = (TextView) rootView.findViewById(R.id.text_Polynomial_Reduction_System);
        txt_Point_Result = (TextView) rootView.findViewById(R.id.text_Result_Point_Reduction_System);

        btn_showSolution = (Button) rootView.findViewById(R.id.button_show_solution_reduction_system);
        btn_showSolution.setOnClickListener(this);

        return rootView;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_show_solution_reduction_system:
                try {
                    buildMatriz();
                    sistemasEcuaciones(matrizPuntos);
                }catch (Exception e){
                    Toast.makeText(getContext(), "All fields must have values", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void createDatas(List<String> points, double pointToReplace, int order){
        POINTS.clear();
        POINTS.addAll(points);
        Puntico=pointToReplace;
        totalPuntos=order;
        matrizPuntos = new double[POINTS.size()][2];
    }

    public void buildMatriz(){
        int x=0;
        for(int i=0;i<totalPuntos;i++){
            for(int j=0;j<2;j++){
                matrizPuntos[i][j]=Double.parseDouble(POINTS.get(x));
                Toast.makeText(getContext(), matrizPuntos[i][j]+"", Toast.LENGTH_SHORT).show();
                x++;
            }
        }
    }

    void sistemasEcuaciones(double [][] matrizA){
        Matrix matriza = new Matrix(matrizA);
        int filas = matriza.getRowDimension();
        Matrix A = new Matrix(filas, filas);
        Matrix B = new Matrix(filas, 1);
        for(int i = 1; i <= filas; i++){
            for(int j = filas; j >=1; j--){
                double aux = Math.pow(matriza.get(i-1, 0), filas-j);
                A.set(i-1, j-1, aux);
            }
        }

        for(int i = 1; i<=filas; i++){
            double aux2 = matriza.get(i-1, 1);
            B.set(i-1, 0, aux2);
        }
        A.print(filas, filas);
        B.print(filas, 1);
        //Matriz Aumentada
        int filasA = A.getRowDimension();
        Matrix matrizAum = new Matrix(filasA, filasA+1);
        for (int i=1; i <= filasA; i++ ){
            for (int j=1; j<=filasA; j++){
                double aux1=A.get(i-1,j-1);
                matrizAum.set(i-1, j-1, aux1);

            }
        }
        for (int i=1; i <= filasA; i++ ){
            double aux2=B.get(i-1,0);
            matrizAum.set(i-1,filasA,aux2);
        }
        matrizAum.print(filasA, filasA+1);
        //Llamamos a eliminasion gaussiana
        //Como tenemos una matriz de tipo matrix, fue necesario tener otro metodo que recibiera esta matriz
        double []vector = gauss2(matrizAum);
        String string = "";
        for(int i = 1; i <= filas; i++){
            if(i==filas){
                string = string + Double.toString(vector[i-1]);
            }else{
                string = string + Double.toString(vector[i-1])+ " * " +" (x^" + Double.toString(filas-i)+")"+ " + ";
            }
        }
        String ret="P(X) = ";
        ret+=string;
        System.out.println(string);
        txt_Polynomial.setText(ret);
        //Con este estring debo permitir resolver el sistema
        //Ingreso el numero por el que deseo reemplazar
        /*int n = 3;
        double solucion = solucionSistemaFuncion(string,3 );
        System.out.println(solucion);
        txt_Polynomial.setText("X = "+solucion);*/
    }

    double[] gauss2(Matrix aumentada){
        int filas = aumentada.getRowDimension();
        int columnas = aumentada.getColumnDimension();
        for(int i = 1; i <= filas-1; i++ ){
            for(int j = i+1; j <= filas; j++){
                double aux = aumentada.get(j-1, i-1)/aumentada.get(i-1, i-1);
                for(int l = i; l <= filas+1; l++){
                    double aux2 = aumentada.get(j-1, l-1) - aumentada.get(i-1, l-1)*aux;
                    aumentada.set(j-1, l-1,aux2);
                }
            }
        }
        double []fin = new double[filas];
        for(int i = filas; i>=1; i--){
            double count = 0;
            for(int k = i+1; k <= filas; k++){
                count = count + fin[k-1]*aumentada.get(i-1,k-1);
            }
            fin[i-1] = (aumentada.get(i-1, filas)- count)/aumentada.get(i-1, i-1);
        }
        for(int x = 0; x < fin.length; x++){
            System.out.println(fin[x]);
        }
        return fin;
    }
    double solucionSistemaFuncion(String funcion, int x){
        double funcionx = 0;
        SimpleEvaluationContext context = new SimpleEvaluationContext();
        try {
            Expr Fx = ExprParser.parse(funcion.replaceAll("x", "(" + x + ")"));
            Exprs.toUpperCase(Fx);
            if (Fx instanceof ExprEvaluatable) {
                Fx = ((ExprEvaluatable) Fx).evaluate(context);
                funcionx = Double.parseDouble(Fx.toString());
            }
        }catch(Exception e){
            System.out.println("Error");
        }
        return funcionx;
    }
}
