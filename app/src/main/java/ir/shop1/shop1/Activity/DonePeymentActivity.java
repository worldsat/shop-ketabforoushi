package ir.shop1.shop1.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ir.shop1.shop1.R;

public class DonePeymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_peyment);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        TextView MessageTxt = findViewById(R.id.message2);
        Intent in = getIntent();
        Uri data = in.getData();
        if (data != null) {

            String rdata = data.toString().replace("https://", "");

            MessageTxt.setText(Message(rdata));

        }
    }


    private String Message(String str) {
        String str2 = "";

        switch (str) {
            case "-1": {
                str2 = "تراکنش توسط خریدار کنسل شده است";
                break;
            }
            case "79": {
                str2 = "مبلغ سند برگشتی، از مبلغ تراکنش اصلی بیشتر است";
                break;
            }
            case "14": {
                str2 = "شماره کارت نامعتبر است";
                break;
            }

            case "12": {
                str2 = "چنین صادر کننده کارتی وجود ندارد";
                break;
            }
            case "33": {
                str2 = "از تاریخ انقاضی کارت گذشته است و کارت دیگر معتبر نیست";
                break;
            }
            case "38": {
                str2 = "رمز کارت 3 مرتبه اشتباه وارد شده است در نتیجه کارت غیر فعال خواهد شد";
                break;
            }
            case "55": {
                str2 = "خریدار رمز کارت را اشتباه وارد کرده است";
                break;
            }
            case "61": {
                str2 = " مبلغ بیش از سقف برداشت می باشد";
                break;
            }
            case "51": {
                str2 = "موجودی حساب خریدار کافی نیست";
                break;
            }
            case "84": {
                str2 = "سیستم بانک صادر کننده کارت خریدار، در وضعیت عملیاتی نمی باشد";
                break;
            }

        }

        return str2;
    }
}
