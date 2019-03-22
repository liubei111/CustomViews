package com.xiaobei.android.customviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }

    public void CBClick(View v){
        Toast.makeText(this,getResources().getString(R.string.custom_button),Toast.LENGTH_SHORT).show();
    }
}
