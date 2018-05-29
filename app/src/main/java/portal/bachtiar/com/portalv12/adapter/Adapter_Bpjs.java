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
import portal.bachtiar.com.portalv12.model.m_Bpjs;

/**
 * Created by bachtiar on 25/11/17.
 */

public class Adapter_Bpjs extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<m_Bpjs> bpjsData;

    public Adapter_Bpjs(Activity activity, List<m_Bpjs> bpjsData) {
        this.activity = activity;
        this.bpjsData = bpjsData;
    }

    @Override
    public int getCount() {
        return bpjsData.size();
    }

    @Override
    public Object getItem(int location) {
        return bpjsData.get(location);
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
            convertView = inflater.inflate(R.layout.f6_list_bpjs, null);
        }

        TextView noKK = (TextView) convertView.findViewById(R.id.listf6noKk);
        TextView noKtp = (TextView) convertView.findViewById(R.id.listf6noKtp);
        TextView date = (TextView) convertView.findViewById(R.id.listf6Date);
        TextView name = (TextView) convertView.findViewById(R.id.listf6Name);
        TextView status = (TextView) convertView.findViewById(R.id.listf6Status);

        m_Bpjs data = bpjsData.get(position);

        noKK.setText(data.getNokk());
        noKtp.setText(data.getNoktp());
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
