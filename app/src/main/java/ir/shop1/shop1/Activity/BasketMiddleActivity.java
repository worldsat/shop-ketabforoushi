package ir.shop1.shop1.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ir.shop1.shop1.Engine.SetterGetterNumberOrder;
import ir.shop1.shop1.R;
import ir.shop1.shop1.Volley.getToken;
import ir.shop1.shop1.Volley.setBasket;


public class BasketMiddleActivity extends AppCompatActivity {


    private EditText nameEdt, tellEdt, ostanEdt, shahrEdt, addressEdt, postalEdt, mobileEdt;
    private TextView addressTxt;
    private ProgressBar progressBar;
    private String RadioPostSelected = "2", RadioPaymentSelected = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


//        send_radio();
//        payment_radio();
        findViewByid();
        Toolbar();
        Basket();
        setText();
        final TextView nextBasketBtn = findViewById(R.id.nextBasketBtn);
        nextBasketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEdt.getText().toString().length() < 3) {
                    nameEdt.setError(getString(R.string.error_name_short));
                } else if (nameEdt.getText().toString().isEmpty()) {
                    nameEdt.setError(getString(R.string.error_name));
                } else if (ostanEdt.getText().toString().isEmpty()) {
                    ostanEdt.setError(getString(R.string.error_ostan));
                } else if (shahrEdt.getText().toString().isEmpty()) {
                    shahrEdt.setError(getString(R.string.error_shahr));
                } else if (tellEdt.getText().toString().length() < 8) {
                    tellEdt.setError(getString(R.string.error_tell));
                } else if (postalEdt.getText().toString().length() < 10) {
                    postalEdt.setError(getString(R.string.error_postal2));
                } else if (mobileEdt.getText().toString().length() < 11) {
                    mobileEdt.setError(getString(R.string.error_mobile));
                } else {

                    SharedPreferences Profile_Buyer = getApplicationContext().getSharedPreferences("Profile", 0);
                    Profile_Buyer.edit().putString("Fullname", nameEdt.getText().toString())
                            .putString("Ostan", ostanEdt.getText().toString())
                            .putString("Shahr", shahrEdt.getText().toString())
                            .putString("Address", addressEdt.getText().toString())
                            .putString("PhoneNumber", tellEdt.getText().toString())
                            .putString("mobile", mobileEdt.getText().toString())
                            .putString("postal", postalEdt.getText().toString())

//                            .putString("RadioPostSelected", RadioPostSelected)
//                            .putString("RadioPaymentSelected", RadioPaymentSelected)
                            .apply();


                    ProgressDialog dialog = new ProgressDialog(BasketMiddleActivity.this);

                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog = ProgressDialog.show(BasketMiddleActivity.this, "", "لطفا منتظر بمانید...", true);

                    setBasket setBasket = new setBasket();
                    String addressFull = ostanEdt.getText().toString() + " " + shahrEdt.getText().toString() + " " + addressEdt.getText().toString();
                    setBasket.register(BasketMiddleActivity.this, dialog,postalEdt.getText().toString(), addressFull, tellEdt.getText().toString(), mobileEdt.getText().toString());

                }


            }
        });
    }

    private void findViewByid() {

        nameEdt = findViewById(R.id.nameEdt);
        tellEdt = findViewById(R.id.tellEdt);
        ostanEdt = findViewById(R.id.ostanEdt);
        shahrEdt = findViewById(R.id.shahrEdt);
        addressEdt = findViewById(R.id.addressEdt);
        addressTxt = findViewById(R.id.addressTxt);
        progressBar = findViewById(R.id.progressBarPLus2);
        postalEdt = findViewById(R.id.postalEdt);
        progressBar.setVisibility(View.GONE);
        nameEdt.requestFocus();
        mobileEdt = findViewById(R.id.mobileEdt);
    }

    private void Toolbar() {
        Toolbar my_toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(my_toolbar);
        RelativeLayout BasketLayout = findViewById(R.id.BasketLayout);
        BasketLayout.setVisibility(View.VISIBLE);
        TextView titleActionBar = findViewById(R.id.TitleActionBar);
        titleActionBar.setText("تکمیل اطلاعات");
        LinearLayout backIcon = findViewById(R.id.backButton);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        ImageView profile = findViewById(R.id.profile);
        profile.setVisibility(View.GONE);
    }

    private void Basket() {
        //set badgetNumber
        TextView BadgeCounter = findViewById(R.id.badgeCounter);

        SetterGetterNumberOrder setterGetter = new SetterGetterNumberOrder(getApplicationContext());
        BadgeCounter.setText(setterGetter.getNumberOrder());

        int badget_number = Integer.valueOf(setterGetter.getNumberOrder());

        if (badget_number != 0) {
            BadgeCounter.setVisibility(View.VISIBLE);
            BadgeCounter.setText(String.valueOf(badget_number));
        } else {
            BadgeCounter.setVisibility(View.GONE);
        }
        getToken token = new getToken();
        if (!token.Ok(getApplicationContext())) {
            BadgeCounter.setVisibility(View.GONE);
        }

        // set Basket icon button
        ImageView basket_icon_bar = findViewById(R.id.basket_icon);

        BadgeCounter.setVisibility(View.GONE);
        basket_icon_bar.setVisibility(View.GONE);

        basket_icon_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("NameActivity", "BasketFragment");
                getApplicationContext().startActivity(intent);
                // overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void setText() {

        SharedPreferences sp = getApplicationContext().getSharedPreferences("Profile", 0);

        addressEdt.setText(sp.getString("Address", ""));
        tellEdt.setText(sp.getString("PhoneNumber", ""));
        nameEdt.setText(sp.getString("Fullname", ""));
        ostanEdt.setText(sp.getString("Ostan", ""));
        shahrEdt.setText(sp.getString("Shahr", ""));
        mobileEdt.setText(sp.getString("mobile", ""));
        postalEdt.setText(sp.getString("postal", ""));
    }
//    private void send_radio() {
//
//        final RadioGroup send_radio = findViewById(R.id.RadioGroup1);
//
//        send_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(RadioGroup arg0, int arg1) {
//                // TODO Auto-generated method stub
//                switch (send_radio.getCheckedRadioButtonId()) {
//                    case R.id.post_radio:
//
//                        //  addressTxt.setVisibility(View.VISIBLE);
//                        //  addressEdt.setVisibility(View.VISIBLE);
//                        RadioPostSelected = "1";
//                        break;
//                    case R.id.barbari_radio:
//
//                        //  addressTxt.setVisibility(View.VISIBLE);
//                        // addressEdt.setVisibility(View.VISIBLE);
//                        RadioPostSelected = "2";
//                        break;
//                    case R.id.namayande_radio:
//
//                        // addressTxt.setVisibility(View.GONE);
//                        // addressEdt.setVisibility(View.GONE);
//                        RadioPostSelected = "3";
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
//    }
//
//    private void payment_radio() {
//
//        final RadioGroup payment_radio = findViewById(R.id.RadioGroup2);
//        payment_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(RadioGroup arg0, int arg1) {
//                // TODO Auto-generated method stub
//                switch (payment_radio.getCheckedRadioButtonId()) {
//                    case R.id.cash_radio:
//
//                        RadioPaymentSelected = "1";
//                        break;
//                    case R.id.finance_radio:
//
//                        RadioPaymentSelected = "2";
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
//    }


}
