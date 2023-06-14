package com.example.cdd.GameOver.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cdd.Game.UI.GamingInterfaceActivity;
import com.example.cdd.R;

public class Defeat extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.defeat);
    }

    public void click_defeat_continue(View view) {
        startActivity(new Intent(this, GamingInterfaceActivity.class));
    }

    public void click_defeat_exit(View view) {
        finishAffinity();
    }
}
