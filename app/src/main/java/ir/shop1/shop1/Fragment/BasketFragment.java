package ir.shop1.shop1.Fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;

import ir.shop1.shop1.Activity.BasketMiddleActivity;
import ir.shop1.shop1.Activity.ItemActivity;
import ir.shop1.shop1.Activity.LoginActivity;
import ir.shop1.shop1.AlertDialog.LoginSignupAlert;
import ir.shop1.shop1.Engine.ManagementBasket;
import ir.shop1.shop1.Engine.SetterGetterBill;
import ir.shop1.shop1.R;
import ir.shop1.shop1.Volley.getBasket2;
import ir.shop1.shop1.Volley.getTakhfifCode;
import ir.shop1.shop1.Volley.getToken;
import ir.shop1.shop1.Volley.setBasket2;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasketFragment extends Fragment {

    public BasketFragment() {

    }

    private TextView totalPriceImpure, takhfifTxt;
    private TextView totalPricePure;
    private MaterialDialog Rating_dialog;
    private String str;
    private Button nextBasket, takhfifBtn;
    private DecimalFormat formatter = new DecimalFormat("###,###,###,###");
    private EditText takhfifEdt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_basket, container, false);

        return inflater.inflate(R.layout.fragment_basket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProgressBar progressBar = getView().findViewById(R.id.progressBar);
        RecyclerView recyclerViewlist = getView().findViewById(R.id.RecyclerView);
        TextView emptyText = getView().findViewById(R.id.emptyText);
        Button loginBasketBtn = getView().findViewById(R.id.loginBasketBtn);

        nextBasket = getView().findViewById(R.id.nextBasket);
        totalPriceImpure = getView().findViewById(R.id.totalImpure);
        totalPricePure = getView().findViewById(R.id.totalPure);
        takhfifTxt = getView().findViewById(R.id.takhfifTxt);
        takhfifEdt = getView().findViewById(R.id.takhfifEdt);
        takhfifBtn = getView().findViewById(R.id.codeBtn);

        ConstraintLayout BasketLayout = getView().findViewById(R.id.Basket_layout);
        ConstraintLayout BillLayout = getView().findViewById(R.id.Bill_Layout);

        emptyText.setVisibility(View.GONE);
        loginBasketBtn.setVisibility(View.GONE);
        loginBasketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        // Check Login
//        getToken token = new getToken();
//
//        if (token.Ok(getActivity())) {
//            getBasket getBasket = new getBasket();
//            getBasket.get_Items(getActivity(), progressBar, recyclerViewlist, emptyText, BasketLayout, BillLayout, totalPriceImpure, totalPricePure);

        ManagementBasket managementBasket = new ManagementBasket(getActivity());


//
//        } else {
//            setHiddenLayout(getActivity());
//            emptyText.setVisibility(View.VISIBLE);
//            loginBasketBtn.setVisibility(View.VISIBLE);
//
//        }

        ArrayList<String> idProducts = managementBasket.getProducts();
        if (idProducts.size() == 0) {
            setHiddenLayout(getActivity());
            emptyText.setVisibility(View.VISIBLE);

        } else {

            getBasket2 getBasket2 = new getBasket2();
            getBasket2.get_Items(getActivity(), managementBasket.getProducts(), progressBar, recyclerViewlist, emptyText, BasketLayout, BillLayout, totalPriceImpure, totalPricePure);

        }
        //end check Login


        SetterGetterBill setterGetterBill = new SetterGetterBill();
        str = formatter.format(Long.valueOf(setterGetterBill.getPriceItem(getActivity()))) + getActivity().getString(R.string.currency);

        Button nextBasket = getView().findViewById(R.id.nextBasket);
        nextBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                setFinalizeBasket setFinalizeBasket = new setFinalizeBasket();
//                setFinalizeBasket.setFinalize(getActivity());

//
//                SetterGetterNumberOrder setterGetterNumberOrder = new SetterGetterNumberOrder(getActivity());
//                setterGetterNumberOrder.emptyNumberOrder();
                getToken token = new getToken();
                LoginSignupAlert loginAlert = new LoginSignupAlert();

                if (token.Ok(getActivity())) {
                    Intent intent = new Intent(getActivity(), BasketMiddleActivity.class);
                    intent.putExtra("NameActivity", "BasketFragment");
                    getActivity().startActivity(intent);

                    setBasket2 setBasket2 = new setBasket2();
                    setBasket2.register(getActivity());
                } else {
                    loginAlert.alertShow(getActivity(), ItemActivity.class);
                }


                // LoginSignupAlert loginSignupAlert = new LoginSignupAlert();
                //  loginSignupAlert.alertShow(getActivity(), MainActivity.class);
            }
        });


        takhfifBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTakhfifCode getTakhfifCode = new getTakhfifCode();
                getTakhfifCode.get_takhfif(getActivity(), takhfifEdt.getText().toString());
            }
        });

    }

    public void setPriceBill(Context context) {
        totalPriceImpure = ((Activity) context).findViewById(R.id.totalImpure);
        totalPricePure = ((Activity) context).findViewById(R.id.totalPure);
        SetterGetterBill setterGetterBill = new SetterGetterBill();
        str = formatter.format(Long.valueOf(setterGetterBill.getPriceItem(((Activity) context)))) + ((Activity) context).getString(R.string.currency);
        try {
            totalPriceImpure.setText(str);
            totalPricePure.setText(str);
        } catch (Exception e) {

        }
        takhfifTxt = ((Activity) context).findViewById(R.id.takhfifTxt);
        SharedPreferences Bill = context.getSharedPreferences("Bill", 0);
        if (Bill != null) {
            takhfifTxt.setText(Bill.getString("takhfif", "0") + " تومان ");
        }
    }

    public void setHiddenLayout(Context context) {
        ConstraintLayout BasketLayout = ((Activity) context).findViewById(R.id.Basket_layout);
        ConstraintLayout BillLayout = ((Activity) context).findViewById(R.id.Bill_Layout);
        ConstraintLayout BillLayout2 = ((Activity) context).findViewById(R.id.Bill_Layout2);
        Button nextBasket = ((Activity) context).findViewById(R.id.nextBasket);
        try {
            BasketLayout.setVisibility(View.GONE);
            BillLayout.setVisibility(View.GONE);
            BillLayout2.setVisibility(View.GONE);
            nextBasket.setVisibility(View.GONE);
        } catch (Exception e) {
            Log.i("mohsenjamali", "setHiddenLayoutError: " + e);
        }
    }

    public void setShowLayout(Context context) {
        ConstraintLayout BasketLayout = ((Activity) context).findViewById(R.id.Basket_layout);
        ConstraintLayout BillLayout = ((Activity) context).findViewById(R.id.Bill_Layout);
        ConstraintLayout BillLayout2 = ((Activity) context).findViewById(R.id.Bill_Layout2);
        Button nextBasket = ((Activity) context).findViewById(R.id.nextBasket);
        try {
            BasketLayout.setVisibility(View.VISIBLE);
            BillLayout.setVisibility(View.VISIBLE);
            BillLayout2.setVisibility(View.VISIBLE);
            nextBasket.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            Log.i("mohsenjamali", "setShowLayoutError: " + e);
        }

    }


}
