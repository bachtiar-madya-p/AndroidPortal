package portal.bachtiar.com.portalv12.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Pattern;

import portal.bachtiar.com.portalv12.R;
import portal.bachtiar.com.portalv12.dataaccess.Server;
import portal.bachtiar.com.portalv12.utils.AppController;
import portal.bachtiar.com.portalv12.utils.SessionManager;

import static portal.bachtiar.com.portalv12.dataaccess.Server.BASE_URL;

/**
 * Created by bachtiar on 25/11/17.
 */

public class F7_Activity_IdCard extends AppCompatActivity {
    public static final int PERMISSIONS_REQUEST_CODE = 0;
    public static final int FILE_PICKER_REQUEST_CODE = 1;
    private static final String DIRECTORY = "/portal";
    Integer hasil;
    String path, newPath, filename, newFileName;

    String nik, name, phone, email, plant, dept, pwd, imgUser, tag, regDate;
    SessionManager session;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoaderFeed();
    String uri = Server.BASE_URL + "idcard/create";
    String uploadUri = BASE_URL + "idcard/file";
    String lastId = BASE_URL + "idcard/lastid";
    String folderUri = Server.FOLDER_URL + "idcard/";
    ProgressDialog pDialog;
    NetworkImageView F7UserImg;
    TextView F7userName, F7UserNik, filestringf7;
    EditText F7Content;
    Button F7Send, loadf7doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f7_idcard);

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

        F7UserImg = (NetworkImageView) findViewById(R.id.F7UserImg);
        filestringf7 = (TextView) findViewById(R.id.filestringf7);
        F7userName = (TextView) findViewById(R.id.F7userName);
        F7UserNik = (TextView) findViewById(R.id.F7UserNik);
        loadf7doc = (Button) findViewById(R.id.loadf7doc);
        F7Content = (EditText) findViewById(R.id.F7Content);
        F7Send = (Button) findViewById(R.id.F7Send);

        F7UserImg.setImageUrl(imgUser, imageLoader);
        F7userName.setText(name);
        F7UserNik.setText(nik);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Mengirim ...");

        loadf7doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkPermissionsAndOpenFilePicker();
                getUserData();
            }
        });

        F7Send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                uploadMultipart();

            }
        });
    }
    public void getUserData() {
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, lastId, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            hasil= response.getInt("id");
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

    private void checkPermissionsAndOpenFilePicker() {
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            openFilePicker();
        }
    }

    private void showError() {
        Toast.makeText(this, "Allow external storage reading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFilePicker();
                } else {
                    showError();
                }
            }
        }
    }

    private void openFilePicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                .withRootPath("/storage/")
                .withHiddenFiles(false)
                .withFilter(Pattern.compile(".*\\.zip$"))
                .withTitle("Pilih file")
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);

            if (path != null) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("ddMMMyy");
                String formattedDate = df.format(c.getTime());
                filename = path.substring(path.lastIndexOf("/") + 1);
                File file = new File(path);
                file = new File(file.getAbsolutePath());
                File newName = null;
                try {
                    InputStream in = new FileInputStream(file);
                    try {
                        File newDirectory = new File(Environment.getExternalStorageDirectory()+ DIRECTORY);
                        if (!newDirectory.exists()) {
                            newDirectory.mkdirs();
                        }
                        newName = new File(newDirectory + "/" + formattedDate+"-"+ hasil+"-" + filename);
                        OutputStream out = new FileOutputStream(new File(newName.getAbsolutePath().toString()), true);
                        try {
                            byte[] buf = new byte[1024];
                            int len;
                            while ((len = in.read(buf)) > 0) {
                                out.write(buf, 0, len);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                newPath = newName.getPath();
                newFileName = newPath.substring(newPath.lastIndexOf("/") + 1);
                filestringf7.setText(filename);
                Toast.makeText(this, "file: " + filename, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void uploadMultipart() {
        if (path == null) {
            Toast.makeText(this, "File lampiran masih kosong", Toast.LENGTH_LONG).show();
        } else {
            try {
                String uploadId = UUID.randomUUID().toString();
                new MultipartUploadRequest(this, uploadId, uploadUri)
                        .addFileToUpload(newPath, "file")
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload();
                sendRequest();
            } catch (Exception exc) {
                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendRequest() {
        try {
            pDialog.dismiss();
            RequestQueue requestQueue = Volley.newRequestQueue(F7_Activity_IdCard.this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("content", F7Content.getText().toString());
            jsonBody.put("doc", folderUri + newFileName);
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
                    pDialog.hide();
                    Log.i("VOLLEY", response);
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(F7_Activity_IdCard.this);
                    builder.setMessage("Data terkirim")
                            .setIcon(R.drawable.ic_check)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent a = new Intent(F7_Activity_IdCard.this, F7_List_IdCard .class);
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
                    builder = new AlertDialog.Builder(F7_Activity_IdCard.this);
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
