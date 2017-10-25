package co.edu.eafit.numericalanalysis.equiationssystems.SolutionTabs;

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

import Jama.*;

/**
 * Created by Jrincon on 28/05/2016.
 */

public class SolutionGaussElimination extends Fragment implements View.OnClickListener{
    View rootView;
    TextView txt_MatrixA, txt_VectorB, txt_MatrixSolucion, txt_vectorSolution;
    Button btn_showSolution;
    String mA="", vB="", mS="", vS="";
    static List<String> VectorB = new ArrayList<String>();
    static List<String> MatrixA = new ArrayList<String>();
    static List <Double> MatrixAumentada = new ArrayList<Double>();
    static int order;
    static double matrixSolution[][];
    Matrix mAumentada;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_solution_gauss_elimination, container, false);
        txt_MatrixA = (TextView) rootView.findViewById(R.id.text_matrixA_Gauss_Elimination);
        txt_VectorB = (TextView) rootView.findViewById(R.id.text_vectorB_Gauss_Elimination);
        txt_MatrixSolucion = (TextView) rootView.findViewById(R.id.text_final_matrix_Gauss_Elimination);
        txt_vectorSolution = (TextView) rootView.findViewById(R.id.text_vector_solution_Gauss_Elimination);
        btn_showSolution = (Button) rootView.findViewById(R.id.button_show_solution_gauss_elimination);
        btn_showSolution.setOnClickListener(this);
        return rootView;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_show_solution_gauss_elimination:
                mA=buildMa();
                vB=buildVb();
                mS=buildMs();
                vS=buildVs();
                txt_MatrixA.setText(mA);
                txt_VectorB.setText(vB);
                txt_MatrixSolucion.setText(mS);
                txt_vectorSolution.setText(vS);
        }
    }

    public void createDatas(List<String>vectorB, List<String>matrixA, int orden){
        order=orden;
        matrixSolution=new double [order][order+1];
        VectorB.clear();
        MatrixA.clear();
        MatrixAumentada.clear();
        VectorB.addAll(vectorB);
        MatrixA.addAll(matrixA);
    }

    public String buildMa(){
        String ret="Matrix A:"+"\n";
        int x=0;
        for(int i=0;i<order;i++){
            ret+="|";
            for(int j=0;j<order;j++){
                ret+=MatrixA.get(x)+"|";
                x++;
            }
            ret+="\n";
        }
        return ret;
    }

    public String buildVb(){
        String ret="Vector B:"+"\n";
        for(int i=0;i<order;i++){
            ret+="|"+VectorB.get(i)+"|"+"\n";
        }
        return ret;
    }

    public String buildMs(){
        int x=0;
        for (int i=0;i<order;i++){
            for(int j=0;j<order;j++){
                MatrixAumentada.add(Double.parseDouble(MatrixA.get(x)));
                x++;
            }
            MatrixAumentada.add(Double.parseDouble(VectorB.get(i)));
        }
        x=0;
        for (int i=0;i<order;i++){
            for(int j=0;j<=order;j++){
                matrixSolution[i][j]=MatrixAumentada.get(x);
                x++;
            }
        }
        mAumentada=new Matrix(matrixSolution);
        double div=0;
        boolean succes=true;
        for(int k=0;k<order-1;k++){
            if(matrixSolution[k][k]==0) {succes=false; break;} // Mensaje division por cero
            for(int i=k+1;i<order;i++){
                div=matrixSolution[i][k]/matrixSolution[k][k];
                for(int j=k;j<=order;j++){
                    matrixSolution[i][j]=matrixSolution[i][j]-div*matrixSolution[k][j];
                }
            }
        }
        String ret="System Matrix:\n";
        if(succes) {
            for (int i = 0; i < order; i++) {
                ret += "|";
                for (int j = 0; j <= order; j++) {
                    ret += matrixSolution[i][j] + "|";
                }
                ret += "\n";
            }
        }
        return ret;
    }

    public String buildVs(){
        int filas = mAumentada.getRowDimension();
        for(int i = 1; i <= filas-1; i++ ){
            for(int j = i+1; j <= filas; j++){
                double aux = mAumentada.get(j-1, i-1)/mAumentada.get(i-1, i-1);
                for(int l = i; l <= filas+1; l++){
                    double aux2 = mAumentada.get(j-1, l-1) - mAumentada.get(i-1, l-1)*aux;
                    mAumentada.set(j-1, l-1,aux2);
                }
            }
        }
        double []fin = new double[filas];
        for(int i = filas; i>=1; i--){
            double count = 0;
            for(int k = i+1; k <= filas; k++){
                count = count + fin[k-1]*mAumentada.get(i-1,k-1);
            }
            fin[i-1] = (mAumentada.get(i-1, filas)- count)/mAumentada.get(i-1, i-1);
        }
        String ret="Vector Solution\n";
        for (int i=0;i<filas;i++){
            ret+="X"+i+" = "+fin[i]+"\n";
        }
        return ret;
    }
}