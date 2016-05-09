package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class EventDetails extends Activity 
{
	Context context=this;
	ImageView edit_details;
	TextView Category,Name,Date,Time,Description,sDate,sTime;
	String item,nameString,dateString,timeString,sdateString,stimeString,categoryString,descriptionString,alarm;
	int mYear,mDay,mMonth,mHour,mMinute,recieve_key,sYear,sMonth,sDay, sHour, sMinute;
	int alarm_id = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_details);
		

		//////////////////receive values from list all or alarm receiver class////////////////////
		
		Intent intent=getIntent();
		
		nameString = intent.getStringExtra("name");
		dateString = intent.getStringExtra("date");
		timeString =intent.getStringExtra("time");
		sdateString = intent.getStringExtra("startdate");
		stimeString =intent.getStringExtra("starttime");
		categoryString = intent.getStringExtra("category");
		descriptionString = intent.getStringExtra("description");
		alarm = intent.getStringExtra("alarm_id");
		recieve_key=intent.getIntExtra("key", 0);
		Toast.makeText(context, nameString+"--"+dateString+"--"+timeString+"--"+
							sdateString+"--"+stimeString+"--"+categoryString+"--"+
							descriptionString+"--"+alarm+"--"+recieve_key, Toast.LENGTH_LONG).show();
		
		alarm_id=Integer.valueOf(alarm);
		
		ImageView Image = (ImageView) findViewById(R.id.imageView);
		Category = (TextView) findViewById(R.id.category_details);
		Name = (TextView) findViewById(R.id.event_details);
		Date = (TextView) findViewById(R.id.date_details);
		Time = (TextView) findViewById(R.id.time_details);
		sDate = (TextView) findViewById(R.id.start_date_detail);
		sTime = (TextView) findViewById(R.id.start_time_detail);
		Description = (TextView) findViewById(R.id.description_details);
		
		ImageButton edit_btn = (ImageButton) findViewById(R.id.imageButtonEdit);
		edit_details = (ImageView) findViewById(R.id.edit_iv);

		if(categoryString.contentEquals("Anivarsary"))
			Image.setImageResource(R.drawable.anivarsary);

		else if(categoryString.contentEquals("Birthday"))
			Image.setImageResource(R.drawable.birthday);

		else if(categoryString.contentEquals("Meeting"))
			Image.setImageResource(R.drawable.meeting);

		else
			Image.setImageResource(R.drawable.others);
		

		
		Name.setText(nameString);
		Category.setText(categoryString);
		Date.setText(dateString);
		Time.setText(timeString);
		sDate.setText(sdateString);
		sTime.setText(stimeString);
		Description.setText(descriptionString);
		
		edit_details.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{
				final Dialog dialog=new Dialog(context);
				dialog.setContentView(R.layout.fragment_add_event);
				dialog.setTitle("Edit Event");
				dialog.setCanceledOnTouchOutside(false);
				
				Button save;
				final TextView start_time;
				final TextView start_date;
				final TextView alarm_date;
				final TextView alarm_time;
				final EditText evnt_name;
				EditText evnt_desc;
				Spinner spn;
				
				spn = (Spinner)dialog.findViewById(R.id.spinner);
				
				List<String> categories = new ArrayList<String>();
				categories.add(Category.getText().toString());
		        categories.add("Anivarsary");
		        categories.add("Birthday");
		        categories.add("Meeting");
		        categories.add("Others");
		        if (Category.getText().toString().equals("Anivarsary")) 
		        {
					categories.remove(1);
				}
		        else if (Category.getText().toString().equals("Birthday")) 
		        {
					categories.remove(2);
				}
		        else if (Category.getText().toString().equals("Meeting")) 
		        {
					categories.remove(3);
				}
		        else if (Category.getText().toString().equals("Others")) 
		        {
					categories.remove(4);
				}
		        
		        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categories);
		        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        spn.setAdapter(dataAdapter);
		        
		        spn.setOnItemSelectedListener(new OnItemSelectedListener() 
				{
					@Override
					public void onItemSelected(AdapterView<?> parent, View view,int position, long id) 
					{
						item = parent.getItemAtPosition(position).toString();
						Toast.makeText(context, item, Toast.LENGTH_LONG).show();
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) 
					{
						// TODO Auto-generated method stub
					}
				});
		        
		        start_date=(TextView) dialog.findViewById(R.id.start_date);
		        start_time=(TextView) dialog.findViewById(R.id.start_time);
		        alarm_date=(TextView) dialog.findViewById(R.id.alarm_date);
		        alarm_time=(TextView) dialog.findViewById(R.id.alarm_time);
		        save = (Button) dialog.findViewById(R.id.save_btn);
		        
		        evnt_name = (EditText) dialog.findViewById(R.id.event_name);
		        evnt_desc = (EditText) dialog.findViewById(R.id.description);
		        
		        evnt_name.setText(Name.getText().toString());
		        start_date.setText(Date.getText().toString());
		        start_time.setText(Time.getText().toString());
		        alarm_date.setText(Date.getText().toString());
		        alarm_time.setText(Time.getText().toString());
		        evnt_desc.setText(Description.getText().toString());
		        
		        start_date.setOnClickListener(new OnClickListener() 
		        {
					
					@Override
					public void onClick(View arg0) 
					{
						final Calendar c = Calendar.getInstance();
						sYear = c.get(Calendar.YEAR);
						sMonth = c.get(Calendar.MONTH);
						sDay = c.get(Calendar.DAY_OF_MONTH);
						 
						DatePickerDialog dpd = new DatePickerDialog(context,new DatePickerDialog.OnDateSetListener() 
						{
						 
						     @Override
						     public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) 
						     {
						              sYear = year;
						              sMonth = monthOfYear;
						              sDay = dayOfMonth;
						              start_date.setText(sDay+"/"+sMonth+"/"+sYear);
						     }
						        
						}, sYear, sMonth, sDay);
						
						dpd.show();
					}
				});
		        
		        alarm_date.setOnClickListener(new OnClickListener()
		        {
					
					@Override
					public void onClick(View arg0) 
					{
						final Calendar c = Calendar.getInstance();
						mYear = c.get(Calendar.YEAR);
						mMonth = c.get(Calendar.MONTH);
						mDay = c.get(Calendar.DAY_OF_MONTH);
						 
						DatePickerDialog dpd = new DatePickerDialog(context,new DatePickerDialog.OnDateSetListener() 
						{
						 
						     @Override
						     public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) 
						     {
						              mYear = year;
						              mMonth = monthOfYear;
						              mDay = dayOfMonth;
						              alarm_date.setText(mDay+"/"+mMonth+"/"+mYear);
						     }
						        
						}, mYear, mMonth, mDay);
						
						dpd.show();
					}
				});
		        
		        alarm_time.setOnClickListener(new OnClickListener() 
		        {
					
					@Override
					public void onClick(View arg0) 
					{
						TimePickerDialog tpd = new TimePickerDialog(context,new TimePickerDialog.OnTimeSetListener() 
						{
						 
						     @Override
						     public void onTimeSet(TimePicker view, int hourOfDay,int minute) 
						     {
						        mHour = hourOfDay;
						        mMinute = minute;
						        alarm_time.setText(mHour+":"+mMinute);
						     }
						}, mHour, mMinute, false);
						
						tpd.show();
						
					}
				});
		        
		        start_time.setOnClickListener(new OnClickListener() 
		        {
					
					@Override
					public void onClick(View arg0) 
					{
						TimePickerDialog tpd = new TimePickerDialog(context,new TimePickerDialog.OnTimeSetListener() 
						{
						 
						     @Override
						     public void onTimeSet(TimePicker view, int hourOfDay,int minute) 
						     {
						    	 sHour = hourOfDay;
							        sMinute = minute;
							        start_time.setText(sHour+":"+sMinute);
							 }
							     
						}, sHour, sMinute, false);
						
						tpd.show();
						
					}
				});
		        
		        save.setOnClickListener(new OnClickListener() 
		        {
					
					@Override
					public void onClick(View arg0) 
					{
						dialog.cancel();
						
						DatabaseHandler db = new DatabaseHandler(context);
				        
				        addalarm(mYear,mMonth,mDay,mHour,mMinute);
					}

					private void addalarm(int mYear, int mMonth, int mDay, int mHour,int mMinute)
					{
						//////////////add alarm///////////////
				        
					    Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
					    //cal.add(Calendar.SECOND, 10);

					    cal.set(Calendar.DATE,mDay);  //1-31
					    cal.set(Calendar.MONTH,mMonth);  //first month is 0!!! January is zero!!!
					    cal.set(Calendar.YEAR,mYear);//year...

					    cal.set(Calendar.HOUR_OF_DAY, mHour);  //HOUR
					    cal.set(Calendar.MINUTE, mMinute);       //MIN
					    cal.set(Calendar.SECOND, 0);       //SEC

					    DatabaseHandler db = new DatabaseHandler(context);
					    alarm_id = (int) System.currentTimeMillis();
					    
					    Log.d("Insert: ", "Inserting ..");
					      //db.addContact(new Contact(evnt_name.getText().toString(), item , String.valueOf(mDay) + "-" + String.valueOf(mMonth) + "-" +String.valueOf(mYear) ,String.valueOf(mHour) + ":" +String.valueOf(mMinute),evnt_desc.getText().toString(),alarm_id));
					    
					      //Toast.makeText(getActivity().getApplicationContext(), String.valueOf(alarm_id), Toast.LENGTH_LONG).show();
					      
					      AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
				          Intent intent = new Intent(context,AlarmReceiver.class);

				          intent.putExtra("name", evnt_name.getText().toString());
				          intent.putExtra("category", item.toString());
				          
/*
				  	     // Reading all contacts
				  	      Log.d("Reading: ", "Reading all contacts..");
				  	      List<Contact> contacts = db.getAllContacts();       
				  	 
				  	       
				  	      for (Contact cn : contacts) 
				  	      {
				  	    	  	namearray.add(cn.getName());
				  	            datearray.add(cn.getDate());
				  	            timearray.add(cn.getTime());
				  	            descriptionarray.add(cn.getDescription());
				  	            categoryarray.add(cn.getCategory());
				  	                
				  	       }
				  	        
				  	        int index = namearray.indexOf(evnt_name.getText().toString());
				  	        
				  	        intent.putStringArrayListExtra("date", (ArrayList<String>) datearray);
				  	        intent.putStringArrayListExtra("time", (ArrayList<String>) timearray);
				  	        intent.putStringArrayListExtra("category", (ArrayList<String>) categoryarray);
				  	        intent.putStringArrayListExtra("description", (ArrayList<String>) descriptionarray);
				  	        intent.putExtra("index",String.valueOf(index));
				            
*/
				            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarm_id, intent, PendingIntent.FLAG_ONE_SHOT);
				            alarmManager.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(), pendingIntent);
					}
				});
				dialog.show();
			}
		});
		
		edit_btn.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View view) 
			{
				Builder alertBuilder=new Builder(context);
				alertBuilder.setTitle("Attention");
				alertBuilder.setMessage("Are you sure you want to delete this word?")
				.setCancelable(false)
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int id) 
					{
						dialog.cancel();
					}
				})
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() 
				{	
					@Override
					public void onClick(DialogInterface dialog, int id) 
					{
						//databaseHandler=new DatabaseHandler(context);
						//Contact contact=new Contact(recieve_key, recieve_word, recieve_meaning, recieve_antonim, recieve_synonim);
						
						//databaseHandler.deleteContact(contact);
						
		                //Word_Details.this.finish();
						AlarmManager alarmManager = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
				        Intent intent = new Intent(getApplicationContext(),AlarmReceiver.class);
						PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), alarm_id, intent, PendingIntent.FLAG_ONE_SHOT);
			            alarmManager.cancel(pendingIntent);
				    	
						DatabaseHandler handler=new DatabaseHandler(context);
						Toast.makeText(getApplicationContext(), String.valueOf(alarm_id)+"--"+recieve_key+"--"+Name.getText().toString()+"--"+
								Category.getText().toString()+"--"+Date.getText().toString()+"--"+
								Time.getText().toString()+"--"+Description.getText().toString()+"--"+alarm_id+"--"+
								sDate.getText().toString()+"--"+sTime.getText().toString(), Toast.LENGTH_LONG).show();
						Contact contact=new Contact(recieve_key, Name.getText().toString(), Category.getText().toString(), Date.getText().toString(), Time.getText().toString(), Description.getText().toString(), alarm_id, sDate.getText().toString(), sTime.getText().toString());
						handler.deleteContact(contact);
						
						EventDetails.this.finish();



					}
				});
				AlertDialog alertDialog=alertBuilder.create();
				alertDialog.show();
				
			}
		});
	}
}
