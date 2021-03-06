package co.edu.eafit.numericalanalysis.equiationssystems.EquationTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.numericalanalysis.R;
import co.edu.eafit.numericalanalysis.equiationssystems.SolutionTabs.SolutionRichardson;
import co.edu.eafit.numericalanalysis.equiationssystems.SolutionTabs.SolutionSOR;

/**
 * Created by Stiven on 05/06/16.
 */
public class AlgorithmSOR extends Fragment implements View.OnClickListener{
    View rootView;
    Button btn_generate_order, btn_calculate_order;
    EditText inpt_SOR_Order, inpt_SOR_Iteraions, inpt_SOR_Delta, inpt_SOR_Tolerance, valuesVectorB[], valuesSystemMatrix[][], valuesVectorInicial[];
    TextView txt_SOR_Vector_Initial,txt_SOR_Vector_B,txt_SOR_System_Matrix;
    TableLayout vectorInitial,vectorB,systemMatriz;

    int Order, Iterations;
    double delta,tolerance;
    List<String> ValuesIntVectorB = new ArrayList<String>();
    List<String> ValuesIntMatrixA = new ArrayList<String>();
    List<String> ValuesIntVectorInicial = new ArrayList<String>();

    SolutionSOR solution= new SolutionSOR();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_algorithm_sor, container, false);
        inpt_SOR_Order = (EditText) rootView.findViewById(R.id.inpt_sor_order);
        inpt_SOR_Iteraions = (EditText) rootView.findViewById(R.id.inpt_sor_iterations);
        inpt_SOR_Delta = (EditText) rootView.findViewById(R.id.inpt_sor_delta);
        inpt_SOR_Tolerance= (EditText) rootView.findViewById(R.id.inpt_sor_tolerance);
        btn_generate_order = (Button) rootView.findViewById(R.id.button_Generate_SOR);
        btn_calculate_order = (Button) rootView.findViewById(R.id.button_Calculate_SOR);

        txt_SOR_Vector_B=(TextView) rootView.findViewById(R.id.txt_vector_B_SOR);
        txt_SOR_Vector_Initial=(TextView) rootView.findViewById(R.id.txt_vector_inicial_SOR);
        txt_SOR_System_Matrix=(TextView) rootView.findViewById(R.id.txt_system_matrix_SOR);

        txt_SOR_Vector_Initial.setText("");
        txt_SOR_Vector_B.setText("");
        txt_SOR_System_Matrix.setText("");

        vectorInitial=(TableLayout) rootView.findViewById(R.id.matrix_sor_vector_inicial);
        vectorB=(TableLayout) rootView.findViewById(R.id.matrix_sor_vector_b);
        systemMatriz=(TableLayout) rootView.findViewById(R.id.matrix_sor_system_matrix);

        btn_generate_order.setOnClickListener(this);
        btn_calculate_order.setOnClickListener(this);
        return rootView;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_Generate_SOR:
                try {
                    Order=Integer.parseInt(inpt_SOR_Order.getText().toString());
                    Iterations=Integer.parseInt(inpt_SOR_Iteraions.getText().toString());
                    delta=Double.parseDouble(inpt_SOR_Delta.getText().toString());
                    tolerance=Double.parseDouble(inpt_SOR_Tolerance.getText().toString());
                    valuesVectorB=new EditText[Order];
                    valuesVectorInicial=new EditText[Order];
                    valuesSystemMatrix= new EditText[Order][Order];
                    makeVectorB(Order);
                    makeSystemMatrix(Order);
                    makeVectorInicial(Order);
                }catch (Exception e){
                    Toast.makeText(getContext(), "All fields must have values", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_Calculate_SOR:
                try {
                    boolean a, b, c;
                    a = catchDatasVectorB(Order);
                    b = catchDatasSystemMatrix(Order);
                    c = catchDatasVectorInicial(Order);
                    if (a&&b&&c) solution.createDatas(ValuesIntVectorB, ValuesIntMatrixA, ValuesIntVectorInicial, Order, Iterations, delta,tolerance);
                    ValuesIntVectorB.clear();
                    ValuesIntMatrixA.clear();
                    ValuesIntVectorInicial.clear();
                }catch (Exception e){
                    Toast.makeText(getContext(), "All fields must have values", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void makeVectorB(int size){
        vectorB.removeAllViews();
        txt_SOR_Vector_B.setText("Vector B:");
        txt_SOR_Vector_B.setTextSize(20);

        TableRow.LayoutParams layoutCelda;
        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow fila = new TableRow(getActivity());
        fila.setLayoutParams(layoutFila);

        for(int i=0;i<size;i++){
            EditText text = new EditText(getActivity());
            layoutCelda = new TableRow.LayoutParams(100, TableRow.LayoutParams.WRAP_CONTENT);
            text.setLayoutParams(layoutCelda);
            text.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            fila.addView(text);
            valuesVectorB[i] = text;
        }
        vectorB.addView(fila);
    }

    public void makeVectorInicial(int size){
        vectorInitial.removeAllViews();
        txt_SOR_Vector_Initial.setText("Vector Initial:");
        txt_SOR_Vector_Initial.setTextSize(20);

        TableRow.LayoutParams layoutCelda;
        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow fila = new TableRow(getActivity());
        fila.setLayoutParams(layoutFila);

        for(int i=0;i<size;i++){
            EditText text = new EditText(getActivity());
            layoutCelda = new TableRow.LayoutParams(100, TableRow.LayoutParams.WRAP_CONTENT);
            text.setLayoutParams(layoutCelda);
            text.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            fila.addView(text);
            valuesVectorInicial[i] = text;
        }
        vectorInitial.addView(fila);
    }

    public void makeSystemMatrix(int size){
        systemMatriz.removeAllViews();
        txt_SOR_System_Matrix.setText("Matrix A:");
        txt_SOR_System_Matrix.setTextSize(20);
        for(int i=0;i<size;i++){
            TableRow.LayoutParams layoutCelda;
            TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            TableRow fila = new TableRow(getActivity());
            fila.setLayoutParams(layoutFila);

            for(int j=0;j<size;j++){
                EditText text=new EditText(getActivity());
                layoutCelda = new TableRow.LayoutParams(100, TableRow.LayoutParams.WRAP_CONTENT);
                text.setLayoutParams(layoutCelda);
                text.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_NUMBER_FLAG_SIGNED);
                fila.addView(text);
                valuesSystemMatrix[i][j]=text;
            }
            systemMatriz.addView(fila);
        }
    }

    public boolean catchDatasVectorB(int size){
        try {
            for (int i = 0; i < size; i++) {
                ValuesIntVectorB.add((valuesVectorB[i].getText().toString()));
                //Toast.makeText(getContext(), ValuesIntVectorB.get(i)+"", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getContext(), "All vectorB fields must have values", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean catchDatasVectorInicial(int size){
        try {
            for (int i = 0; i < size; i++) {
                ValuesIntVectorInicial.add((valuesVectorInicial[i].getText().toString()));
                //Toast.makeText(getContext(), ValuesIntVectorB.get(i)+"", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getContext(), "All vectorB fields must have values", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean catchDatasSystemMatrix(int size){
        try{
            for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                    ValuesIntMatrixA.add((valuesSystemMatrix[i][j].getText().toString()));
                    //Toast.makeText(getContext(), ValuesIntMatrixA.get(i)+"", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(getContext(), "All system matrix fields must have values", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
