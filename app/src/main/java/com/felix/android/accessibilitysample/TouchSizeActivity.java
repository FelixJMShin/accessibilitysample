package com.felix.android.accessibilitysample;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TouchSizeActivity extends AppCompatActivity {
    private static final int EXTRA_RIGHT_SIZE = 200;
    private static final int EXTRA_BOTTOM_SIZE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_size);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
           actionBar.setDisplayHomeAsUpEnabled(true);

        View parentView = findViewById(R.id.touch_size_constraint_layout);

        parentView.post(new Runnable() {
            @Override
            public void run() {
                Rect delegateArea = new Rect();
                Button textButton = findViewById(R.id.touch_delegate_button);

                textButton.getHitRect(delegateArea);

                textButton.setEnabled(true);
                textButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(TouchSizeActivity.this, getString(R.string.touch_button_clicked_message), Toast.LENGTH_SHORT).show();
                    }
                });

                delegateArea.right += EXTRA_RIGHT_SIZE;
                delegateArea.bottom += EXTRA_BOTTOM_SIZE;

                TouchDelegate touchDelegate = new TouchDelegate(delegateArea, textButton);

                if(View.class.isInstance(textButton.getParent()))
                    ((View) textButton.getParent()).setTouchDelegate(touchDelegate);



            }
        });


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
