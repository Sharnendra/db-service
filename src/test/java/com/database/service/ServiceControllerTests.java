package com.database.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.database.service.controller.DbService;
import com.database.service.modal.Quotes;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceControllerTests {
	
	private MockMvc mockMvc;
	
	private static String Data="["+"Google"+"]";
	private static String Request="{\"userName\":\"sam\",\"quotes\":"+Data+"\"}";
	
	@Mock
	private DbService dbService;
	
	@Mock
	private Quotes quotes;
	
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(dbService).build();
    }
	
	@Test
	public void testAddFunctioanlity() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add").contentType(MediaType.APPLICATION_JSON).content(Request);
		mockMvc.perform(requestBuilder).andReturn();
	}
	
	@Test
	public void testViewFunctioanlity() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getdata/sam").contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(404, result.getResponse().getStatus());
	}
	
	@Test
	public void testDeleteFunctioanlity() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/delete/sam").contentType(MediaType.APPLICATION_JSON).content(Request);
		mockMvc.perform(requestBuilder).andReturn();
	}

}
