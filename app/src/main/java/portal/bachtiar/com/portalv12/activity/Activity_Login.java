package portal.bachtiar.com.portalv12.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import portal.bachtiar.com.portalv12.MainActivity;
import portal.bachtiar.com.portalv12.R;
import portal.bachtiar.com.portalv12.dataaccess.Server;
import portal.bachtiar.com.portalv12.utils.AppController;
import portal.bachtiar.com.portalv12.utils.SessionManager;
import portal.bachtiar.com.portalv12.utils.Utility;

/**
 * Created by Bachtiar M Permadi on 09/08/2017.
 */

public class Activity_Login extends Activity {

    private Boolean exit = false;
    SessionManager session;
    ProgressDialog prgDialog;
    Button btnLogin;
    TextView errMsg, linkRegis;
    EditText inpNik;
    ShowHidePasswordEditText inpPwd;
    String URL = Server.BASE_URL + "login/dologin";
    String uri = Server.BASE_URL + "user/find/nik/";
    String finalUri;
    String getNik;
    String nik;
    String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            session.checkLogin();
            navigatetoHomeActivity();
        }

        errMsg = (TextView) findViewById(R.id.errMsg);
        inpNik = (EditText) findViewById(R.id.logNik);
        inpPwd = (ShowHidePasswordEditText) findViewById(R.id.logPwd);
        linkRegis = (TextView) findViewById(R.id.linkregis);
        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Please wait...");
        prgDialog.setCancelable(false);
        btnLogin = (Button) findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nik = inpNik.getText().toString();
                pwd = inpPwd.getText().toString();
                JSONObject body = new JSONObject();
                if (Utility.isNotNull(nik) && Utility.isNotNull(pwd)) {
                    try {
                        body.put("nik", nik);
                        body.put("pwd", pwd);
                        checkLogin(body);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "NIK atau password masih kosong", Toast.LENGTH_LONG).show();
                }
            }

            public void checkLogin(JSONObject body) throws JSONException, UnsupportedEncodingException {
                finalUri = uri + nik;
                getNik = nik;
                errMsg.setVisibility(TextView.VISIBLE);
                prgDialog.show();
                StringEntity entity = new StringEntity(body.toString());
                AsyncHttpClient client = new AsyncHttpClient();
                client.post(getApplicationContext(), URL, entity, "application/json", new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        prgDialog.hide();
                        try {
                            if (response.getBoolean("status")) {
                                getUserData();
                                session.createLoginSession(nik);
                                navigatetoHomeActivity();
                            } else {
                                Toast.makeText(getApplicationContext(), response.getString("error_msg"), Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Throwable error,
                                          JSONObject content) {
                        prgDialog.hide();
                        if (statusCode == 404) {
                            Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                        } else if (statusCode == 500) {
                            Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }

        });

        linkRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Login.this, Activity_Register.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
        } else {
            exit = true;
            moveTaskToBack(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }

    public void getUserData() {
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, finalUri, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Integer Tnik = response.getInt("nik");
                            String Tname = response.getString("name");
                            String Tphone = response.getString("phone");
                            String Temail = response.getString("email");
                            String Tplant = response.getString("plant");
                            String Tdept = response.getString("dept");
                            String Tpwd = response.getString("pwd");
                            String TimgUser = response.getString("imgUser");
                            String TregDate = response.getString("regDate");
                            String Ttag = response.getString("tag");

                            session.createLoginSession(Tnik.toString(), Tname, Tphone, Temail, Tplant, Tdept, Tpwd, TimgUser, Ttag, TregDate);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                });
        AppController.getInstance().addToRequestQueue(obreq);
    }

    public void navigatetoHomeActivity() {
        Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(homeIntent);
    }
}