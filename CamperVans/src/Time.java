import java.util.Calendar;
public class Time {
	private int Hour;
	private String Month;
	private int day;
	private int Month2;
	public Time (int Hour, String Month, int day){
		this.Hour=Hour;
		this.day=day;
		this.Month=Month;
		
		if(Month.equals("Jan")) Month2 = 1;
		if(Month.equals("Feb")) Month2 = 2;
		if(Month.equals("Mar")) Month2 = 3;
		if(Month.equals("Apr")) Month2 = 4;
		if(Month.equals("May")) Month2 = 5;
		if(Month.equals("Jun")) Month2 = 6;
		if(Month.equals("Jul")) Month2 = 7;
		if(Month.equals("Aug")) Month2 = 8;
		if(Month.equals("Sep")) Month2 = 9;
		if(Month.equals("Oct")) Month2 = 10;
		if(Month.equals("Nov")) Month2 = 11;
		if(Month.equals("Dec")) Month2 = 12;
	}
	public int getHour() {
		return Hour;
	}

	public String getMonth() {
		return Month;
	}

	public int getDay() {
		return day;
	}

	public int getMonth2() {
		return Month2;
	}

	
	public void getTime(){
		System.out.println(Hour + " " + Month2  + " " + day);
	}
	public void timeadd(int h){
		Calendar a = Calendar.getInstance();
		a.set(Calendar.MONTH,Month2);
		a.set(Calendar.DAY_OF_MONTH,day);
		a.set(Calendar.HOUR_OF_DAY,Hour);
		a.add(Calendar.HOUR_OF_DAY, h);
		Hour = a.get(Calendar.HOUR_OF_DAY);
		day = a.get(Calendar.DAY_OF_MONTH);
		Month2 = a.get(Calendar.MONTH);
	}
	public Time timeCopy(){
		Time a = new Time(this.Hour,this.Month,this.day);
		return a;
	}
	public int BeforeOrAfter(Time x){
		Calendar a = Calendar.getInstance();
		a.set(Calendar.MONTH,Month2);
		a.set(Calendar.DAY_OF_MONTH,day);
		a.set(Calendar.HOUR_OF_DAY,Hour);
		Calendar b = Calendar.getInstance();
		b.set(Calendar.MONTH,x.getMonth2());
		b.set(Calendar.DAY_OF_MONTH,x.getDay());
		b.set(Calendar.HOUR_OF_DAY,x.getHour());
		if(a.before(b)||Month2<x.getMonth2()) return 1;
		if(a.after(b)||Month2>x.getMonth2()) return 2;
		if(a.equals(b)||Month2==x.getMonth2()) return 0;
		return 0;
	}


	
	
}

