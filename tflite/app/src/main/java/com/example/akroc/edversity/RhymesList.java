package com.example.akroc.edversity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.akroc.edversity.adapter.CustomAdapter;
import com.example.akroc.edversity.models.VideoDetails;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RhymesList extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    ListView ytubeList;
    String url="https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=UUsCe7SNQckiRJ6y563SIupg&key=AIzaSyCM-ECdhOcjH_bR9cVoQJ2EFc1wLoC_yCE";
    ArrayList<VideoDetails> videoDetailsArrayList;
    CustomAdapter customAdapter;

    public static final String API_KEY = "AIzaSyBatvuGyYhfzC3jWnWDPQbc-OHzB3VHBTg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhymes_list);
        ytubeList= (ListView) findViewById(R.id.youtube_list);
        videoDetailsArrayList= new ArrayList<>();
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.rhtoolbar);
        setSupportActionBar(toolbar);
        customAdapter=new CustomAdapter(RhymesList.this,videoDetailsArrayList);

        displayVideos();
    }

    private void displayVideos()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest request= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject= new JSONObject(response);
                    JSONArray jsonArray= jsonObject.getJSONArray("items");
                    for(int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject jsonObject1= jsonArray.getJSONObject(i);
                        JSONObject jsonsnippet= jsonObject1.getJSONObject("snippet");
                        JSONObject jsonVideoId= jsonsnippet.getJSONObject("resourceId");
                        JSONObject jsonObjectDefault= jsonsnippet.getJSONObject("thumbnails").getJSONObject("medium");
                        String vid_Id=jsonVideoId.getString("videoId");
                        VideoDetails vd = new VideoDetails();
                        vd.setVideoId(vid_Id);
                        vd.setTitle(jsonsnippet.getString("title"));
                        vd.setDescription(jsonsnippet.getString("description"));
                        vd.setUrl(jsonObjectDefault.getString("url"));
                        videoDetailsArrayList.add(vd);
                    }

                    ytubeList.setAdapter(customAdapter);
                    customAdapter.notifyDataSetChanged();

                }catch (JSONException e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.rhymes_menu,menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<VideoDetails> result= new ArrayList<>();
                for(VideoDetails x: videoDetailsArrayList)
                {
                    if(x.getTitle().toLowerCase().contains(s.toLowerCase())){
                       result.add(x);
                    }
                }
                int x=0;
                for(VideoDetails res: result){
                    x++;
                }
                ((CustomAdapter) ytubeList.getAdapter()).update(result);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.settings)
        {
            startActivity(new Intent(RhymesList.this,Settings.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
