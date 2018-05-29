package portal.bachtiar.com.portalv12.utils;

/**
 * Created by root on 14/04/16.
 */

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class  JSONfunctions extends AsyncTask<String, Void, JSONObject> {

    public static String getResponseText(InputStream in) {
        return new Scanner(in).useDelimiter("\\A").next();
    }

    @Override
    public JSONObject doInBackground(String... params) {
        JSONObject object = new JSONObject();
        try{
            InputStream in = null;
            String serviceResult = "";
            URL url = new URL(params[0]);


            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Content-length", "0");
            httpConn.setUseCaches(false);
            httpConn.setAllowUserInteraction(false);
            if(httpConn.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                in = httpConn.getInputStream();
                object = new JSONObject(getResponseText(in));

            }

        }
        catch (JSONException e) {
            if (e.getMessage()!=null)
                Log.e("Error", e.getMessage());
        } catch (MalformedURLException e) {
            if (e.getMessage()!=null)
                Log.e("Error", e.getMessage());
        } catch (IOException e) {
            if (e.getMessage()!=null)
                Log.e("Error", e.getMessage());
        }
        return object;
    }
}

