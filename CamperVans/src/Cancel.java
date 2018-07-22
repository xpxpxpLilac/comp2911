import java.util.List;

//operation
public class Cancel {
	//newCancel(int)
	//find booking (int, bo(list)
	/*int i;
	for(i = 0; i < lo.size(); i++){
		if(bo.get(i).getID == int){break;}
	}
	if(i == bo.size()){ i = -1;}
	return i;
}*/
	public Cancel(int index,List<Booking> bo, List<Location> lo){
		if(findBooking(index,bo) >= 0){
			deletePrevious(bo.get(findBooking(index,bo)),lo); //have to delete the previous record
			bo.remove(findBooking(index,bo));
			System.out.println("Cancel "+ String.valueOf(index));
		} else {
			System.out.println("Cancel rejected");
		}
	}
	
	public int findBooking(int index, List<Booking> bo){
		int i;
		for(i = 0; i < bo.size(); i++){
			if(bo.get(i).getBookID() == index){break;}
		}
		if(i == bo.size()){i = -1;}
		
		return i;
	}
	//if(findBook < 0)  rejected 
	//failed  cancel rejected
	//else remove
	//print"cancel int"
	public void deletePrevious(Booking book, List<Location> lo){
		while(book.getLos().size() != 0){
				
			int i =0;
			while(i<book.getLos().get(0).getCarName().size()){
				book.getLos().get(0).getCarName().get(i).deleteTime(book.getTime());
				i++;
			}
			book.getLos().remove(0);
		}
			
	}
}

