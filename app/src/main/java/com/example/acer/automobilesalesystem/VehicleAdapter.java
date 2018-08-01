package com.example.acer.automobilesalesystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.automobilesalesystem.models.VehicleResponseModel;

import java.util.ArrayList;

public class VehicleAdapter extends BaseAdapter {


    public Context context;
    public static ViewHolder viewHolder;
    private ArrayList<VehicleResponseModel> vehicleList;

    public VehicleAdapter(ArrayList<VehicleResponseModel> vehicleList, Context context) {
        this.vehicleList = vehicleList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return vehicleList.size();
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

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item, parent, false);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) rowView.findViewById(R.id.tv_vehicle_title);
            viewHolder.brand = (TextView) rowView.findViewById(R.id.tv_vehicle_brand);
            viewHolder.price = (TextView) rowView.findViewById(R.id.tv_vehicle_price);
            viewHolder.description = (TextView) rowView.findViewById(R.id.tv_vehicle_description);
            viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
            viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
            rowView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(vehicleList.get(position).vehicleType + "");
        viewHolder.brand.setText(vehicleList.get(position).brand + "");
        viewHolder.price.setText(vehicleList.get(position).price + "");
        viewHolder.description.setText(vehicleList.get(position).description + "");

        if (vehicleList.get(position).image != null && vehicleList.get(position).image.equalsIgnoreCase(""))
            Glide.with(context)
                    .load(vehicleList.get(position).image)
                    .into(viewHolder.cardImage);

        return rowView;
    }



    public static class ViewHolder {
        public static FrameLayout background;
        public TextView name;
        public TextView brand;
        public TextView price;
        public TextView description;
        public ImageView cardImage;


    }

}
