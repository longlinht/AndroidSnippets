package io.github.longlinht.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;

import io.github.longlinht.library.android.AndroidUnit;
import io.github.longlinht.library.android.AndroidVersion;
import io.github.longlinht.library.permission.PermissionChecker;
import io.github.longlinht.library.permission.PermissionItem;
import io.github.longlinht.library.permission.PermissionListener;
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

                PermissionItem item =
                        new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE)
                                .rationalMessage("permission to download")
                                .deniedMessage("deny permission will lead download fail");
                PermissionChecker.instance(MainActivity.this)
                        .check(item, new PermissionListener() {
                            @Override
                            public void permissionGranted() {

                            }

                            @Override
                            public void permissionDenied() {

                            }
                        });

            }
        });
    }
}
