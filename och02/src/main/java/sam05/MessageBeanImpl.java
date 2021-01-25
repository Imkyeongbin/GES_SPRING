package sam05;

public class MessageBeanImpl implements MessageBean {
	private String name;
	private String greet;
	
	public MessageBeanImpl(String name , String greet) {
		this.name = name;
		this.greet = greet;
	}
	
	public void sayHello() {
		System.out.println(name+"님 !! " + greet);
	}

}
