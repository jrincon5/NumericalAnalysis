package co.edu.eafit.numericalanalysis.onevariable.AdapterTabsOV;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.edu.eafit.numericalanalysis.onevariable.AlgorithmTabs.AlgorithmFalseRule;
import co.edu.eafit.numericalanalysis.onevariable.IncrementalSearches;
import co.edu.eafit.numericalanalysis.onevariable.IterationsResults;

/**
 * Created by Jrincon on 17/03/2016.
 */
public class PagerAdapterFalseRule extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterFalseRule(FragmentManager fm, int NumOfTabs) {
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
                AlgorithmFalseRule tab2 = new AlgorithmFalseRule();
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
