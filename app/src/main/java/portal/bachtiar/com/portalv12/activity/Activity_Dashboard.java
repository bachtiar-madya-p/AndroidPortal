package portal.bachtiar.com.portalv12.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import portal.bachtiar.com.portalv12.R;
import portal.bachtiar.com.portalv12.custom.CustomAndroidGridViewAdapter;



/**
 * Created by Bachtiar M Permadi on 30/10/2017.
 */

public class Activity_Dashboard extends AppCompatActivity  {
    GridView androidGridView;

    public static String[] gridViewStrings = {
            "Daftar Beasiswa",
            "Daftar BPJS",
            "Ubah Data Id Card",
            "Surat Keterangan",
            "Keluhan Absensi",
            "Keluhan Cuti",
            "Keluhan Overtime"

    };
    public static int[] gridViewImages = {
            R.drawable.ico_beasiswa,
            R.drawable.ico_bpjs,
            R.drawable.ico_idcard,
            R.drawable.ico_sk,
            R.drawable.ico_absensi,
            R.drawable.ico_cuti,
            R.drawable.ico_overtime
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        CustomAndroidGridViewAdapter AdapterViewAndroid = new CustomAndroidGridViewAdapter(Activity_Dashboard.this, gridViewStrings, gridViewImages);
        androidGridView=(GridView)findViewById(R.id.grid);
        androidGridView.setAdapter(AdapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {


                if( gridViewImages[+i] == R.drawable.ico_beasiswa)
                {
                    Intent intent = new Intent(getApplicationContext(), F4_Activity_Beasiswa.class);
                    startActivity(intent);
                }
                else if(gridViewImages[+i] == R.drawable.ico_bpjs)
                {
                    Intent intent = new Intent(getApplicationContext(), F6_Activity_Bpjs.class);
                    startActivity(intent);
                }

                else if(gridViewImages[+i] == R.drawable.ico_idcard)
                {
                    Intent intent = new Intent(getApplicationContext(), F7_Activity_IdCard.class);
                    startActivity(intent);
                }
                else if(gridViewImages[+i] == R.drawable.ico_sk)
                {
                    Intent intent = new Intent(getApplicationContext(), F5_Activity_SuratKeputusan.class);
                    startActivity(intent);
                }
                else if(gridViewImages[+i] == R.drawable.ico_absensi)
                {
                    Intent intent = new Intent(getApplicationContext(), F1_Activity_kelAbsensi.class);
                    startActivity(intent);
                }
                else if(gridViewImages[+i] == R.drawable.ico_cuti)
                {
                    Intent intent = new Intent(getApplicationContext(), F2_Activity_kelCuti.class);
                    startActivity(intent);
                }
                else if(gridViewImages[+i] == R.drawable.ico_overtime)
                {
                    Intent intent = new Intent(getApplicationContext(), F3_Activity_kelOvertime.class);
                    startActivity(intent);
                }
            }
        });
    }
}
