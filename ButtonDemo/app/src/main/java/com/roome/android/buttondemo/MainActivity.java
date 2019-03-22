package com.roome.android.buttondemo;

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

    public void BtnClick(View v){
        Toast.makeText(this,"哈哈哈",Toast.LENGTH_SHORT).show();
    }
}
