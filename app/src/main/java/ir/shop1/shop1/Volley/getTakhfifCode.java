package ir.shop1.shop1.Volley;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.shop1.shop1.Engine.SetterGetterNumberOrder;
import ir.shop1.shop1.Fragment.BasketFragment;
import ir.shop1.shop1.R;


/**
 * Created by Moh3n on 20/07/2018.
 */

public class getTakhfifCode {

    public void get_takhfif(final Context context, final String code) {

        final SharedPreferences sp = context.getSharedPreferences("Token", 0);
        String urlJsonArray = context.getString(R.string.site) + "/api/Payment/Discount/Code";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlJsonArray,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

//                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        SharedPreferences Bill = context.getSharedPreferences("Bill", 0);
                        try {
                            JSONObject jsonRootObject = new JSONObject(response);
                            String takhfifFee = jsonRootObject.getString("Amount");
                            String statusCode = jsonRootObject.getString("StatusCode");


                            if (statusCode.equals("0")) {

                                Bill.edit().putString("takhfif", takhfifFee).apply();
                                BasketFragment basketFragment = new BasketFragment();
                                basketFragment.setPriceBill(context);

                                Toast.makeText(context, "تخفیف اعمال شد", Toast.LENGTH_SHORT).show();
                            } else {
                                Bill.edit().putString("takhfif", "0").apply();
                            }
                        } catch (Exception e) {
                            Log.i("moh3n", "onResponseError: " + e);
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("mohsenjamali", "onErrorResponseBasketNumber: " + error.getMessage());

            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
                MyData.put("Api_token", sp.getString("token", "nothing"));
                MyData.put("DiscountCode", code);
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
