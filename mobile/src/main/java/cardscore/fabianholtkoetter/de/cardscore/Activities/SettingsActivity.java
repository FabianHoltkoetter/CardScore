package cardscore.fabianholtkoetter.de.cardscore.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import cardscore.fabianholtkoetter.de.cardscore.R;

/**.
 * Organization: Weisswurst Systems
 * Project: CardScore
 * Author(s): Fabian Holtkoetter
 * Date: 26.01.2015
 * OS: Windows 8.1
 * Java-Version: 1.8
 * Processor: Intel Core i5-4300 CPU @ 1.9GHz
 * RAM: 4GB
 */
public class SettingsActivity extends PreferenceActivity {

    SharedPreferences mSharedPreferences;
    ListPreference listPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext()).getString(getString(R.string.setting_theme_key), "light").equals(getString(R.string.setting_theme_default_value))) {
            setTheme(R.style.CardScoreLightTheme);
        } else {
            setTheme(R.style.CardScoreDarkTheme);
        }
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        PreferenceManager.setDefaultValues(getBaseContext(), R.xml.app_preferences, false);
        //noinspection deprecation
        addPreferencesFromResource(R.xml.app_preferences);
        //noinspection deprecation
        listPreference = (ListPreference) findPreference(getString(R.string.setting_theme_key));
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle(getString(R.string.popup_info_title))
                        .setMessage(getString(R.string.popup_info_restart_text))
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.popup_positive_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(SettingsActivity.this, DashboardActivity.class);
                                startActivity(i);
                                System.exit(0);
                            }
                        })
                        .setNegativeButton(getString(R.string.popup_negative_no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                .show();
                return true;
            }
        });
    }
}
