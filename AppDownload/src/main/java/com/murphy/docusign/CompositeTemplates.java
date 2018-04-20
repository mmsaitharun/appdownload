package com.murphy.docusign;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompositeTemplates {
	
	private String compositeTemplateId;
	
	private InlineTemplates inlineTemplates;

	public String getCompositeTemplateId() {
		return compositeTemplateId;
	}

	public void setCompositeTemplateId(String compositeTemplateId) {
		this.compositeTemplateId = compositeTemplateId;
	}

	public InlineTemplates getInlineTemplates() {
		return inlineTemplates;
	}

	public void setInlineTemplates(InlineTemplates inlineTemplates) {
		this.inlineTemplates = inlineTemplates;
	}

	@Override
	public String toString() {
		return "CompositeTemplates [compositeTemplateId=" + compositeTemplateId + ", inlineTemplates=" + inlineTemplates
				+ "]";
	}
	
	

}
