package com.ocbcmcd.config.test;

public class DataTest {
	private String smptpHost;
	
	public DataTest(String smptpHost) {
		super();
		this.smptpHost = smptpHost;
		System.out.println("smptpHost: " + smptpHost);
	}

	public String getSmptpHost() {
		return smptpHost;
	}

	public void setSmptpHost(String smptpHost) {
		this.smptpHost = smptpHost;
	}
	
	
}
