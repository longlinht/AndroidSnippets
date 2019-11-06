package com.example.ffmpeg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv;
    Button protocol;
    Button format;
    Button codec;
    Button filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        protocol = findViewById(R.id.btn_protocol);
        format = findViewById(R.id.btn_format);
        codec = findViewById(R.id.btn_codec);
        filter = findViewById(R.id.btn_filter);
        protocol.setOnClickListener(this);
        format.setOnClickListener(this);
        codec.setOnClickListener(this);
        filter.setOnClickListener(this);
        tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

    }
    public native String stringFromJNI();

    public native String urlprotocolinfo();
    public native String avformatinfo();
    public native String avcodecinfo();
    public native String avfilterinfo();

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_protocol:
                tv.setText(urlprotocolinfo());
                break;
            case R.id.btn_format:
                tv.setText(avformatinfo());
                break;
            case R.id.btn_codec:
                tv.setText(avcodecinfo());
                break;
            case R.id.btn_filter:
                tv.setText(avfilterinfo());
                break;
            default:
                break;
        }
    }
}
