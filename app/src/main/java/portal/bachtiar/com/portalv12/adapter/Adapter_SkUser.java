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
import portal.bachtiar.com.portalv12.model.m_SkUser;

/**
 * Created by bachtiar on 25/11/17.
 */

public class Adapter_SkUser extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<m_SkUser> skUserData;

    public Adapter_SkUser(Activity activity, List<m_SkUser> skUserData) {
        this.activity = activity;
        this.skUserData = skUserData;
    }

    @Override
    public int getCount() {
        return skUserData.size();
    }

    @Override
    public Object getItem(int location) {
        return skUserData.get(location);
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
            convertView = inflater.inflate(R.layout.f5_list_skuser, null);
        }

        TextView noSk = (TextView) convertView.findViewById(R.id.listf5nosk);
        TextView date = (TextView) convertView.findViewById(R.id.listf5Date);
        TextView name = (TextView) convertView.findViewById(R.id.listf5Name);
        TextView status = (TextView) convertView.findViewById(R.id.listf5Status);

        m_SkUser data = skUserData.get(position);

        noSk.setText(data.getNoSk());
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
