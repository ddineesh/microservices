package com.dinesh.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class UserDaoService {

	private static List<User> users= new ArrayList<User>();
	private static int userCounter=3;
	
	static {
		
		users.add(new User(3311, "Dinesh", new Date("01-Sep-1983")));
		users.add(new User(3312, "Suba", new Date("05-Dec-1984")));
		users.add(new User(3313, "Ilakkiya", new Date("07-July-2010")));
		users.add(new User(3314, "Oviya", new Date("07-July-2010")));
	}
	
	
	public List<User> getListOfUsers()
	{
		return users;
	}
	
	public User saveUser(User user)
	{
		
		if(null==user.getId())
		{
		   user.setId(userCounter++);
		}
		users.add(user);
		
		return user;
	}
	
	public User deleteUser(int id)
	{
		/*
		 * for(User user : users) { if(id==user.getId()) users.remove(user); break; }
		 */
		
		Iterator<User> iterator=users.iterator();
		while(iterator.hasNext())
		{
			User user=iterator.next();
			if(user.getId()==id)
			{
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
	public User  findUser(int id)
	{
		for(User user : users)
		{
			if(id==user.getId())
			{
				return user;
			}
		}
		return null;
	}
}
