package com.gdagtekin.zubizucardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DataActivity extends AppCompatActivity {

    private TextView title, text;
    private ImageView image;
    private Button follow;
    private boolean isFollow;

    private final static String BUNDLE_EXTRAS="BUNDLE_EXTRAS";
    private final static String BUNDLE_TEXT="BUNDLE_TEXT";
    private final static String BUNDLE_TITLE="BUNDLE_TITLE";
    private final static String BUNDLE_IMAGE="BUNDLE_IMAGE";
    private final static String BUNDLE_FOLLOW="BUNDLE_FOLLOW";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        title=(TextView)findViewById(R.id.tvDataTitle);
        text=(TextView)findViewById(R.id.tvDataText);
        image=(ImageView) findViewById(R.id.ivDataImage);
        follow=(Button) findViewById(R.id.btDataTakipEt);
        DisplayMetrics metrics =getResources().getDisplayMetrics();
        int height = (int) (metrics.widthPixels * 0.5625);
        image.getLayoutParams().height = height;

        //Bundle ile tıklanın cardviewdan veriler geliyor
        Bundle bundle=getIntent().getBundleExtra(BUNDLE_EXTRAS);
        title.setText(bundle.getString(BUNDLE_TITLE));
        text.setText(bundle.getString(BUNDLE_TEXT));
        ImageLoader.getInstance().displayImage(bundle.getString(BUNDLE_IMAGE)
                ,image);
        isFollow=bundle.getBoolean(BUNDLE_FOLLOW);

        if(bundle.getBoolean(BUNDLE_FOLLOW))
            follow.setBackgroundResource(R.drawable.ic_star_black_24dp);
        else
            follow.setBackgroundResource(R.drawable.ic_star_border_black_24dp);

    }
}
