package ir.shop1.shop1.Volley;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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
import ir.shop1.shop1.Adapter.getNewsAdapter;
import ir.shop1.shop1.OnLoadMoreListener;
import ir.shop1.shop1.R;


public class getNews {

    private getNewsAdapter ad;
    private TextView tryagain_txt;
    private Button tryagain_btn;
    private int pageTotal = 0;
    private int page = 1;


    private static List<String> ImageItems = new ArrayList<>();
    private static List<String> DiscountItems = new ArrayList<>();
    private static List<String> IdItems = new ArrayList<>();
    private static List<String> DescriptionItems = new ArrayList<>();
    private static List<String> TitleItems = new ArrayList<>();
    private static List<String> PriceItems = new ArrayList<>();
    private static List<String> TextItems = new ArrayList<>();
    private static List<String> QuantityItems = new ArrayList<>();
    private static List<String> LikeItems = new ArrayList<>();
    private static List<String> TotalVotesItems = new ArrayList<>();
    private static List<String> TotalCommentItems = new ArrayList<>();
    private static List<String> ThumbnailItems = new ArrayList<>();
    private static List<String> OtherItems = new ArrayList<>();

    public void get_news(final Context context, final ProgressBar ProgressBar, final RecyclerView recyclerViewlist) {


        InternetActivity internetCheck = new InternetActivity();
        internetCheck.CheckNet(context);

        final String urlJsonArray = context.getString(R.string.site) + "/api/News/Index";
        recyclerViewlist.setVisibility(View.INVISIBLE);
        ProgressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlJsonArray,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            recyclerViewlist.setVisibility(View.VISIBLE);
                            // tryagain_txt.setVisibility(View.GONE);
                            // tryagain_btn.setVisibility(View.GONE);


                            IdItems.clear();
                            TitleItems.clear();


                            // JSONArray array = jsonRootObject.optJSONArray("");
                            // JSONArray array = new JSONObject(response).getJSONArray("");

                            JSONObject jsonRootObject = new JSONObject(response);
                            JSONArray array = jsonRootObject.optJSONArray("Data");

                            pageTotal = jsonRootObject.getInt("TotalItemCount") / 8;

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject person = array.getJSONObject(i);


                                IdItems.add(person.getString("Id"));
                                TitleItems.add(person.getString("Title"));

                                Log.i("mohsenjamali", "onResponseNews: " + TitleItems.get(i));
                                // Log.i("mohsenjamali", "onResponse: " + person.getString("Id"));
                            }
                            try {

                                recyclerViewlist.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

                                ad = new getNewsAdapter(context, IdItems, TitleItems, recyclerViewlist);
                                recyclerViewlist.setAdapter(ad);

                            } catch (Exception e) {
                                Toast.makeText(context, "خطایی پیش آمده است لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                                Log.i("mohsenjamali", "onResponseErrorProduct: " + e);
                            }

                            recyclerViewlist.setVisibility(View.VISIBLE);
                            ProgressBar.setVisibility(View.GONE);

                            ad.setOnLoadMoreListener(new OnLoadMoreListener() {
                                @Override
                                public void onLoadMore() {
                                    TitleItems.add(null);
                                    ad.notifyItemInserted(TitleItems.size() - 1);

                                    //-----------------------------------------------------------------------------------------------
                                    RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
                                    StringRequest MyStringRequest = new StringRequest(Request.Method.POST, urlJsonArray, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                            TitleItems.remove(TitleItems.size() - 1);
                                            ad.notifyItemRemoved(TitleItems.size());

                                            try {
                                                JSONObject jsonRootObject = new JSONObject(response);
                                                JSONArray jsonArray = jsonRootObject.optJSONArray("Data");

                                                for (int i = 0; i < jsonArray.length(); i++) {

                                                    JSONObject person = jsonArray.getJSONObject(i);

                                                    IdItems.add(person.getString("Id"));
                                                    TitleItems.add(person.getString("Title"));

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
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();

                            ProgressBar.setVisibility(View.GONE);

                            //    tryagain_txt.setVisibility(View.VISIBLE);
                            //    tryagain_btn.setVisibility(View.VISIBLE);
                            recyclerViewlist.setVisibility(View.GONE);
                            //    tryagain_txt.setText("ارتباط با سرور برقرار نشد !");
                            Log.i("mohsenjamali", "onResponseErrorProduct: " + e.getMessage());
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
                Log.i("mohsenjamali", "onResponseErrorProduct: " + error.getMessage());

            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<>();
                MyData.put("PageNumber", String.valueOf(page));
                //  MyData.put("category_id", Category_id);
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
