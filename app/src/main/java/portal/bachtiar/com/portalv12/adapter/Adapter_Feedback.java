package portal.bachtiar.com.portalv12.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import portal.bachtiar.com.portalv12.R;
import portal.bachtiar.com.portalv12.model.m_feedback;
import portal.bachtiar.com.portalv12.utils.AppController;

/**
 * Created by bachtiar on 26/11/17.
 */

public class Adapter_Feedback extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<m_feedback> feedbackData;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoaderFeed();

    public Adapter_Feedback(Activity activity, List<m_feedback> feedbackData) {
        this.activity = activity;
        this.feedbackData = feedbackData;
    }

    @Override
    public int getCount() {
        return feedbackData.size();
    }

    @Override
    public Object getItem(int location) {
        return feedbackData.get(location);
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
            convertView = inflater.inflate(R.layout.content_feedback, null);
        }

        NetworkImageView imgUser = (NetworkImageView)convertView.findViewById(R.id.fbUserImg);
        TextView content = (TextView) convertView.findViewById(R.id.fbContent);
        TextView date = (TextView) convertView.findViewById(R.id.fbPostDate);
        TextView name = (TextView) convertView.findViewById(R.id.fbUserName);

        m_feedback data = feedbackData.get(position);

        imgUser.setImageUrl(data.getImgUser(),imageLoader);
        content.setText(data.getContent());
        date.setText(data.getPosdate());
        name.setText(data.getName());

        return convertView;

    }
}
