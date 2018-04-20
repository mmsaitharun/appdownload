/**
 * 
 */
package com.murphy.downtime.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author INC00718
 *
 */

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestRest {
	
	@GET
	@Path("/t")
	public String test() {
		return "Working";
	}

}
