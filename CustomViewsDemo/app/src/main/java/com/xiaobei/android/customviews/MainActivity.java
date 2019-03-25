package com.xiaobei.android.customviews;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.liuxiaobei.customviews.CustomTextView;


public class MainActivity extends Activity {

    private CustomTextView ctv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        initView();
    }

    private void initView() {
        ctv_text = (CustomTextView) findViewById(R.id.ctv_text);
        ctv_text.setCTColors(1,3,getResources().getColor(R.color.colorAccent),4,6,getResources().getColor(R.color.colorPrimary));
        ctv_text.setCTSizes(0,1,10,1,3,16,4,6,30,9,10,10);
        ctv_text.setCTUnderlines(1,3);
        ctv_text.setCTDeleteLines(4,6);
        ctv_text.setCTSuperscripts(0,1);
        ctv_text.setCTSubscripts(9,10);
        ctv_text.setCTBolds(7,8);
        ctv_text.setCTItalic(8,9);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0,-20,60,40);
        ctv_text.setCTImages(6,7,drawable);
    }

    public void CBClick(View v){
        Toast.makeText(this,getResources().getString(R.string.custom_button),Toast.LENGTH_SHORT).show();
    }
}
