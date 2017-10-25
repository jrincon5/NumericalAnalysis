package co.edu.eafit.numericalanalysis.onevariable;

import android.graphics.Paint;
import android.graphics.Rect;
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
 * Created by Jrincon on 22/03/2016.
 */

public class IterationsResults extends Fragment implements AdapterView.OnItemClickListener {
    View rootView;
    public static List<String> N = new ArrayList<String>();
    public static List<String> A = new ArrayList<String>();
    public static List<String> B = new ArrayList<String>();
    public static List<String> X = new ArrayList<String>();
    public static List<String> FX = new ArrayList<String>();
    public static List<String> Error = new ArrayList<String>();
    public String[] header = {"  N", "                       A", "                       B", "                       X", "                     F(X)", "                     Error"};
    public String[] header2 = {"  N", "                       X", "                     G(X)", "                     Error"};
    static TableLayout tabla;
    static boolean typeMethod=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_iterations_results, container, false);
        tabla = (TableLayout) rootView.findViewById(R.id.tabla);
        loadHead();
        loadValues();
        return rootView;
    }

    public void loadValues() {
        try {
            if(typeMethod) {
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
                    //-------------Columna A------------
                    TextView text2 = new TextView(getActivity());
                    text2.setText(A.get(i));
                    text2.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
                    text2.setBackgroundResource(R.drawable.tabla_celda);
                    layoutCelda = new TableRow.LayoutParams(
                            400, TableRow.LayoutParams.WRAP_CONTENT
                    );
                    text2.setLayoutParams(layoutCelda);
                    fila.addView(text2);
                    //-------------Fin Columna A----------
                    //-------------Columna B----------
                    TextView text3 = new TextView(getActivity());
                    text3.setText(B.get(i));
                    text3.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
                    text3.setBackgroundResource(R.drawable.tabla_celda);
                    layoutCelda = new TableRow.LayoutParams(
                            400, TableRow.LayoutParams.WRAP_CONTENT
                    );
                    text3.setLayoutParams(layoutCelda);
                    fila.addView(text3);
                    //----------Fin Columna B----------
                    //-------------Columna X-----------
                    TextView text4 = new TextView(getActivity());
                    text4.setText(X.get(i));
                    text4.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
                    text4.setBackgroundResource(R.drawable.tabla_celda);
                    layoutCelda = new TableRow.LayoutParams(
                            400, TableRow.LayoutParams.WRAP_CONTENT
                    );
                    text4.setLayoutParams(layoutCelda);
                    fila.addView(text4);
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
                    text6.setText(Error.get(i));
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
            }else{
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
                    TextView text4 = new TextView(getActivity());
                    text4.setText(X.get(i));
                    text4.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
                    text4.setBackgroundResource(R.drawable.tabla_celda);
                    layoutCelda = new TableRow.LayoutParams(
                            400, TableRow.LayoutParams.WRAP_CONTENT
                    );
                    text4.setLayoutParams(layoutCelda);
                    fila.addView(text4);
                    //-------------Fin Columna X----------
                    //-------------Columna GX----------
                    TextView text5 = new TextView(getActivity());
                    text5.setText(FX.get(i));
                    text5.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
                    text5.setBackgroundResource(R.drawable.tabla_celda);
                    layoutCelda = new TableRow.LayoutParams(
                            400, TableRow.LayoutParams.WRAP_CONTENT
                    );
                    text5.setLayoutParams(layoutCelda);
                    fila.addView(text5);
                    //-------------Fin Columna GX----------
                    //-------------Columna Error----------
                    TextView text6 = new TextView(getActivity());
                    text6.setText(Error.get(i));
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
        }catch(Exception ex){
        }
    }

    public void loadHead(){
        try {
            tabla.removeAllViews();
            if(typeMethod) {
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
                for (int j = 0; j < 6; j++) {
                    TextView text = new TextView(getActivity());
                    text.setText(header[j]);
                    text.setTypeface(null, Typeface.BOLD);
                    text.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
                    text.setBackgroundResource(R.drawable.tabla_head);
                    layoutCelda = new TableRow.LayoutParams(getTextWidth(text.getText().toString()), TableRow.LayoutParams.WRAP_CONTENT);
                    text.setLayoutParams(layoutCelda);
                    fila.addView(text);
                }
                tabla.addView(fila);
            }else{
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
                for (int j = 0; j < 4; j++) {
                    TextView text = new TextView(getActivity());
                    text.setText(header2[j]);
                    text.setTypeface(null, Typeface.BOLD);
                    text.setGravity(Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK);
                    text.setBackgroundResource(R.drawable.tabla_head);
                    layoutCelda = new TableRow.LayoutParams(getTextWidth(text.getText().toString()), TableRow.LayoutParams.WRAP_CONTENT);
                    text.setLayoutParams(layoutCelda);
                    fila.addView(text);
                }
                tabla.addView(fila);
            }
        }catch (Exception e){
        }
    }

    private int getTextWidth(String text){
        Paint p = new Paint();
        Rect bounds = new Rect();
        p.setTextSize(50);
        p.getTextBounds(text, 0, text.length(), bounds);
        return bounds.width();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT)
                .show();
    }

    public void makingList(List<String>n,List<String>a,List<String>b,List<String>x,List<String>fx,List<String>error) {
        if(typeMethod) {
            N.clear();
            B.clear();
            A.clear();
            X.clear();
            FX.clear();
            Error.clear();
            N.addAll(n);
            A.addAll(a);
            B.addAll(b);
            X.addAll(x);
            FX.addAll(fx);
            Error.addAll(error);
        }else{
            N.clear();
            X.clear();
            FX.clear();
            Error.clear();
            N.addAll(n);
            X.addAll(x);
            FX.addAll(fx);
            Error.addAll(error);
        }
    }

    public void setTypeMethod(boolean method){
        typeMethod=method;
    }
}
