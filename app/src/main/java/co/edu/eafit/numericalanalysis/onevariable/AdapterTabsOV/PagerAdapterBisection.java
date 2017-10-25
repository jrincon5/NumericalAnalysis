package co.edu.eafit.numericalanalysis.onevariable.AdapterTabsOV;

/**
 * Created by Jrincon on 6/03/2016.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.edu.eafit.numericalanalysis.onevariable.AlgorithmTabs.AlgorithmBisection;
import co.edu.eafit.numericalanalysis.onevariable.IterationsResults;
import co.edu.eafit.numericalanalysis.onevariable.IncrementalSearches;

public class PagerAdapterBisection extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterBisection(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                IncrementalSearches tab1 = new IncrementalSearches();
                return tab1;
            case 1:
                AlgorithmBisection tab2 = new AlgorithmBisection();
                return tab2;
            case 2:
                IterationsResults tab3 = new IterationsResults();
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
