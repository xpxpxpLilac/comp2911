import java.util.List;

//operation
public class Changing {
	//
	//
	/*change(BookingID,time,auto,manual,bo)
	 * look through bo to find particular booking
	 * remove all previous booking
	 * check whether can be done
	 * new change(same to book) change all terms in booking
	 * print
	 */
	public Changing(int index,List<Booking> bo,List<Location> lo, Timeslot time, int auto, int manual){
		if(checkValid(index,bo)){
			Booking temBook = record(findBooking(index,bo));
			deletePrevious(findBooking(index,bo),lo);
			if(findBooking(index,bo).checkBook(lo, time, auto, manual)){
				findBooking(index,bo).setAutoNum(auto);                                            
				findBooking(index,bo).setManualNum(manual);                                        
				findBooking(index,bo).setTime(time);                                               
				findBooking(index,bo).addBook(lo, time, auto, manual);
				printOut(findBooking(index,bo));
			} else {
				findBooking(index,bo).setAutoNum(temBook.getAutoNum());                                            
				findBooking(index,bo).setManualNum(temBook.getManualNum());                                        
				findBooking(index,bo).setTime(temBook.getTime());
				 Location l[] = new Location[temBook.getLos().size()];
				 temBook.getLos().toArray(l);    
				 for(int i = 0; i < temBook.getLos().size(); i++){
					   findBooking(index,bo).getLos().add(temBook.getLos().get(i));
					   Vans v[] = new Vans[l[i].getCarName().size()];
					   l[i].getCarName().toArray(v);
				       for(int j = 0; j < l[i].getCarName().size(); j++){				    	  				    	   				          
				                                           
				             findBooking(index,bo).getLos().get(i).addNewVan(v[j]);
				             v[j].addTime(temBook.getTime());                                     	   
				       }	
				 }  
				 
				System.out.println("Change rejected");
				
			}
		} else {
			System.out.println("Change rejected");
		}
	}
	public Boolean checkValid(int index,List<Booking> bo){
		Boolean bl = true;
		int i;
		for(i = 0; i < bo.size(); i++){
			if(bo.get(i).getBookID() == index){break;}
		}
		if(i == bo.size()){bl = false;}
		return bl;
	}
	public Booking findBooking(int index,List<Booking> bo){    
       	int i;
       	for(i = 0; i < bo.size(); i++){
       		if(bo.get(i).getBookID() == index){break;}
       	}
		return bo.get(i);
	}
	public Booking record(Booking book){
		Booking tem = new Booking(book.getTime(),book.getAutoNum(),book.getManualNum(),book.getBookID());
		for(int i = 0; i < book.getLos().size(); i++){
			tem.getLos().add(book.getLos().get(i));
		}
		return tem;
	}
	public void deletePrevious(Booking book, List<Location> lo){
		while(book.getLos().size() != 0){
			
			int i =0;
			while(i<book.getLos().get(0).getCarName().size()){
				book.getLos().get(0).getCarName().get(i).deleteTime(book.getTime());
				i++;
			}
			book.getLos().remove(0);
		}
		
	}//not finish!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!che
	
	//printout!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	//deletePreviousRecord
	//autoChange
	//manualChange
	//printout?//?????
	public void printOut(Booking book){
		System.out.print("Change" + " "+ String.valueOf(book.getBookID())); 
		
		 for(int i = 0;i < book.getLos().size(); i++){
			 System.out.print(" "+book.getLos().get(i).getLocationName());
	            for(int j = 0; j < book.getLos().get(i).getCarName().size(); j++) {
	            	System.out.print(" "+ book.getLos().get(i).getCarName().get(j).getName());
	            	if(j != book.getLos().get(i).getCarName().size()-1){
	            		System.out.print(",");
	            	}
	            }
	            if(i != book.getLos().size()-1){
	            	System.out.print(";");
	            } else {
	            	System.out.println();
	            }
	     }
		
	}
}
