package com.dinesh.springbootjpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootJpaApplicationTests {

	@Test
	void contextLoads() {
	}
	/*
	 * @Autowired BankAccountDAO bankAccountDAO=new BankAccountDAO();
	 * 
	 * @Test public void
	 * givenPersonEntity_whenInsertedTwiceWithEntityManager_thenEntityExistsExceptionIsThrown
	 * () { assertThatExceptionOfType(EntityExistsException.class).isThrownBy(() ->
	 * { bankAccountDAO.createAccount(new BankAccount(1L, "firstname", 100));
	 * bankAccountDAO.createAccount(new BankAccount(1L, "firstname", 100)); }); }
	 */
}
