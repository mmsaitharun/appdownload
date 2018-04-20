package com.murphy.docusign;

public class Signers {
	
	private String clientUserId;
	
	private String email;
	
	private String name;
	
	private String roleName;
	
	private String recipientId;
	
	private Tabs tabs;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public Tabs getTabs() {
		return tabs;
	}

	public void setTabs(Tabs tabs) {
		this.tabs = tabs;
	}

	@Override
	public String toString() {
		return "Signers [clientUserId=" + clientUserId + ", email=" + email + ", name=" + name + ", roleName="
				+ roleName + ", recipientId=" + recipientId + ", tabs=" + tabs + "]";
	}
	
	

}
