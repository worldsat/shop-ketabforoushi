package ir.shop1.shop1.Volley;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.shop1.shop1.Activity.InternetActivity;
import ir.shop1.shop1.Adapter.getCategoryAdapter;
import ir.shop1.shop1.R;


public class getCategory {

    private getCategoryAdapter ad;
    private TextView tryagain_txt;
    private Button tryagain_btn;
    private int pageTotal = 0;
    private int page = 1;


    private static List<String> ImageItems = new ArrayList<>();

    private static List<String> IdItems = new ArrayList<>();

    private static List<String> NameItems = new ArrayList<>();


    public void get_banners(final Context context, final ProgressBar ProgressBar, final RecyclerView recyclerViewlist,final String parent) {

        InternetActivity internetCheck = new InternetActivity();
        internetCheck.CheckNet(context);

        String urlJsonArray = context.getString(R.string.site) +"/api/Category/getCategory?parent_id="+parent;

        recyclerViewlist.setVisibility(View.INVISIBLE);
        ProgressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlJsonArray,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            recyclerViewlist.setVisibility(View.VISIBLE);
                            // tryagain_txt.setVisibility(View.GONE);
                            // tryagain_btn.setVisibility(View.GONE);

                            ImageItems.clear();
                            IdItems.clear();
                            NameItems.clear();

                            // JSONArray array = jsonRootObject.optJSONArray("");
                            // JSONArray array = new JSONObject(response).getJSONArray("");

                            JSONObject jsonRootObject = new JSONObject(response);
                            JSONArray array = jsonRootObject.optJSONArray("Data");

                            // pageTotal = jsonRootObject.getInt("TotalItemCount") / 5;

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject person = array.getJSONObject(i);

                                ImageItems.add(person.getString("Image"));
                                IdItems.add(person.getString("Id"));
                                NameItems.add(person.getString("Name"));

                              //  Log.i("mohsenjamali", "onResponse: " + person.getString("Id"));
                            }
                            try {
                                recyclerViewlist.setLayoutManager(new GridLayoutManager(context, 2));
                                ad = new getCategoryAdapter(context, ImageItems, IdItems, NameItems, recyclerViewlist);
                                recyclerViewlist.setAdapter(ad);

                            } catch (Exception e) {
                                Toast.makeText(context, "خطایی پیش آمده است لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                                Log.i("mohsenjamali", "onResponseError: " + e);
                            }

                            recyclerViewlist.setVisibility(View.VISIBLE);
                            ProgressBar.setVisibility(View.GONE);

                           /* ad.setOnLoadMoreListener(new OnLoadMoreListener() {
                                @Override
                                public void onLoadMore() {
                                    NameItems.add(null);
                                    ad.notifyItemInserted(NameItems.size() - 1);

                                    //-----------------------------------------------------------------------------------------------
                                    RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
                                    StringRequest MyStringRequest = new StringRequest(Request.Method.GET, urlJsonArray, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                            NameItems.remove(NameItems.size() - 1);
                                            ad.notifyItemRemoved(NameItems.size());

                                            try {
                                                JSONObject jsonRootObject = new JSONObject(response);
                                                JSONArray jsonArray = jsonRootObject.optJSONArray("Data");

                                                for (int i = 0; i < jsonArray.length(); i++) {

                                                    JSONObject person = jsonArray.getJSONObject(i);

                                                    ImageItems.add(person.getString("Image"));
                                                    IdItems.add(person.getString("Id"));
                                                    NameItems.add(person.getString("Name"));


                                                }
                                                ad.setLoaded();

                                            } catch (JSONException e) {
                                                ProgressBar.setVisibility(View.GONE);
                                                //         tryagain_txt.setVisibility(View.VISIBLE);
                                                //         tryagain_btn.setVisibility(View.VISIBLE);
                                                recyclerViewlist.setVisibility(View.GONE);
                                                //         tryagain_txt.setText("خطایی داخلی رخ داده است !");
                                                Log.i("mohsenjamali", "onErrorResponse:loadmore1 " + e.getMessage());
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            ProgressBar.setVisibility(View.GONE);
                                            //    tryagain_txt.setVisibility(View.VISIBLE);
                                            //    tryagain_btn.setVisibility(View.VISIBLE);
                                            recyclerViewlist.setVisibility(View.GONE);
                                            //    tryagain_txt.setText("خطای داخلی رخ داده است !");
                                            Log.i("mohsenjamali", "onErrorResponse1::loadmore2 " + error.getMessage());
                                        }
                                    }) {
                                        protected Map<String, String> getParams() {
                                            Map<String, String> MyData = new HashMap<>();
                                            page = page + 1;
                                            MyData.put("PageNumber", String.valueOf(page));
                                            return MyData;
                                        }
                                    };

                                    if (page <= pageTotal) {
                                        MyRequestQueue.add(MyStringRequest);
                                    }
                                    ad.notifyDataSetChanged();
                                }
                            });*/

                        } catch (JSONException e) {
                            e.printStackTrace();

                            ProgressBar.setVisibility(View.GONE);

                            //    tryagain_txt.setVisibility(View.VISIBLE);
                            //    tryagain_btn.setVisibility(View.VISIBLE);
                            recyclerViewlist.setVisibility(View.GONE);
                            //    tryagain_txt.setText("ارتباط با سرور برقرار نشد !");
                            Log.i("mohsenjamali", "onErrorResponse: " + e.getMessage());
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                ProgressBar.setVisibility(View.GONE);
                //     tryagain_txt.setVisibility(View.VISIBLE);
                //     tryagain_btn.setVisibility(View.VISIBLE);
                recyclerViewlist.setVisibility(View.GONE);
                //     tryagain_txt.setText("مشکل در دریافت اطلاعات !");
                Log.i("mohsenjamali", "onErrorResponse: " + error.getMessage());

            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
                MyData.put("PageNumber", String.valueOf(page));
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
