package jig.minecraft.bukkit.android.bukkitmessenger;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	private SharedPreferences preferences;
	private NotificationManager notMan;

	public GCMIntentService() {
		super("90834741325");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		notMan = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		super.onStart(intent, startId);
	}

	@Override
	protected void onError(Context arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onMessage(Context arg0, Intent arg1) {
		if (this.preferences.getBoolean("serviceSwitch", false)) {
			if (arg1.getStringExtra("refferer").equalsIgnoreCase("playerJoin")) {
				if (this.preferences.getBoolean("reffererPlayerJoin", false)) {
					this.showNotification(
							"Player " + arg1.getStringExtra("sender")
									+ " joined", false, 0);
				}
			} else if (arg1.getStringExtra("refferer").equalsIgnoreCase(
					"requestAdmin")) {
				if (this.preferences.getBoolean("reffererAdmin", false)) {
					this.showNotification(
							"Player " + arg1.getStringExtra("sender")
									+ " asked for an admin", false, 0);
				}
			} else if (arg1.getStringExtra("refferer").equalsIgnoreCase(
					"requestParty")) {
				if (this.preferences.getBoolean("reffererParty", false)) {
					if (arg1.getStringExtra("msg") != null) {
						this.showNotification(
								arg1.getStringExtra("msg"), false, 0);
					} else {
						this.showNotification(
								"Player " + arg1.getStringExtra("sender")
										+ " invited to a minecraft party :)", false, 0);
					}
				}
			} else if (arg1.getStringExtra("refferer").equalsIgnoreCase(
					"requestTest")) {
				if (this.preferences.getBoolean("reffererTest", false)) {
					this.showNotification("wuhu!", false, 0);
				}
			}
		}
	}

	@Override
	protected void onRegistered(Context arg0, String arg1) {
		HttpClient httpClient = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(
				"http://h2100899.stratoserver.net:50123/bukkitmessenger/register/"
						+ arg1);

		HttpResponse response;

		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		HttpClient httpClient = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(
				"http://h2100899.stratoserver.net:50123/bukkitmessenger/unregister/"
						+ arg1);

		HttpResponse response;

		try {
			response = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showNotification(String text, boolean ongoing, int id) {
		Notification notification = new Notification(R.drawable.ic_launcher,
				text, System.currentTimeMillis());
		// if (ongoing) {
		// notification.flags = Notification.FLAG_ONGOING_EVENT;
		// } else {
		// notification.flags = Notification.FLAG_AUTO_CANCEL;
		// }
		//
		// // PendingIntent contentIntent =
		// PendingIntent.getActivity(getApplicationContext(), 0,
		// // new Intent(getApplicationContext(),NotificationActivity.class),
		// // PendingIntent.FLAG_UPDATE_CURRENT);
		// // notification.setLatestEventInfo(this, “NotificationActivity”,
		// text, contentIntent);
		// notMan.notify(id, notification);
		//
		//

		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		Context context = this.getApplicationContext();
		Notification notification2 = new Notification(icon, text, when);

		String title = context.getString(R.string.app_name);

		Intent notificationIntent = new Intent(context, MainActivity.class);
		// set intent so it does not start a new activity
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent intent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		notification2.setLatestEventInfo(context, title, text, intent);
		notification2.flags |= Notification.FLAG_AUTO_CANCEL;

		// Play default notification sound
		notification2.defaults |= Notification.DEFAULT_SOUND;

		// Vibrate if vibrate is enabled
		notification2.defaults |= Notification.DEFAULT_VIBRATE;
		notMan.notify(0, notification2);

	}

}
