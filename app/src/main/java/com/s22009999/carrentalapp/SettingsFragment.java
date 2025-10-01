package com.s22009999.carrentalapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SeekBarPreference;
import androidx.preference.SwitchPreferenceCompat;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        // Set up preferences
        SwitchPreferenceCompat darkModePreference = findPreference("dark_mode");
        SeekBarPreference brightnessPreference = findPreference("brightness");
        ListPreference orientationPreference = findPreference("screen_orientation");

        // Dark mode preference
        if (darkModePreference != null) {
            darkModePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean isDarkMode = (boolean) newValue;
                applyDarkMode(isDarkMode);
                return true;
            });
        }

        // Brightness preference
        if (brightnessPreference != null) {
            brightnessPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                int brightness = (int) newValue;
                applyBrightness(brightness);
                return true;
            });
        }

        // Screen orientation preference
        if (orientationPreference != null) {
            orientationPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                String orientation = (String) newValue;
                applyScreenOrientation(orientation);
                return true;
            });
        }

        // Initialize preferences from shared preferences
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        applySettingsFromPreferences();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Handle preference changes
        if ("dark_mode".equals(key)) {
            boolean isDarkMode = sharedPreferences.getBoolean(key, false);
            applyDarkMode(isDarkMode);
        } else if ("brightness".equals(key)) {
            int brightness = sharedPreferences.getInt(key, 128);
            applyBrightness(brightness);
        } else if ("screen_orientation".equals(key)) {
            String orientation = sharedPreferences.getString(key, "auto");
            applyScreenOrientation(orientation);
        }
    }

    private void applySettingsFromPreferences() {
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();

        // Dark mode
        boolean isDarkMode = sharedPreferences.getBoolean("dark_mode", false);
        applyDarkMode(isDarkMode);

        // Brightness
        int brightness = sharedPreferences.getInt("brightness", 128);
        applyBrightness(brightness);

        // Screen orientation
        String orientation = sharedPreferences.getString("screen_orientation", "auto");
        applyScreenOrientation(orientation);
    }

    private void applyDarkMode(boolean isDarkMode) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void applyBrightness(int brightness) {
        WindowManager.LayoutParams layoutParams = getActivity().getWindow().getAttributes();
        layoutParams.screenBrightness = brightness / 255.0f;
        getActivity().getWindow().setAttributes(layoutParams);
    }

    @SuppressLint("WrongConstant")
    private void applyScreenOrientation(String orientation) {
        if ("portrait".equals(orientation)) {
            getActivity().setRequestedOrientation(Configuration.ORIENTATION_PORTRAIT);
        } else if ("landscape".equals(orientation)) {
            getActivity().setRequestedOrientation(Configuration.ORIENTATION_LANDSCAPE);
        } else {
            getActivity().setRequestedOrientation(Configuration.ORIENTATION_UNDEFINED);
        }
    }
}