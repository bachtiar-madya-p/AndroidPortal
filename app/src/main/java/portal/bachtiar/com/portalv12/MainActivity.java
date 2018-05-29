package portal.bachtiar.com.portalv12;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import portal.bachtiar.com.portalv12.activity.Activity_Dashboard;
import portal.bachtiar.com.portalv12.activity.Activity_FeedCmt;
import portal.bachtiar.com.portalv12.activity.Activity_Feedback;
import portal.bachtiar.com.portalv12.activity.Activity_UserProfile;
import portal.bachtiar.com.portalv12.activity.F1_List_KelAbsensi;
import portal.bachtiar.com.portalv12.activity.F2_List_KelCuti;
import portal.bachtiar.com.portalv12.activity.F3_List_KelOvertime;
import portal.bachtiar.com.portalv12.activity.F4_List_Beasiswa;
import portal.bachtiar.com.portalv12.activity.F5_ListSkUser;
import portal.bachtiar.com.portalv12.activity.F6_List_BPjs;
import portal.bachtiar.com.portalv12.activity.F7_List_IdCard;
import portal.bachtiar.com.portalv12.adapter.Adapter_NewsFeed;
import portal.bachtiar.com.portalv12.dataaccess.Server;
import portal.bachtiar.com.portalv12.model.m_NewsFeed;
import portal.bachtiar.com.portalv12.utils.AppController;
import portal.bachtiar.com.portalv12.utils.SessionManager;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private Boolean exit = false;
    Toolbar toolbar;
    ListView list;
    SwipeRefreshLayout swipe;
    List<m_NewsFeed> feedList = new ArrayList<m_NewsFeed>();
    Adapter_NewsFeed adapter;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    public static final String uri = Server.BASE_URL + "newsfeed/list";

    public static final String TAG = MainActivity.class.getSimpleName();
    public ProgressDialog progressDialog;
    SessionManager session;
    String nik, name, imgUser;
    TextView navNik, navName;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoaderFeed();
    NetworkImageView navImgUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());
        if(session.isLoggedIn()){
            session.checkLogin();
        }

        HashMap<String, String> user = session.getUserDetails();
        nik = user.get(SessionManager.KEY_NIK);
        name = user.get(SessionManager.KEY_NAME);
        imgUser = user.get(SessionManager.KEY_IMGUSER);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Activity_Dashboard.class);
                startActivity(intent);
            }
        });
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navName);
        navName.setText(name);
        navNik = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navNik);
        navNik.setText(nik);
        navImgUser = (NetworkImageView) navigationView.getHeaderView(0).findViewById(R.id.navImg);
        navImgUser.setImageUrl(imgUser, imageLoader);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.isChecked()) item.setChecked(false);
                else item.setChecked(true);
                drawer.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_userProfile:
                        Intent a = new Intent(MainActivity.this, Activity_UserProfile.class);
                        a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(a);
                        return true;
                    case R.id.nav_Logout:
                        session.logoutUser();
                        return true;
                    case R.id.nav_Beasiswa:
                        Intent beasiswa = new Intent(MainActivity.this, F4_List_Beasiswa.class);
                        beasiswa.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(beasiswa);
                        return true;
                    case R.id.nav_Bpjs:
                        Intent bpjs = new Intent(MainActivity.this, F6_List_BPjs.class);
                        bpjs.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(bpjs);
                        return true;
                    case R.id.nav_IdCard:
                        Intent idcard = new Intent(MainActivity.this, F7_List_IdCard.class);
                        idcard.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(idcard);
                        return true;
                    case R.id.nav_skUser:
                        Intent sk = new Intent(MainActivity.this, F5_ListSkUser.class);
                        sk.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(sk);
                        return true;
                    case R.id.nav_KelAbsensi:
                        Intent absensi = new Intent(MainActivity.this, F1_List_KelAbsensi.class);
                        absensi.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(absensi);
                        return true;
                    case R.id.nav_KelCuti:
                        Intent cuti = new Intent(MainActivity.this, F2_List_KelCuti.class);
                        cuti.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(cuti);
                        return true;
                    case R.id.nav_KeluhanOvertime:
                        Intent overtime = new Intent(MainActivity.this, F3_List_KelOvertime.class);
                        overtime.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(overtime);
                        return true;
                    case R.id.nav_feedback:
                        Intent feedback = new Intent(MainActivity.this, Activity_Feedback.class);
                        feedback.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(feedback);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Kesalahan Terjadi ", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        list = (ListView) findViewById(R.id.newsFeedList);
        adapter = new Adapter_NewsFeed(MainActivity.this, feedList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent result = new Intent(MainActivity.this, Activity_FeedCmt.class);

                String roomId = feedList.get(position).getRoomId().toString();
                result.putExtra("roomId", roomId);

                result.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(result);
            }
        });

        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           onRefresh();
                       }
                   }
        );
    }

    @Override
    public void onRefresh() {
        feedList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }

    private void callVolley() {
        if (isWorkingInternetPersent()) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading data ...... ");
            progressDialog.show();
            feedList.clear();
            adapter.notifyDataSetChanged();
            swipe.setRefreshing(true);

            JsonArrayRequest jArr = new JsonArrayRequest(uri, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d(TAG, response.toString());

                    // Parsing json
                    for (int i = 0; i < response.length(); i++) {
                        try {

                            JSONObject obj = response.getJSONObject(i);
                            m_NewsFeed sn = new m_NewsFeed();
                            sn.setRoomId(obj.getInt("roomId"));
                            sn.setPosdate(obj.getString("posdate"));
                            sn.setImgContent(obj.getString("imgContent"));
                            sn.setContent(obj.getString("content"));
                            sn.setCount(obj.getString("count"));
                            JSONObject objNik = obj.getJSONObject("nik");
                            sn.setNik(objNik.getInt("nik"));
                            sn.setName(objNik.getString("name"));
                            sn.setPhone(objNik.getString("phone"));
                            sn.setEmail(objNik.getString("email"));
                            sn.setPlant(objNik.getString("plant"));
                            sn.setDept(objNik.getString("dept"));
                            sn.setPwd(objNik.getString("pwd"));
                            sn.setRegDate(objNik.getString("regDate"));
                            sn.setTag(objNik.getString("tag"));
                            sn.setGcmToken(objNik.getString("gcmToken"));
                            sn.setImgUser(objNik.getString("imgUser"));
                            feedList.add(sn);
                            progressDialog.hide();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                    swipe.setRefreshing(false);
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    swipe.setRefreshing(false);
                }
            });
            progressDialog.dismiss();
            AppController.getInstance().addToRequestQueue(jArr);
        } else {
            showAlertDialog(MainActivity.this, "Internet Connection",
                    "You don't have internet connection", false);
        }
    }

    public boolean isWorkingInternetPersent() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getBaseContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                finish();
                System.exit(0);
            }
        });
        alertDialog.show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            this.drawer.closeDrawer(GravityCompat.START);
        } else if (exit) {
            finish();
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
