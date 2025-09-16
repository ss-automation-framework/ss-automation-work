package com.qa.opencart.utils;

public class StringUtils {
	
	public static String getRandomEmail() {
		return "uiautomation"+ System.currentTimeMillis()+"@open.com";
	}

	//can use this query to fetch any user details: select * from user where email like 'uiautomation%'
}
