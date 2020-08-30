package com.android.vishal.demoproject.comparator;

import com.android.vishal.demoproject.model.CustomerModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<CustomerModel> {

    @Override
    public int compare(CustomerModel o1, CustomerModel o2) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String arg0 = o1.getDate().replace("/","-");
        String arg1 = o2.getDate().replace("/","-");
        int compareResult = 0;

        try {
            Date arg0Date = format.parse(arg0);
            Date arg1Date = format.parse(arg1);
            compareResult = arg0Date.compareTo(arg1Date);
        } catch (ParseException e) {
            e.printStackTrace();
            compareResult = arg0.compareTo(arg1);
        }

        return compareResult;
    }

}
