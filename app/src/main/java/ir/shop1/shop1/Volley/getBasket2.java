package ir.shop1.shop1.Volley;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.shop1.shop1.Activity.InternetActivity;
import ir.shop1.shop1.Adapter.getBasketAdapter;
import ir.shop1.shop1.Engine.ManagementBasket;
import ir.shop1.shop1.Engine.SetterGetterBill;
import ir.shop1.shop1.Engine.SetterGetterNumberOrder;
import ir.shop1.shop1.Fragment.BasketFragment;
import ir.shop1.shop1.R;

/**
 * Created by Moh3n on 20/07/2018.
 */

public class getBasket2 {
    private getBasketAdapter ad;
    private BasketFragment basketFragment;
    private static List<String> ImageItems = new ArrayList<>();
    private static List<String> IdItems = new ArrayList<>();
    private static List<String> NameItems = new ArrayList<>();
    private static List<String> PriceItems = new ArrayList<>();
    private static List<String> QuantityItems = new ArrayList<>();
    private DecimalFormat formatter = new DecimalFormat("###,###,###,###");

    public void get_Items(final Context context, final ArrayList<String> idsList, final ProgressBar ProgressBar, final RecyclerView recyclerViewlist, final TextView emptyText, final ConstraintLayout BasketLayout, final ConstraintLayout BillLayout, final TextView TotalImpute, final TextView Totalpure) {

        InternetActivity internetCheck = new InternetActivity();
        internetCheck.CheckNet(context);

        final SharedPreferences sp = context.getSharedPreferences("Token", 0);
        String urlJsonArray = context.getString(R.string.site) + "/api/Factor/ShowProductsInfo";
        Log.i("moh3n", "get_Items: "+urlJsonArray);
        recyclerViewlist.setVisibility(View.INVISIBLE);
        ProgressBar.setVisibility(View.VISIBLE);
        basketFragment = new BasketFragment();
        basketFragment.setHiddenLayout(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlJsonArray,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.i("moh3n", "onResponse: "+response);
                            ProgressBar.setVisibility(View.GONE);
                            if (response.equals("{\"Message\":1}")) {
//                                emptyText.setVisibility(View.VISIBLE);
//                                basketFragment.setHiddenLayout(context);
//
//                                SetterGetterNumberOrder SetterGetterNumberOrder = new SetterGetterNumberOrder(context);
//                                SetterGetterNumberOrder.emptyNumberOrder();

                            } else {
                                recyclerViewlist.setVisibility(View.VISIBLE);
                                basketFragment.setShowLayout(context);

                                // tryagain_txt.setVisibility(View.GONE);
                                // tryagain_btn.setVisibility(View.GONE);

                                NameItems.clear();
                                PriceItems.clear();
                                IdItems.clear();
                                QuantityItems.clear();
                                ImageItems.clear();

                                JSONObject jsonRootObject = new JSONObject(response);
                                JSONArray array = jsonRootObject.optJSONArray("Items");


                                SetterGetterNumberOrder SetterGetterNumberOrder = new SetterGetterNumberOrder(context);
                                SetterGetterNumberOrder.emptyNumberOrder();

                                SetterGetterBill SetterGetterBill = new SetterGetterBill();
                                SetterGetterBill.EmptyTotalPrice(context);

                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject person = array.getJSONObject(i);
                                    NameItems.add(person.getString("ProductName"));
                                    PriceItems.add(person.getString("UnitPrice"));
                                    IdItems.add(person.getString("Id"));
//                                    QuantityItems.add(person.getString("Qty"));
                                    ImageItems.add(person.getString("Thumbnail"));
                                    ManagementBasket managementBasket = new ManagementBasket(context);

                                    ArrayList<String> Qty = managementBasket.getCount();
                                    SetterGetterNumberOrder.setNumberOrder(Qty.get(i), "+");
                                    SetterGetterBill.setPriceItem(context, person.getString("UnitPrice"), Qty.get(i), "plus");

                                }

                                //update Price Bill Impure & Pure
                                basketFragment.setPriceBill(context);

                                //set empty message in basket
                                emptyText.setVisibility(View.GONE);


                                String str = formatter.format(Long.valueOf(SetterGetterBill.getPriceItem(context))) + context.getString(R.string.currency);

                                TotalImpute.setText(str);
                                Totalpure.setText(str);

                                try {

                                    recyclerViewlist.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                                    ad = new getBasketAdapter(context, IdItems, NameItems, PriceItems, QuantityItems, ImageItems, emptyText, recyclerViewlist);
                                    recyclerViewlist.setAdapter(ad);

                                } catch (Exception e) {
                                    Toast.makeText(context, "خطایی پیش آمده است لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                                    Log.i("mohsenjamali", "onResponseError: " + e);
                                }

                                recyclerViewlist.setVisibility(View.VISIBLE);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            recyclerViewlist.setVisibility(View.GONE);
                            ProgressBar.setVisibility(View.GONE);
                            basketFragment.setHiddenLayout(context);
                            //    tryagain_txt.setVisibility(View.VISIBLE);
                            //    tryagain_btn.setVisibility(View.VISIBLE);
                            //    tryagain_txt.setText("ارتباط با سرور برقرار نشد !");
                            Log.i("mohsenjamali", "onErrorResponse: " + e.getMessage());
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                recyclerViewlist.setVisibility(View.GONE);
                ProgressBar.setVisibility(View.GONE);
                basketFragment.setHiddenLayout(context);

                //     tryagain_txt.setVisibility(View.VISIBLE);
                //     tryagain_btn.setVisibility(View.VISIBLE);
                //     tryagain_txt.setText("مشکل در دریافت اطلاعات !");
                Log.i("mohsenjamali", "onErrorResponse: " + error.getMessage());

            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
//                MyData.put("Api_token", sp.getString("token", "nothing"));
                String str = "" + idsList;
                MyData.put("productIds", str.replace("[", "").replace("]", ""));
                return MyData;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                300000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}
