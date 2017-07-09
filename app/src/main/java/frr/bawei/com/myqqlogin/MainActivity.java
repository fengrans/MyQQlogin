package frr.bawei.com.myqqlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.Config;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       TextView textView= (TextView) findViewById(R.id.tv_login);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginQQ();

            }
        });
        Config.DEBUG = true;
    }
    private void LoginQQ() {

        final UMShareAPI umShareAPI = UMShareAPI.get(this);
        UMShareConfig config = new UMShareConfig();
        umShareAPI.setShareConfig(config);//是否每次都重新获取授权
        config.isNeedAuthOnGetUserInfo(true);

        //调起QQ登陆
        umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);


    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调

            Toast.makeText(getApplicationContext(), "platform succeed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
//成功的方法
            String profile_image_url = data.get("iconurl");
            String name = data.get("name");//获取QQ登陆的信息 具体参数名称友盟文档上有

            if (platform.equals(SHARE_MEDIA.QQ)) {//如果是QQ登陆的 就使用上面的参数值
                Log.e("Ss", profile_image_url + "  " + name);
                Intent intent=new Intent(MainActivity.this,UserActivity.class);
                intent.putExtra("image",profile_image_url);
                intent.putExtra("username",name);
                startActivity(intent);

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //回调结果
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

//    private class BaseUiListener implements IUiListener {
//
//        private String openidString;
//
//        public void onComplete(JSONObject response) {
//
////            mBaseMessageText.setText("onComplete:");
////            mMessageText.setText(response.toString());
//
//            try {
////获得的数据是JSON格式的，获得你想获得的内容
////如果你不知道你能获得什么，看一下下面的LOG
//                Log.e("jiexingxing", "-------------" + response.toString());
//                String openidString = ((JSONObject) response).getString("openid");
//
////access_token= ((JSONObject) response).getString("access_token");
//// expires_in = ((JSONObject) response).getString("expires_in");
//            } catch (JSONException e) {
//// TODO Auto-generated catch block
//
//                e.printStackTrace();
//
//            }
//
//            doComplete(response);
//
//        }
//
//        protected void doComplete(JSONObject values) {
//
//        }
//
//        @Override
//        public void onComplete(Object o) {
//
//        }
//
//        @Override
//
//        public void onError(UiError e) {
//
////            showResult("onError:", "code:" + e.errorCode + ", msg:"
////                    + e.errorMessage + ", detail:" + e.errorDetail);
//        }
//
//        @Override
//
//        public void onCancel() {
////            showResult("onCancel", "");
//        }
//
//    }
}
