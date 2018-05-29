package portal.bachtiar.com.portalv12.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import portal.bachtiar.com.portalv12.MainActivity;
import portal.bachtiar.com.portalv12.R;
import portal.bachtiar.com.portalv12.adapter.Adapter_Bpjs;
import portal.bachtiar.com.portalv12.dataaccess.Server;
import portal.bachtiar.com.portalv12.model.m_Bpjs;
import portal.bachtiar.com.portalv12.utils.AppController;
import portal.bachtiar.com.portalv12.utils.SessionManager;

/**
 * Created by bachtiar on 25/11/17.
 */

public class F6_List_BPjs extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = F6_List_BPjs.class.getSimpleName();
    String uri1 = Server.BASE_URL + "bpjs/find/nik/";
    String URI, nik;
    public ProgressDialog progressDialog;
    SessionManager session;
    public List<m_Bpjs> bpjsDataList = new ArrayList<m_Bpjs>();
    public ListView listView;
    SwipeRefreshLayout swipe;
    public Adapter_Bpjs adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f6_riwayat_bpjs);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        nik = user.get(SessionManager.KEY_NIK);
        URI = uri1 + nik;

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        listView = (ListView) findViewById(R.id.bpjsList);
        adapter = new Adapter_Bpjs(F6_List_BPjs.this, bpjsDataList);
        listView.setAdapter(adapter);

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
        bpjsDataList.clear();
        adapter.notifyDataSetChanged();
        callVolley();
    }
    private void callVolley() {
        if (isWorkingInternetPersent()) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading data ...... ");
            progressDialog.show();
            bpjsDataList.clear();
            adapter.notifyDataSetChanged();
            swipe.setRefreshing(true);

            JsonArrayRequest jArr = new JsonArrayRequest(URI, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d(TAG, response.toString());

                    // Parsing json
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject obj = response.getJSONObject(i);
                            m_Bpjs sn = new m_Bpjs();
                            sn.setId(obj.getInt("id"));
                            sn.setNokk(obj.getString("nokk"));
                            sn.setNoktp(obj.getString("noktp"));
                            sn.setDate(obj.getString("date"));
                            sn.setStatus(obj.getString("status"));
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
                            sn.setImgUser(objNik.getString("imgUser"));

                            bpjsDataList.add(sn);
                            progressDialog.hide();
                            swipe.setRefreshing(false);
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
            showAlertDialog(F6_List_BPjs.this, "Internet Connection",
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
        Intent a = new Intent(F6_List_BPjs.this, MainActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
    }
}
