package sam05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sam05.MessageBean;

public class Ex05 {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean05.xml");
		MessageBean mb = (MessageBean) ac.getBean("mb5");
		// MessageBean mb = new MessageBeanKo();
		mb.sayHello();
	}

}
