package ir.shop1.shop1.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import co.ronash.pushe.Pushe;
import ir.shop1.shop1.AlertDialog.LoginSignupAlert;
import ir.shop1.shop1.Engine.SetterGetterNumberOrder;
import ir.shop1.shop1.Fragment.BasketFragment;
import ir.shop1.shop1.Fragment.FourFragment;
import ir.shop1.shop1.Fragment.NavigationDrawerFragment;
import ir.shop1.shop1.Fragment.OneFragment;
import ir.shop1.shop1.Fragment.ThreeFragment;
import ir.shop1.shop1.Fragment.TwoFragment;
import ir.shop1.shop1.R;
import ir.shop1.shop1.Volley.getBasketNumber;
import ir.shop1.shop1.Volley.getToken;
import ir.shop1.shop1.otherClass.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    private Toolbar my_toolbar;
    private BottomNavigationView bottomNavigation, bottomNavigation2;
    private Fragment fragment, fragment2;
    private FragmentManager fragmentManager;
    private ImageView basket_float;
    private MaterialDialog about, contact;
    private TextView BadgeCounter;
    private Bundle address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        Pushe.initialize(this,true);


        bottomNavigation = findViewById(R.id.bottomnav);
        bottomNavigation2 = findViewById(R.id.bottomnav1);

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        basket_float = findViewById(R.id.basket_float);
        my_toolbar = findViewById(R.id.toolbar);
        BadgeCounter = findViewById(R.id.badgeCounter);
        address = getIntent().getExtras();

        /**this is for dynamic category.delete   SharedPreferences categoryMiddleId for static middle category**/
        SharedPreferences categoryMiddleId = getApplicationContext().getSharedPreferences("CategoryMiddle", 0);
        categoryMiddleId.edit().clear().apply();


        Basket();
        BottomNavigationsimple();
        NavigationDrawer();
        setBadgeCounter(this);


    }

    private void BottomNavigationsimple() {
        bottomNavigation.inflateMenu(R.menu.bottom_menu);
        bottomNavigation.setSelected(true);
        fragmentManager = getSupportFragmentManager();

//        //set default fragment
//        fragment = new SearchFragment();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.main_container, fragment).commit();
//        //end

        //set default fragment
        if (address != null && address.getString("NameActivity", "null").equals("BasketFragment")) {
            fragment = new BasketFragment();
            bottomNavigation.setSelectedItemId(R.id.action_basket);
        } else if (address != null && address.getString("NameActivity", "null").equals("TwoFragment")) {
            fragment = new TwoFragment();
            bottomNavigation.setSelectedItemId(R.id.action_category);
        } else {
            fragment = new OneFragment();
            bottomNavigation.setSelectedItemId(R.id.action_main_menu);
        }


        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment).commit();
        //end

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.action_main_menu:
                        fragment = new OneFragment();
                        setBadgeCounter(MainActivity.this);
                        break;
                    case R.id.action_category:
                        fragment = new TwoFragment();
                        setBadgeCounter(MainActivity.this);
                        break;
                    case R.id.action_search:
                        fragment = new ThreeFragment();
                        setBadgeCounter(MainActivity.this);
                        break;
                    case R.id.action_buy_list:
                        fragment = new FourFragment();
                        setBadgeCounter(MainActivity.this);
                        break;
                    case R.id.action_basket:
                        fragment = new BasketFragment();
                        setBadgeCounter(MainActivity.this);
                        break;
                }
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });

        // bottomNavigation.getMenu().findItem(R.id.action_cart).setChecked(true);

        //disable shift bottom navigation
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            // set your height here
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 23, displayMetrics);
            // set your width here
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 23, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
    }
//    private void BottomNavigation() {
//        //   bottomNavigation.inflateMenu(R.menu.menu_main);
//        //  bottomNavigation2.inflateMenu(R.menu.buttom_menu2);
//        bottomNavigation.setSelected(true);
//        bottomNavigation2.setSelected(true);
//        fragmentManager = getSupportFragmentManager();
//
//        //set default fragment
//        if (address != null && address.getString("NameActivity", "null").equals("BasketFragment")) {
//            fragment = new BasketFragment();
//        } else if (address != null && address.getString("NameActivity", "null").equals("TwoFragment")) {
//            fragment = new TwoFragment();
//        } else {
//            fragment = new OneFragment();
//        }
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.main_container, fragment).commit();
//        //end
//        bottomNavigation2.getMenu().getItem(0).setCheckable(false);
//        bottomNavigation2.getMenu().getItem(1).setCheckable(false);
//
//        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                switch (id) {
//                    case R.id.action_main_menu:
//                        fragment = new OneFragment();
//
//
//                        //off or on selected button color
//                        bottomNavigation.getMenu().getItem(0).setCheckable(true);
//                        bottomNavigation.getMenu().getItem(1).setCheckable(true);
//                        bottomNavigation2.getMenu().getItem(0).setCheckable(false);
//                        bottomNavigation2.getMenu().getItem(1).setCheckable(false);
//
//                        break;
//                    case R.id.action_category:
//                        fragment = new TwoFragment();
//
//                        bottomNavigation.getMenu().getItem(0).setCheckable(true);
//                        bottomNavigation.getMenu().getItem(1).setCheckable(true);
//                        bottomNavigation2.getMenu().getItem(0).setCheckable(false);
//                        bottomNavigation2.getMenu().getItem(1).setCheckable(false);
//
//                        break;
//                }
//
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.main_container, fragment).commit();
//                return true;
//            }
//        });
//        bottomNavigation2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                switch (id) {
//
//                    case R.id.action_search:
//                        fragment = new ThreeFragment();
//
//                        bottomNavigation2.getMenu().getItem(0).setCheckable(true);
//                        bottomNavigation2.getMenu().getItem(1).setCheckable(true);
//                        bottomNavigation.getMenu().getItem(0).setCheckable(false);
//                        bottomNavigation.getMenu().getItem(1).setCheckable(false);
//                        break;
//                    case R.id.action_buy_list:
//                        fragment = new FourFragment();
//
//                        bottomNavigation2.getMenu().getItem(0).setCheckable(true);
//                        bottomNavigation2.getMenu().getItem(1).setCheckable(true);
//                        bottomNavigation.getMenu().getItem(0).setCheckable(false);
//                        bottomNavigation.getMenu().getItem(1).setCheckable(false);
//                        break;
//                }
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.main_container, fragment).commit();
//                return true;
//            }
//        });
//
//        basket_float.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                BasketFragment.getInstance().BadgeCounter(BadgeCounter);
//                // new BasketFragment().BadgeCounter(BadgeCounter);
//                fragment = new BasketFragment();
//
//                bottomNavigation.getMenu().getItem(0).setCheckable(false);
//                bottomNavigation.getMenu().getItem(1).setCheckable(false);
//                bottomNavigation2.getMenu().getItem(0).setCheckable(false);
//                bottomNavigation2.getMenu().getItem(1).setCheckable(false);
//
//
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.main_container, fragment, "Basket").commit();
//
//            }
//        });
//        // bottomNavigation.getMenu().findItem(R.id.action_cart).setChecked(true);
//
//        //disable shift bottom navigation
//        //   BottomNavigationViewHelper.disableShiftMode(bottomNavigation);
//
//        //set Icon size
//        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigation.getChildAt(0);
//        for (int i = 0; i < menuView.getChildCount(); i++) {
//            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
//            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
//            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//            // set your height here
//            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, displayMetrics);
//            // set your width here
//            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, displayMetrics);
//            iconView.setLayoutParams(layoutParams);
//
//        }
//        BottomNavigationMenuView menuView2 = (BottomNavigationMenuView) bottomNavigation2.getChildAt(0);
//        for (int i = 0; i < menuView2.getChildCount(); i++) {
//            final View iconView = menuView2.getChildAt(i).findViewById(android.support.design.R.id.icon);
//            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
//            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//            // set your height here
//            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 22, displayMetrics);
//            // set your width here
//            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 22, displayMetrics);
//            iconView.setLayoutParams(layoutParams);
//
//        }
//
//
//    }

    public void NavigationDrawer() {
        final LayoutInflater factory = getLayoutInflater();

        final View textEntryView = factory.inflate(R.layout.fragment_one, null);
        //navigation drawer
        NavigationDrawerFragment my_nav = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        my_nav.setUp(R.id.fragment_navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), my_toolbar);
        //end navigation drawer
    }

    private void Basket() {

        TextView titleActionBar = findViewById(R.id.TitleActionBar);
        titleActionBar.setText("کتاب دانش");
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/akaman.ttf");
        titleActionBar.setTypeface(face);
        titleActionBar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        //set badgetNumber

        BadgeCounter.setVisibility(View.GONE);

        SetterGetterNumberOrder setterGetter = new SetterGetterNumberOrder(MainActivity.this);
        BadgeCounter.setText(setterGetter.getNumberOrder());

        int badget_number = Integer.valueOf(setterGetter.getNumberOrder());

        TextView BadgeCounter_bottom = findViewById(R.id.badgeCounter_bottom);

        if (badget_number != 0) {
            BadgeCounter_bottom.setVisibility(View.VISIBLE);
            BadgeCounter_bottom.setText(String.valueOf(badget_number));
        } else {
            BadgeCounter_bottom.setVisibility(View.GONE);
        }
        getToken token = new getToken();
        if (!token.Ok(MainActivity.this)) {
            BadgeCounter_bottom.setVisibility(View.GONE);
        }
        LinearLayout backButton = findViewById(R.id.backButton);
        backButton.setVisibility(View.GONE);

        // set CalculateBasket icon button
        ImageView basket_icon_bar = findViewById(R.id.basket_icon);
        basket_icon_bar.setVisibility(View.GONE);

        RelativeLayout BasketLayout = findViewById(R.id.BasketLayout);
        BasketLayout.setVisibility(View.GONE);

        ImageView profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check Login
                getToken token = new getToken();
                LoginSignupAlert loginAlert = new LoginSignupAlert();
                if (token.Ok(getApplicationContext())) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                } else {
                    loginAlert.alertShow(MainActivity.this, MainActivity.class);
                }

            }
        });

    }


    private static final int Time_Between_Two_Back = 2000;
    private long TimeBackPressed;

    @Override
    public void onBackPressed() {


        if (TimeBackPressed + Time_Between_Two_Back > System.currentTimeMillis()) {
            finishAffinity();
            return;
        } else {
            Toast.makeText(getBaseContext(), "جهت خروج، مجددا کلید برگشت را فشار دهید", Toast.LENGTH_SHORT).show();
        }
        TimeBackPressed = System.currentTimeMillis();
    }


    //set BadgeCounter from Basketfragment
    public void setBadgeCounter(Context context) {
        getToken token = new getToken();

        if (token.Ok(context)) {
            getBasketNumber getBasketNumber = new getBasketNumber();
            getBasketNumber.get_Items(context);

        }

        SetterGetterNumberOrder setterGetter = new SetterGetterNumberOrder(context);
        TextView BadgeCounter = ((Activity) context).findViewById(R.id.badgeCounter_bottom);
        BadgeCounter.setText(setterGetter.getNumberOrder());


        if (setterGetter.getNumberOrder().equals("0")) {
            BadgeCounter.setVisibility(View.GONE);
        }
    }
}
