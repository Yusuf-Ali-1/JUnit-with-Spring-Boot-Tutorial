package com.techtalentsouth.Tutorial;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TutorialApplicationTests {
	


	@Autowired
	private GreetingController greetingController;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	void setUp() {
		System.out.println("TESTER IS WORKING");
	}

	@Test
	void contextLoads() {
		assertNotNull(greetingController);
		
	}
	
	@Test
	void checkForStringInView() {
		
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greeting", String.class)).contains("Player1");
		
	}
	
	@AfterEach
	void finalTouch() {
		System.out.println("GOOD JOB");
	}
	
	
	
	
// TESTING WEB APPLICATION TEST	/////////////////////////////////////////////
	
	@Autowired
	private HomeController homeController;

	@Test
	public void contexLoads() throws Exception {
		assertThat(homeController).isNotNull();
		assertThat(greetingController).isNotNull();
	}
	
	@Test
	public void indexShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("This is a tester page for using JUnit tests");
	}
	
	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/greeting",
				String.class)).contains("Hello");
	}
	
	
	
// WEB LAYER TEST	//////////////////////////////////////////////
	

	
	

		@Autowired
		private MockMvc mockMvc;

		@Test
		public void webLayerShouldReturnDefaultMessage() throws Exception {
			this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
					.andExpect(content().string(containsString("This is a tester page for using JUnit tests")));
		}
	

// WEB MOCK TEST ////////////////////////////////////////////////

		
		

		@MockBean
		private GreetingService service;

		@Test
		public void greetingShouldReturnMessageFromService() throws Exception {
			when(service.greeting()).thenReturn("Hello");
			this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
					.andExpect(content().string(containsString("see")));
		}
	
	
	
	
}


