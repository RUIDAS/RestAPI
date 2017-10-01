package com.sample.ruidas.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Properties;


import com.sample.ruidas.pojo.Account;
import com.sample.ruidas.util.ReadProperties;

/**
 * @author Ujjal
 *
 */
@Path("/v1/accounts/account")
public class AccountRestService {
	
	public static String DUMP_DATA = "customerDetails";

	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{account_id}")
	public Response getAccount(@PathParam("account_id") String accountId) {
 
		String output = "Hi : " + accountId;
		return Response.status(200).entity(output).build();
 
	}
	
	
	@GET
	@Path("/getList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> listAllAccounts() {
		List<Account> accounts = ReadProperties.read();
		return accounts;
	}
	
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response createAccount(Account account) {
 
		String message  = ReadProperties.write(account);
		String output = ""+message+" " + account.getCustomerName();
		return Response.status(200).entity(output).build();
 
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateAccount(Account account) {
 
		String message   = ReadProperties.update(account);
		String output = ""+message +" : " + account.getCustomerName();
		return Response.status(200).entity(output).build();
 
	}
	
	
}