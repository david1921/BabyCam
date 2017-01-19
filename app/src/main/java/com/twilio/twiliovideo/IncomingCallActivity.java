package com.twilio.twiliovideo;

import android.app.Activity;
import android.app.ListActivity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirection;

import java.util.ArrayList;
import java.util.Arrays;

public class IncomingCallActivity extends Activity {
    private GestureDetector gd;
    private  ImageView pickUpIcon;
    private  String callerDevice = "";
    private  String callerFirebaseToken= "";
  float x1,x2;
    SwipeActionAdapter swipeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            int flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;

        getWindow().addFlags(flags);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            callerDevice = data.getString("CALLER_ID");
            callerFirebaseToken = data.getString("CALLER_FIREBASE_TOKEN");
        }

        setContentView(R.layout.activity_incoming_call);
        pickUpIcon = (ImageView) findViewById(R.id.callPickUpIcon);
        pickUpIcon.setImageResource(R.drawable.pickup);

        //Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_anim);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 3.0f, 1f, 3.0f,  pickUpIcon.getWidth() / 2.0f,  pickUpIcon.getHeight() / 2.0f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setInterpolator(this, android.R.interpolator.accelerate_decelerate);
        pickUpIcon.startAnimation(scaleAnimation);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.e("ScaleActivity", ">>>>>>>>>>>>>>>>>>>>>>>>Scale started");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.e("ScaleActivity", ">>>>>>>>>>>>>>>>>>>>>>>>>Scale ended");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

   //     gd = new GestureDetector(this, this);

    }

//    @Override
//    public boolean onDown(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public void onShowPress(MotionEvent e) {
//
//    }
//
//    @Override
//    public boolean onSingleTapUp(MotionEvent e) {
//        return false;
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        return false;
//    }
//
//    @Override
//    public void onLongPress(MotionEvent e) {
//
//    }
//
//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        float sensitivity = 50;
//        if(e2.getX() - e1.getX() > sensitivity){
//            //Setting Image Resource to Right_Arrow on Swipe Right
//            pickUpIcon.setImageResource(R.drawable.icon);
//
//            return true;
//        }
//        return true;
//    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN: {
                x1 = touchevent.getX();
                // y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = touchevent.getX();
                //  y2 = touchevent.getY();


                if (x1 < x2) {
                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
                    Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startIntent.putExtra("tag", "ACCEPT");
                    startIntent.putExtra("CALLER_ID", callerDevice);
                    startIntent.putExtra("CALLER_FIREBASE_TOKEN", callerFirebaseToken);
                    getApplicationContext().startActivity(startIntent);
                }

                // if right to left sweep event on screen
                if (x1 > x2) {
                    Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
                    this.finish();
                }
                break;
            }
        }
        return false;
    }

}
