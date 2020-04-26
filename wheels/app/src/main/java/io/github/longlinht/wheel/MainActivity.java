package io.github.longlinht.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.github.longlinht.library.android.AndroidUnit;
import io.github.longlinht.library.android.AndroidVersion;
import io.github.longlinht.library.widget.CountDownView;

public class MainActivity extends AppCompatActivity {

    private CountDownView countDownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownView = findViewById(R.id.countdown);
        countDownView.start(3, true, new CountDownView.OnCompletedListener() {
            @Override
            public void onCompleted() {

            }
        });
    }
}
