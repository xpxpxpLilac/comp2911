public class Timeslot{
	//field
	//Calendar start
	private Time start;
	//Calendar end
	private Time end;
	//constructor
	//new(start,end)
	public Timeslot(Time start, Time end){
		this.start = start;
		this.end = end;
	}
	//method
	public Time getStart(){
		return this.start;
	}
	public Time getEnd(){
		return this.end;
	}

    public Boolean checkOverlap(Timeslot time){
    
    	if((this.end.BeforeOrAfter(time.start) == 2) && (this.start.BeforeOrAfter(time.start) == 1) && (this.end.BeforeOrAfter(time.end) == 1)){
    		return false;
    	}
        if((this.start.BeforeOrAfter(time.end) == 1)  && (this.start.BeforeOrAfter(time.start) == 2) && (this.end.BeforeOrAfter(time.end) == 2)){
    		return false;
    	}
    	if((this.start.BeforeOrAfter(time.start) == 2) && (this.end.BeforeOrAfter(time.end) == 1)){
    		return false;
    	}
    	if((this.start.BeforeOrAfter(time.start) == 1) && (this.end.BeforeOrAfter(time.end) == 2)){
    		return false;
    	}
    	return true;

    }
    public int BeforeOrAfter(Timeslot time){
    	int i = 0;
    	Time timeStart = time.start.timeCopy();
    	Time timeEnd = time.end.timeCopy();
    	timeStart.timeadd(-1);
    	timeEnd.timeadd(1);
    	Timeslot newTime = new Timeslot(timeStart,timeEnd);
    	if(checkOverlap(newTime)){
    		if(this.start.BeforeOrAfter(timeEnd) == 2){/////after
    			i = 1;
    		}
    		if(this.end.BeforeOrAfter(timeStart) == 1){ //before
    			i = 2;
    		}
    	} else {
    		i = -1;
    	}
    	return i;
    }

}

