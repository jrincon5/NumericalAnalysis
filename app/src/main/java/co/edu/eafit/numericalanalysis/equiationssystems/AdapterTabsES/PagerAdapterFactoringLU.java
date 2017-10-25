package co.edu.eafit.numericalanalysis.equiationssystems.AdapterTabsES;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.edu.eafit.numericalanalysis.equiationssystems.EquationTabs.AlgorithmFactoringLU;
import co.edu.eafit.numericalanalysis.equiationssystems.SolutionTabs.SolutionFactoringLU;


/**
 * Created by Stiven on 01/06/16.
 */
public class PagerAdapterFactoringLU extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterFactoringLU(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AlgorithmFactoringLU tab1 = new AlgorithmFactoringLU();
                return tab1;
            case 1:
                SolutionFactoringLU tab2 = new SolutionFactoringLU();
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
