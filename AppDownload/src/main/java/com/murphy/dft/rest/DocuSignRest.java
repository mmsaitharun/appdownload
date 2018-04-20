package com.murphy.dft.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import com.murphy.docusign.DocuSignAPITrigger;
import com.murphy.docusign.DocuSignDocumentDto;
import com.murphy.docusign.DocumentSignatureLinkDto;

@Path("/docusign")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class DocuSignRest {
	
	
   @POST
   @Path("/document/send")
   public JSONObject docusignTrigger(DocuSignDocumentDto inputDto) {
    	
    	return new DocuSignAPITrigger().docusignTrigger(inputDto);
    }
   
   @POST
   @Path("/document/sign")
   public JSONObject getDocuSignUrl(DocumentSignatureLinkDto inputDto) {
    	
    	return new DocuSignAPITrigger().getDocuSignUrl(inputDto);
    }

}
