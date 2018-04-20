package com.murphy.docusign;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DocuSignDocumentDto {

	private String emailSubject;
	
	private String status;
	
	private String enableWetSign;
	
	private CompositeTemplates compositeTemplates;

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEnableWetSign() {
		return enableWetSign;
	}

	public void setEnableWetSign(String enableWetSign) {
		this.enableWetSign = enableWetSign;
	}

	public CompositeTemplates getCompositeTemplates() {
		return compositeTemplates;
	}

	public void setCompositeTemplates(CompositeTemplates compositeTemplates) {
		this.compositeTemplates = compositeTemplates;
	}

	@Override
	public String toString() {
		return "DocuSignDocumentDto [emailSubject=" + emailSubject + ", status=" + status + ", enableWetSign="
				+ enableWetSign + ", compositeTemplates=" + compositeTemplates + "]";
	}
	
	
	
}
