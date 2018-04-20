package com.murphy.docusign;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class SignHereTabs {
	
	private String stampType;
	
	private String name;
	
	private String tabLabel;
	
	private String anchorString;
	
	private String anchorIgnoreIfNotPresent;
	
	private String anchorXOffset;
	
	private String anchorYOffset;
	
	private String anchorUnits;
	
	private String anchorCaseSensitive;
	
	private String anchorMatchWholeWord;
	
	private String anchorHorizontalAlignment;
	
	private String tabId;
	
	private String tabType;

	public String getStampType() {
		return stampType;
	}

	public void setStampType(String stampType) {
		this.stampType = stampType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTabLabel() {
		return tabLabel;
	}

	public void setTabLabel(String tabLabel) {
		this.tabLabel = tabLabel;
	}

	public String getAnchorString() {
		return anchorString;
	}

	public void setAnchorString(String anchorString) {
		this.anchorString = anchorString;
	}

	public String getAnchorIgnoreIfNotPresent() {
		return anchorIgnoreIfNotPresent;
	}

	public void setAnchorIgnoreIfNotPresent(String anchorIgnoreIfNotPresent) {
		this.anchorIgnoreIfNotPresent = anchorIgnoreIfNotPresent;
	}

	public String getAnchorXOffset() {
		return anchorXOffset;
	}

	public void setAnchorXOffset(String anchorXOffset) {
		this.anchorXOffset = anchorXOffset;
	}

	public String getAnchorYOffset() {
		return anchorYOffset;
	}

	public void setAnchorYOffset(String anchorYOffset) {
		this.anchorYOffset = anchorYOffset;
	}

	public String getAnchorUnits() {
		return anchorUnits;
	}

	public void setAnchorUnits(String anchorUnits) {
		this.anchorUnits = anchorUnits;
	}

	public String getAnchorCaseSensitive() {
		return anchorCaseSensitive;
	}

	public void setAnchorCaseSensitive(String anchorCaseSensitive) {
		this.anchorCaseSensitive = anchorCaseSensitive;
	}

	public String getAnchorMatchWholeWord() {
		return anchorMatchWholeWord;
	}

	public void setAnchorMatchWholeWord(String anchorMatchWholeWord) {
		this.anchorMatchWholeWord = anchorMatchWholeWord;
	}

	public String getAnchorHorizontalAlignment() {
		return anchorHorizontalAlignment;
	}

	public void setAnchorHorizontalAlignment(String anchorHorizontalAlignment) {
		this.anchorHorizontalAlignment = anchorHorizontalAlignment;
	}

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	public String getTabType() {
		return tabType;
	}

	public void setTabType(String tabType) {
		this.tabType = tabType;
	}

	@Override
	public String toString() {
		return "SignHereTabs [stampType=" + stampType + ", name=" + name + ", tabLabel=" + tabLabel + ", anchorString="
				+ anchorString + ", anchorIgnoreIfNotPresent=" + anchorIgnoreIfNotPresent + ", anchorXOffset="
				+ anchorXOffset + ", anchorYOffset=" + anchorYOffset + ", anchorUnits=" + anchorUnits
				+ ", anchorCaseSensitive=" + anchorCaseSensitive + ", anchorMatchWholeWord=" + anchorMatchWholeWord
				+ ", anchorHorizontalAlignment=" + anchorHorizontalAlignment + ", tabId=" + tabId + ", tabType="
				+ tabType + "]";
	}
	
	
	

}
