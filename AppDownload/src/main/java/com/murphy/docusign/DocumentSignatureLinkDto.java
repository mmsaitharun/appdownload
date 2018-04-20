package com.murphy.docusign;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class DocumentSignatureLinkDto {
	
	private String clientUserId;
	
	private String email;
	
	private String userName;
	
	private String recipientId;
	
	private String returnUrl;
	
	private String authenticationMethod;
	
	private String authenticationInstant;
	
	private String securityDomain;
	
	private String envelopeId;

	public String getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getAuthenticationMethod() {
		return authenticationMethod;
	}

	public void setAuthenticationMethod(String authenticationMethod) {
		this.authenticationMethod = authenticationMethod;
	}

	public String getAuthenticationInstant() {
		return authenticationInstant;
	}

	public void setAuthenticationInstant(String authenticationInstant) {
		this.authenticationInstant = authenticationInstant;
	}

	public String getSecurityDomain() {
		return securityDomain;
	}

	public void setSecurityDomain(String securityDomain) {
		this.securityDomain = securityDomain;
	}

	public String getEnvelopeId() {
		return envelopeId;
	}

	public void setEnvelopeId(String envelopeId) {
		this.envelopeId = envelopeId;
	}

	@Override
	public String toString() {
		return "DocumentSignatureLinkDto [clientUserId=" + clientUserId + ", email=" + email + ", userName=" + userName
				+ ", recipientId=" + recipientId + ", returnUrl=" + returnUrl + ", authenticationMethod="
				+ authenticationMethod + ", authenticationInstant=" + authenticationInstant + ", securityDomain="
				+ securityDomain + ", envelopeId=" + envelopeId + "]";
	}
	
	

}
