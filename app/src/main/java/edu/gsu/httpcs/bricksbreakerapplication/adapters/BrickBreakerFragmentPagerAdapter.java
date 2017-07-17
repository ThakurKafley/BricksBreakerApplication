package edu.gsu.httpcs.bricksbreakerapplication.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.Pair;

import java.util.ArrayList;

/**
 * Created by thaku on 7/4/2017.
 */

public class BrickBreakerFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<Pair<String, Fragment>> list;
    public BrickBreakerFragmentPagerAdapter(FragmentManager fm, ArrayList<Pair<String, Fragment>> list) {
        super(fm);
        this.list=list;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position).second;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).first;
    }
}
//package edu.gsu.httpcs.bricksbreakerapplication.adapters;
//
//        import android.support.v4.app.Fragment;
//        import android.support.v4.app.FragmentManager;
//        import android.support.v4.app.FragmentStatePagerAdapter;
//        import android.support.v4.util.Pair;
//
//        import java.util.ArrayList;
//
//
//
//public class BrickBreakerFragmentPagerAdapter extends FragmentStatePagerAdapter {
//    private final ArrayList<Pair<String, Fragment>> list;
//
//    public BrickBreakerFragmentPagerAdapter(FragmentManager fm, ArrayList<Pair<String,Fragment>>list) {
//        super(fm);
//        this.list=list;
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        return list.get(position).second;
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return list.get(position).first;
//    }
//}
//
