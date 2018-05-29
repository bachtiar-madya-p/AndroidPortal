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
import portal.bachtiar.com.portalv12.model.m_KelOvertime;

/**
 * Created by bachtiar on 17/11/17.
 */

public class Adapter_KelOvertime extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<m_KelOvertime> OvertimeData;

    public Adapter_KelOvertime(Activity activity, List<m_KelOvertime> OvertimeData) {
        this.activity = activity;
        this.OvertimeData = OvertimeData;
    }

    @Override
    public int getCount() {
        return OvertimeData.size();
    }

    @Override
    public Object getItem(int location) {
        return OvertimeData.get(location);
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
            convertView = inflater.inflate(R.layout.f3_list_kel_overtime, null);
        }

        TextView content = (TextView) convertView.findViewById(R.id.listf3Content);
        TextView date = (TextView) convertView.findViewById(R.id.listf3Date);
        TextView name = (TextView) convertView.findViewById(R.id.listf3Name);
        TextView status = (TextView) convertView.findViewById(R.id.listf3Status);

        m_KelOvertime data = OvertimeData.get(position);

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
