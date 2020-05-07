package com.dinesh.rest.webservices.restfulwebservices;

public class HelloWolrdBean {
	
private String message;

public HelloWolrdBean(String message)
{
	this.message=message;
}

public void setMessage(String message) {
	this.message = message;
}

@Override
public String toString() {
	return "HelloWolrdBean [message=" + message + "]";
}

public String getMessage() {
	return message;
}



}
