package com.murphy.iop.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.app.util.RestUtil;
import com.murphy.dft.util.AppUtils;

/**
 * @author INC00718
 *
 */
@Path("/app")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class IOPDownloadRest {
	
	@GET
	@Path("/download")
	public String getDownloadData(@QueryParam("fileType") String fileType) {
		fileType = fileType.toUpperCase();
		String response = "";
//		IOPDownloadResponse response = new IOPDownloadResponse();
		if(!AppUtils.isEmpty(fileType)) {
//			response.setUrl(IOPAppUtils.getFileUrl(fileType));
//			response.setVersion(IOPAppUtils.getVersionMap().get(fileType));
			response = RestUtil.callRest("https://a65dee8964f1.us2.hana.ondemand.com/WORKBOX/GetFromAPP_FILE_IOP.xsjs?fileType='"+fileType+"'");
		}
		return response;
//		return response;
	}
}
