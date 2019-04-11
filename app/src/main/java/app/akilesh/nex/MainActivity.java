package app.akilesh.nex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import app.akilesh.nex.fragments.AboutFragment;
import app.akilesh.nex.fragments.DeviceFragment;
import app.akilesh.nex.fragments.HelpFragment;
import app.akilesh.nex.fragments.HomeFragment;
import app.akilesh.nex.fragments.ThemeFragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.List;
import java.util.Objects;

import static android.view.View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
import static android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
import static android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;



public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, AboutFragment.OnFragmentInteractionListener, HelpFragment.OnFragmentInteractionListener, ThemeFragment.OnFragmentInteractionListener, DeviceFragment.OnFragmentInteractionListener {
    int currentNightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        int themeID = sharedPref.getInt("ThemePrefs",-1);
        AppCompatDelegate.setDefaultNightMode(themeID);
        currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                // Night mode is not active, we're in day time
                decorView.setSystemUiVisibility(SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);

            case Configuration.UI_MODE_NIGHT_YES:
                // Night mode is active, we're at night!

            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                // We don't know what mode we're in, assume notnight
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        fragment = HomeFragment.newInstance();
                        break;
                    case R.id.menu_info:
                        fragment = AboutFragment.newInstance();
                        break;
                    case R.id.menu_help:
                        fragment = HelpFragment.newInstance();
                        break;

                    case R.id.menu_invert:
                        fragment = ThemeFragment.newInstance();
                        break;

                    case R.id.device_info:
                        fragment = DeviceFragment.newInstance();
                        break;


                }
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, fragment);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, HomeFragment.newInstance());
        fragmentTransaction.commit();


    }


    private boolean isCallable(Intent intent) {
        List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    public void launchJunkCleaner(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.evenwell.cleaner", "com.evenwell.cleaner.MainActivity");
        if (isCallable(intent)) startActivity(intent);
        else Toast.makeText(this, "Junk Cleaner is not installed", Toast.LENGTH_SHORT).show();

    }


    public void launchSmartBoost(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.evenwell.smartboost", "com.evenwell.smartboost.activities.MainActivity");
        if (isCallable(intent)) startActivity(intent);
        else Toast.makeText(this, "Smart Boost is not installed", Toast.LENGTH_SHORT).show();

    }

    public void launchTaskManager(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.evenwell.memorycleaner", "com.evenwell.memorycleaner.MainActivity");
        if (isCallable(intent)) startActivity(intent);
        else Toast.makeText(this, "Task Manager is not installed", Toast.LENGTH_SHORT).show();

    }

    public void launchVirusScan(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.evenwell.viruscan", "com.evenwell.viruscan.qscanner.VirusScannerActivity");
        if (isCallable(intent)) startActivity(intent);
        else Toast.makeText(this, "Virus Scanner is not installed", Toast.LENGTH_SHORT).show();

    }

    public void launchAIFloatingTouch(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.nbc.AIFloatingTouch", "com.nbc.AIFloatingTouch.STouchActivity");
        if (isCallable(intent)) startActivity(intent);
        else Toast.makeText(this, "AI Floating Touch is not installed", Toast.LENGTH_SHORT).show();

    }

    public void launchScreenRecorder(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.nbc.android.screenrecord", "com.nbc.android.screenrecord.ui.ActivitySetting");
        if (isCallable(intent)) startActivity(intent);
        else Toast.makeText(this, "AI Floating Touch is not installed", Toast.LENGTH_SHORT).show();

    }

    public void launchFireWall(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.evenwell.firewall", "com.evenwell.firewall.TrafficControl");
        if (isCallable(intent)) startActivity(intent);
        else Toast.makeText(this, "App Traffic Control is not installed", Toast.LENGTH_SHORT).show();

    }

    public void launchGameAssistant(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.nbc.gameassistant", "com.nbc.gameassistant.GameAssistantActivity");
        if (isCallable(intent)) startActivity(intent);
        else Toast.makeText(this, "Game assistant is not installed", Toast.LENGTH_SHORT).show();

    }

}


