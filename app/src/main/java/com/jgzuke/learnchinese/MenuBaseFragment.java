package com.jgzuke.learnchinese;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by jgzuke on 15-06-08.
 */
public abstract class MenuBaseFragment extends Fragment {

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static MenuBaseFragment newInstance(int sectionNumber) {
        MenuBaseFragment fragment;
        switch(sectionNumber) {
            case 0:
                fragment = new MenuTranslateFragment();
                break;
            default:
                fragment = new MenuSettingsFragment();
                break;
        }
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getTitle());
    }

    public abstract String getTitle();
}