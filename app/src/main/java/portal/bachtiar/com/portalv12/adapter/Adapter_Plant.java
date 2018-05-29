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
import portal.bachtiar.com.portalv12.model.m_Plant;

/**
 * Created by bachtiar on 08/12/17.
 */

public class Adapter_Plant extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<m_Plant> plantData;

    public Adapter_Plant(Activity activity, List<m_Plant> plantData) {
        this.activity = activity;
        this.plantData = plantData;
    }

    @Override
    public int getCount() {
        return plantData.size();
    }

    @Override
    public Object getItem(int location) {
        return plantData.get(location);
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
            convertView = inflater.inflate(R.layout.list_plant, null);
        }

        TextView content = (TextView) convertView.findViewById(R.id.txtPlant);

        m_Plant data = plantData.get(position);

        content.setText(data.getPlant());

        return convertView;

    }
}
