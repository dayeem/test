package com.concretepage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.concretepage.config.AppConfig;
import com.concretepage.dao.IPersonDao;
//http://www.concretepage.com/spring-4/spring-4-hibernate-4-gradle-integration-example-using-annotation
//http://stackoverflow.com/questions/26548505/org-hibernate-hibernateexception-access-to-dialectresolutioninfo-cannot-be-null
//http://stackoverflow.com/questions/23466951/hibernate-4-access-to-dialectresolutioninfo-cannot-be-null-when-hibernate-diale
//http://stackoverflow.com/questions/21282432/hibernateexception-access-to-dialectresolutioninfo-cannot-be-null-when-hiberna

public class Spring4Hibernate4Test {
  public static void main(String[] args) {
	  AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
  	  ctx.register(AppConfig.class);
	  ctx.refresh();
	  IPersonDao pdao = ctx.getBean(IPersonDao.class);
	  pdao.savePerson();
	  System.out.println("Done");
  }
} 