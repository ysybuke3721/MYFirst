package com.slr.slrapp.beans;

import java.util.ArrayList;

public class China {
    public ArrayList<Province> citylist;

   public class Province {
        public ArrayList<Area> c	;
        public String p;

       public  class Area{
           public ArrayList<Street> a;
            public String n;
           public class Street{
               public String s;
           }
        }
    }
}
