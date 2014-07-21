package bq.spring.bean;

public class URLTransfer {
	
	private String url;
	
	private String protocal;

	private String openapiURL;

	public String getOpenapiURL() {
		return openapiURL;
	}

	public void setOpenapiURL(String openapiURL) {
		this.openapiURL = openapiURL;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProtocal() {
		return protocal;
	}

	public void setProtocal(String protocal) {
		this.protocal = protocal;
	}
	
}
