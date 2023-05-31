package com.example.atmakan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class TherapistControl extends AppCompatActivity {
    SeekBar seekBar, seekbar2;
    AudioManager audioManager;
    ImageView backimage;
    int brightnes;
    ContentResolver contentResolver;
    Window window;
    LinearLayout homeLayout, settingLayout, profileLayout;
    private int selecttabmenu = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_control);
        homeLayout = findViewById(R.id.homeLayout);
        settingLayout = findViewById(R.id.settingLayout);
        profileLayout = findViewById(R.id.profileLayout);

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 1) {
                    Intent intent = new Intent(TherapistControl.this, TherapistHomepage.class);
                    startActivity(intent);
                    selecttabmenu = 1;
                }
            }
        });

        settingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 2) {
                    Intent intent = new Intent(TherapistControl.this, TherapistSetting.class);
                    startActivity(intent);
                    selecttabmenu = 2;
                }
            }
        });
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttabmenu != 3) {
                    Intent intent = new Intent(TherapistControl.this, TherapistProfilePage.class);
                    startActivity(intent);
                    selecttabmenu = 3;
                }
            }
        });

        backimage = findViewById(R.id.imageButton2);
        backimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(TherapistControl.this, TherapistSetting.class);
                startActivity(intent);
            }
        });
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBar.setMax(maxVolume);
        seekBar.setProgress(currentVolume);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekbar2 = (SeekBar) findViewById(R.id.seekBar2);
        contentResolver = getContentResolver();
        window = getWindow();
        seekbar2.setMax(255);
        seekbar2.setKeyProgressIncrement(1);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(this)) {
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getApplication().getPackageName()));
                startActivity(intent);
            }
        }
        try {
            brightnes = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS);
            seekbar2.setProgress(brightnes);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                brightnes = i;
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, brightnes);
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.screenBrightness = brightnes / (float) 300;
                window.setAttributes(layoutParams);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}