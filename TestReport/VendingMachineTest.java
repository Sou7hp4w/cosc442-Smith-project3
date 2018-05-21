/**
 * 
 */
package edu.towson.cis.cosc442.project3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jeffrey
 *
 */
public class VendingMachineTest {

	/**Test object for vending machine class tests	 */
	VendingMachine machine1;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		machine1 = new VendingMachine();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		machine1 = null;	
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.VendingMachine#addItem(edu.towson.cis.cosc442.project3.VendingMachineItem, java.lang.String)}.
	 */
	@Test
	public void testAddItem() {
		VendingMachineItem item1 = new VendingMachineItem("Cookie", 1.25);
		machine1.addItem(item1, "B");
		assertEquals(item1.toString(), machine1.getItem("B").toString());
		// proves item was added to correct spot if retrieved item matches input item
	}
	
	/**
	 * Testing thrown exception condition 1
	 * Test method for {@link edu.towson.cis.cosc442.project3.VendingMachine#addItem(edu.towson.cis.cosc442.project3.VendingMachineItem, java.lang.String)}.
	 */
	@Test
	public void testAddItem2() {
		VendingMachineItem item1 = new VendingMachineItem("Cookie", 1.25);
		VendingMachineItem item2 = new VendingMachineItem("Snacc", 1.00);
		machine1.addItem(item1, "A");
		try {
			machine1.addItem(item2,  "A");
		}catch(Exception e) {
			String expMessage = "Slot A already occupied";
			assertEquals(expMessage, e.getMessage());
		}
	}
	
	/**
	 * Testing thrown exception condtion 2
	 * Test method for {@link edu.towson.cis.cosc442.project3.VendingMachine#addItem(edu.towson.cis.cosc442.project3.VendingMachineItem, java.lang.String)}.
	 */
	@Test
	public void testAddItem3() {
		VendingMachineItem item1 = new VendingMachineItem("Cookie", 1.25);
		try {
			machine1.addItem(item1, "G");
		}catch(Exception e) {
			String expectedMessage = "Invalid code for vending machine item";
			assertEquals(expectedMessage, e.getMessage());
		}
		// proves item was added to correct spot if retrieved item matches input item
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc442.project2.vendingmachine.VendingMachine#getItem(java.lang.String)}.
	 *
	@Test
	public void testGetItem() {
		fail("Not yet implemented");
	}*/

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.VendingMachine#removeItem(java.lang.String)}.
	 */
	@Test
	public void testRemoveItem() {
		VendingMachineItem item1 = new VendingMachineItem("Cookie", 1.25);
		machine1.addItem(item1,  "D");
		assertEquals(item1.toString(), machine1.removeItem("D").toString());
		
		// if item removed equals item that should be in machine, pass
		// else should fail
	}
	
	/**
	 * Testing already empty exception
	 * Test method for {@link edu.towson.cis.cosc442.project3.VendingMachine#removeItem(java.lang.String)}.
	 */
	@Test
	public void testRemoveItem2() {
		VendingMachineItem item3;
		//machine1.addItem(item1,  "D");
		try {
			item3 = machine1.removeItem("C");
		}catch(Exception e) {
			assertEquals("Slot C is empty -- cannot remove item",e.getMessage());
		}
		
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.VendingMachine#insertMoney(double)}.
	 */
	@Test
	public void testInsertMoney() {
		double oldBal = machine1.balance;
		machine1.insertMoney(2.00);
		assertEquals((2.00 + oldBal), (machine1.balance + oldBal), .0001);
	}

	/**
	 * Testing invalid amount exception
	 * Test method for {@link edu.towson.cis.cosc442.project3.VendingMachine#insertMoney(double)}.
	 */
	@Test
	public void testInsertMoney2() {
		try {
			machine1.insertMoney(-1.00);
		}catch(Exception e) {
			assertEquals("Invalid amount.  Amount must be >= 0", e.getMessage());
		}
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.VendingMachine#getBalance()}.
	 */
	@Test
	public void testGetBalance() {
		assertEquals(machine1.balance, machine1.getBalance(), .0001);
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.VendingMachine#makePurchase(java.lang.String)}.
	 */
	@Test
	public void testMakePurchase() {
		VendingMachineItem item1 = new VendingMachineItem("Cookie", 1.25);
		machine1.addItem(item1, "C");
		machine1.insertMoney(2.00);
		assertEquals(true, machine1.makePurchase("C"));
		assertEquals(.75, machine1.getBalance(), .001);
		assertEquals(null, machine1.getItem("C"));
	}

	/**
	 * Testing other item == null boundary
	 * 
	 * Test method for {@link edu.towson.cis.cosc442.project3.VendingMachine#makePurchase(java.lang.String)}.
	 */
	@Test
	public void testMakePurchase2() {
		VendingMachineItem item1 = new VendingMachineItem("Cookie", 1.25);
		machine1.addItem(item1, "C");
		machine1.insertMoney(2.00);
		assertEquals(false, machine1.makePurchase("B"));
		assertEquals(2.00, machine1.getBalance(), .001);
		assertEquals(item1.toString(), machine1.getItem("C").toString());
	}
	
	/**
	 * Testing balance < price boundary
	 * 
	 * Test method for {@link edu.towson.cis.cosc442.project3.VendingMachine#makePurchase(java.lang.String)}.
	 */
	@Test
	public void testMakePurchase3() {
		VendingMachineItem item1 = new VendingMachineItem("Cookie", 1.25);
		machine1.addItem(item1, "C");
		machine1.insertMoney(1.00);
		assertEquals(false, machine1.makePurchase("C"));
		assertEquals(1.00, machine1.getBalance(), .001);
		assertEquals(item1.toString(), machine1.getItem("C").toString());
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.VendingMachine#returnChange()}.
	 */
	@Test
	public void testReturnChange() {
		VendingMachineItem item1 = new VendingMachineItem("Cookie", 1.25);
		machine1.addItem(item1, "B");
		machine1.insertMoney(2.00);
		machine1.makePurchase("B");
		assertEquals(.75, machine1.returnChange(), .001);
		assertEquals(0, machine1.getBalance(), .001);
	}

}
