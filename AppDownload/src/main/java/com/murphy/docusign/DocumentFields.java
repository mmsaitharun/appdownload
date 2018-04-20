package com.murphy.docusign;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DocumentFields {
	
	private String name;
	
	private String value;

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

	@Override
	public String toString() {
		return "DocumentFields [name=" + name + ", value=" + value + "]";
	}
	
	

}
