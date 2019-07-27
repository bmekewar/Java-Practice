package com.bvm.main;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ChMapCheck {

	public static void main(String[] args) {
		ConcurrentHashMap<String, Object> scenarioStore = new ConcurrentHashMap<String, Object>();
		scenarioStore.put("abcd", new Integer("12"));
		scenarioStore.put("abcd1", new Integer("11"));
		scenarioStore.put("abcd2", new Integer("19"));
		scenarioStore.put("abcd3", new Integer("22"));
		
		System.out.println("CH : "+scenarioStore.toString());

		scenarioStore.put("abcd1", new Integer("03"));
		System.out.println("CH : "+scenarioStore.toString());
		
		scenarioStore.put("abcd3", new Integer("06"));
		System.out.println("CH : "+scenarioStore.toString());
		
		String o = null;
		String s = new String();
		o = s;
		
		if((o != null) && !(o.isEmpty()))
			System.out.println("String = "+o);
		
		//InputStream stream = new ByteArrayInputStream(null);
		
/*		String path = "/CSVProperties/Recordset/RowId";
		List<String> mappedCSVHeaders = new ArrayList<String>();
		String[] sr = path.split("/");
		if(sr !=null && sr.length >2){
			if(sr[sr.length-1].equals("RowId"))
				System.out.println(sr[sr.length-1]);
			else 
				mappedCSVHeaders.add(sr[sr.length-1]);
		}
		System.out.println(mappedCSVHeaders.toString());*/
		
		
		////////////// 
		
        List<CsvProperty> mappedCsvHeaders = new ArrayList<CsvProperty>();
        mappedCsvHeaders.add(new CsvProperty("Ram"));
        mappedCsvHeaders.add(new CsvProperty("Hariom"));
        mappedCsvHeaders.add(new CsvProperty("Shiv"));
//      list1.add(new CsvProperty("Shiv", false));
        List<CsvProperty> uploadedCsvHeaders = new ArrayList<CsvProperty>();
        //uploadedCsvHeaders.add(new CsvProperty("Ram"));
       // uploadedCsvHeaders.add(new CsvProperty("Shiv"));
        //uploadedCsvHeaders.add(new CsvProperty("Hariom"));
        uploadedCsvHeaders.add(new CsvProperty("header1"));
        uploadedCsvHeaders.add(new CsvProperty("header2"));
        uploadedCsvHeaders.add(new CsvProperty("header3"));
        uploadedCsvHeaders.add(new CsvProperty("header4"));
        boolean valid = false;
        if(uploadedCsvHeaders!= null && (uploadedCsvHeaders.size()>=mappedCsvHeaders.size())){
        	valid = true;
            for (int i=0; i <uploadedCsvHeaders.size();i++) {
            	if(i < mappedCsvHeaders.size() ){
	            	if(!(mappedCsvHeaders.get(i).equals(uploadedCsvHeaders.get(i)))){
	            		valid = false;
	            		//System.out.println("is Equal"+valid);
	            		break;
	            	} 
            	} else {
	            	System.out.println("Validation Completed!");
	            	break;
            	}
            	
            }
        }
		System.out.println("is valid"+valid);
		
	}

}
