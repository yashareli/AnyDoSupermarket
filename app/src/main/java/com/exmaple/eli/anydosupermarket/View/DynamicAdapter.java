package com.exmaple.eli.anydosupermarket.View;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.exmaple.eli.anydosupermarket.Logic.IOnNewProductListener;
import com.exmaple.eli.anydosupermarket.Logic.ModelManager;
import com.exmaple.eli.anydosupermarket.Logic.ProductData;
import com.exmaple.eli.anydosupermarket.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eliyashar on 18/10/16.
 */
public class DynamicAdapter extends ArrayAdapter<ProductData> implements IOnNewProductListener {

    private ModelManager mModelManager;
    private int mResourceId;
    private Handler mHandler;
    public DynamicAdapter(Context context, int resource, List<ProductData> items) {
        super(context, resource, items);
        mResourceId = resource;
        mHandler = new Handler();
        mModelManager = ModelManager.getInstance(getContext());
        mModelManager.observe(this);
    }

    @Override
    public void onNewProduct(final ProductData product) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                add(product);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = View.inflate(getContext(), mResourceId, null);
        }
        View circleView = convertView.findViewById(R.id.circle_view);
        TextView weightText = (TextView) convertView.findViewById(R.id.weight_text);
        TextView nameText = (TextView) convertView.findViewById(R.id.name_text);

        ProductData product = getItem(position);
//        circleView.setBackgroundColor(product.getColor());
        GradientDrawable drawable = (GradientDrawable) circleView.getBackground().mutate();
        drawable.setColor(product.getColor());
        weightText.setText(product.getWeight());
        nameText.setText(product.getName());
        return convertView;
    }
}
