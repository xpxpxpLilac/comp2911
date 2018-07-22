import java.util.ArrayList;
import java.util.List;

public class Location {
	//field
	//locationName
	private String locationName; 
	//vansList
	private List<Vans> carName;
	
	//constructor new location
	public Location(String loName){
		this.locationName = loName;
		//add new vans
		this.carName = new ArrayList<Vans>();
	}

	public String getLocationName() {
		return locationName;
	}

	public List<Vans> getCarName() {
		return this.carName;
		
	}
	//method
	//checkVans
	public Boolean checkVans(Vans car){
		return this.carName.contains(car);
	}
	//addNewVans
	public void addNewVan(Vans newVan){
		                                                                      
		if(!checkVans(newVan)){
			this.carName.add(newVan);																	
		}
	}
	public void deleteVan(Vans van){
		this.getCarName().remove(van);
	}
	//showContent	
	public void content(){
		int i = 0;
		while(i < this.carName.size()){
			this.carName.get(i).printTime();
			i++;
		}
	}




	
	
}
