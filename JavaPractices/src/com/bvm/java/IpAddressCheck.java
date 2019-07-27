package com.bvm.java;
import java.io.*;

public class IpAddressCheck 
{
	public static void main(String args[])throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter IP Address:");
		String ipAddress = (String) bf.readLine();
		
		try
		{
			if ( ipAddress.matches( "^.[0-9]{1,3}/..[0-9]{1,3}/..[0-9]{1,3}/..[0-9]{1,3}" ) == false )
			{
				throw new Exception( "IP Address is an invalid format!  Must be: [0-255].[0-255].[0-255].[0-255]" );
			}	
		}
		catch (Exception e)
		{
			System.out.println("Exception::\n"+e.getMessage());
		}
	}
}