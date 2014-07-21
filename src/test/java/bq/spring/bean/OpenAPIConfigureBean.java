package bq.spring.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenAPIConfigureBean {

	private Map<String, URLTransfer> map = new HashMap<>();
	
	private List<URLTransfer> list = new ArrayList<>();

	public Map<String, URLTransfer> getMap() {
		return map;
	}

	public void setMap(Map<String, URLTransfer> map) {
		this.map = map;
	}

	public List<URLTransfer> getList() {
		return list;
	}

	public void setList(List<URLTransfer> list) {
		this.list = list;
	}

}
