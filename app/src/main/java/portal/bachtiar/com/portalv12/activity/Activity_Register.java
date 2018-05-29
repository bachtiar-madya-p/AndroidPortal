package portal.bachtiar.com.portalv12.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import portal.bachtiar.com.portalv12.R;
import portal.bachtiar.com.portalv12.dataaccess.Server;
import portal.bachtiar.com.portalv12.utils.Utility;

/**
 * Created by Bachtiar M Permadi on 19/09/2017.
 */

public class Activity_Register extends AppCompatActivity {

    public static final String TAG = Activity_Register.class.getSimpleName();
    private Button btnRegister;
    private EditText regNik, regName, regEmail, regPhone, regPwd;
    private Spinner regPlant, regDept;
    private TextView linkLogin, regMsg;
    ProgressDialog prgDialog;
    String uri = Server.BASE_URL + "register/doregister";

    String nik, nama, plant, dept, phone, email, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        regNik = (EditText) findViewById(R.id.regNik);
        regName = (EditText) findViewById(R.id.regNama);
        regPlant = (Spinner) findViewById(R.id.regPlant);
        regDept = (Spinner) findViewById(R.id.regDept);
        regPhone = (EditText) findViewById(R.id.regPhone);
        regEmail = (EditText) findViewById(R.id.regEmail);
        regPwd = (EditText) findViewById(R.id.regPwd);
        regMsg = (TextView) findViewById(R.id.regMsg);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        linkLogin = (TextView) findViewById(R.id.linkLogin);
        linkLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent homeIntent = new Intent(getApplicationContext(), Activity_Login.class);
                startActivity(homeIntent);
            }
        });
        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Please wait...");
        prgDialog.setCancelable(false);
        regPlant = (Spinner) findViewById(R.id.regPlant);
        regDept = (Spinner) findViewById(R.id.regDept);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nik = regNik.getText().toString();
                nama = regName.getText().toString();
                plant = regPlant.getSelectedItem().toString();
                dept = regDept.getSelectedItem().toString();
                phone = regPhone.getText().toString();
                email = regEmail.getText().toString();
                pwd = regPwd.getText().toString();

                JSONObject body = new JSONObject();
                if (regPlant.getSelectedItemId() == 0) {
                    Toast.makeText(getApplicationContext(), "anda belum memilih plant anda",
                            Toast.LENGTH_LONG).show();
                } else if (regDept.getSelectedItemId() == 0) {
                    Toast.makeText(getApplicationContext(), "anda belum memilih departemen anda",
                            Toast.LENGTH_LONG).show();
                } else if (Utility.isNotNull(nik) && Utility.isNotNull(nama) && Utility.isNotNull(phone)
                        && Utility.isNotNull(email)
                        && Utility.isNotNull(pwd)) {
                    if (Utility.validate(email)) {
                        try {
                            body.put("nik", nik);
                            body.put("name", nama);
                            body.put("plant", plant);
                            body.put("dept", dept);
                            body.put("phone", phone);
                            body.put("email", email);
                            body.put("pwd", pwd);
                            sendData(body);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "email tidak valid",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "masih ada data kosong",
                            Toast.LENGTH_LONG).show();
                }
            }

            public void sendData(JSONObject body) throws JSONException, UnsupportedEncodingException {
                regMsg.setVisibility(View.VISIBLE);
                prgDialog.show();
                StringEntity entity = new StringEntity(body.toString());
                AsyncHttpClient client = new AsyncHttpClient();
                client.post(getApplicationContext(), uri, entity, "application/json", new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        prgDialog.hide();
                        try {
                            if (response.getBoolean("status")) {
                                setDefaultValues();
                                btnRegister.setEnabled(false);
                                prgDialog.hide();
                                AlertDialog.Builder builder;
                                builder = new AlertDialog.Builder(Activity_Register.this);
                                builder.setMessage("Registrasi berhasi. kami akan melakukan validasi pada data anda")
                                        .setIcon(R.drawable.ic_check)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                Intent a = new Intent(Activity_Register.this, Activity_Login.class);
                                                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(a);
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                            } else {
                                String msg = response.getString("error_msg");
                                regMsg.setText(msg);
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
    }

    private void setDefaultValues() {
        regNik.setText("");
        regName.setText("");
        regEmail.setText("");
        regPhone.setText("");
        regPwd.setText("");
    }

}