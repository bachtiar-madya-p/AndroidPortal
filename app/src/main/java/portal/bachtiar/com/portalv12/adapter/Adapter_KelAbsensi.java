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
import portal.bachtiar.com.portalv12.model.m_KelAbsensi;

/**
 * Created by bachtiar on 16/11/17.
 */

public class Adapter_KelAbsensi extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<m_KelAbsensi> absensiData;

    public Adapter_KelAbsensi(Activity activity, List<m_KelAbsensi> absensiData) {
        this.activity = activity;
        this.absensiData = absensiData;
    }

    @Override
    public int getCount() {
        return absensiData.size();
    }

    @Override
    public Object getItem(int location) {
        return absensiData.get(location);
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
            convertView = inflater.inflate(R.layout.f1_list_kel_absensi, null);
        }

        TextView content = (TextView) convertView.findViewById(R.id.listf1Content);
        TextView date = (TextView) convertView.findViewById(R.id.listf1Date);
        TextView name = (TextView) convertView.findViewById(R.id.listf1Name);
        TextView status = (TextView) convertView.findViewById(R.id.listf1Status);

        m_KelAbsensi data = absensiData.get(position);

        content.setText(data.getContent());
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
