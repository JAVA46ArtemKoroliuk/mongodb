package telran.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.spring.accounting.controller.AccountingController;
import telran.spring.accounting.model.Account;
import telran.spring.accounting.service.AccountingService;
@WebMvcTest(AccountingController.class)
class AccountingManagementControllerTest {
	@MockBean
AccountingService accountingService;
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	MockMvc mockMvc;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	
	@WithMockUser(roles = "ADMIN")
	void addUserNormalTest() throws Exception {
		Account account = new Account();
		account.username = "test@gmail.com";
		account.password = "12345.com";
		account.roles = new String[]{"USER"};
		String accountJSON = mapper.writeValueAsString(account);
		mockMvc.perform(post("http://localhost/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(accountJSON))
				.andDo(print())
				.andExpect(status().isOk());
		
		
	}
	@Test
	
	@WithMockUser(roles = "ADMIN")
	void addUserBadRequestTest() throws Exception {
		Account account = new Account();
		account.username = "test";
		account.password = "123";
		account.roles = new String[]{"USER"};
		String accountJSON = mapper.writeValueAsString(account);
		mockMvc.perform(post("http://localhost/accounts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(accountJSON))
				.andDo(print())
				.andExpect(status().isBadRequest());
		
		
	}
	@Test
	
	@WithMockUser(roles = "ADMIN_REMOVER")
	void deleteUserBadRequestTest() throws Exception {
		
		
		mockMvc.perform(delete("http://localhost/accounts/vasya"))
				.andDo(print())
				.andExpect(status().isBadRequest());
		
		
	}
	@Test
	@WithMockUser(roles = "ADMIN_REMOVER")
	void deleteUserNormaltTest() throws Exception {
		
		
		mockMvc.perform(delete("http://localhost/accounts/vasya@gmail.com"))
				.andDo(print())
				.andExpect(status().isOk());
		
		
	}


}
