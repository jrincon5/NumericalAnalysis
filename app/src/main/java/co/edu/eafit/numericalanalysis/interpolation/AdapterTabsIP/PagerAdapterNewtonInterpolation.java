package co.edu.eafit.numericalanalysis.interpolation.AdapterTabsIP;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.edu.eafit.numericalanalysis.interpolation.PolynomialTabs.PolynomialLagrangeInterpolation;
import co.edu.eafit.numericalanalysis.interpolation.PolynomialTabs.PolynomialNewtonInterpolation;
import co.edu.eafit.numericalanalysis.interpolation.SolutionTabs.SolutionNewtonInterpolation;

/**
 * Created by Stiven on 05/06/16.
 */
public class PagerAdapterNewtonInterpolation extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterNewtonInterpolation(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PolynomialNewtonInterpolation tab1 = new PolynomialNewtonInterpolation();
                return tab1;
            case 1:
                SolutionNewtonInterpolation tab2 = new SolutionNewtonInterpolation();
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
