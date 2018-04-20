package oneapp.demo.dft.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.murphy.dft.util.AppUtils;

/**
 * @author INC00718
 *
 */
@Path("/tapp")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class DownloadRest {
	
	@GET
	@Path("/download")
	public DownloadResponse getDownloadData(@QueryParam("fileType") String fileType) {
		fileType = fileType.toUpperCase();
		DownloadResponse response = new DownloadResponse();
		if(!AppUtils.isEmpty(fileType)) {
			response.setUrl(AppUtils.getFileUrl(fileType));
			response.setVersion(AppUtils.getVersionMap().get(fileType));
		}
		return response;
	}
	
	@GET
	@Path("/checkConnection")
	public String checkConnection() {
		return (AppUtils.getFileUrl("APK"));
	}
	
	@GET
	@Path("/checkConnection2")
	public String checkConnection2() {
		return (AppUtils.getFileUrl2("APK"));
	}
}
