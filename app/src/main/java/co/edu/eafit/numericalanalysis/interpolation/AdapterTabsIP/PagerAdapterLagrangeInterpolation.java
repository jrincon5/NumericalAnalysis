package co.edu.eafit.numericalanalysis.interpolation.AdapterTabsIP;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.edu.eafit.numericalanalysis.interpolation.PolynomialTabs.PolynomialLagrangeInterpolation;
import co.edu.eafit.numericalanalysis.interpolation.PolynomialTabs.PolynomialReductionSystems;
import co.edu.eafit.numericalanalysis.interpolation.SolutionTabs.SolutionLagrangeInterpolation;
import co.edu.eafit.numericalanalysis.interpolation.SolutionTabs.SolutionReductionSystems;

/**
 * Created by Stiven on 05/06/16.
 */
public class PagerAdapterLagrangeInterpolation extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterLagrangeInterpolation(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PolynomialLagrangeInterpolation tab1 = new PolynomialLagrangeInterpolation();
                return tab1;
            case 1:
                SolutionLagrangeInterpolation tab2 = new SolutionLagrangeInterpolation();
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
