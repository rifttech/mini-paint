package org.apparatum.mini_paint;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    @BindView(R.id.canvas)
    CanvasView mCanvasView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private boolean mFabStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab)
    public void openMenu(){

        mCanvasView.clearCanvas();

        /*
        if (mFabStatus){
            expandFabMenu();
        }else {
            hideFabMenu();
        }
        mFabStatus = !mFabStatus;
        */
    }

    private void hideFabMenu() {
        Log.i(TAG, "hideFabMenu: ");
    }


    private void expandFabMenu() {
        Log.i(TAG, "expandFabMenu: ");
    }


}