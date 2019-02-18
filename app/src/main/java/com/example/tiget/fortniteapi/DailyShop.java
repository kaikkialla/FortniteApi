package com.example.tiget.fortniteapi;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tiget.fortniteapi.model.Item;
import com.example.tiget.fortniteapi.model.ShopResult;
import com.example.tiget.fortniteapi.service.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tiget.fortniteapi.DailyShop.adapter;
import static com.example.tiget.fortniteapi.DailyShop.setBackground;
import static com.example.tiget.fortniteapi.MainActivity.BackgroundScreens;
import static com.example.tiget.fortniteapi.MainActivity.SCREEN_WIDTH_PX;
import static com.example.tiget.fortniteapi.MainActivity.density;
import static com.example.tiget.fortniteapi.MainActivity.sharedPreferences;
import static com.example.tiget.fortniteapi.Presenter.requestShop;
import static com.example.tiget.fortniteapi.ShopAdapter.mIcon;
import static com.example.tiget.fortniteapi.ShopAdapter.mItems;
import static com.example.tiget.fortniteapi.ShopAdapter.setInfo;


public class DailyShop extends Fragment {

    static ImageView bg;
    static GridView gridview;
    public static ShopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.daily_shop_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ShopAdapter(getActivity());
        bg = view.findViewById(R.id.bg);
        gridview = view.findViewById(R.id.gridView);

        gridview.setNumColumns(2);
        gridview.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Presenter.getInstance().setBackground();
    }

    public static void setBackground(int i) {
        bg.setImageResource(i);
    }
}


class Presenter{

    public static Presenter instance;
    public static Presenter getInstance() {
        if (instance == null) { instance = new Presenter(); }
        return instance;
    }

    public void setBackground() {
        int id = sharedPreferences.getInt("image", 0);
        int image = BackgroundScreens[id];
        DailyShop.setBackground(image);
    }

    public static void requestShop(String language, int pos) {
        Service.getInstance().getShop(language).enqueue(new Callback<ShopResult>() {
            @Override
            public void onResponse(Call<ShopResult> call, Response<ShopResult> response) {
            List<Item> itemList = response.body().items;
            String image =  itemList.get(pos).image;
            String name = itemList.get(pos).name;
            int price =  itemList.get(pos).price;


            /*
                В mItems добавляем новый элемент и делаем swap(еще и в onResume)

             */
            //mItems.add(new Item(image, name, price));
            //mItems.add(new Item());
            setInfo(image, name, price);

            adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ShopResult> call, Throwable t) {}
        });
    }
}



class ShopAdapter extends BaseAdapter {
    public static List<Item> mItems = new ArrayList<>();
    private static Context mContext;
    private static ImageView mIcon;
    private static TextView mName;
    private static TextView mPrice;

    public ShopAdapter(Context c) {
        this.mContext = c;
    }




    public int getCount() {
        return mItems.size();
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = new View(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate(R.layout.shop_grid_view_item, parent, false);
        } else {
            view = convertView;
        }

        mIcon = view.findViewById(R.id.icon);
        mName = view.findViewById(R.id.name);
        mPrice = view.findViewById(R.id.price);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)(SCREEN_WIDTH_PX - 8 * density) / 2, (int)(SCREEN_WIDTH_PX - 8 * density) / 2);
        mIcon.setLayoutParams(params);

        mName.setSelected(true);

        requestShop("en", position);

        return view;


    }

    public static void setInfo(String image, String name, int price) {
        Glide.with(mContext).load(image).into(mIcon);
        mName.setText(name);
        mPrice.setText(String.valueOf(price));
    }

    public void swap(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }



    // references to our images

}
