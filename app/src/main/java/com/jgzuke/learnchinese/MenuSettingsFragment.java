package com.jgzuke.learnchinese;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jgzuke on 15-06-08.
 */
public class MenuSettingsFragment extends MenuBaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu_settings, container, false);
        return rootView;
    }

    public MenuSettingsFragment() {
    }

    @Override
    public String getTitle() {
        return getString(R.string.main_activity_drawer_settings);
    }
}
