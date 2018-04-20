package com.murphy.docusign;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InlineTemplates {

	private String sequence;
	
	private Recipients recipients;
	
	private Documents documents;

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public Recipients getRecipients() {
		return recipients;
	}

	public void setRecipients(Recipients recipients) {
		this.recipients = recipients;
	}

	public Documents getDocuments() {
		return documents;
	}

	public void setDocuments(Documents documents) {
		this.documents = documents;
	}

	@Override
	public String toString() {
		return "InlineTemplates [sequence=" + sequence + ", recipients=" + recipients + ", documents=" + documents
				+ "]";
	}
	
	
}
