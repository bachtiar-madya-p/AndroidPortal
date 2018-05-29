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
import portal.bachtiar.com.portalv12.model.m_IdCard;

/**
 * Created by bachtiar on 25/11/17.
 */

public class Adapter_IdCard extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<m_IdCard> idcardData;

    public Adapter_IdCard(Activity activity, List<m_IdCard> idcardData) {
        this.activity = activity;
        this.idcardData = idcardData;
    }

    @Override
    public int getCount() {
        return idcardData.size();
    }

    @Override
    public Object getItem(int location) {
        return idcardData.get(location);
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
            convertView = inflater.inflate(R.layout.f7_list_idcard, null);
        }

        TextView content = (TextView) convertView.findViewById(R.id.listf7Content);
        TextView date = (TextView) convertView.findViewById(R.id.listf7Date);
        TextView name = (TextView) convertView.findViewById(R.id.listf7Name);
        TextView status = (TextView) convertView.findViewById(R.id.listf7Status);

        m_IdCard data = idcardData.get(position);

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
