package com.lsc.mvc.model;

import java.util.ArrayList;

public class Facility {
	
	// Class Variables
	
	// --------------------------------------------------
	
		protected String facilityNumber;
		protected String facilityType;
		protected String facilityName;
		protected String facilityDescription;
		protected Integer capacity;
		
		public String getFacilityNumber() { return facilityNumber; }
		public String getFacilityType() { return facilityType; }
		public String getFacilityName() { return facilityName; }
		public String getFacilityDescription() { return facilityDescription; }
		public Integer getCapacity() { return capacity; }
		
	// --------------------------------------------------
		
	public static String validateFacilityNumber(String facNum) {
		// Dummy Code: return no error
		// If there is any error at any check, the error will be appended to err
		
		// Example Implementation: 
		// if(validateFacilityNumber(facNum)!= "") { ... } else { ... }
		
		String err = "";
		
		// Checks that no blanks
		
		// Checks that facNum matches pattern F###
		
		return err;
	}
	public static ArrayList<String> getFacilityTypes() {
		// Dummy code: returns hard-coded arraylist of facility types
		
		// Example Implementation:
		// ArrayList<String> fTypeList = getFacilityTypes();
		
		// Initialize ArrayList<String>
		ArrayList<String> fTypeList = new ArrayList<String>();
		
		// Gets all unique facilityTypes from database
		// and adds to arraylist to return
		
		fTypeList.add("Conference Room");
		fTypeList.add("Meeting Room");
		fTypeList.add("Karaoke Room");
		fTypeList.add("Function Room");
		fTypeList.add("Badminton Court");
		fTypeList.add("Squash Court");
		fTypeList.add("Tennis Court");
		fTypeList.add("Basketball Court");
		
		return fTypeList;
	}
	public static ArrayList<Facility> getFacilityListByType(String facType) {
		// Dummy code: 4 Facility objects hard-coded and added to 1 ArrayList<Facility>, 
		// then returned
		
		// Example Implementation: 
		// ArrayList<Facility> fList = getFacilityListByType(facType);
		
		// Initialize ArrayList<Facility>
		ArrayList<Facility> fList = new ArrayList<Facility>();
		
		// Validate facType

			// Gets Facility objects based on facType
			// and add Facility objects to ArrayList<Facility>
		
		Facility f1 = new Facility();
		f1.facilityNumber = "F01";
		f1.facilityType = "Conference Room";
		f1.facilityName = "Platinum Room";
		f1.facilityDescription = "This is a sample description that is relatively long.";
		f1.capacity = 40;
		fList.add(f1);
		
		Facility f2 = new Facility();
		f2.facilityNumber = "F02";
		f2.facilityType = "Conference Room";
		f2.facilityName = "Sapphire Room";
		f2.facilityDescription = "This is a sample description that is relatively long.";
		f2.capacity = 20;
		fList.add(f2);
		
		Facility f3 = new Facility();
		f3.facilityNumber = "F03";
		f3.facilityType = "Tennis Court";
		f3.facilityName = "Tennis Court 1";
		f3.facilityDescription = "This is a sample description that is relatively long.";
		f3.capacity = 4;
		fList.add(f3);
		
		Facility f4 = new Facility();
		f4.facilityNumber = "F04";
		f4.facilityType = "Badminton Court";
		f4.facilityName = "Badminton Court 1";
		f4.facilityDescription = "This is a sample description that is relatively long.";
		f4.capacity = 4;
		fList.add(f4);
		
		return fList;
	}
	public static ArrayList<Facility> getFacilityListByNumber(String facNum) {
		// Dummy code: 1 Facility object hard-coded and added to 1 ArrayList<Facility>, 
		// then returned
		
		// Example Implementation: 
		// ArrayList<Facility> fList = getFacilityListByNumber(facNum);
		
		// Initialize ArrayList<Facility>
		ArrayList<Facility> fList = new ArrayList<Facility>();
		
		// Validate facType

			// Gets Facility object based on facNum
			// and add Facility object to ArrayList<Facility>
		
		Facility f4 = new Facility();
		f4.facilityNumber = "F04";
		f4.facilityType = "Badminton Court";
		f4.facilityName = "Badminton Court 1";
		f4.facilityDescription = "This is a sample description that is relatively long.";
		f4.capacity = 4;
		fList.add(f4);
		
		return fList;
	}
	public static ArrayList<Facility> getFacilityListByName(String facName){
		// Dummy code: 4 Facility objects hard-coded and added to 1 ArrayList<Facility>, 
		// then returned
		
		// Example Implementation: 
		// ArrayList<Facility> fList = getFacilityListByName(facName);
		
		// Initialize ArrayList<Facility>
		ArrayList<Facility> fList = new ArrayList<Facility>();
		
		// Validate facType

			// Gets Facility objects based on facName
			// and add Facility objects to ArrayList<Facility>
		
		Facility f1 = new Facility();
		f1.facilityNumber = "F01";
		f1.facilityType = "Conference Room";
		f1.facilityName = "Platinum Room";
		f1.facilityDescription = "This is a sample description that is relatively long.";
		f1.capacity = 40;
		fList.add(f1);
		
		Facility f2 = new Facility();
		f2.facilityNumber = "F02";
		f2.facilityType = "Conference Room";
		f2.facilityName = "Sapphire Room";
		f2.facilityDescription = "This is a sample description that is relatively long.";
		f2.capacity = 20;
		fList.add(f2);
		
		Facility f3 = new Facility();
		f3.facilityNumber = "F05";
		f3.facilityType = "Meeting Room";
		f3.facilityName = "Opal Room";
		f3.facilityDescription = "This is a sample description that is relatively long.";
		f3.capacity = 12;
		fList.add(f3);
		
		Facility f4 = new Facility();
		f4.facilityNumber = "F04";
		f4.facilityType = "Function Room";
		f4.facilityName = "Diamond Room";
		f4.facilityDescription = "This is a sample description that is relatively long.";
		f4.capacity = 30;
		fList.add(f4);
		
		return fList;
	}
	public static Facility getFacility(String facNum) {
		// Dummy code: 1 Facility object hard-coded and returned
		
		// Example Implementation: 
		// Facility f = getFacility(facNum);
		
		// Check that no blanks
		
		// Validate facNum
		
		// Get Facility object based on facNum
		
		Facility f = new Facility();
		f.facilityNumber = "F04";
		f.facilityType = "Badminton Court";
		f.facilityName = "Badminton Court 1";
		f.facilityDescription = "This is a sample description that is relatively long.";
		f.capacity = 4;
		
		return f;
	}
	public static String updateFacility
		(String facNum, String facType, String facName,
	     String facDesc, Integer cap) {
		// Dummy code: return success
		// If there is any error at any point, the error will be appended to err
		
		// Example Implementation: 
		// if(updateFacility
		//		(facNum, facType, facName,
		//	    facDesc, cap) != "") { ... } else { ... }
		
		String err = "";
		
		// Checks that all required fields provided
		
		// Validate facNum
		
		// Validate facType
		
		// Validate cap
		
		return err;
		
	}
	public static String addFacility
	    (String facType, String facName,
	     String facDesc, Integer cap) {
		// Dummy code: return success
		// If there is any error at any point, the error will be appended to err
		
		// Example Implementation: 
		// if(addFacility
		//		(facType, facName,
		//	    facDesc, cap) != "") { ... } else { ... }
		
		String err = "";
		
		// Checks that all required fields provided
		
		// Validate facType
		
		// Validate cap
		
		return err;
		
	}
	public static String removeFacility(String facNum) {
		// Dummy code: return success
		// If there is any error at any point, the error will be appended to err
		
		// Example Implementation: 
		// if(removeFacility(facNum) != "") { ... } else { ... }
		
		String err = "";
		
		// Validate facNum
		
		// Checks that facNum exists in database
		
		// Find Facility object
		
		// Remove Facility object
		
		return err;
	}
}
