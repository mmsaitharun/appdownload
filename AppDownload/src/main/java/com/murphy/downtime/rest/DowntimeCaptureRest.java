package com.murphy.downtime.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.murphy.downtime.dto.DowntimeCaptureDto;
import com.murphy.downtime.dto.DowntimeCaptureFetchResponseDto;
import com.murphy.downtime.dto.ResponseMessage;
import com.murphy.downtime.service.DowntimeCapture;
import com.murphy.downtime.service.DowntimeCaptureLocal;

@Path("/downtimeCapture")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class DowntimeCaptureRest {
	

	DowntimeCaptureLocal downtimeCaptureLocal;
	
	@POST
	@Path("/updateDowntimeCapture")
	public ResponseMessage createTaskFromTemplate(DowntimeCaptureDto downtimeCaptureDto) {
		downtimeCaptureLocal = new DowntimeCapture();
		return downtimeCaptureLocal.insertOrUpdateCounts(downtimeCaptureDto);
	}

	@GET
	@Path("/getDowntimeCapture")
	public DowntimeCaptureFetchResponseDto fetchDowntimeCapture(@QueryParam("originalDateEntered") String originalDateEntered, @QueryParam("uwiId") String uwiId) {
		downtimeCaptureLocal = new DowntimeCapture();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date originalDate = null;
		DowntimeCaptureFetchResponseDto responseDto = null;
		try {
			originalDate = df.parse(originalDateEntered);
			responseDto = downtimeCaptureLocal.fetchRecordForProvidedUwiIdAndDate(originalDate, uwiId);
		} catch (ParseException e) {
			System.err.println("Exception : "+e.getMessage());
		}
		// LOGGER.info("Inside delivery data creation");
		return responseDto;
	}
	
	public static void main(String[] args) throws ParseException {
		String d = "2018-04-20T00:00:00";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date date = df.parse(d);
		System.out.println(date);
	}

}
