package com.example.acer.automobilesalesystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.automobilesalesystem.models.VehicleResponseModel;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class frontpageactivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    private Button button1;
    private Button button2;
    private ProgressBar progress;
    public static ViewHolder viewHolder;
    private ArrayList<Data> array;
    private SwipeFlingAdapterView flingContainer;
    private VehicleAdapter adapter;
    private ArrayList<VehicleResponseModel> vehicleList;
    private Button btnNoNewVehicle;
    private MySharedPreference preference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpageactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        preference = new MySharedPreference(this);

        //-----------------------------------swippe---------------------------/

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
        btnNoNewVehicle = findViewById(R.id.btnNoVehicle);
        progress = findViewById(R.id.progress);
        this.vehicleList = new ArrayList<>();
        getVehicleList();

    }

    private void getVehicleList() {
        progress.setVisibility(View.VISIBLE);
        flingContainer.setVisibility(View.GONE);
        ApiService service = RetrofitSingleton.getApiService();
        Call<ArrayList<VehicleResponseModel>> call = service.getVehicleList();
        call.enqueue(new Callback<ArrayList<VehicleResponseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<VehicleResponseModel>> call, Response<ArrayList<VehicleResponseModel>> response) {
                if (response.isSuccessful()) {
                    vehicleList = response.body();
                    showSwipableVehicleList();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<VehicleResponseModel>> call, Throwable t) {

            }
        });
    }

    private void showSwipableVehicleList() {

        adapter = new VehicleAdapter(vehicleList, frontpageactivity.this);
        flingContainer.setAdapter(adapter);
        progress.setVisibility(View.GONE);
        flingContainer.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                vehicleList.remove(0);
                adapter.notifyDataSetChanged();
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
            }

            @Override
            public void onRightCardExit(Object dataObject) {

                vehicleList.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                if (itemsInAdapter < 1) {

                    btnNoNewVehicle.setVisibility(View.VISIBLE);
                    flingContainer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                adapter.notifyDataSetChanged();
            }
        });
    }


    public static class ViewHolder {
        public static FrameLayout background;
        public TextView DataText;
        public ImageView cardImage;


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.frontpageactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

      /*  if (id == R.id.action_settings) {
            return true;
        }*/
      if (id == R.id.signout)
          signoutClicked();

        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {
            Intent intent;
            intent = new Intent(frontpageactivity.this, SellVehicle.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(frontpageactivity.this, AboutUs.class);
            startActivity(i);
//        } else if (id == R.id.myprofile) {
//            Intent it=new Intent(frontpageactivity.this,UserProfile.class);
//            startActivity(it);

        } else if (id == R.id.nav_share) {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String shareBody = "your subject here";
            myIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
            myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(myIntent, "Share using"));

        } else if (id == R.id.nav_send) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(frontpageactivity.this);

            View mView = getLayoutInflater().inflate(R.layout.popwindow, null);
            final EditText mfeedback = (EditText) mView.findViewById(R.id.editText4);
            Button msubmit = (Button) mView.findViewById(R.id.submitButton2);


            msubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mfeedback.getText().toString().isEmpty()) {
                        Toast.makeText(frontpageactivity.this, "Please write something before you submit your feedback.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(frontpageactivity.this, "Thank you for your feedback :)", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(frontpageactivity.this, frontpageactivity.class);
                        startActivity(i);
                    }

                }
            });
            builder.setView(mView);
            final AlertDialog dialog = builder.create();
            dialog.show();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void signoutClicked() {
        preference.setKeyValues("isLoggedIn", false);
        openLoginActivity();
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void openActivity4() {
        Intent intent = new Intent(this, SellVehicle.class);
        startActivity(intent);
    }
}

