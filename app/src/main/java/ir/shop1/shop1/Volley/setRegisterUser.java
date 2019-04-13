package ir.shop1.shop1.Volley;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import ir.shop1.shop1.Activity.InternetActivity;
import ir.shop1.shop1.Activity.MainActivity;
import ir.shop1.shop1.Engine.SnakBar;
import ir.shop1.shop1.R;


public class setRegisterUser {


    public void register(final Context context, final String name, final String password, final String mobile, final String address, final String postal, final String tell, final String email, final ProgressBar ProgressBar, final Button send, final Class className) {

        InternetActivity internetCheck = new InternetActivity();
        internetCheck.CheckNet(context);


        String urlJsonArray = context.getString(R.string.site) +"/api/User/Register";


        ProgressBar.setVisibility(View.VISIBLE);
        send.setText("");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlJsonArray,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        ProgressBar.setVisibility(View.GONE);
                        send.setText(context.getString(R.string.signupButton));
                        SnakBar snakBar = new SnakBar();

                        switch (response) {
                            case "{\"Message\":0}":

                                // Intent intent = new Intent(context, className);
                                getToken getToken = new getToken();
                                getToken.connect(context, email, password);
                                Toast.makeText(context, context.getString(R.string.signupOk), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);


                                break;
                            case "{\"Message\":1}":

                                snakBar.snakShow(context, context.getString(R.string.error_email_repetetive));

                                break;
                            case "{\"Message\":2}":

                                snakBar.snakShow(context, context.getString(R.string.error_mobile_repetetive));

                                break;
                        }
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                ProgressBar.setVisibility(View.GONE);
                send.setText(context.getString(R.string.signupButton));
                Log.i("mohsenjamali", "onErrorResponse: " + error.getMessage());
            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
                MyData.put("Fullname", name);
                MyData.put("Address", address);
                MyData.put("Password", password);
                MyData.put("PhoneNumber", tell);
                MyData.put("Mobile", mobile);
                MyData.put("PostalCode", postal);
                MyData.put("Email", email);

                return MyData;
            }
        };
        // Log.i("mohsenjamali", "setLike: " + Comment_id + " " + Like + sp.getString("token", "nothing"));
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                300000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


}
