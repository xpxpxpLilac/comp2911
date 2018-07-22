import java.util.ArrayList;
import java.util.List;

public class Vans {
	//field
	//location name
	static long ONE_HOUR = 3540000;   //59'
	private String location;
	//name
	private String vanName;
	//motorcycle type
	private String vanType;
	//time slot
	private List<Timeslot> timeSlot;
	
	//constructor new Vans
	public Vans(String location, String name, String type){
		this.location = location;
		this.vanName = name;
		this.vanType = type;
		this.timeSlot = new ArrayList<Timeslot>();
	}
	//method
	public String getLocation(){
		return this.location;
	}
	public String getName(){
		return this.vanName;
	}
	public String getType(){
		return this.vanType;
	}
	public List<Timeslot> getTimeSlot() {
		return timeSlot;
	}
	//manageTime add delete check
	//checkTime
	public Boolean checkTime(Timeslot time){
		
		for(int i = 0; i < this.timeSlot.size(); i++){
			
			if(time.BeforeOrAfter(this.timeSlot.get(i)) == -1){
				
				return false;
			}
		}
		return true;
		
	}
	//addTime
	public void addTime(Timeslot time){
		if(checkTime(time) == true){
			this.timeSlot.add(time);
			for(int i = 0;i < this.timeSlot.size(); i++){
				for(int j = 0;j <this.timeSlot.size(); j++){
					if(this.timeSlot.get(i).BeforeOrAfter(this.timeSlot.get(j))==2){
						Timeslot change = this.timeSlot.get(j);
					    this.timeSlot.set(j,this.timeSlot.get(i));
						this.timeSlot.set(i, change);					
					}
					j++;
				}
				i++;
			}
		}
	}
	//deleteTime
	public Boolean deleteTime(Timeslot time){
		return this.timeSlot.remove(time);
	}
	public void printTime(){
		int i = 0;
		while(i < this.timeSlot.size()){
			if(this.timeSlot.get(i).getStart().getHour() < 10){
			System.out.println(this.location+" "+this.vanName+" "+"0"+(this.timeSlot.get(i).getStart().getHour())+":00"+" "+this.timeSlot.get(i).getStart().getMonth()+" "+this.timeSlot.get(i).getStart().getDay()+" "+
		(this.timeSlot.get(i).getEnd().getHour())+":00"+" "+this.timeSlot.get(i).getEnd().getMonth()+" "+this.timeSlot.get(i).getEnd().getDay());
			}
			if(this.timeSlot.get(i).getEnd().getHour() < 10){
				System.out.println(this.location+" "+this.vanName+" "+(this.timeSlot.get(i).getStart().getHour())+":00"+" "+this.timeSlot.get(i).getStart().getMonth()+" "+this.timeSlot.get(i).getStart().getDay()+" "+
						"0"+(this.timeSlot.get(i).getEnd().getHour())+":00"+" "+this.timeSlot.get(i).getEnd().getMonth()+" "+this.timeSlot.get(i).getEnd().getDay());
			}else{
				System.out.println(this.location+" "+this.vanName+" "+(this.timeSlot.get(i).getStart().getHour())+":00"+" "+this.timeSlot.get(i).getStart().getMonth()+" "+this.timeSlot.get(i).getStart().getDay()+" "+
						(this.timeSlot.get(i).getEnd().getHour())+":00"+" "+this.timeSlot.get(i).getEnd().getMonth()+" "+this.timeSlot.get(i).getEnd().getDay());
			}
			i++;
		}
		

	}
	
}
