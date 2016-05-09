package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

public class AlarmReceiver  extends BroadcastReceiver
{


	List <String> datearray = new ArrayList<String>();
	List <String> timearray = new ArrayList<String>();
	List <String> categoryarray = new ArrayList<String>();
	List <String> descriptionarray = new ArrayList<String>();
	
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		
		//////////////////receive values from add event class////////////////////
		
		String nameString = intent.getStringExtra("name");
		datearray = intent.getStringArrayListExtra("date");
		timearray = intent.getStringArrayListExtra("time");
		categoryarray = intent.getStringArrayListExtra("category");
		descriptionarray = intent.getStringArrayListExtra("description");
		int index = Integer.valueOf(intent.getStringExtra("index"));
		
		int Image = 0;
		
		
		String categoryString = categoryarray.get(index);
		String dateString = datearray.get(index);
		String timeString = timearray.get(index);
		String descriptionString =descriptionarray.get(index);
		
		if(categoryString.equals("Birthday"))
			Image =R.drawable.birthday;
		

		else if(categoryString.equals("Anivarsary"))
			Image =R.drawable.anivarsary;
		

		else if(categoryString.equals("Meeting"))
			Image =R.drawable.meeting;
		
		else
			Image =R.drawable.others;
			 
        
		int uniqueInt = (int) (System.currentTimeMillis() & 0xfffffff);
	        
	        
		Toast.makeText(context, "ALarm Triggerd", Toast.LENGTH_LONG).show();
		
		
		
		//////////////////building notification////////////////////
		
		
		// define sound URI, the sound to be played when there's a notification
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        // intent triggered, you can add other intent for other actions
        Intent i = new Intent(context, EventDetails.class);
        
        i.putExtra("name", nameString);
		i.putExtra("category", categoryString);
		i.putExtra("date", dateString);
		i.putExtra("time", timeString);
		intent.putExtra("description", descriptionString);

		PendingIntent pendingIntent = PendingIntent.getActivity(context, uniqueInt, i, PendingIntent.FLAG_UPDATE_CURRENT);
		
        // this is it, we'll build the notification!
        // in the addAction method, if you don't want any icon, just set the first param to 0
        Notification mNotification = new Notification.Builder(context)

            .setContentTitle(nameString)
            .setContentText(categoryString)
            .setSmallIcon(Image)
            .setContentIntent(pendingIntent)
            .setSound(soundUri)

            .addAction(Image, "View", pendingIntent)

            .build();

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        // If you want to hide the notification after it was selected, do the code below
        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        
        notificationManager.notify(0, mNotification);
		
	}

}
