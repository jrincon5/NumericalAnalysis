package co.edu.eafit.numericalanalysis.interpolation.AdapterTabsIP;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.edu.eafit.numericalanalysis.equiationssystems.EquationTabs.AlgorithmRichardson;
import co.edu.eafit.numericalanalysis.equiationssystems.IterationsResultMatrix;
import co.edu.eafit.numericalanalysis.equiationssystems.SolutionTabs.SolutionRichardson;
import co.edu.eafit.numericalanalysis.interpolation.PolynomialTabs.PolynomialReductionSystems;
import co.edu.eafit.numericalanalysis.interpolation.SolutionTabs.SolutionReductionSystems;

/**
 * Created by Stiven on 05/06/16.
 */
public class PagerAdapterReductionSystems extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterReductionSystems(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PolynomialReductionSystems tab1 = new PolynomialReductionSystems();
                return tab1;
            case 1:
                SolutionReductionSystems tab2 = new SolutionReductionSystems();
                return tab2;
            /*case 2:
                IterationsResultMatrix tab3 = new IterationsResultMatrix();
                return tab3;*/
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
