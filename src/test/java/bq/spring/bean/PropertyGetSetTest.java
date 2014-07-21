package bq.spring.bean;

import org.junit.Test;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;

public class PropertyGetSetTest {

	@Test
	public void test(){
		OpenAPIConfigureBean bean = new OpenAPIConfigureBean();
		
		BeanWrapperImpl bw = new BeanWrapperImpl(bean);
		bw.setAutoGrowNestedPaths(true);
		
		bw.setPropertyValue("map['key1']", new URLTransfer());
		bw.setPropertyValue("map['key1'].url", "key1");
		bw.setPropertyValue("map['key1'].openapiURL", "url1");
		bw.setPropertyValue("map['key1'].protocal", "protocal1");
		System.out.println(bean.getMap().get("key1").getOpenapiURL());
		
		MutablePropertyValues props =  new MutablePropertyValues();
		props.addPropertyValue("map[key2].url", "key2");
		props.addPropertyValue("map[key2].openapiURL", "url2");
		props.addPropertyValue("map[key2].protocal", "protocal2");
		bw.setPropertyValues(props);
		System.out.println(bean.getMap().get("key2").getOpenapiURL());
		
		bw.setPropertyValue("list[0]", new URLTransfer());
		bw.setPropertyValue("list[1]", new URLTransfer());
		bw.setPropertyValue("list[2].url", "key3");
		bw.setPropertyValue("list[3].openapiURL", "url3");
		bw.setPropertyValue("list[4].protocal", "protocal3");
		System.out.println(bean.getList().get(2).getUrl());
	}
	
}
