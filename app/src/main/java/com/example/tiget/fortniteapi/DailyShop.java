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

import com.example.tiget.fortniteapi.model.ShopResult;
import com.example.tiget.fortniteapi.service.Service;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tiget.fortniteapi.DailyShop.setBackground;
import static com.example.tiget.fortniteapi.MainActivity.BackgroundScreens;
import static com.example.tiget.fortniteapi.MainActivity.SCREEN_WIDTH_PX;
import static com.example.tiget.fortniteapi.MainActivity.density;
import static com.example.tiget.fortniteapi.MainActivity.sharedPreferences;


public class DailyShop extends Fragment {

    static ImageView bg;
    static GridView gridview;
    static ShopAdapter adapter;

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
}



class ShopAdapter extends BaseAdapter {
    private Context mContext;
    Response<ShopResult> resultResponse;

    public ShopAdapter(Context c) {
        this.mContext = c;
    }




    public int getCount() {
        return 1;
    }

    public Object getItem(int position) {
        return 1;
    }

    public long getItemId(int position) {
        return position;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("fpkspfsa", "Succ");
        View view;
        ImageView icon;
        TextView name;
        TextView price;

        if (convertView == null) {
            view = new View(mContext);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate(R.layout.shop_grid_view_item, parent, false);
        } else {
            view = convertView;
        }



        icon = view.findViewById(R.id.icon);
        name = view.findViewById(R.id.name);
        price = view.findViewById(R.id.price);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)(SCREEN_WIDTH_PX - 8 * density) / 2, (int)(SCREEN_WIDTH_PX - 8 * density) / 2);
        icon.setLayoutParams(params);

        name.setSelected(true);



        Service.getSevices().getShop("en").enqueue(new Callback<ShopResult>() {
            @Override
            public void onResponse(Call<ShopResult> call, Response<ShopResult> response) {
                Log.e("fpkspfsa", "Success");
                resultResponse = response;

            }

            @Override
            public void onFailure(Call<ShopResult> call, Throwable t) {
                Log.e("fpkspfsa", "Fail");
            }
        });
        return view;
    }



    // references to our images

}
