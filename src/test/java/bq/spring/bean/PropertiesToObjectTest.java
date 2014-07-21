package bq.spring.bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Test;
import org.springframework.beans.BeanWrapperImpl;

public class PropertiesToObjectTest {

	@Test
	public void testPropertiesfile2Bean(){
		Properties props = new Properties();

		InputStream inStream = null;
		try {
			inStream = getClass().getClassLoader().getResourceAsStream("bq/spring/bean/open-api-transform.properties");
			props.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		OpenAPIConfigureBean bean = new OpenAPIConfigureBean();
		
		BeanWrapperImpl bw = new BeanWrapperImpl(bean);
		bw.setAutoGrowNestedPaths(true);
		
		Set<Entry<Object, Object>> entrySet = props.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			bw.setPropertyValue(entry.getKey().toString(), entry.getValue());
		}
		
		System.out.println(bean.getList().get(0).getUrl());
	}
	
}
