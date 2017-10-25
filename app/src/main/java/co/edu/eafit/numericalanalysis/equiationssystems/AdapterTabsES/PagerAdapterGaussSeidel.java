package co.edu.eafit.numericalanalysis.equiationssystems.AdapterTabsES;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.edu.eafit.numericalanalysis.equiationssystems.EquationTabs.AlgorithmGaussSeidel;
import co.edu.eafit.numericalanalysis.equiationssystems.EquationTabs.AlgorithmJacobi;
import co.edu.eafit.numericalanalysis.equiationssystems.IterationsResultMatrix;
import co.edu.eafit.numericalanalysis.equiationssystems.SolutionTabs.SolutionGaussSeidel;
import co.edu.eafit.numericalanalysis.equiationssystems.SolutionTabs.SolutionJacobi;

/**
 * Created by Stiven on 01/06/16.
 */
public class PagerAdapterGaussSeidel extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterGaussSeidel(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AlgorithmGaussSeidel tab1 = new AlgorithmGaussSeidel();
                return tab1;
            case 1:
                SolutionGaussSeidel tab2 = new SolutionGaussSeidel();
                return tab2;
            case 2:
                IterationsResultMatrix tab3 = new IterationsResultMatrix();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
