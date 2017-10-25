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

import Jama.Matrix;
import co.edu.eafit.numericalanalysis.R;

/**
 * Created by Stiven on 01/06/16.
 */
public class SolutionFactoringLU extends Fragment implements View.OnClickListener{
    View rootView;
    TextView txt_MatrixA, txt_VectorB, txt_MatrixL, txt_MatrixU, txt_VectorC, txt_VectorX;
    Button btn_showSolution;
    String mA="", vB="", mS="", vS="", mU="", mL;
    static List<String> VectorB = new ArrayList<String>();
    static List<String> MatrixA = new ArrayList<String>();
    static List <Double> MatrixAumentada = new ArrayList<Double>();
    static int order;
    static String tipoMetodo;
    static Matrix L;
    static Matrix U;
    Matrix matrizA;
    Matrix matrizB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_solution_factoring_lu, container, false);
        txt_MatrixA = (TextView) rootView.findViewById(R.id.text_matrixA_Factoring_Lu);
        txt_VectorB = (TextView) rootView.findViewById(R.id.text_vectorB_Factoring_Lu);
        txt_MatrixL = (TextView) rootView.findViewById(R.id.text_matrixL_Factoring_Lu);
        txt_MatrixU = (TextView) rootView.findViewById(R.id.text_matrixU_Factoring_Lu);
        txt_VectorC = (TextView) rootView.findViewById(R.id.text_vectorC_Factoring_Lu);
        txt_VectorX = (TextView) rootView.findViewById(R.id.text_vectorX_Factoring_Lu);
        btn_showSolution = (Button) rootView.findViewById(R.id.button_show_solution_factoring_lu);
        btn_showSolution.setOnClickListener(this);
        return rootView;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_show_solution_factoring_lu:
                mA=buildMa();
                vB=buildVb();
                txt_MatrixA.setText(mA);
                txt_VectorB.setText(vB);
                if(tipoMetodo.equals("Crout")){
                    Crout();
                }
                if(tipoMetodo.equals("Doulittle")){
                    Doulittle();
                }
                if(tipoMetodo.equals("Cholesky")){
                    Cholesky();
                }
        }
    }

    public void createDatas(List<String> vectorB, List<String>matrixA, int orden, String metodo){
        order=orden;
        tipoMetodo=metodo;
        VectorB.clear();
        MatrixA.clear();
        MatrixAumentada.clear();
        VectorB.addAll(vectorB);
        MatrixA.addAll(matrixA);
        U=new Matrix(order,order);
        L=new Matrix(order,order);
    }

    public String buildMa(){
        String ret="Matrix A:"+"\n";
        double[][] m= new double[order][order];
        int x=0;
        for(int i=0;i<order;i++){
            ret+="|";
            for(int j=0;j<order;j++){
                ret+=MatrixA.get(x)+"|";
                m[i][j]=Double.parseDouble(MatrixA.get(x));
                x++;
            }
            ret+="\n";
        }
        matrizA=new Matrix(m);
        return ret;
    }

    public String buildVb(){
        String ret="Vector B:"+"\n";
        double [][] b=new double[order][1];
        for(int i=0;i<order;i++){
            ret+="|"+VectorB.get(i)+"|"+"\n";
            b[i][0]=Double.parseDouble(VectorB.get(i));
        }
        matrizB=new Matrix(b);
        return ret;
    }

    public void Crout(){
        for(int i = 1; i <= matrizA.getRowDimension(); i++){
            U.set(i-1, i-1, 1);
        }
        for(int i = 1; i <= order; i++){
            for(int j = i; j <= order; j++){
                double count = 0;
                for(int k = 2; k <= i; k++){
                    count = count + U.get(k-2, i-1)* L.get(j-1, k-2)/U.get(i-1, i-1);
                }
                double aux2 = matrizA.get(j-1, i-1) - count/U.get(i-1, i-1);
                L.set(j-1, i-1, aux2);
            }
            for(int j = i; j <= order-1; j++){
                double count2 = 0;
                for(int k = 2; k <= i; k++){
                    count2 = count2+U.get(k-2, j)*L.get(i-1, k-2);
                }
                double aux3 = (matrizA.get(i-1, j)-count2)/L.get(i-1, i-1);
                U.set(i-1, j, aux3);
            }
        }
        String u="Matrix U:\n";
        for(int i=0;i<order;i++){
            u+="|";
            for(int j=0;j<order;j++){
                u+=U.get(i,j)+"|";
            }
            u+="\n";
        }
        txt_MatrixU.setText(u);
        String l="Matrix L:\n";
        for(int i=0;i<order;i++){
            l+="|";
            for(int j=0;j<order;j++){
                l+=L.get(i,j)+"|";
            }
            l+="\n";
        }
        txt_MatrixL.setText(l);
        sustitucionProgresiva(L, matrizB, U);
    }

    public void Doulittle(){
        for(int i = 1; i <= matrizA.getRowDimension(); i++){
            L.set(i - 1, i - 1, 1);
        }
        for(int i = 1; i <= order; i++){
            L.set(i-1, i-1, 1);
        }
        for(int i = 1; i <= order; i++){
            for(int j = i; j <= order; j++){
                double count = 0;
                for(int k = 2; k <= i; k++){
                    count = count + U.get(k-2, j-1)* L.get(i-1, k-2);
                }
                double aux2 = matrizA.get(i-1, j-1) - count/L.get(i-1, i-1);
                U.set(i-1, j-1, aux2);
            }
            for(int j = i; j <= order-1; j++){
                double count2 = 0;
                for(int k = 2; k <= i; k++){
                    count2 = count2+U.get(k-2, i-1)*L.get(j, k-2);
                }

                double aux3 = (matrizA.get(j, i-1)-count2)/U.get(i-1, i-1);
                L.set(j, i-1, aux3);
            }
        }
        String u="Matrix U:\n";
        for(int i=0;i<order;i++){
            u+="|";
            for(int j=0;j<order;j++){
                u+=U.get(i,j)+"|";
            }
            u+="\n";
        }
        txt_MatrixU.setText(u);
        String l="Matrix L:\n";
        for(int i=0;i<order;i++){
            l+="|";
            for(int j=0;j<order;j++){
                l+=L.get(i,j)+"|";
            }
            l+="\n";
        }
        txt_MatrixL.setText(l);
        sustitucionProgresiva(L, matrizB, U);
    }

    void Cholesky(){
        int filas = matrizA.getRowDimension();
        double count = 0;

        Matrix L = new Matrix(filas, filas);
        Matrix U = new Matrix(filas, filas);

        for (int i=1; i<=filas; i++){
            count = 0;
            for (int j=1; j<= i-1; j++){
                count = count + L.get(i-1, j-1)*U.get (j-1,i-1);
            }
            double aux = Math.sqrt(matrizA.get(i-1,i-1)-count);
            System.out.println(aux);
            U.set(i-1,i-1,aux);
            L.set(i-1,i-1,aux);
            for (int k = i+1; k<=filas; k++){
                double count2 = 0;
                for (int l = 1; l<=i-1; l++){
                    count2 = count2 + L.get(k-1, l-1)*U.get(l-1,i-1);
                }
                double aux2 = (matrizA.get(k-1,i-1)-count2)/L.get(i-1,i-1);
                L.set(k-1,i-1,aux2);
            }
            for (int k = i+1; k<=filas; k++){
                double count2 = 0;
                for (int l = 1; l<=i-1; l++){
                    count2 = count2 + L.get(i-1, l-1)*U.get(l-1,k-1);
                }
                double aux2 = (matrizA.get(k-1,i-1)-count2)/L.get(i-1,i-1);
                U.set(i-1,k-1,aux2);
            }
        }
        String u="Matrix U:\n";
        for(int i=0;i<order;i++){
            u+="|";
            for(int j=0;j<order;j++){
                u+=U.get(i,j)+"|";
            }
            u+="\n";
        }
        txt_MatrixU.setText(u);
        String l="Matrix L:\n";
        for(int i=0;i<order;i++){
            l+="|";
            for(int j=0;j<order;j++){
                l+=L.get(i,j)+"|";
            }
            l+="\n";
        }
        txt_MatrixL.setText(l);
        sustitucionProgresiva(L, matrizB, U);
    }


     void sustitucionProgresiva(Matrix L, Matrix b, Matrix U){
        //Matriz Aumentada
        int filas = L.getRowDimension();
        Matrix matrizAum = new Matrix(filas, filas+1);
        for (int i=1; i <= filas; i++ ){
            for (int j=1; j<=filas; j++){
                double aux1=L.get(i-1,j-1);
                matrizAum.set(i-1, j-1, aux1);
            }
        }
        for (int i=1; i <= filas; i++ ){
            double aux2=b.get(i-1,0);
            matrizAum.set(i-1,filas,aux2);
        }
        matrizAum.print(filas, filas + 1);
        //-------
        Matrix result = new Matrix(1, filas);
        for(int i = 1; i <= filas; i++){
            double count = 0;
            for(int j = 1; j <= i; j++){
                count = count + result.get(0, j-1)* matrizAum.get(i-1, j-1);
            }
            double aux = (b.get(i-1, 0)-count)/matrizAum.get(i-1, i-1);
            result.set(0, i-1, aux);
        }
        //result.print(1, filas);
         String c="Vector C:\n";
         for(int i=0;i<filas;i++){
             c+="|"+result.get(0,i)+"|";
             c+="\n";
         }
         txt_VectorC.setText(c);
         sustitucionRegresiva(result, U);
    }

    void sustitucionRegresiva(Matrix result, Matrix U){

        //Matriz Aumentada
        int filas = U.getRowDimension();
        Matrix matrizAum = new Matrix(filas, filas+1);
        for (int i=1; i <= filas; i++ ){
            for (int j=1; j<=filas; j++){
                double aux1=U.get(i-1,j-1);
                matrizAum.set(i-1, j-1, aux1);
            }
        }
        for (int i=1; i <= filas; i++ ){
            double aux2=result.get(0,i-1);
            matrizAum.set(i-1,filas,aux2);
        }

        matrizAum.print(filas, filas+1);

        Matrix resultFin = new Matrix(1, filas);

        for(int i = filas; i >= 1; i--){
            double count = 0;
            for(int j = i; j <= filas-1; j++){
                count = count + resultFin.get(0, j)* matrizAum.get(i-1, j);
            }
            double aux = (matrizAum.get(i-1, filas)-count)/matrizAum.get(i-1, i-1);
            resultFin.set(0, i-1, aux);
        }
        String x="Vector X:\n";
        for(int i=0;i<filas;i++){
            x+="|"+resultFin.get(0,i)+"|";
            x+="\n";
        }
        txt_VectorX.setText(x);
    }
}