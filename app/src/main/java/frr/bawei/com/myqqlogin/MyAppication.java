package frr.bawei.com.myqqlogin;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by Administrator on 2017/7/9.
 */
public class MyAppication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

    } {


        PlatformConfig.setQQZone("1106276370", "SsfzEdvLJ8FDHnzm");
    }
}
