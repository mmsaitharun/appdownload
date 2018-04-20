package com.murphy.docusign;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Recipients {
	
	
	private List<Signers> signers;

	public List<Signers> getSigners() {
		return signers;
	}

	public void setSigners(List<Signers> signers) {
		this.signers = signers;
	}

	@Override
	public String toString() {
		return "Recipients [signers=" + signers + "]";
	}
	
	

}
