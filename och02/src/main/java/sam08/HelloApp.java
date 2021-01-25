package sam08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("/sam08/bean08.xml");
		MessageBean mb = (MessageBean)ac.getBean("mb8");
		mb.sayHello();

	}

}
