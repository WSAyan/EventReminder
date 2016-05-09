package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.List;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListAll extends Fragment 
{

	ListView list;
	Context context;
	final List<Integer>keys =new ArrayList<Integer>();
	final List <String> events = new ArrayList<String>();
	final List <String> startdate = new ArrayList<String>();
	final List <String> starttime = new ArrayList<String>();
	final List <String> date = new ArrayList<String>();
	final List <String> time = new ArrayList<String>();
	final List <String> category = new ArrayList<String>();
	final List <String> description = new ArrayList<String>();
	final List <Integer> alarm_id = new ArrayList<Integer>();
	ArrayAdapter<String> adapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,Bundle savedInstanceState) 
	{
 
        View rootView = inflater.inflate(R.layout.fragment_list_all, container, false);
        context=container.getContext();
        DatabaseHandler db = new DatabaseHandler(container.getContext());
         
        
     // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();       
 
        for (Contact cn : contacts) 
        {
            events.add(cn.getName());
        }
        
            
        list=(ListView)rootView.findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(context, R.layout.list_single, R.id.txt, events);
        
        list.setAdapter(adapter);
        
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() 
        {
             @Override
             public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
             {
            	 DatabaseHandler ha=new DatabaseHandler(context);
            	 List<Contact> con = ha.getAllContacts();
            	 keys.clear();
            	 events.clear();
            	 startdate.clear();
            	 starttime.clear();
            	 date.clear();
            	 time.clear();
            	 category.clear();
            	 description.clear();
            	 alarm_id.clear();
                 for (Contact cn : con) 
                 {
                 	 keys.add(cn.get_id());
                     events.add(cn.getName());
                     startdate.add(cn.getStart_date());
                     starttime.add(cn.getStart_time());
                     date.add(cn.getDate());
                     time.add(cn.getTime());
                     category.add(cn.getCategory());
                     description.add(cn.getDescription());
                     alarm_id.add(cn.getAlarm_id());
                 }
                    Intent intent=new Intent(container.getContext(), EventDetails.class);
                    
                    intent.putExtra("name", events.get(position));
                    intent.putExtra("startdate",startdate.get(position));
                    intent.putExtra("starttime", starttime.get(position));
                    intent.putExtra("date",date.get(position));
                    intent.putExtra("time", time.get(position));
                    intent.putExtra("category", category.get(position));
                    intent.putExtra("description", description.get(position));
                    intent.putExtra("alarm_id", alarm_id.get(position).toString());
                    intent.putExtra("key", keys.get(position));
                    
                    Toast.makeText(container.getContext(), alarm_id.get(position).toString()+"--"+keys.get(position), Toast.LENGTH_LONG).show();
                    
                    startActivity(intent);
             }
                    
        });
         
        return rootView;
    }
	
	@Override
	public void onResume() 
	{
		super.onResume();
		Log.d("resume", "resume call hoise....");
		DatabaseHandler handler=new DatabaseHandler(context);
		List<Contact> contacts =handler.getAllContacts();
	    events.clear();
	    for (Contact cn : contacts) 
        {
        	events.add(cn.getName());
        }
	    
	    adapter = new ArrayAdapter<String>(context, R.layout.list_single, R.id.txt, events);
        
        list.setAdapter(adapter);
	}
}