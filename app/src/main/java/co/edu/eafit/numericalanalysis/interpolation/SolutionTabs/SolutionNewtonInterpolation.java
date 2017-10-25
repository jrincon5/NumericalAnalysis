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

import co.edu.eafit.numericalanalysis.R;

import Jama.Matrix;

/**
 * Created by Stiven on 05/06/16.
 */
public class SolutionNewtonInterpolation extends Fragment implements View.OnClickListener {
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
                    newtonInterpolation();
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

    void newtonInterpolation(){
        //Matrix matrizInterpolation = new Matrix(matrizA);
        int filas = matrizInterpolation.getRowDimension();
        Matrix matDifDiv = new Matrix(filas, filas);
        for(int i = 1; i <= filas; i++){
            double aux = matrizInterpolation.get(i-1, 1);
            matDifDiv.set(i-1, 0, aux);
        }
        for(int i = 2; i <= filas; i++){
            for(int j = i; j <=filas; j++){
                double aux2 = (matDifDiv.get(j-2, i-2) - matDifDiv.get(j-1, i-2)) / (matrizInterpolation.get(j-i, 0)-matrizInterpolation.get(j-1, 0));
                matDifDiv.set(j-1, i-1, aux2);
            }
        }
        matDifDiv.print(filas, filas);
        String funcion = "";
        String multi = "";
        for(int i = 1; i <= filas; i++){
            for(int j = 2; j <= i; j++){
                if(multi == ""){
                    multi =  " (x- " + Double.toString(matrizInterpolation.get(j-2, 0)) + " ) ";
                }else{
                    multi = multi + " * " + " (x- " + Double.toString(matrizInterpolation.get(j-2, 0)) + " ) ";
                }
            }
            if(funcion == ""){
                if(multi==""){
                    funcion =  Double.toString(matDifDiv.get(i-1, i-1));
                }else{
                    funcion =  Double.toString(matDifDiv.get(i-1, i-1)) + " * " + multi;
                }
            }else{
                if(multi == ""){
                    funcion = funcion + " + " + Double.toString(matDifDiv.get(i-1, i-1));
                }else{
                    funcion = funcion + " + " + Double.toString(matDifDiv.get(i-1, i-1)) + " * " + multi;
                }

            }
            multi = "";
        }
        String ret="P(X) = ";
        ret+=funcion;
        txt_Polynomial.setText(ret);
    }
}
