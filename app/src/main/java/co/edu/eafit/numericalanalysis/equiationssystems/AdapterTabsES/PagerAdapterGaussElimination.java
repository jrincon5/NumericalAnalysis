package co.edu.eafit.numericalanalysis.equiationssystems.AdapterTabsES;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.edu.eafit.numericalanalysis.equiationssystems.EquationTabs.AlgorithmGaussElimination;
import co.edu.eafit.numericalanalysis.equiationssystems.SolutionTabs.SolutionGaussElimination;

/**
 * Created by Jrincon on 28/05/2016.
 */
public class PagerAdapterGaussElimination extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterGaussElimination(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AlgorithmGaussElimination tab1 = new AlgorithmGaussElimination();
                return tab1;
            case 1:
                SolutionGaussElimination tab2 = new SolutionGaussElimination();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
