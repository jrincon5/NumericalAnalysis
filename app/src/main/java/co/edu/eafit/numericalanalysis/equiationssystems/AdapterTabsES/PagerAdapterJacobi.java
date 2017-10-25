package co.edu.eafit.numericalanalysis.equiationssystems.AdapterTabsES;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.edu.eafit.numericalanalysis.equiationssystems.EquationTabs.AlgorithmJacobi;
import co.edu.eafit.numericalanalysis.equiationssystems.IterationsResultMatrix;
import co.edu.eafit.numericalanalysis.equiationssystems.SolutionTabs.SolutionJacobi;

/**
 * Created by Stiven on 01/06/16.
 */

public class PagerAdapterJacobi extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterJacobi(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AlgorithmJacobi tab1 = new AlgorithmJacobi();
                return tab1;
            case 1:
                SolutionJacobi tab2 = new SolutionJacobi();
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

