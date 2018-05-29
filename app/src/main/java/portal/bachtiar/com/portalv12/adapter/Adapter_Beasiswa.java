package portal.bachtiar.com.portalv12.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import portal.bachtiar.com.portalv12.R;
import portal.bachtiar.com.portalv12.model.m_Beasiswa;

/**
 * Created by bachtiar on 25/11/17.
 */

public class Adapter_Beasiswa extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<m_Beasiswa> beasiswaData;

    public Adapter_Beasiswa(Activity activity, List<m_Beasiswa> beasiswaData) {
        this.activity = activity;
        this.beasiswaData = beasiswaData;
    }

    @Override
    public int getCount() {
        return beasiswaData.size();
    }

    @Override
    public Object getItem(int location) {
        return beasiswaData.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.f4_list_beasiswa, null);
        }

        TextView namaAnak = (TextView) convertView.findViewById(R.id.listf4NamaAnak);
        TextView date = (TextView) convertView.findViewById(R.id.listf4Date);
        TextView name = (TextView) convertView.findViewById(R.id.listf4Name);
        TextView status = (TextView) convertView.findViewById(R.id.listf4Status);

        m_Beasiswa data = beasiswaData.get(position);

        namaAnak.setText(data.getNmAnak());
        date.setText(data.getDate());
        name.setText(data.getName());
        status.setText(data.getStatus());
        if(status.getText().toString().trim().equals("waiting"))
        {
            status.setBackgroundResource(R.color.red);
        }
        else if(status.getText().toString().trim().equals("accept"))
        {
            status.setBackgroundResource(R.color.gren);
        }

        return convertView;

    }
}
