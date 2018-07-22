import java.util.ArrayList;
import java.util.List;

//store booking info
public class Booking {                                          
	//field
	/**
	 * @author Lilac Liu
	 */
	private int bookID;
	private Timeslot time;
	private int autoNum;
	private int manualNum;
	private List<Location> Los;
	/**
	 * pre: need to create a new book
	 * @param time
	 * @param autoNum
	 * @param manualNum
	 * @param ID
	 */
	public Booking(Timeslot time, int autoNum, int manualNum, int ID) {
		super();
		this.time = time;
		this.autoNum = autoNum;
		this.manualNum = manualNum;
		this.bookID = ID;
		this.Los = new ArrayList<Location>();
	}
	/**
	 * constructor return a new Booking
	 * @return 
	 */

	
	/**
	 * 
	 * @return
	 */
	public int getBookID() {
		return this.bookID;
	}
	/**
	 * 
	 * @return
	 */
	public Timeslot getTime() {
		return this.time;
	}
	/**
	 * 
	 * @return
	 */
	public int getAutoNum() {
		return this.autoNum;
	}
	/**
	 * 
	 * @return
	 */
	public int getManualNum() {
		return this.manualNum;
	}
	/**
	 * 
	 * @return
	 */
	public List<Location> getLos() {
		return this.Los;
	}
	/**
	 * 
	 * @param time
	 */
	
	public void setTime(Timeslot time) {
		this.time = time;
	}
	/**
	 * 
	 * @param autoNum
	 */
	public void setAutoNum(int autoNum) {
		this.autoNum = autoNum;
	}
	/**
	 * 
	 * @param manualNum
	 */
	public void setManualNum(int manualNum) {
		this.manualNum = manualNum;
	}


	//method
	//check whether a new booking can be made
	/**
	 * 
	 * @param lo
	 * @param time
	 * @param auto
	 * @param manual
	 * @return
	 */
	public Boolean checkBook(List<Location> lo, Timeslot time,int auto, int manual){                  
		    
		   Boolean bl = true;
		   Location l[] = new Location[lo.size()];
		   lo.toArray(l);
		   
		   search:
		   for(int i = 0; i < lo.size(); i++){
			   
			   Vans v[] = new Vans[l[i].getCarName().size()];
			   l[i].getCarName().toArray(v);
		       
			   for(int j = 0; j < l[i].getCarName().size(); j++){
		    	   if(auto == 0 && manual == 0) break search;
		           if(v[j].getType().equals("Automatic") && auto != 0 && v[j].checkTime(time)) {
		             auto = auto - 1;
		           } else if(v[j].getType().equals("Manual") && manual != 0 && v[j].checkTime(time)) {
		        	   manual = manual - 1;		   
		           }
		        }  
		   }	
	
		   if(auto != 0 || manual != 0 ){
			   //object rejected
			   bl = false;
		   } 
		   return bl;
	}
	//input the location info and add time to vans
	/**
	 * 
	 * @param lo
	 * @param time
	 * @param auto
	 * @param manual
	 */
	public void addBook(List<Location> lo, Timeslot time,int auto, int manual){                  
	    
		 
		   Location l[] = new Location[lo.size()];
		   lo.toArray(l);
		   
		   search:
		   for(int i = 0; i < lo.size(); i++){
			   Location location = new Location(l[i].getLocationName());
			   Vans v[] = new Vans[l[i].getCarName().size()];
			   l[i].getCarName().toArray(v);
		       for(int j = 0; j < l[i].getCarName().size(); j++){
		    	   if(auto == 0 && manual == 0) break search;
		    	   
		           if(v[j].getType().equals("Automatic") && auto != 0 && v[j].checkTime(time)) {
		             auto = auto - 1;
		             lo.get(i).getCarName().get(j).addTime(time);                                
		             location.addNewVan(v[j]);
		           } else if(v[j].getType().equals("Manual") && manual != 0 && v[j].checkTime(time)) {
		        	   manual = manual - 1;
		        	   lo.get(i).getCarName().get(j).addTime(time);                              
		        	   location.addNewVan(v[j]);	
		        	   
		           }	        
		       if(location.getCarName().size() != 0 && this.doesnothave(location)){
		    	   							
	        	   this.Los.add(location);
		       }}
		   }	
		  
	}
	
	public void printOut(){
		
		
		System.out.print("Booking" + " "+ String.valueOf(this.bookID)); 
		
		 for(int i = 0;i < this.Los.size(); i++){
			 System.out.print(" "+this.Los.get(i).getLocationName());
	            for(int j = 0; j < this.Los.get(i).getCarName().size(); j++) {
	            	System.out.print(" "+this.Los.get(i).getCarName().get(j).getName());
	            	if(j != this.Los.get(i).getCarName().size()-1){
	            		System.out.print(",");
	            	}
	            }
	            if(i != this.Los.size()-1){
	            	System.out.print(";");
	            } else {
	            	System.out.println();
	            }
	     }
		
	}
		public boolean doesnothave(Location loc){
			int i = 0;
			while(i<Los.size()){
				if(Los.get(i).getLocationName().equals(loc.getLocationName())){return false;}
				i++;
			}
			return true;
		}


}
