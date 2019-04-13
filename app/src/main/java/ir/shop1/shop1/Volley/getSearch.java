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
import ir.shop1.shop1.Adapter.getSearchAdapter;
import ir.shop1.shop1.OnLoadMoreListener;
import ir.shop1.shop1.R;


public class getSearch {

    private getSearchAdapter ad;
    private TextView tryagain_txt;
    private Button tryagain_btn;
    private int pageTotal = 0;
    private int page = 1;



    private static List<String> ImageItems = new ArrayList<>();
    private static List<String> DiscountItems = new ArrayList<>();
    private static List<String> IdItems = new ArrayList<>();
    private static List<String> DescriptionItems = new ArrayList<>();
    private static List<String> NameItems = new ArrayList<>();
    private static List<String> PriceItems = new ArrayList<>();
    private static List<String> TextItems = new ArrayList<>();
    private static List<String> QuantityItems = new ArrayList<>();
    private static List<String> LikeItems = new ArrayList<>();
    private static List<String> TotalVotesItems = new ArrayList<>();
    private static List<String> TotalCommentItems = new ArrayList<>();
    private static List<String> OtherItems = new ArrayList<>();
    private static List<String> ThumbnailItems = new ArrayList<>();
    private static List<String> entesharatItems = new ArrayList<>();
    private static List<String> nevisandeItems = new ArrayList<>();
    private static List<String> salechapItems = new ArrayList<>();
    private static List<String> chapItems = new ArrayList<>();
    private static List<String> isbnItems = new ArrayList<>();
    private static List<String> virayeshItems = new ArrayList<>();
    public void get_banners(final Context context, final ProgressBar ProgressBar, final RecyclerView recyclerViewlist, final String Name_search, final TextView nothing_search, final String Intent) {
        page = 1;

        InternetActivity internetCheck = new InternetActivity();
        internetCheck.CheckNet(context);


        final String urlJsonArray = context.getString(R.string.site) +"/api/product/getProducts";
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
                            ImageItems.clear();
                            DiscountItems.clear();
                            IdItems.clear();
                            DescriptionItems.clear();
                            NameItems.clear();
                            PriceItems.clear();
                            TextItems.clear();
                            QuantityItems.clear();
                            LikeItems.clear();
                            TotalVotesItems.clear();
                            TotalCommentItems.clear();
                            OtherItems.clear();
                            ThumbnailItems.clear();
                            entesharatItems.clear();
                            nevisandeItems.clear();
                            salechapItems.clear();
                            chapItems.clear();
                            isbnItems.clear();
                            virayeshItems.clear();
                            // Log.i("mohsenjamali", "onResponse: " + response);


                            JSONObject jsonRootObject = new JSONObject(response);
                            JSONArray array = jsonRootObject.optJSONArray("Data");


                            //visible textview for 0 Item
                            if (jsonRootObject.getInt("TotalItemCount") == 0) {
                                nothing_search.setVisibility(View.VISIBLE);
                            } else {
                                nothing_search.setVisibility(View.GONE);
                            }


                            pageTotal = jsonRootObject.getInt("TotalItemCount") / 5;

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject person = array.getJSONObject(i);

                                ImageItems.add(person.getString("Main_Image"));
                                DiscountItems.add(person.getString("Discount"));
                                IdItems.add(person.getString("Id"));
                                NameItems.add(person.getString("Name"));
                                DescriptionItems.add(person.getString("Desc"));
                                PriceItems.add(person.getString("Price"));
                                TextItems.add(person.getString("Name"));
                                QuantityItems.add(person.getString("Qty"));
                                LikeItems.add(person.getString("Like"));
                                TotalVotesItems.add(person.getString("TotalVotes"));
                                TotalCommentItems.add(person.getString("TotalComment"));
                                OtherItems.add(person.getString("Images"));
                                ThumbnailItems.add(person.getString("Thumbnail"));
                                entesharatItems.add(person.getString("Publisher"));
                                nevisandeItems.add(person.getString("Author"));
                                salechapItems.add(person.getString("Year"));
                                chapItems.add(person.getString("Published"));
                                isbnItems.add(person.getString("ISBN"));
                                virayeshItems.add(person.getString("Editor"));

                                // Log.i("mohsenjamali", "onResponse: " + person.getString("Id"));
                            }
                            try {

                                recyclerViewlist.setLayoutManager(new GridLayoutManager(context, 2));

                                ad = new getSearchAdapter(context, ImageItems, DiscountItems, IdItems, DescriptionItems, NameItems, PriceItems, TextItems, QuantityItems, LikeItems, TotalVotesItems, TotalCommentItems, OtherItems,ThumbnailItems,entesharatItems, nevisandeItems, salechapItems, chapItems, isbnItems,virayeshItems, Intent, "", "", recyclerViewlist);
                                recyclerViewlist.setAdapter(ad);

                            } catch (Exception e) {
                                Toast.makeText(context, "خطایی پیش آمده است لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                                Log.i("mohsenjamali", "onResponseError: " + e);
                            }

                            recyclerViewlist.setVisibility(View.VISIBLE);
                            ProgressBar.setVisibility(View.GONE);

                            ad.setOnLoadMoreListener(new OnLoadMoreListener() {
                                @Override
                                public void onLoadMore() {
                                    NameItems.add(null);
                                    ad.notifyItemInserted(NameItems.size() - 1);

                                    //-----------------------------------------------------------------------------------------------
                                    RequestQueue MyRequestQueue = Volley.newRequestQueue(context);
                                    StringRequest MyStringRequest = new StringRequest(Request.Method.POST, urlJsonArray, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                            NameItems.remove(NameItems.size() - 1);
                                            ad.notifyItemRemoved(NameItems.size());

                                            try {
                                                JSONObject jsonRootObject = new JSONObject(response);
                                                JSONArray jsonArray = jsonRootObject.optJSONArray("Data");

                                                for (int i = 0; i < jsonArray.length(); i++) {

                                                    JSONObject person = jsonArray.getJSONObject(i);

                                                    ImageItems.add(person.getString("Images"));
                                                    DiscountItems.add(person.getString("Discount"));
                                                    IdItems.add(person.getString("Id"));
                                                    NameItems.add(person.getString("Name"));
                                                    DescriptionItems.add(person.getString("Desc"));
                                                    PriceItems.add(person.getString("Price"));
                                                    TextItems.add(person.getString("Name"));
                                                    QuantityItems.add(person.getString("Qty"));
                                                    LikeItems.add(person.getString("Like"));
                                                    TotalVotesItems.add(person.getString("TotalVotes"));
                                                    TotalCommentItems.add(person.getString("TotalComment"));
                                                    OtherItems.add(person.getString("Images"));
                                                    ThumbnailItems.add(person.getString("Thumbnail"));
                                                    entesharatItems.add(person.getString("Publisher"));
                                                    nevisandeItems.add(person.getString("Author"));
                                                    salechapItems.add(person.getString("Year"));
                                                    chapItems.add(person.getString("Published"));
                                                    isbnItems.add(person.getString("ISBN"));
                                                    virayeshItems.add(person.getString("Editor"));

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
                                            MyData.put("name", changeNumberToEN(Name_search));
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
                MyData.put("name", changeNumberToEN(Name_search));
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

    private String changeNumberToEN(String num) {
        num = num.replaceAll("۰", "0");
        num = num.replaceAll("۱", "1");
        num = num.replaceAll("۲", "2");
        num = num.replaceAll("۳", "3");
        num = num.replaceAll("۴", "4");
        num = num.replaceAll("۵", "5");
        num = num.replaceAll("۶", "6");
        num = num.replaceAll("۷", "7");
        num = num.replaceAll("۸", "8");
        num = num.replaceAll("۹", "9");
        return num;
    }
}
