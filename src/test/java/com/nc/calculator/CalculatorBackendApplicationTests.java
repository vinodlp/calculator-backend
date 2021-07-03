package com.nc.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc 
class CalculatorBackendApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCalculatePositiveCase() throws Exception
	{
		ResultActions result = this.mockMvc.perform(post("/api/v1/calculator").contentType(MediaType.APPLICATION_JSON)
	            .content("{\r\n"
	            		+ "    \"formula\":\"1*2\",\r\n"
	            		+ "    \"result\":\"\"\r\n"
	            		+ "}"))
	            .andDo(print())
	            .andExpect(status().is2xxSuccessful())
	            .andExpect(jsonPath("$").value("2.0"));

	}
	
	@Test
	public void testCalculateNegativeCase() throws Exception
	{
		ResultActions result = this.mockMvc.perform(post("/api/v1/calculator").contentType(MediaType.APPLICATION_JSON)
	            .content("{\r\n"
	            		+ "    \"formula\":\"1*2)\",\r\n"
	            		+ "    \"result\":\"\"\r\n"
	            		+ "}"))
	            .andDo(print())
	            .andExpect(status().is2xxSuccessful())
	            .andExpect(jsonPath("$").value("Invalid Expression"));;
		
	}	

}
