package com.example.acer.automobilesalesystem;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import java.util.ArrayList;
import java.util.List;
public class frontpageactivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    private Button button1;
    private Button button2;
    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<Data> array;
    private SwipeFlingAdapterView flingContainer;



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

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        array = new ArrayList<>();
        array.add(new Data("https://www.nriol.com/images/toyota1.png", "Brand: Toyata \n Model : Toyota Corolla Altis" + '\n' +
                "Variant : JS \n Price : Rs. 13.27 Lakh"));
        array.add(new Data("https://www.nriol.com/images/toyota2.png", "Brand: Toyota \n Model :  Toyota Etios	 \n Variant : J (Petrol) \n		Price : Rs. 6.03 lakhs"));
        array.add(new Data("https://www.nriol.com/images/toyota7.png", "Brand: Toyota \n Model : Toyota New Camry" + '\n' +
                "Variant : 2.5 G AT (Petrol) \n Price : Rs. 30.28 Lakh"));
        array.add(new Data("https://https://www.nriol.com/images/swift.png", "Brand: Hyundai \n Model :  Swift	 \n Variant : LXI(petrol) \n		Price : Rs. 6.03 lakhs"));
        array.add(new Data("https://www.nriol.com/images/vitara-brezza.png", "Brand: Hyundai \n Model : Vitara Brezza" + '\n' +
                "Variant : LDI(diesel) \n Price : Rs. 13.27 Lakh"));
        array.add(new Data("https://www.nriol.com/images/aventador.png", "Brand: Lamborgini \n Model : Aventador 	 \n Variant : LP 700-4 Roadster(petrol)\n		Price : 7.39 Crores"));
        array.add(new Data("https://www.nriol.com/images/huracan.png", "Model : Huracan" + '\n' +
                "Variant : Performante(petrol) \n Price : Rs 4.38 Crores"));
        array.add(new Data("https://www.nriol.com/images/urus.png", "Model :  Toyota Etios	 \n Variant : Twin-Turbo V8(petrol) \n		Price : Rs 3.35 Crores"));
        array.add(new Data("https://www.nriol.com/images/toyota1.png", "Model : Toyota Corolla Altis" + '\n' +
                "Variant : JS \n Price : Rs. 13.27 Lakh"));
        array.add(new Data("https://www.nriol.com/images/toyota2.png", "Model :  Toyota Etios	 \n Variant : J (Petrol) \n		Price : Rs. 6.03 lakhs"));
        array.add(new Data("https://www.nriol.com/images/toyota1.png", "Model : Toyota Corolla Altis" + '\n' +
                "Variant : JS \n Price : Rs. 13.27 Lakh"));
        array.add(new Data("https://www.nriol.com/images/toyota2.png", "Model :  Toyota Etios	 \n Variant : J (Petrol) \n		Price : Rs. 6.03 lakhs"));


        myAppAdapter = new MyAppAdapter(array, frontpageactivity.this);
        flingContainer.setAdapter(myAppAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                array.remove(0);
                myAppAdapter.notifyDataSetChanged();
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
            }

            @Override
            public void onRightCardExit(Object dataObject) {

                array.remove(0);
                myAppAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

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

                myAppAdapter.notifyDataSetChanged();
            }
        });
    }


    public static class ViewHolder {
        public static FrameLayout background;
        public TextView DataText;
        public ImageView cardImage;


    }

    public class MyAppAdapter extends BaseAdapter {


        public List<Data> parkingList;
        public Context context;

        private MyAppAdapter(List<Data> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.DataText = (TextView) rowView.findViewById(R.id.bookText);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.DataText.setText(parkingList.get(position).getDescription() + "");

            Glide.with(frontpageactivity.this).load(parkingList.get(position).getImagePath()).into(viewHolder.cardImage);

            return rowView;
        }
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

        if (id == R.id.action_settings) {
            return true;
        }

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
            intent = new Intent(frontpageactivity.this,SellVehicle.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            Intent i=new Intent(frontpageactivity.this,AboutUs.class);
            startActivity(i);
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
                        Toast.makeText(frontpageactivity.this,"Please write something before you submit your feedback.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(frontpageactivity.this, "Thank you for your feedback :)", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(frontpageactivity.this,frontpageactivity.class);
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
    public void openActivity4(){
        Intent intent=new Intent(this,SellVehicle.class);
        startActivity(intent);
    }




}

