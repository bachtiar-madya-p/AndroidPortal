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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import portal.bachtiar.com.portalv12.MainActivity;
import portal.bachtiar.com.portalv12.R;
import portal.bachtiar.com.portalv12.adapter.Adapter_FeedCmt;
import portal.bachtiar.com.portalv12.dataaccess.Server;
import portal.bachtiar.com.portalv12.model.m_FeedComment;
import portal.bachtiar.com.portalv12.utils.AppController;
import portal.bachtiar.com.portalv12.utils.SessionManager;

/**
 * Created by bachtiar on 26/11/17.
 */

public class Activity_FeedCmt extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = Activity_FeedCmt.class.getSimpleName();
    private ImageLoader imageLoader = AppController.getInstance().getImageLoaderFeed();
    String uri1 = Server.BASE_URL + "feedcomment/listbyroom/";
    String uriPostCmt = Server.BASE_URL + "feedcomment/create";
    String URI, nik, name, phone, email, plant, dept, pwd, imgUser, tag, regDate;
    public ProgressDialog progressDialog;
    SessionManager session;
    public List<m_FeedComment> cmtDataList = new ArrayList<m_FeedComment>();
    public ListView listView;
    SwipeRefreshLayout swipe;
    public Adapter_FeedCmt adapter;
    String roomId;
    EditText inpCmt;
    Button cmtSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedcomment);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        nik = user.get(SessionManager.KEY_NIK);
        nik = user.get(SessionManager.KEY_NIK);
        name = user.get(SessionManager.KEY_NAME);
        phone = user.get(SessionManager.KEY_PHONE);
        email = user.get(SessionManager.KEY_EMAIL);
        plant = user.get(SessionManager.KEY_PLANT);
        dept = user.get(SessionManager.KEY_DEPT);
        pwd = user.get(SessionManager.KEY_PWD);
        imgUser = user.get(SessionManager.KEY_IMGUSER);
        regDate = user.get(SessionManager.KEY_REGDATE);
        tag = user.get(SessionManager.KEY_TAG);

        roomId = getIntent().getStringExtra("roomId");
        URI = uri1 + roomId;

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        listView = (ListView) findViewById(R.id.feedCmtList);
        adapter = new Adapter_FeedCmt(Activity_FeedCmt.this, cmtDataList);
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
        inpCmt = (EditText) findViewById(R.id.inpCmt);
        cmtSend = (Button) findViewById(R.id.cmtSend);
        cmtSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(inpCmt.getText().toString())) {

                } else {
                    sendData();
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        cmtDataList.clear();
        adapter.notifyDataSetChanged();
        getData();
    }

    private void getData() {
        if (isWorkingInternetPersent()) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Loading data ...... ");
            progressDialog.show();
            cmtDataList.clear();
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
                            m_FeedComment sn = new m_FeedComment();
                            sn.setId(obj.getInt("id"));
                            sn.setCmtcontent(obj.getString("cmtcontent"));
                            sn.setCmtposdate(obj.getString("cmtposdate"));
                            sn.setRoomId(obj.getInt("roomId"));
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

                            cmtDataList.add(sn);
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
        } else

        {
            showAlertDialog(Activity_FeedCmt.this, "Internet Connection",
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
        Intent a = new Intent(Activity_FeedCmt.this, MainActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);

    }

    private void sendData() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(Activity_FeedCmt.this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("cmtcontent", inpCmt.getText().toString());
            jsonBody.put("roomId", roomId);
            JSONObject obj = new JSONObject();
            obj.put("nik", nik);
            obj.put("name", name);
            obj.put("phone", phone);
            obj.put("email", email);
            obj.put("plant", plant);
            obj.put("dept", dept);
            obj.put("pwd", pwd);
            obj.put("imgUser", imgUser);
            obj.put("regDate", regDate);
            obj.put("tag", tag);

            jsonBody.put("nik", obj);
            final String requestBody = jsonBody.toString();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, uriPostCmt, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                    onRefresh();
                    inpCmt.setText("");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    android.support.v7.app.AlertDialog.Builder builder;
                    builder = new android.support.v7.app.AlertDialog.Builder(Activity_FeedCmt.this);
                    builder.setMessage("Gagal")
                            .setIcon(R.drawable.ic_cancel)
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    android.support.v7.app.AlertDialog alert = builder.create();
                    alert.show();
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
