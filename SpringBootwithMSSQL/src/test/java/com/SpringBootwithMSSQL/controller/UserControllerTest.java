package com.SpringBootwithMSSQL.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;

import com.SpringBootwithMSSQL.controller.UserController;
import com.SpringBootwithMSSQL.entity.User;
import com.SpringBootwithMSSQL.service.UserService;

@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	@Mock
	private UserService service;

	@InjectMocks
	private UserController controller;
	
	private List<User> prepareUserRecords(){
		List<User> user = new ArrayList<User>();
		User user1 = new User(1L, "mpy6Q","gdfXj");
		User user2 = new User(2L, "MwAyq","kB33y");
		user.add(user1);
		user.add(user2);
		return user;
	}
	
	@Test
	public void testFetchAllPass() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareUserRecords());
		List<User> user = prepareUserRecords();
		List<User> userFromController =  controller.fetchAll();
		for(int i=0; i<user.size();i++) {
			Assertions.assertEquals(user.get(i).getId(), userFromController.get(i).getId());
            Assertions.assertEquals(user.get(i).getFn(), userFromController.get(i).getFn());
            Assertions.assertEquals(user.get(i).getLn(), userFromController.get(i).getLn());
		}
		
	}

	@Test
	public void testFetchAllFailure() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareUserRecords());
		List<User> user = null; //Intentionally made null to fail the test.
		List<User> userFromController =  controller.fetchAll();
		Assertions.assertNotEquals(user, userFromController);
	}
	
	
	 @Test public void fetchByIdPass() { 
		 Mockito
	        .when(controller.fetchById(1L)).thenReturn(prepareUserRecords().get(0));
			User userById = prepareUserRecords().get(0);
			User userByIdFromController = controller.fetchById(1L);
			
			Assertions.assertEquals(userById.getId(), userByIdFromController.getId());
			
		     
			Assertions.assertEquals(userById.getFn(), userByIdFromController.getFn());
			Assertions.assertEquals(userById.getLn(), userByIdFromController.getLn());
	 }

	 @Test public void fetchByIdFailure() { 
		 Mockito
	        .when(controller.fetchById(1L)).thenReturn(prepareUserRecords().get(0));
			User userById = prepareUserRecords().get(1);
			User userByIdFromController = controller.fetchById(1L);
			
			Assertions.assertNotEquals(userById.getId(), userByIdFromController.getId());
			

        Assertions.assertNotEquals(userById.getFn(), userByIdFromController.getFn());
        Assertions.assertNotEquals(userById.getLn(), userByIdFromController.getLn());
	 }
	 
	 @Test
	 public void deletePass() { 
		 controller.delete(1L);
		 Assertions.assertTrue(true); // This line will be executed only if there is no error in calling the controller for delete as there is no return value.
	 }

	@Test
	public void createPass() {
		User userToBeCreated = prepareUserRecords().get(0);
		User userReturned = prepareUserRecords().get(0);
		userReturned.setId(7L); //Changed the ID.
		
		Mockito
			.when(controller.create(userToBeCreated)).thenReturn(userReturned);
		
		User userFromController  = controller.create(userToBeCreated);
		Assertions.assertNotEquals(userToBeCreated.getId(), userFromController.getId()); //Since Id of created one is mocked as changed from within serviceid, it cannot be equal. 
		
        Assertions.assertEquals(userToBeCreated.getFn(), userFromController.getFn());
        Assertions.assertEquals(userToBeCreated.getLn(), userFromController.getLn());
	}
	
	@Test
	public void createFailure() {
		User userToBeCreated = prepareUserRecords().get(0);
		User userReturned = null; // Intentionally left to null to fail the case. 
				
		Mockito
			.when(controller.create(userToBeCreated)).thenReturn(userReturned);
		
			User userFromController  = controller.create(userToBeCreated);
		Assertions.assertNull(userFromController);
	}
	
	/*
	 * @Test public void update() { ResponseEntity<Object> responseObject = null;
	 * 
	 * Mockito.when(controller.update(userToBeUpdated,
	 * "")).thenReturn(responseObject); }
	 */	
}