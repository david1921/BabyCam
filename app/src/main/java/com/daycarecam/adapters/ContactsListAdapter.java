package com.daycarecam.adapters;

/**
 * Created by dmbur00 on 1/10/17.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.twilio.twiliovideo.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ContactsListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> name;
    //private final ArrayList<String> imgid;

    public ContactsListAdapter(Activity context, ArrayList<String> name) {
        super(context, R.layout.content_list_view_items, name);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.name = name;
        //this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.content_list_view_items, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.name);
       // ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        txtTitle.setText(name.get(position));
//        URL url = null;
//        InputStream is = null;
//        try {
//            url = new URL(imgid.get(position));
//            is = url.openConnection().getInputStream();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//        Bitmap bitmap = BitmapFactory.decodeStream(is);
//        imageView.setImageBitmap(bitmap);

        return rowView;

    };
}