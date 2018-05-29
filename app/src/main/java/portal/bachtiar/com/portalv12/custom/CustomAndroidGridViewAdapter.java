package portal.bachtiar.com.portalv12.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import portal.bachtiar.com.portalv12.R;

import static portal.bachtiar.com.portalv12.R.id.grid;

/**
 * Created by Bachtiar M Permadi on 30/10/2017.
 */

public class CustomAndroidGridViewAdapter extends BaseAdapter {
    private Context mContext;
    LayoutInflater inflter;
    private final String[] gridViewString;
    private final int[] ImageId;

    public CustomAndroidGridViewAdapter(Context context, String[] gridViewString, int[] ImageId) {
        mContext = context;
        this.ImageId = ImageId;
        this.gridViewString = gridViewString;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return gridViewString.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.dashboard_grid_layout, null);
        ImageView gridViewImageId = (ImageView) view.findViewById(R.id.gridview_image);
        TextView gridViewImageString = (TextView) view.findViewById(R.id.gridview_text);
        gridViewImageString.setText(gridViewString[i]);
        gridViewImageId.setImageResource(ImageId[i]);
        return view;
    }
}
