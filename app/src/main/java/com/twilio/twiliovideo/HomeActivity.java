package com.twilio.twiliovideo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.daycarecam.adapters.ContactsListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {
//*************refresh page evryday or if call dont succeed refresh screen ,might need fresh firebase token*************
    private String  contactsResponseJson;
    private OkHttpClient client = new OkHttpClient();
    //ArrayList<HashMap<String,String>> contactList = new ArrayList<HashMap<String, String>>();
    ArrayList<String> contactList = new ArrayList<String>();
    ArrayList<String> firebaseList = new ArrayList<String>();
    ArrayList<String> imageList = new ArrayList<String>();
    ListView simpleListView;
    ListAdapter adapter;
    TextView name;
    ContactsListAdapter contactAdapter =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        name = (TextView)findViewById(R.id.name);
        simpleListView=(ListView)findViewById(R.id.simpleListView);
        new GetContactsTask(this).execute();

        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = contactList.get(position);
                String firebaseToken = firebaseList.get(position);
                Intent userDataIntent = new Intent(getApplicationContext(), MakeCallActivity.class);
                userDataIntent.putExtra("FIREBASE_TOKEN", firebaseToken);
                userDataIntent.putExtra("NAME", name);
                startActivity(userDataIntent);
            }
        });


    }

    String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    class GetContactsTask extends AsyncTask<String, Void, String> {

        private Activity mContext;
        public GetContactsTask(Activity context){
            mContext = context;
        }

        protected String doInBackground(String... urls) {

            JSONArray contactsArray = null;
            try {
                contactsResponseJson = doGetRequest("https://iot-api.herokuapp.com/users.json");
                contactsArray = new JSONArray(contactsResponseJson);
                for (int i = 0; i < contactsArray.length();i++){
                    JSONObject contactObj = contactsArray.getJSONObject(i);
                    String email = contactObj.getString("email");
                    String firebase_token = contactObj.getString("firebase_token");
                    contactList.add(email);
                    firebaseList.add(firebase_token);
                    Log.e("msg", ">>>>>>>>>>>>>>>>>" +email);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

         return contactsResponseJson ;
        }

        protected void onPostExecute(String jsonListResponse) {


            contactAdapter = new ContactsListAdapter(mContext, contactList);
            simpleListView.setAdapter(contactAdapter);
        }
    }

}
