package portal.bachtiar.com.portalv12.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.HashMap;

import portal.bachtiar.com.portalv12.R;
import portal.bachtiar.com.portalv12.dataaccess.Server;
import portal.bachtiar.com.portalv12.utils.AppController;
import portal.bachtiar.com.portalv12.utils.SessionManager;

/**
 * Created by Bachtiar M Permadi on 30/10/2017.
 */

public class Activity_UserProfile extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    private static final String DIRECTORY = "/portal";

    String URL = Server.BASE_URL + "user/image";
    ProgressDialog progressDialog;
    String nik, name, phone, email, plant, dept, pwd, imgUser, tag, regDate;
    SessionManager session;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoaderFeed();
    NetworkImageView PimgUser;
    EditText PuserName, PuserPhone, PuserEmail, PuserPlant, PuserDept;
    TextView PuserNik, PuserRegDate, PuserPwd, PuserTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

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

        PimgUser = (NetworkImageView) findViewById(R.id.PimgUser);
        PuserName = (EditText) findViewById(R.id.PuserName);
        PuserNik = (TextView) findViewById(R.id.PuserNik);
        PuserPhone = (EditText) findViewById(R.id.PuserPhone);
        PuserEmail = (EditText) findViewById(R.id.PuserEmail);
        PuserPlant = (EditText) findViewById(R.id.PuserPlant);
        PuserDept = (EditText) findViewById(R.id.PuserDept);
        PuserPwd = (TextView) findViewById(R.id.PuserPwd);
        PuserTag = (TextView) findViewById(R.id.PuserTag);
        PuserRegDate = (TextView) findViewById(R.id.PuserRegDate);

        PimgUser.setImageUrl(imgUser, imageLoader);
        PuserNik.setText(nik);
        PuserName.setText(name);
        PuserPhone.setText(phone);
        PuserEmail.setText(email);
        PuserPlant.setText(plant);
        PuserDept.setText(dept);
        PuserPwd.setText(pwd);
        PuserPwd.setVisibility(View.INVISIBLE);
        PuserRegDate.setText(regDate);
        PuserTag.setText(tag);
        PuserTag.setVisibility(View.INVISIBLE);

        PimgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}