package ir.shop1.shop1.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ir.shop1.shop1.R;

public class ForgetPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }
}
