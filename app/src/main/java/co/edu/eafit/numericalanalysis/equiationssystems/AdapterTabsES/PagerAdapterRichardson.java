package co.edu.eafit.numericalanalysis.equiationssystems.AdapterTabsES;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.edu.eafit.numericalanalysis.equiationssystems.EquationTabs.AlgorithmRichardson;
import co.edu.eafit.numericalanalysis.equiationssystems.IterationsResultMatrix;
import co.edu.eafit.numericalanalysis.equiationssystems.SolutionTabs.SolutionRichardson;

/**
 * Created by Stiven on 02/06/16.
 */
public class PagerAdapterRichardson extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterRichardson(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AlgorithmRichardson tab1 = new AlgorithmRichardson();
                return tab1;
            case 1:
                SolutionRichardson tab2 = new SolutionRichardson();
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