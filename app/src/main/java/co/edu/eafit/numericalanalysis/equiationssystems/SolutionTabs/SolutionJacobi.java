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
import co.edu.eafit.numericalanalysis.equiationssystems.IterationsResultMatrix;

/**
 * Created by Stiven on 01/06/16.
 */
public class SolutionJacobi extends Fragment implements View.OnClickListener{
    View rootView;
    Button btn_showSolution;
    TextView txt_MatrixA, txt_VectorB, txt_MatrixT, txt_VectorC, txt_VectorX;
    String mA="", vB="", vI="", vS="";
    static int order=0, Iterations=0;
    static double matrixSolution[][], Delta, Tolerance;
    static List<String> VectorB = new ArrayList<String>();
    static List<String> MatrixA = new ArrayList<String>();
    static List<String> VectorInicial = new ArrayList<String>();
    static List <Double> MatrixAumentada = new ArrayList<Double>();
    Matrix matrizA;
    Matrix vectorB;
    Matrix vectorInitial;
    IterationsResultMatrix iterations= new IterationsResultMatrix();

    static List<String> Xvalue = new ArrayList<String>();
    static List<String> Nvalue = new ArrayList<String>();
    static List<String> FXvalue = new ArrayList<String>();
    static List<String> Errorvalue = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_solution_jacobi, container, false);
        txt_MatrixA=(TextView) rootView.findViewById(R.id.text_matrixA_Jacobi);
        txt_VectorB=(TextView) rootView.findViewById(R.id.text_vectorB_Jacobi);
        txt_MatrixT=(TextView) rootView.findViewById(R.id.text_matrixT_Jacobi);
        txt_VectorC=(TextView) rootView.findViewById(R.id.text_vectorC_Jacobi);
        txt_VectorX=(TextView) rootView.findViewById(R.id.text_vectorX_Jacobi);

        btn_showSolution=(Button) rootView.findViewById(R.id.button_show_solution_jacobi);
        btn_showSolution.setOnClickListener(this);
        return rootView;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_show_solution_jacobi:
                mA=buildMa();
                vB=buildVb();
                buildVinit();
                Jacobi(Iterations, Tolerance, Delta);
                txt_MatrixA.setText(mA);
                txt_VectorB.setText(vB);
        }
    }

    public void createDatas(List<String> vectorB, List<String>matrixA, List<String>vectorInicial, int orden, int iterations, double delta, double tolerance){
        order=orden;
        Iterations=iterations;
        Delta=delta;
        Tolerance=tolerance;
        matrixSolution=new double [order][order+1];
        VectorB.clear();
        VectorInicial.clear();
        MatrixA.clear();
        MatrixAumentada.clear();
        VectorB.addAll(vectorB);
        MatrixA.addAll(matrixA);
        VectorInicial.addAll(vectorInicial);
    }

    public void Jacobi(int iter, double toler, double delta){
        int filas = matrizA.getRowDimension();

        Matrix L = new Matrix(filas, filas);
        Matrix U = new Matrix(filas, filas);
        Matrix D = new Matrix(filas, filas);
        Matrix T = new Matrix(filas, filas);
        Matrix C = new Matrix(1, filas);

        //Lleno las matrices L y U y multiplico cada valor por -1
        for(int i = 1; i <= filas; i++){
            for(int j = i +1; j <= filas; j++){
                double aux = matrizA.get(j-1, i-1)*-1;
                //Creo la matriz L
                L.set(j-1, i-1, aux);
                double aux2 = matrizA.get(i-1, j-1)*-1;
                //Creo la matriz U
                U.set(i-1, j-1, aux2);
            }
            double aux3 = matrizA.get(i-1, i-1);
            //Creo la matrid
            D.set(i-1, i-1, aux3);
        }

        T = D.inverse().times(L.plus(U));
        C = D.inverse().times(vectorB);
        //Me imprime la matriz T y C
        //T.print(filas, filas);
        //C.print(1, filas);

        String t="Matrix T:"+"\n";
        for(int i=0;i<order;i++){
            t+="|";
            for(int j=0;j<order;j++){
                t+=T.get(i,j)+"|";
            }
            t+="\n";
        }
        txt_MatrixT.setText(t);

        String c="Vector C:\n";
        for(int i=0;i<filas;i++){
            c+="|"+C.get(i,0)+"|";
            c+="\n";
        }
        txt_VectorC.setText(c);

        //--------------------------------------------------------------------------
        //Para aplicar los criterios de parada

        int n = 1;
        //Matrix iniciales = new Matrix(Matriziniciales);

        Matrix X = new Matrix(filas,1);
        Matrix aux1 = new Matrix(filas,1);
        Matrix aux2 = new Matrix(filas,1);
        Matrix xAux = new Matrix(filas,1);
        Matrix Xant = new Matrix(filas,1);

        for(int i = 1; i <= filas; i++){
            double aux = vectorInitial.get(i-1,0);
            X.set(i-1 ,0,  aux);
        }

        for(int i=0;i<order;i++){
            Xvalue.add(X.get(i, 0) + "");
        }
        Nvalue.add(0+"");
        Errorvalue.add("N/A");
        FXvalue.add(matrizA.times(X).plus(vectorB).normF()+"");
        //FXvalue.add("No funciono");

        while(n <= iter){
            //Hago Xant= X;
            Nvalue.add(n+"");

            for(int i = 1; i <= filas; i++){
                double aux = X.get(i-1, 0);
                Xant.set(i-1,0, aux);
            }
            xAux = T.times(X);
            X =  xAux.plus(C);

            for(int i=0;i<order;i++){
                Xvalue.add(X.get(i,0)+"");
            }
            aux1 = X.minus(Xant);
            aux2 = matrizA.times(X).minus(vectorB);
            Errorvalue.add(aux1.normF()+"");
            FXvalue.add(aux2.normF()+"");
            if(aux1.normF() < toler)break;
            if(aux2.normF() < delta)break;
            n++;
        }
        //X.print(filas, 1);

        String x="Vector X:\n";
        for(int i=0;i<filas;i++){
            x+="|"+X.get(i,0)+"|";
            x += "\n";
        }
        txt_VectorX.setText(x);
        iterations.makingList(Nvalue,Xvalue,FXvalue,Errorvalue,order);
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
        vectorB=new Matrix(b);
        return ret;
    }

    public void buildVinit(){
        //String ret="Vector Initial:"+"\n";
        double [][] ini=new double[order][1];
        for(int i=0;i<order;i++){
            //ret+="|"+VectorInicial.get(i)+"|"+"\n";
            ini[i][0]=Double.parseDouble(VectorInicial.get(i));
        }
        vectorInitial=new Matrix(ini);
    }
}

