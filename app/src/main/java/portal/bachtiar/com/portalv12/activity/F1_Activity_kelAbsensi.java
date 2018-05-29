package portal.bachtiar.com.portalv12.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import portal.bachtiar.com.portalv12.R;
import portal.bachtiar.com.portalv12.dataaccess.Server;
import portal.bachtiar.com.portalv12.utils.AppController;
import portal.bachtiar.com.portalv12.utils.SessionManager;

/**
 * Created by Bachtiar M Permadi on 09/11/2017.
 */

public class F1_Activity_kelAbsensi extends AppCompatActivity {

    String nik, name, phone, email, plant, dept, pwd, imgUser, tag, regDate, sContent;
    SessionManager session;
    public ProgressDialog pDialog;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoaderFeed();
    String uri = Server.BASE_URL + "absensi/create";
    ProgressDialog prgDialog;
    NetworkImageView F1userImg;
    TextView F1userName, F1UserNik;
    EditText F1content;
    Button F1Send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f1_kel_absensi);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
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

        F1userImg = (NetworkImageView) findViewById(R.id.F1userImg);
        F1userName = (TextView) findViewById(R.id.F1userName);
        F1UserNik = (TextView) findViewById(R.id.F1UserNik);

        F1userImg.setImageUrl(imgUser, imageLoader);
        F1userName.setText(name);
        F1UserNik.setText(nik);

        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Please wait...");
        prgDialog.setCancelable(false);
        F1content = (EditText) findViewById(R.id.F1content);
        F1Send = (Button) findViewById(R.id.F1Send);

        sContent = F1content.getText().toString();

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Mengirim ...");

        F1Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(F1content.getText().toString()))
                {

                }
                else
                {
                    sendData();
                }

            }
        });
    }

    private void sendData() {
        try {
            pDialog.show();
            RequestQueue requestQueue = Volley.newRequestQueue(F1_Activity_kelAbsensi.this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("content", F1content.getText().toString());
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
            StringRequest stringRequest = new StringRequest(Request.Method.POST, uri, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pDialog.dismiss();
                    Log.i("VOLLEY", response);
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(F1_Activity_kelAbsensi.this);
                    builder.setMessage("Data terkirim")
                            .setIcon(R.drawable.ic_check)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent a = new Intent(F1_Activity_kelAbsensi.this, F1_List_KelAbsensi.class);
                                    a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(a);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    pDialog.hide();
                    Log.e("VOLLEY", error.toString());
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(F1_Activity_kelAbsensi.this);
                    builder.setMessage("Gagal")
                            .setIcon(R.drawable.ic_cancel)
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
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
