package info.androidhive.slidingmenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddEvent extends Fragment implements OnItemSelectedListener
{
	
	Button save;
	TextView start_time,start_date,alarm_date,alarm_time;
	EditText evnt_name,evnt_desc;
	Spinner sp;
	int mYear,mMonth,mDay, mHour, mMinute,sYear,sMonth,sDay, sHour, sMinute;
	String item;
	int i=0;
	int alarm_id=0;
	
	final List <String> namearray = new ArrayList<String>();
	final List <String> datearray = new ArrayList<String>();
	final List <String> timearray = new ArrayList<String>();
	final List <String> sdatearray = new ArrayList<String>();
	final List <String> stimearray = new ArrayList<String>();
	final List <String> categoryarray = new ArrayList<String>();
	final List <String> descriptionarray = new ArrayList<String>();
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
 
        View rootView = inflater.inflate(R.layout.fragment_add_event, container, false);
        
        final Context context = container.getContext();
    
        
        
        // Spinner element
        Spinner spinner = (Spinner)rootView.findViewById(R.id.spinner);
        
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Anivarsary");
        categories.add("Birthday");
        categories.add("Meeting");
        categories.add("Others");
        
        // Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
		
		// Drop down layout style - list view with radio button
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);
        
        start_date=(TextView) rootView.findViewById(R.id.start_date);
        start_time=(TextView) rootView.findViewById(R.id.start_time);
        alarm_date=(TextView) rootView.findViewById(R.id.alarm_date);
        alarm_time=(TextView) rootView.findViewById(R.id.alarm_time);
        save = (Button) rootView.findViewById(R.id.save_btn);
        
        evnt_name = (EditText) rootView.findViewById(R.id.event_name);
        evnt_desc = (EditText) rootView.findViewById(R.id.description);
        
        
        
        
        Calendar c = Calendar.getInstance();
        
        SimpleDateFormat format1,format2;
        
        format1 = new SimpleDateFormat("dd/MM/yyyy");
        format2 = new SimpleDateFormat("HH:mm");
        
        start_date.setText(format1.format(c.getTime()));
        start_time.setText(format2.format(c.getTime()));
        
        
        alarm_date.setText(format1.format(c.getTime()));
        alarm_time.setText(format2.format(c.getTime()));
        
        
        
        
        start_date.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View arg0) 
			{
				final Calendar c = Calendar.getInstance();
				sYear = c.get(Calendar.YEAR);
				sMonth = c.get(Calendar.MONTH);
				sDay = c.get(Calendar.DAY_OF_MONTH);
				 
				DatePickerDialog dpd = new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener() 
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
				 
				DatePickerDialog dpd = new DatePickerDialog(getActivity(),new DatePickerDialog.OnDateSetListener() 
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
				TimePickerDialog tpd = new TimePickerDialog(getActivity(),new TimePickerDialog.OnTimeSetListener() 
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
				TimePickerDialog tpd = new TimePickerDialog(getActivity(),new TimePickerDialog.OnTimeSetListener() 
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

			      /**
			         * CRUD Operations
			       * */
			      // Inserting Contacts
			      Log.d("Insert: ", "Inserting ..");
			      db.addContact(new Contact(evnt_name.getText().toString(), item ,
			    		  alarm_date.getText().toString() ,alarm_time.getText().toString(),
			    		  evnt_desc.getText().toString(),alarm_id,start_date.getText().toString(),
			    		  start_time.getText().toString()));
			    
			      Toast.makeText(getActivity().getApplicationContext(), String.valueOf(alarm_id), Toast.LENGTH_LONG).show();
			      
			      AlarmManager alarmManager = (AlarmManager)getActivity().getApplicationContext().getSystemService(Context.ALARM_SERVICE);
		          Intent intent = new Intent(getActivity().getApplicationContext(),AlarmReceiver.class);

		          intent.putExtra("name", evnt_name.getText().toString());
                  intent.putExtra("startdate",start_date.getText().toString());
                  intent.putExtra("starttime", start_time.getText().toString());
                  intent.putExtra("date",alarm_date.getText().toString());
                  intent.putExtra("time", alarm_time.getText().toString());
                  intent.putExtra("category", item);
                  intent.putExtra("description", evnt_desc.getText().toString());
                  intent.putExtra("alarm_id", alarm_id);
                  //intent.putExtra("key", keys.get(position));
		          
		          PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), alarm_id, intent, PendingIntent.FLAG_ONE_SHOT);
		          alarmManager.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(), pendingIntent);
			}
		});
        
        return rootView;
    }


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) 
	{
		// On selecting a spinner item
		item = parent.getItemAtPosition(position).toString();
				
		// Showing selected spinner item
		Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) 
	{
	}

}
