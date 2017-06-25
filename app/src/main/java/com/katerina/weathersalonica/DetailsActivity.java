package com.katerina.weathersalonica;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_details, new PlaceholderFragment())
                    .commit();
        }
    }


    public static class PlaceholderFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            Intent weatherPackage = getActivity().getIntent();
            View rootView = inflater.inflate(R.layout.activity_details, container, false);

            if(weatherPackage != null && weatherPackage.hasExtra(Intent.EXTRA_TEXT)){
                String weather = weatherPackage.getStringExtra(Intent.EXTRA_TEXT);
                TextView textView = (TextView)rootView.findViewById(R.id.detail_text);
                textView.setText(weather);
            }
            return  rootView;
        }
    }


}
