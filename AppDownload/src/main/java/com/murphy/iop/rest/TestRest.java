/**
 * 
 */
package com.murphy.iop.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author INC00718
 *
 */

@Path("/test")
public class TestRest {
	
	@GET
	@Path("/t")
	@Produces(MediaType.APPLICATION_JSON)
	public String test() {
		return "Working";
	}

}
