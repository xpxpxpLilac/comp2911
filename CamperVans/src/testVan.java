import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class testVan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          
         List<Location> lo = new ArrayList<Location>();
         List<Booking> bo = new ArrayList<Booking>();
      
         Scanner sc  = null;
         try{
        	 sc = new Scanner(new FileReader(args[0]));
        	 String curr;
        	 while(sc.hasNextLine()){
        		 curr = sc.next();
        		 switch(curr){
        		 	case "Location":  //Location CBD Wicked Automatic
        		 		String loName = sc.next();                    
        		 		Location newLo = new Location(loName);
        		 		
        		 		String carName = sc.next();                   
        		 		String type = sc.next();                       
        		 		Vans van = new Vans(loName,carName,type);
        		 		
        		 		if(checkLoIndex(lo,loName) < 0){
        		 			lo.add(newLo);
        		 			lo.get(checkLoIndex(lo,loName)).addNewVan(van);
        		 			
        		 		} else {
        		 			lo.get(checkLoIndex(lo,loName)).addNewVan(van);
        		 		}
        		 		
        		 		sc.nextLine();break;
        		 		//add location info array
        		 	case "Request":   //Request 1 23 Mar 25 12 Mar 26 3 Automatic 1 Manual
        		 		int auto = 0;
        		 		int manual = 0;
        		 		try{
        		 		
        		 			int ID = sc.nextInt();
        		 			
        		 			int startTime = sc.nextInt();
        		 			String startMonth = sc.next();
        		 			int startDay = sc.nextInt();
        		 		
        		 			int endTime = sc.nextInt();
        		 			String endMonth = sc.next();
        		 			int endDay = sc.nextInt();
        		 		
        		 			int num1 = sc.nextInt();
        		 			String type1 = sc.next();
        		 			//System.out.println(type1);
        		 			if(type1.equals("Automatic")){
        		 				if(sc.hasNextInt()){
        		 					int num2 = sc.nextInt();
        		 					manual = num2;
        		 					//System.out.println(manual);
        		 				}
        		 				auto = num1;
        		 			} else if(type1.equals("Manual")){
        		 				if(sc.hasNextInt()){
        		 					//System.out.println(sc.next());
        		 					int num2 = sc.nextInt();
        		 					auto = num2;        		 					
        		 				}
        		 				manual = num1;
        		 				
        		 			}
        		 			Time start = new Time(startTime,startMonth,startDay);
        		 			Time end = new Time(endTime,endMonth,endDay);
        		
        		 			Timeslot timeslot = new Timeslot(start, end);
        		 			Booking book = new Booking(timeslot,auto,manual,ID);
        		 			if(book.checkBook(lo, timeslot, auto, manual) == false){
        		 				System.out.println("Booking rejected");
        		 			} else {
        		 				book.addBook(lo, timeslot, auto, manual);
        		 				bo.add(book);
        		 				book.printOut();
        		 			}
        		 		}
        		 		catch(InputMismatchException e2){       		 			
        		 		}
        		 		if(sc.hasNextLine()){sc.nextLine();}break;
        		 		
        		 	case "Change":    //Change 1 23 Mar 27 23 Mar 29 3 Manual 2 Automatic
        		 		int autoRe = 0;
        		 		int manualRe = 0;
        		 		try{
        		 		
        		 			int ID = sc.nextInt();
        		 			int startTime = sc.nextInt();
        		 			String startMonth = sc.next();
        		 			int startDay = sc.nextInt();
        		 		
        		 			int endTime = sc.nextInt();
        		 			String endMonth = sc.next();
        		 			int endDay = sc.nextInt();
        		 		
        		 			int num1 = sc.nextInt();
        		 			String type1 = sc.next();
        		 			if(type1.equals("Automatic")){
        		 				if(sc.hasNextInt()){
        		 					int num2 = sc.nextInt();
        		 					manualRe = num2;
        		 				}
        		 				autoRe = num1;
        		 			} else if(type1.equals ("Manual")){
        		 				if(sc.hasNextInt()){
        		 					int num2 = sc.nextInt();                                  
        		 					autoRe = num2;                                              
        		 				}
        		 				manualRe = num1;
        		 			}
        		 			Time start = new Time(startTime,startMonth,startDay);
        		 			Time end = new Time(endTime,endMonth,endDay);
        		 			
        		 			Timeslot timeslot = new Timeslot(start,end);
        		 			new Changing(ID, bo, lo, timeslot, autoRe, manualRe);
        	
        		 		}
        		 		catch(InputMismatchException e2){
        		 			System.out.println("Change rejected");        		 			
        		 		}
        		 		if(sc.hasNextLine()){sc.nextLine();}break;
        		 		//new change
        		 		
        		 	case "Cancel"://Cancel 3
        		 		int ID = sc.nextInt();
        		 		new Cancel(ID, bo, lo);
        		 		break;
        		 		//new cancel
        		 	case "Print":     //Print CBD
        		 		//print out        		 		
        		 		String name = sc.next();
        		 		for(int i = 0; i < lo.size(); i++){
        		 			if(lo.get(i).getLocationName().equals(name)){
        		 				lo.get(i).content();
        		 			}
        		 		}
        		 		
        		        break;
        		 	case "#":
        		 		if(sc.hasNextLine()){sc.nextLine();}break;
        		 }
        	 }
         } catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  catch (NoSuchElementException e2) {
			
		}
         finally{
        	 if(sc != null){ sc.close();}
         }
         //System.out.println(lo.get(0).getLocationName());
        
	}
	public static int checkLoIndex(List<Location> lo, String loName){
		int i;
		for(i = 0; i < lo.size(); i++){
			if(lo.get(i).getLocationName().equals(loName)){break;}
		}
		if(i == lo.size()){ i = -1;}
		return i;
	}
	public static Calendar createNewCal(int time, String month, int day){
        String str = String.valueOf(time) + " " + "00"+ " "+"00" +" "+ "Mar"+" " +String.valueOf(day)+" " + String.valueOf(2017);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat("HH mm ss MMM dd yyyy",Locale.US);
         
 		try {
 			Date date = sdf.parse(str);
 			calendar.setTime(date);
 			System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
 	         
 		} catch (ParseException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
 		return calendar;
	}

}
