package com.sample.ruidas.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sample.ruidas.pojo.Account;

/**
 * @author Ujjal
 *
 */
public class ReadProperties {
	
	
	public static String COMMA = ",";
	public static String PIPE = "|";
	public static String BRACKETSTART = "{";
	public static String BRACKETEND = "}";
	public static String THIRDBRACKET = "]";
	public static String CUSTOMER = "customerDetails";
	
	
	
	public static String update(Account account){
		
		List<Account> custList = new ArrayList<Account>();
		Properties prop = loadProperties();
		Account acn = new Account();
		boolean canUpdate = false;
		
		String message = "";
		String arrayCustomers = prop.getProperty(CUSTOMER);
		String[] customers  = arrayCustomers.split(COMMA);
		
		
		 for (String string : customers) {
			 acn = new Account();
			 int start = string.indexOf(BRACKETSTART);
			 int end  = string.indexOf(BRACKETEND);
			 String details = string.substring(start+1, end);
			 String[] array =  details.split("\\"+PIPE);
			 acn.setCustomerName(array[0]);
			 acn.setCurrency(array[1]);
			 acn.setAccountBalance(Double.parseDouble(array[2]));
			 custList.add(acn);
			
		}
		 
		 StringBuffer formUpdateData = new StringBuffer();
		 StringBuffer inputData = new StringBuffer();
		 
		String cust = account.getCustomerName();
		if(cust != null){
		for (int i = 0; i < custList.size(); i++) {
			Account acnt = custList.get(i);
			if(acnt.getCustomerName().toUpperCase().equals(cust.toUpperCase())){
				canUpdate = true;
				formUpdateData = formUpdateData.append(acnt.getCustomerName()).append(PIPE).
						append(acnt.getCurrency()).append(PIPE).append(acnt.getAccountBalance());
				
			}
		}
		}
		
		
		
		if(canUpdate){
			
			inputData = inputData.append(account.getCustomerName()).append(PIPE).
					append(account.getCurrency()).append(PIPE).append(account.getAccountBalance());
			
			
			String  updatedForm = arrayCustomers.replace(formUpdateData, inputData);
			
			
			 Properties properties = new Properties();
			 properties.setProperty(CUSTOMER, updatedForm.toString());
			
			 
			 try {
					
				 File file = new File(ReadProperties.class.getClassLoader().getResource("dumpData.properties").getFile());
				 FileOutputStream fileOut = new FileOutputStream(file);
					properties.store(fileOut, "#Dummy Data ");
					fileOut.close();
					message = " Updated Successfully";
					
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else{
			message = "Customer Not Found with name ";
		}
		
		
		return message;
	}
	
	public static String write(Account account){
		
		List<Account> custList = new ArrayList<Account>();
		Properties prop = loadProperties();
		
		 String arrayCustomers = prop.getProperty(CUSTOMER);
		 
		 String[] customers  = arrayCustomers.split(COMMA);
		String message = "";
		 
		 int end = arrayCustomers.lastIndexOf(BRACKETEND);
		 String buf = arrayCustomers.substring(0, end+1);
		 
		 StringBuffer formData = new StringBuffer();
		if(account != null){
		 formData =  formData.append(buf).append(COMMA).append(BRACKETSTART).append(account.getCustomerName()).
				 append(PIPE).append(account.getCurrency()).append(PIPE).
				 append(account.getAccountBalance()).append(BRACKETEND).append(THIRDBRACKET);
		 
		}
		
		 Properties properties = new Properties();
		 properties.setProperty(CUSTOMER, formData.toString());
		 
			try {
				
		 File file = new File(ReadProperties.class.getClassLoader().getResource("dumpData.properties").getFile());
		 FileOutputStream fileOut = new FileOutputStream(file);
			properties.store(fileOut, "#Dummy Data ");
			fileOut.close();
			message = "Account Created with Name : ";
			
	} catch (FileNotFoundException e) {
		message = "File Not Found";
		e.printStackTrace();
	} catch (IOException e) {
		message = "Error Occurred";
		e.printStackTrace();
	}
	catch (Exception e) {
		message = "Error Occurred";
		e.printStackTrace();
	}
		
		return message;
		
	}
	
	public static Properties loadProperties(){
		
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
		 input = ReadProperties.class.getClassLoader().getResourceAsStream("/dumpData.properties");
		 
		 if(input != null) {
			 prop.load(input);
		 } 
		 
		} catch (FileNotFoundException fex) {
			fex.printStackTrace();
				 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return prop;
		
	}
	
public static List<Account> read(){
		
		Properties prop = loadProperties();
		List<Account> custList = new ArrayList<Account>();
		Account account = new Account();
			 String arrayCustomers = prop.getProperty(CUSTOMER);
			 
			 String[] customers  = arrayCustomers.split(COMMA);
			 
			 for (String string : customers) {
				 account = new Account();
				 int start = string.indexOf(BRACKETSTART);
				 int end  = string.indexOf(BRACKETEND);
				 String details = string.substring(start+1, end);
				 String[] array =  details.split("\\"+PIPE);
				 account.setCustomerName(array[0]);
				 account.setCurrency(array[1]);
				 account.setAccountBalance(Double.parseDouble(array[2]));
				 custList.add(account);
				 
				
				
			}
			 
		
		return custList;
		
	}

}
