package co.edu.eafit.numericalanalysis.onevariable.AdapterTabsOV;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.edu.eafit.numericalanalysis.onevariable.AlgorithmTabs.AlgorithmNewtonRaphson;
import co.edu.eafit.numericalanalysis.onevariable.AlgorithmTabs.AlgorithmNewtonRaphsonModified;
import co.edu.eafit.numericalanalysis.onevariable.IncrementalSearches;
import co.edu.eafit.numericalanalysis.onevariable.IterationsResults;

/**
 * Created by Stiven on 05/06/16.
 */
public class PagerAdapterNewtonRaphsonModified extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterNewtonRaphsonModified(FragmentManager fm, int NumOfTabs) {
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
                AlgorithmNewtonRaphsonModified tab2 = new AlgorithmNewtonRaphsonModified();
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

