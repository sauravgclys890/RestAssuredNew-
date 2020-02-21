package com.restapi.genricLib;

public class Utility {
	
	public static String getScreenshot()
	{
		
		String path=System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";
		
		
		return path;
	}

}
