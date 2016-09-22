package com.lijinhuan.model;

import java.io.Serializable;

public class Test implements Serializable{

	private static final long serialVersionUID = 1505392271461621423L;
	private String id ;
	private String name;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
