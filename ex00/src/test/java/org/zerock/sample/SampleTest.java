package org.zerock.sample;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//단위테스트 도구
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTest {
  
	@Setter(onMethod_=@Autowired)
	private Restaurant restaurant;//= new Restaurant();
	
	//@Ignore
	@Test//@Test는 Junit테스트시 마다 실행. 
	public void testExist() {
		assertNotNull(restaurant);//객체 주입이 정상이면 true
 
	  log.info(restaurant);
	  log.info("----------------------------------");
	  log.info(restaurant.getChef());
	}
}
