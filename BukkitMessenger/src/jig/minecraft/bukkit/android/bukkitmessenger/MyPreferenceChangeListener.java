package jig.minecraft.bukkit.android.bukkitmessenger;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.android.gcm.GCMRegistrar;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Log;

public class MyPreferenceChangeListener implements OnSharedPreferenceChangeListener {

	Context context;
	
	public MyPreferenceChangeListener(Context context) {
		this.context = context;
	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String arg1) {
//		if (arg1.equalsIgnoreCase("serviceSwitch")) {
//			if (arg0.getBoolean("serviceSwitch", false)) {
//				final String regId = GCMRegistrar.getRegistrationId(this.context);
//				if (regId.equals("")) {
//				  GCMRegistrar.register(this.context, "90834741325");
//				} else {
//				  Log.v("BukkitMessenger_MyPreferenceChangeListener", "Already registered");
//				  HttpClient httpClient = new DefaultHttpClient();
//
//					HttpGet httpGet = new HttpGet("http://h2100899.stratoserver.net:50123/bukkitmessenger/register/"+arg1);
//
//					HttpResponse response;
//
//					try {
//						response = httpClient.execute(httpGet);
//					} catch (ClientProtocolException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			} else {
//				GCMRegistrar.unregister(this.context);
//			}
//		}
	}

}
