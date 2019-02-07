package com.nv.Utility;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;


public class Utility {
	
	public static String getOTP(){
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(1000000);
		String formatted = String.format("%6d", num); 
		return formatted;
	}
	
	public static String getResponce(List<String> key,List<String> value){
		JSONObject responce = new JSONObject();
	   try{
			Iterator<String> i1= key.iterator();
		    Iterator<String> i2= value.iterator();
			
		    while(i1.hasNext() && i2.hasNext()){
		    	responce.put(i1.next(), i2.next());
		    }
		    return responce.toString();
	   }catch(Exception e){
		   try {
			   		responce.put("Error", "Error while Creating responce: "+e.toString());
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	   }
		return "";
		
	}
	
	public static String getResponce(String key,String val){
		JSONObject responce = new JSONObject();
		try {
			responce.put(key, val);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responce.toString();
	}
	
	public static <T>String getJsonResponce(List<String> key,List<T> value){
		JSONObject responce = new JSONObject();
		try{
			Iterator<String> i1= key.iterator();
		    Iterator<T> i2= value.iterator();
			
		    while(i1.hasNext() && i2.hasNext()){
		    	responce.put(i1.next(), i2.next());
		    }
	   }catch(Exception e){
		   try {
			   		responce.put("Error", "Error while Creating responce: "+e.toString());
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	   }
		 return responce.toString();
	}
	
	
	public static Date getSqlDate(Date date){
		System.out.println("recived date:"+date);
		   SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		    String returnDate1 = formatter.format(date);
		    SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");  
		    Date returnDate=null;
			try {
				returnDate = formatter1.parse(returnDate1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    return returnDate;
	}
	
	
	
	public static Date getSqlDate(String date){
		System.out.println("recived date:"+date);
		   //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		   // String returnDate1 = formatter.format(date);
		   // System.out.println("formatted date::"+returnDate1);
		    SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");  
		    Date returnDate=null;
			try {
				returnDate = formatter1.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    return returnDate;
	}
	
	
	public static Date getSqlDate1(String date){
		System.out.println("recived date:"+date);
		   //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		   // String returnDate1 = formatter.format(date);
		   // System.out.println("formatted date::"+returnDate1);
		    SimpleDateFormat formatter1 = new SimpleDateFormat("ddMMyyyy");  
		    Date returnDate=null;
			try {
				returnDate = formatter1.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    return returnDate;
	}
	
	public static Date getTodayDate(){
		   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		    Date date = new Date();  
		    return date;
	}
	
	public static Boolean isFrequencyAvaliable(String dbFrequncy,String courseFrequency){
		String db[] = dbFrequncy.split(",");
		String rest[] = courseFrequency.split(",");
		System.out.println("dbfr::"+dbFrequncy);
		System.out.println("coursefr::"+courseFrequency);
		Boolean flag = true;
		for(int i=0;i<db.length;i++){
			for(int j=0;j<rest.length;j++){
				if(db[i].equalsIgnoreCase(rest[j])){
					flag=false;
					break;
				}
			}
		}
		return flag;
	}
	
	public static String getDifference(Date dbDate){
		String diff="";
		try{
			 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
			 String dbDate1 = formatter.format(dbDate);
			 String today = formatter.format(new Date());
			    Date firstDate = sdf.parse(dbDate1);
			    Date secondDate = sdf.parse(today);
			 
			    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
			     diff = ""+TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		  return diff;
	}
	
	public static Boolean isNull(Object object){
		if(object != null){
			return false;
		}else{
			return true;
		}
	}
	
	public static List<LocalDate> getDates
    (Date date1, Date date2,String day)
    {
		LocalDate start = Utility.convertToLocalDate(date1);
		LocalDate end = Utility.convertToLocalDate(date2);
	    List<LocalDate> result = new ArrayList<LocalDate>();
	    int dayNo = Utility.DayNo(day);
	    for (LocalDate date = start;
	         date.isBefore(end) || date.isEqual(end) ;
	         date = date.plusDays(1))
	    {
	        int dateDay = date.getDayOfWeek();
	        if (dateDay == dayNo)
	        {
	            result.add(date);
	        }
	    }
	    return result;
    }    
	
	private static int DayNo(String day){
		int number;
		if(day.equalsIgnoreCase("monday"))
			number=1;
		else if(day.equalsIgnoreCase("tuesday"))
			number =2;
		else if(day.equalsIgnoreCase("wednesday"))
			number =3;
		else if(day.equalsIgnoreCase("thrusday"))
			number =4;
		else if(day.equalsIgnoreCase("friday"))
			number =5;
		else if(day.equalsIgnoreCase("saturday"))
			number =6;
		else 
			number =7;
		return number;
	}
	
	private static LocalDate convertToLocalDate(Date date) {
        if(date == null) return null;
        return new LocalDate(date);
    }
	
	public static Date convertToUtilDate(LocalDate date){
		if(date == null) return null;
		return date.toDate();
	}
	
	
	public static Date getStartDate(Date startDate){
		if(startDate.before(Utility.getTodayDate())){
			return Utility.getTodayDate();
		}else{
			return startDate;
		}		
	}
}
