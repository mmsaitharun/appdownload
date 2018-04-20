package com.murphy.dft.qa.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.murphy.imageprocessing.ImageDto;
import com.murphy.imageprocessing.ImageProcessing;
import com.murphy.imageprocessing.Images;

@Path("/images")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ImageProcessingRest {
	
	
   @POST
   @Path("/concat")
   public ImageDto addImages(Images images) {
    	System.err.println("Image Processing started");
    	return new ImageProcessing().concatImages(images);
    }

}
