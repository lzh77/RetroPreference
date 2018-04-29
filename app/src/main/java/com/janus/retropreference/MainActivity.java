package com.janus.retropreference;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.janus.retropreference.lib.RetroPreference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetroPreference retroPreference = new RetroPreference();
        Setting setting = retroPreference.create(this, Setting.class);

        setting.saveName("janus");

        String raw = getSharedPreferences("setting", MODE_PRIVATE).getString("name", "2");
        Log.d("janus-", "raw:" + raw);

        String name = setting.name("-1");
        Log.d("janus-", "retro:" + name);

    }
}
