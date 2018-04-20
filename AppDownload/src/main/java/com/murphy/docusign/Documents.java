package com.murphy.docusign;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Documents {
	
	private String documentId;
	
	private String name;
	
	private String documentBase64;
	
	private DocumentFields documentFields;

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocumentBase64() {
		return documentBase64;
	}

	public void setDocumentBase64(String documentBase64) {
		this.documentBase64 = documentBase64;
	}

	public DocumentFields getDocumentFields() {
		return documentFields;
	}

	public void setDocumentFields(DocumentFields documentFields) {
		this.documentFields = documentFields;
	}

	@Override
	public String toString() {
		return "Documents [documentId=" + documentId + ", name=" + name + ", documentBase64=" + documentBase64
				+ ", documentFields=" + documentFields + "]";
	}
	
	

}
