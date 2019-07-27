package com.bvm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class delete {

	public static void main(String[] args) {
	    List<TemplateInfo> l = new ArrayList<TemplateInfo>();
	    TemplateInfo template =null;
	    for (int i=0; i < 10; ++i) {
	    	template = new TemplateInfo();
	    	template.setObjectId("abcd"+i);
	        l.add(template);
	    }
	    
	    int index = 0;
	    TemplateInfo selectedTemplateInfo = new TemplateInfo("abcd3");
	    for (Iterator<TemplateInfo> iterator = l.iterator();  iterator.hasNext(); index++)
		{
			TemplateInfo local = iterator.next();
			if (local.equals(selectedTemplateInfo))
		    {
				TemplateInfo temp = new TemplateInfo();
				temp.setObjectId("Bala");
				l.set(index, temp);
		    }
			
			
		}
	    
		
		//l.remove(3);

	    System.out.println(l);
	}

}
