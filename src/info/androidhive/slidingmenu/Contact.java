package info.androidhive.slidingmenu;

public class Contact 
{
	 
    //private variables
    int _id;
    String name;
    String category;
    String date;
    String time;
    String description;
    int alarm_id;
    String start_date;
    String start_time;
 

	// Empty constructor
    public Contact()
    {
 
    }
    
    
    
 public Contact(int _id, String name, String category, String date,
			String time, String description, int alarm_id, String start_date,
			String start_time) {
		super();
		this._id = _id;
		this.name = name;
		this.category = category;
		this.date = date;
		this.time = time;
		this.description = description;
		this.alarm_id = alarm_id;
		this.start_date = start_date;
		this.start_time = start_time;
	}
    
    // constructor
 	public Contact(String name, String category, String date, String time,
			String description, int alarm_id, String start_date,
			String start_time) 
 	{
		super();
		this.name = name;
		this.category = category;
		this.date = date;
		this.time = time;
		this.description = description;
		this.alarm_id = alarm_id;
		this.start_date = start_date;
		this.start_time = start_time;
	}
 
	public String getStart_date() 
	{
		return start_date;
	}

	public void setStart_date(String start_date) 
	{
		this.start_date = start_date;
	}

	public String getStart_time() 
	{
		return start_time;
	}

	public void setStart_time(String start_time) 
	{
		this.start_time = start_time;
	}

    public int get_id() 
    {
		return _id;
	}

	public void set_id(int _id) 
	{
		this._id = _id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getCategory() 
	{
		return category;
	}

	public void setCategory(String category) 
	{
		this.category = category;
	}

	public String getDate() 
	{
		return date;
	}

	public void setDate(String date) 
	{
		this.date = date;
	}

	public String getTime() 
	{
		return time;
	}

	public void setTime(String time) 
	{
		this.time = time;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	
	public int getAlarm_id() 
	{
		return alarm_id;
	}

	public void setAlarm_id(int alarm_id) 
	{
		this.alarm_id = alarm_id;
	}
	
}