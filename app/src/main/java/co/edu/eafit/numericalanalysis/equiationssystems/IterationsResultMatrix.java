package co.edu.eafit.numericalanalysis.equiationssystems;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.numericalanalysis.R;

/**
 * Created by Stiven on 03/06/16.
 */
public class IterationsResultMatrix extends Fragment implements AdapterView.OnItemClickListener{
    View rootView;
    public static List<String> N = new ArrayList<String>();
    public static List<String> X = new ArrayList<String>();
    public static List<String> FX = new ArrayList<String>();
    public static List<String> ERROR = new ArrayList<String>();
    static TableLayout tabla;
    static int Orden=0;

    //public String[] header = {"  N","                       X", "                     F(X)", "                     Error"};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_iterations_result_matrix, container, false);
        tabla = (TableLayout) rootView.findViewById(R.id.tabla_matrix);
        loadHead();
        //loadValues();
        return rootView;
    }

    public void makingList(List<String>n,List<String>x,List<String>fx,List<String>error,int order) {
        N.clear();
        X.clear();
        FX.clear();
        ERROR.clear();
        N.addAll(n);
        X.addAll(x);
        FX.addAll(fx);
        ERROR.addAll(error);
        Orden=order;
    }

    public void loadHead(){
        try {
            tabla.removeAllViews();
            TableRow.LayoutParams layoutCelda;
            TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            TableRow fila = new TableRow(getActivity());
            fila.setLayoutParams(layoutFila);
            fila.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadHead();
                        loadValues();
                        Toast.makeText(getContext(), "Refreshing table", Toast.LENGTH_SHORT).show();
                    }
                });
            //-------------Columna N-------------
            TextView text = new TextView(getActivity());
            text.setText("  N");
            text.setTypeface(null, Typeface.BOLD);
            text.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
            text.setBackgroundResource(R.drawable.tabla_head);
            layoutCelda = new TableRow.LayoutParams(100, TableRow.LayoutParams.WRAP_CONTENT);
            text.setLayoutParams(layoutCelda);
            fila.addView(text);
            //-------------Fin Columna N-------------
            //-------------Columnas X-------------
            for(int i=0;i<Orden;i++) {
                TextView text2 = new TextView(getActivity());
                text2.setText("                       X"+i);
                text2.setTypeface(null, Typeface.BOLD);
                text2.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
                text2.setBackgroundResource(R.drawable.tabla_head);
                layoutCelda = new TableRow.LayoutParams(100, TableRow.LayoutParams.WRAP_CONTENT);
                text2.setLayoutParams(layoutCelda);
                fila.addView(text2);
            }
            //-------------Fin Columnas X-------------
            //-------------Columna F(X)-------------
            TextView text3 = new TextView(getActivity());
            text3.setText("                     F(X)");
            text3.setTypeface(null, Typeface.BOLD);
            text3.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
            text3.setBackgroundResource(R.drawable.tabla_head);
            layoutCelda = new TableRow.LayoutParams(100, TableRow.LayoutParams.WRAP_CONTENT);
            text3.setLayoutParams(layoutCelda);
            fila.addView(text3);
            //-------------Fin Columna F(X)-------------
            //-------------Columna Error-------------
            TextView text4 = new TextView(getActivity());
            text4.setText("                     Error");
            text4.setTypeface(null, Typeface.BOLD);
            text4.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
            text4.setBackgroundResource(R.drawable.tabla_head);
            layoutCelda = new TableRow.LayoutParams(100, TableRow.LayoutParams.WRAP_CONTENT);
            text4.setLayoutParams(layoutCelda);
            fila.addView(text4);
            //-------------Fin Columna F(X)-------------
            tabla.addView(fila);
        }catch (Exception e){
        }
    }

    public void loadValues(){
        int x=0;
        for (int i = 0; i < N.size(); i++) {
            TableRow.LayoutParams layoutCelda;
            TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            TableRow fila = new TableRow(getActivity());
            fila.setLayoutParams(layoutFila);
            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadHead();
                    loadValues();
                    Toast.makeText(getContext(), "Refreshing table", Toast.LENGTH_SHORT).show();
                }
            });
            //-------------Columna N----------
            TextView text = new TextView(getActivity());
            text.setText(N.get(i));
            text.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
            text.setBackgroundResource(R.drawable.tabla_celda);
            layoutCelda = new TableRow.LayoutParams(
                    100, TableRow.LayoutParams.WRAP_CONTENT
            );
            text.setLayoutParams(layoutCelda);
            fila.addView(text);
            //-------------Fin Columna N----------
            //-------------Columna X-----------
            for(int j=0;j<Orden;j++) {
                TextView text4 = new TextView(getActivity());
                text4.setText(X.get(x));
                text4.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
                text4.setBackgroundResource(R.drawable.tabla_celda);
                layoutCelda = new TableRow.LayoutParams(
                        400, TableRow.LayoutParams.WRAP_CONTENT
                );
                text4.setLayoutParams(layoutCelda);
                fila.addView(text4);
                x++;
            }
            //-------------Fin Columna X----------
            //-------------Columna FX----------
            TextView text5 = new TextView(getActivity());
            text5.setText(FX.get(i));
            text5.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
            text5.setBackgroundResource(R.drawable.tabla_celda);
            layoutCelda = new TableRow.LayoutParams(
                    400, TableRow.LayoutParams.WRAP_CONTENT
            );
            text5.setLayoutParams(layoutCelda);
            fila.addView(text5);
            //-------------Fin Columna FX----------
            //-------------Columna Error----------
            TextView text6 = new TextView(getActivity());
            text6.setText(ERROR.get(i));
            text6.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
            text6.setBackgroundResource(R.drawable.tabla_celda);
            layoutCelda = new TableRow.LayoutParams(
                    400, TableRow.LayoutParams.WRAP_CONTENT
            );
            text6.setLayoutParams(layoutCelda);
            fila.addView(text6);
            //-------------Fin Columna N----------
            tabla.addView(fila);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT)
                .show();
    }
}
