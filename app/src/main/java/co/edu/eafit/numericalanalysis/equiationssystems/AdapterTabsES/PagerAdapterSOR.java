package co.edu.eafit.numericalanalysis.equiationssystems.AdapterTabsES;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.edu.eafit.numericalanalysis.equiationssystems.EquationTabs.AlgorithmSOR;
import co.edu.eafit.numericalanalysis.equiationssystems.IterationsResultMatrix;
import co.edu.eafit.numericalanalysis.equiationssystems.SolutionTabs.SolutionSOR;

/**
 * Created by Stiven on 05/06/16.
 */
public class PagerAdapterSOR extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterSOR(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AlgorithmSOR tab1 = new AlgorithmSOR();
                return tab1;
            case 1:
                SolutionSOR tab2 = new SolutionSOR();
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
