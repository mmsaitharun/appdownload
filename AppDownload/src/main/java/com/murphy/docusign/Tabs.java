package com.murphy.docusign;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Tabs {
	
	private List<SignHereTabs> signHereTabs;
	
	private List<InitialHereTabs> initialHereTabs;
	
	private List<FullNameTabs> fullNameTabs;
	
	private List<DateSignedTabs> dateSignedTabs;

	public List<SignHereTabs> getSignHereTabs() {
		return signHereTabs;
	}

	public void setSignHereTabs(List<SignHereTabs> signHereTabs) {
		this.signHereTabs = signHereTabs;
	}

	public List<InitialHereTabs> getInitialHereTabs() {
		return initialHereTabs;
	}

	public void setInitialHereTabs(List<InitialHereTabs> initialHereTabs) {
		this.initialHereTabs = initialHereTabs;
	}

	public List<FullNameTabs> getFullNameTabs() {
		return fullNameTabs;
	}

	public void setFullNameTabs(List<FullNameTabs> fullNameTabs) {
		this.fullNameTabs = fullNameTabs;
	}

	public List<DateSignedTabs> getDateSignedTabs() {
		return dateSignedTabs;
	}

	public void setDateSignedTabs(List<DateSignedTabs> dateSignedTabs) {
		this.dateSignedTabs = dateSignedTabs;
	}

	@Override
	public String toString() {
		return "Tabs [signHereTabs=" + signHereTabs + ", initialHereTabs=" + initialHereTabs + ", fullNameTabs="
				+ fullNameTabs + ", dateSignedTabs=" + dateSignedTabs + "]";
	}
	
	

}
