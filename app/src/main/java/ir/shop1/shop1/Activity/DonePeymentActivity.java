package ir.shop1.shop1.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ir.shop1.shop1.Engine.ManagementBasket;
import ir.shop1.shop1.Engine.SetterGetterBill;
import ir.shop1.shop1.Engine.SetterGetterNumberOrder;
import ir.shop1.shop1.R;

public class DonePeymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_peyment);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        TextView MessageTxt = findViewById(R.id.message);
        Intent in = getIntent();
        Uri data = in.getData();
        if (data != null) {

            String rdata = data.toString().replace("danesh://", "");

            MessageTxt.setText(Message(rdata));

        }

        ImageView profile = findViewById(R.id.profile);
        profile.setVisibility(View.GONE);
        RelativeLayout BasketLayout = findViewById(R.id.BasketLayout);
        BasketLayout.setVisibility(View.GONE);
        LinearLayout backIcon = findViewById(R.id.backButton);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyBAsket();
                startActivity(new Intent(DonePeymentActivity.this, MainActivity.class));
            }
        });
    }

    private void emptyBAsket() {
        SetterGetterNumberOrder setterGetterNumberOrder = new SetterGetterNumberOrder(DonePeymentActivity.this);
        setterGetterNumberOrder.emptyNumberOrder();

        SetterGetterBill setterGetterBill = new SetterGetterBill();
        setterGetterBill.EmptyTotalPrice(DonePeymentActivity.this);

        ManagementBasket managementBasket = new ManagementBasket(DonePeymentActivity.this);
        managementBasket.EmptyBasket();

    }

    @Override
    public void onBackPressed() {
        emptyBAsket();
        startActivity(new Intent(this, MainActivity.class));
    }

    private String Message(String str) {
        String str2 = "";

        switch (str) {
            case "OK": {
                str2 = "از خرید شما متشکریم";
                break;
            }
            case "Canceled By User": {
                str2 = "تراکنش توسط خریدار کنسل شده است";
                break;
            }
            case "Invalid Amount": {
                str2 = "مبلغ سند برگشتی، از مبلغ تراکنش اصلی بیشتر است";
                break;
            }
            case "Invalid Cart Number": {
                str2 = "شماره کارت نامعتبر است";
                break;
            }

            case "No Such Issuer": {
                str2 = "چنین صادر کننده کارتی وجود ندارد";
                break;
            }
            case "Expire Card Pick Up": {
                str2 = "از تاریخ انقاضی کارت گذشته است و کارت دیگر معتبر نیست";
                break;
            }
            case "Allowable PIN Tries Exceeded Pick Up": {
                str2 = "رمز کارت 3 مرتبه اشتباه وارد شده است در نتیجه کارت غیر فعال خواهد شد";
                break;
            }
            case "Incorrect PIN": {
                str2 = "خریدار رمز کارت را اشتباه وارد کرده است";
                break;
            }
            case "Exceeds Withdrawal Amount Limit": {
                str2 = " مبلغ بیش از سقف برداشت می باشد";
                break;
            }
            case "No Sufficient Funds": {
                str2 = "موجودی حساب خریدار کافی نیست";
                break;
            }
            case "Issuer Down Slm": {
                str2 = "سیستم بانک صادر کننده کارت خریدار، در وضعیت عملیاتی نمی باشد";
                break;
            }

        }

        return str2;
    }
}
