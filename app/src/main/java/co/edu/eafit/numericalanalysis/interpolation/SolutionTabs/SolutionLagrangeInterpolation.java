package co.edu.eafit.numericalanalysis.interpolation.SolutionTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;
import co.edu.eafit.numericalanalysis.R;

/**
 * Created by Stiven on 05/06/16.
 */
public class SolutionLagrangeInterpolation extends Fragment implements View.OnClickListener{
    View rootView;
    Button btn_showSolution;
    TextView txt_Polynomial, txt_Point_Result;
    static List<String> POINTS = new ArrayList<String>();
    static double Puntico, matrizPuntos[][];
    static int totalPuntos;
    Matrix matrizInterpolation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_solution_newton_interpolation, container, false);

        txt_Polynomial = (TextView) rootView.findViewById(R.id.text_Polynomial_Newton_Interpolation);
        txt_Point_Result = (TextView) rootView.findViewById(R.id.text_Result_Point_Newton_Interpolation);

        btn_showSolution = (Button) rootView.findViewById(R.id.button_show_solution_newton_interpolation);
        btn_showSolution.setOnClickListener(this);

        return rootView;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_show_solution_newton_interpolation:
                try {
                    matrizPuntos = new double[POINTS.size()][2];
                    buildMatriz();
                    lagrange();
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
    }
    public void buildMatriz(){
        int x=0;
        for(int i=0;i<totalPuntos;i++){
            for(int j=0;j<2;j++){
                matrizPuntos[i][j]=Double.parseDouble(POINTS.get(x));
                //Toast.makeText(getContext(), matrizPuntos[i][j]+"", Toast.LENGTH_SHORT).show();
                x++;
            }
        }
        matrizInterpolation=new Matrix(matrizPuntos);
    }

    void lagrange(){
        int filas = matrizInterpolation.getRowDimension();

        Matrix A = new Matrix(filas, filas);

        String funcion = "";

        String L1 = "";
        String La = "";

        double L2 =1;
        double Ld = 0;

        for(int i = 1; i <=filas; i++){
            for(int j = 1; j <= filas; j++){
                if(Math.abs(i-j)>0){
                    La = " ( "+ "x"+ "-" + Double.toString(matrizInterpolation.get(j-1, 0)) + " ) ";
                    Ld = matrizInterpolation.get(i-1, 0) - matrizInterpolation.get(j-1, 0) ;
                    if(L1 == ""){
                        L1 = La;
                        L2 = L2 * Ld;
                    }else{
                        L1 = L1 + " * " + La ;
                        L2 = L2 * Ld;
                    }

                }
            }
            if(L1 == ""){
                if(funcion == ""){
                    funcion = Double.toString(matrizInterpolation.get(i-1, 1)) + " / " + L2;
                }else{
                    funcion = Double.toString(matrizInterpolation.get(i-1, 1)) + " / " + L2 + " + " + funcion;
                }

            }else{
                if(funcion == ""){
                    funcion = L1 + " * " + Double.toString(matrizInterpolation.get(i-1, 1)) + " / " + L2;
                }else{
                    funcion = L1 + " * " + Double.toString(matrizInterpolation.get(i-1, 1)) + " / " + L2 + " + " + funcion;
                }

            }
            L1 = "";
            L2 = 1;
        }
        String ret="P(X) = ";
        ret+=funcion;
        txt_Polynomial.setText(ret);
    }

}
