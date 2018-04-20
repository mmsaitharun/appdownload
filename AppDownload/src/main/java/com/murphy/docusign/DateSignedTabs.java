package com.murphy.docusign;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class DateSignedTabs {
	
	private String name;
	
	private String value;
	
	private String tabLabel;
	
	private String fontSize;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTabLabel() {
		return tabLabel;
	}

	public void setTabLabel(String tabLabel) {
		this.tabLabel = tabLabel;
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
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
		return "DateSignedTabs [name=" + name + ", value=" + value + ", tabLabel=" + tabLabel + ", fontSize=" + fontSize
				+ ", anchorString=" + anchorString + ", anchorIgnoreIfNotPresent=" + anchorIgnoreIfNotPresent
				+ ", anchorXOffset=" + anchorXOffset + ", anchorYOffset=" + anchorYOffset + ", anchorUnits="
				+ anchorUnits + ", anchorCaseSensitive=" + anchorCaseSensitive + ", anchorMatchWholeWord="
				+ anchorMatchWholeWord + ", anchorHorizontalAlignment=" + anchorHorizontalAlignment + ", tabId=" + tabId
				+ ", tabType=" + tabType + "]";
	}
	
	

}
