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

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;



import static com.example.tiget.fortniteapi.Api.loadStore;
import static com.example.tiget.fortniteapi.MainActivity.BackgroundScreens;
import static com.example.tiget.fortniteapi.MainActivity.SCREEN_WIDTH_PX;
import static com.example.tiget.fortniteapi.MainActivity.density;
import static com.example.tiget.fortniteapi.MainActivity.sharedPreferences;


public class DailyShop extends Fragment {

    ImageView bg;
    GridView gridview;
    ShopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.daily_shop_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ShopAdapter(getContext());
        bg = view.findViewById(R.id.bg);
        bg.setImageResource(BackgroundScreens[sharedPreferences.getInt("image", 0)]);

        loadStore();
        gridview = view.findViewById(R.id.gridView);
        gridview.setNumColumns(2);
        gridview.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Api.DatabaseChangeEvent event) {
        if(event.loaded == true) {
            adapter.notifyDataSetChanged();
            gridview.setAdapter(adapter);
        }



    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}


class ShopAdapter extends BaseAdapter {
    private Context mContext;


    public ShopAdapter(Context c) {
        this.mContext = c;
    }




    public int getCount() {
        return Api.ShopItems.size();
    }

    public Object getItem(int position) {
        return Api.ShopItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
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
        ShopItem item = Api.ShopItems.get(position);
        Glide.with(mContext).load(item.image).into(icon);
        name.setText(item.name);
        price.setText(String.valueOf(item.price));


        return view;
    }

    // references to our images

}

