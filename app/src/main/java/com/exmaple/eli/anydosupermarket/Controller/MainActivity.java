package com.exmaple.eli.anydosupermarket.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.exmaple.eli.anydosupermarket.Logic.ProductData;
import com.exmaple.eli.anydosupermarket.R;
import com.exmaple.eli.anydosupermarket.View.DynamicAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView mListView = (ListView) findViewById(R.id.main_list);
        DynamicAdapter adapter = new DynamicAdapter(this, R.layout.product_view, new ArrayList<ProductData>());
        mListView.setAdapter(adapter);
    }
}
