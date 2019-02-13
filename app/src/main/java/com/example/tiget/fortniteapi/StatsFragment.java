package com.example.tiget.fortniteapi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import static com.example.tiget.fortniteapi.Api.loadStore;
import static com.example.tiget.fortniteapi.Api.userInfo;
import static com.example.tiget.fortniteapi.MainActivity.BackgroundScreens;
import static com.example.tiget.fortniteapi.MainActivity.sharedPreferences;

public class StatsFragment extends Fragment {

    ImageView bg;
    TextView textView;
    EditText editText;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stats_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bg = view.findViewById(R.id.bg);
        editText = view.findViewById(R.id.editText);
        textView = view.findViewById(R.id.textView);
        btn = view.findViewById(R.id.btn);

        bg.setImageResource(BackgroundScreens[sharedPreferences.getInt("image", 0)]);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = editText.getText().toString();
                Api.loadId(uname);
            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void StoreStatusEvent(Api.idStatus event) {
        if(event.loaded == true) {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= userInfo.platforms.size() - 1; i++) {
                sb.append(userInfo.platforms.get(i));
                sb.append(" ");
            }
            String result = sb.toString();

            textView.setText(userInfo.id + "\n" + result);
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