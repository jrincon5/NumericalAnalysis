package co.edu.eafit.numericalanalysis.interpolation.PolynomialTabs;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.numericalanalysis.R;
import co.edu.eafit.numericalanalysis.interpolation.SolutionTabs.SolutionLagrangeInterpolation;
import co.edu.eafit.numericalanalysis.interpolation.SolutionTabs.SolutionNewtonInterpolation;

/**
 * Created by Stiven on 05/06/16.
 */
public class PolynomialLagrangeInterpolation extends Fragment implements View.OnClickListener{
    View rootView;
    EditText inpt_amount_points_lagrange_interpolation, inpt_point, valuesPoints[][];
    Button btn_generate_point, btn_point_replace;
    TableLayout table_points;
    int Points;
    double Point;
    List<String> ValuesDoublePoints = new ArrayList<String>();
    SolutionLagrangeInterpolation solution=new SolutionLagrangeInterpolation();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_polynomial_lagrange_interpolation, container, false);
        inpt_amount_points_lagrange_interpolation=(EditText) rootView.findViewById(R.id.inpt_lagrange_interpolation_amount_points);
        inpt_point=(EditText) rootView.findViewById(R.id.inpt_reduction_lagrange__interpolation_point);

        table_points=(TableLayout) rootView.findViewById(R.id.points_lagrange_interpolation);

        btn_generate_point=(Button) rootView.findViewById(R.id.button_Generate_Points_Lagrange_Inerpolation);
        btn_point_replace=(Button) rootView.findViewById(R.id.button_Calculate_Polynomial_Lagrange_Interpolation);

        btn_generate_point.setOnClickListener(this);
        btn_point_replace.setOnClickListener(this);

        return rootView;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_Generate_Points_Lagrange_Inerpolation:
                try {
                    Points=Integer.parseInt(inpt_amount_points_lagrange_interpolation.getText().toString());
                    valuesPoints=new EditText[Points][2];
                    makePoints(Points);
                }catch (Exception e){
                    Toast.makeText(getContext(), "All fields must have values", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.button_Calculate_Polynomial_Lagrange_Interpolation:
                try {
                    catchPoints(Points);
                    Point=Double.parseDouble(inpt_point.getText().toString());
                    solution.createDatas(ValuesDoublePoints,Point, Points);
                }catch (Exception e){
                    Toast.makeText(getContext(), "All fields must have values", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void makePoints(int size){
        table_points.removeAllViews();

        for(int i=0;i<size;i++){
            TableRow.LayoutParams layoutCelda;
            TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            TableRow fila = new TableRow(getActivity());
            fila.setLayoutParams(layoutFila);

            for(int j=0;j<2;j++) {
                EditText text = new EditText(getActivity());
                layoutCelda = new TableRow.LayoutParams(320, TableRow.LayoutParams.WRAP_CONTENT);
                text.setLayoutParams(layoutCelda);
                text.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                fila.addView(text);
                valuesPoints[i][j] = text;
            }
            table_points.addView(fila);
        }
    }

    public void catchPoints(int size){
        try{
            for(int i=0;i<size;i++){
                for(int j=0;j<2;j++){
                    ValuesDoublePoints.add(valuesPoints[i][j].getText().toString());
                    //Toast.makeText(getContext(), valuesPoints[i][j].getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Toast.makeText(getContext(), "All system matrix fields must have values", Toast.LENGTH_SHORT).show();
        }
    }
}
