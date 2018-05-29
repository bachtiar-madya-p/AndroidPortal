package portal.bachtiar.com.portalv12;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Bachtiar M Permadi on 04/11/2017.
 */

public class Global_class extends Application {
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
