package co.edu.eafit.numericalanalysis.equiationssystems.EquationTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.numericalanalysis.R;
import co.edu.eafit.numericalanalysis.equiationssystems.SolutionTabs.SolutionGaussElimination;

/**
 * Created by Jrincon on 28/05/2016.
 */

public class AlgorithmGaussElimination extends Fragment implements View.OnClickListener{
    View rootView;
    Button btn_generate_order, btn_calculate_order;
    EditText inpt_Gauss_Elimination_Order, valuesVectorB[], valuesSystemMatrix[][];
    TextView txt_Gauss_Elimnation_Vector_B,txt_Gauss_Elimnation_system_matrix;
    TableLayout vectorB,systemMatriz;
    int Order;
    List<String> ValuesIntVectorB = new ArrayList<String>();
    List<String> ValuesIntMatrixA = new ArrayList<String>();

    SolutionGaussElimination solution= new SolutionGaussElimination();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_algorithm_gauss_elimination, container, false);
        inpt_Gauss_Elimination_Order = (EditText) rootView.findViewById(R.id.inpt_gauss_elimination_order);
        btn_generate_order = (Button) rootView.findViewById(R.id.button_Generate_Gauss_Elimination);
        btn_calculate_order = (Button) rootView.findViewById(R.id.button_Calculate_Gauss_Elimination);

        txt_Gauss_Elimnation_Vector_B=(TextView) rootView.findViewById(R.id.txt_vector_B_Gauss_Elimination);
        txt_Gauss_Elimnation_system_matrix=(TextView) rootView.findViewById(R.id.txt_system_matrix_Gauss_Elimination);

        txt_Gauss_Elimnation_Vector_B.setText("");
        txt_Gauss_Elimnation_system_matrix.setText("");

        vectorB=(TableLayout) rootView.findViewById(R.id.matrix_gauss_elimination_vector_b);
        systemMatriz=(TableLayout) rootView.findViewById(R.id.matrix_gauss_elimination_system_matrix);

        btn_generate_order.setOnClickListener(this);
        btn_calculate_order.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_Generate_Gauss_Elimination:
                try {
                    Order=Integer.parseInt(inpt_Gauss_Elimination_Order.getText().toString());
                    valuesVectorB=new EditText[Order];
                    valuesSystemMatrix= new EditText[Order][Order];
                    makeVectorB(Order);
                    makeSystemMatrix(Order);
                }catch (Exception e){
                    Toast.makeText(getContext(), "All fields must have values", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.button_Calculate_Gauss_Elimination:
                boolean a,b;
                a=catchDatasVectorB(Order);
                b=catchDatasSystemMatrix(Order);
                if(a&&b)solution.createDatas(ValuesIntVectorB, ValuesIntMatrixA, Order);
                ValuesIntVectorB.clear();
                ValuesIntMatrixA.clear();
                break;
        }
    }

    public void makeVectorB(int size){
        vectorB.removeAllViews();
        txt_Gauss_Elimnation_Vector_B.setText("Vector B:");
        txt_Gauss_Elimnation_Vector_B.setTextSize(20);

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

    public void makeSystemMatrix(int size){
        systemMatriz.removeAllViews();
        txt_Gauss_Elimnation_system_matrix.setText("Matrix A:");
        txt_Gauss_Elimnation_system_matrix.setTextSize(20);
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
                //Toast.makeText(getContext(), valuesIntVectorB[i]+"", Toast.LENGTH_SHORT).show();
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
                    //Toast.makeText(getContext(), valuesIntSystemMatriz[i][j]+"", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(getContext(), "All system matrix fields must have values", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}