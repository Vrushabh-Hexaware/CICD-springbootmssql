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

import com.SpringBootwithMSSQL.controller.AddressController;
import com.SpringBootwithMSSQL.entity.Address;
import com.SpringBootwithMSSQL.service.AddressService;

@RunWith(MockitoJUnitRunner.class)
//@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {
	@Mock
	private AddressService service;

	@InjectMocks
	private AddressController controller;
	
	private List<Address> prepareAddressRecords(){
		List<Address> address = new ArrayList<Address>();
		Address address1 = new Address(1L, "mWR9s","hYI4l");
		Address address2 = new Address(2L, "YG0y5","dM6Oh");
		address.add(address1);
		address.add(address2);
		return address;
	}
	
	@Test
	public void testFetchAllPass() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareAddressRecords());
		List<Address> address = prepareAddressRecords();
		List<Address> addressFromController =  controller.fetchAll();
		for(int i=0; i<address.size();i++) {
			Assertions.assertEquals(address.get(i).getId(), addressFromController.get(i).getId());
            Assertions.assertEquals(address.get(i).getCity(), addressFromController.get(i).getCity());
            Assertions.assertEquals(address.get(i).getPin(), addressFromController.get(i).getPin());
		}
		
	}

	@Test
	public void testFetchAllFailure() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareAddressRecords());
		List<Address> address = null; //Intentionally made null to fail the test.
		List<Address> addressFromController =  controller.fetchAll();
		Assertions.assertNotEquals(address, addressFromController);
	}
	
	
	 @Test public void fetchByIdPass() { 
		 Mockito
	        .when(controller.fetchById(1L)).thenReturn(prepareAddressRecords().get(0));
			Address addressById = prepareAddressRecords().get(0);
			Address addressByIdFromController = controller.fetchById(1L);
			
			Assertions.assertEquals(addressById.getId(), addressByIdFromController.getId());
			
		     
			Assertions.assertEquals(addressById.getCity(), addressByIdFromController.getCity());
			Assertions.assertEquals(addressById.getPin(), addressByIdFromController.getPin());
	 }

	 @Test public void fetchByIdFailure() { 
		 Mockito
	        .when(controller.fetchById(1L)).thenReturn(prepareAddressRecords().get(0));
			Address addressById = prepareAddressRecords().get(1);
			Address addressByIdFromController = controller.fetchById(1L);
			
			Assertions.assertNotEquals(addressById.getId(), addressByIdFromController.getId());
			

        Assertions.assertNotEquals(addressById.getCity(), addressByIdFromController.getCity());
        Assertions.assertNotEquals(addressById.getPin(), addressByIdFromController.getPin());
	 }
	 
	 @Test
	 public void deletePass() { 
		 controller.delete(1L);
		 Assertions.assertTrue(true); // This line will be executed only if there is no error in calling the controller for delete as there is no return value.
	 }

	@Test
	public void createPass() {
		Address addressToBeCreated = prepareAddressRecords().get(0);
		Address addressReturned = prepareAddressRecords().get(0);
		addressReturned.setId(7L); //Changed the ID.
		
		Mockito
			.when(controller.create(addressToBeCreated)).thenReturn(addressReturned);
		
		Address addressFromController  = controller.create(addressToBeCreated);
		Assertions.assertNotEquals(addressToBeCreated.getId(), addressFromController.getId()); //Since Id of created one is mocked as changed from within serviceid, it cannot be equal. 
		
        Assertions.assertEquals(addressToBeCreated.getCity(), addressFromController.getCity());
        Assertions.assertEquals(addressToBeCreated.getPin(), addressFromController.getPin());
	}
	
	@Test
	public void createFailure() {
		Address addressToBeCreated = prepareAddressRecords().get(0);
		Address addressReturned = null; // Intentionally left to null to fail the case. 
				
		Mockito
			.when(controller.create(addressToBeCreated)).thenReturn(addressReturned);
		
			Address addressFromController  = controller.create(addressToBeCreated);
		Assertions.assertNull(addressFromController);
	}
	
	/*
	 * @Test public void update() { ResponseEntity<Object> responseObject = null;
	 * 
	 * Mockito.when(controller.update(addressToBeUpdated,
	 * "")).thenReturn(responseObject); }
	 */	
}