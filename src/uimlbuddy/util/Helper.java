package uimlbuddy.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import uimlbuddy.model.Constant;
import uimlbuddy.model.Property;

/**
 *
 * @author Kundan
 */
public class Helper 
{
    private static  List<Property> property=new ArrayList<Property>();
    private static HashMap<String,Constant> constant=new HashMap<String, Constant>();
     
    public static void storePropety(ArrayList<Property> alist)
    {      
       property=alist;
        System.out.println("porp********* size "+property.size());
    }
    public static void convertConstantListToMap(ArrayList<Constant> alist)
    {     
        System.out.println("cons################ size "+alist.size());
        for(int i=0;i<alist.size();i++)
        {
            Constant cons=alist.get(i);
           constant.put(cons.getId(),cons);
        }
    }
    
    public static List<Property> getProperty(String propID) {
        List<Property> resp=new ArrayList<>();
        Iterator<Property> itr=property.iterator();
        while(itr.hasNext())
        {
            Property pr=itr.next();
            if(pr.getId().equals(propID))
                resp.add(pr);
        }
        return resp;
    }

    public static HashMap<String, Constant> getConstant() {
        return constant;
    }    
}
