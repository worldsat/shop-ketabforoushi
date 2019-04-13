package ir.shop1.shop1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;

import ir.shop1.shop1.R;


public class WellcomeActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wellcome);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        ImageView iView = findViewById(R.id.loading);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions
                .transforms(new CenterCrop());
        // .override(250, 250)

        Glide.with(getApplicationContext())
                .load(R.mipmap.welcome_background)
                .apply(requestOptions)
                .into(iView);


        timer();


    }


    public void timer() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                    mHandler.post(new Runnable() {

                        @Override
                        public void run() {


                            Intent intent = new Intent(WellcomeActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);


                        }
                    });
                } catch (Exception ignored) {

                }
            }
        }).start();
    }

    @Override
    public void onBackPressed() {

    }
}

