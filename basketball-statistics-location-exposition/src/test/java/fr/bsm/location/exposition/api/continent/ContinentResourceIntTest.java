package fr.bsm.location.exposition.api.continent;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.bsm.location.application.continent.ContinentService;
import fr.bsm.location.domain.common.entity.continent.ContinentEntity;
import fr.bsm.location.domain.common.entity.continent.ContinentsEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ContinentResourceIntTest {

	@MockBean
	private ContinentService continentService;

	@Autowired
	private WebApplicationContext context;

	private MockMvc restMockMvc;

	@Value("classpath:response/continents.json")
	private Resource continentsFile;
	
	@Value("classpath:response/continent.json")
	private Resource continentFile;

	@BeforeEach
	public void setup() {
		restMockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void getContinentsSuccess() throws Exception {
		ContinentsEntity continentsEntity = new ContinentsEntity();
		continentsEntity.setItems(Arrays.asList(getAfricaEntity()));
		when(continentService.findAll()).thenReturn(continentsEntity);
		restMockMvc.perform(get("/continents").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(Files.readString(continentsFile.getFile().toPath()),true));
	}
	
	@Test
	void getContinentsNoContent() throws Exception {
		ContinentsEntity continentsEntity = new ContinentsEntity();
		continentsEntity.setItems(new ArrayList<>());
		when(continentService.findAll()).thenReturn(continentsEntity);
		restMockMvc.perform(get("/continents").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}


	@Test
	void getContinentByIdNotFound() throws Exception {
		when(continentService.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		restMockMvc.perform(get("/continents/{id}","18")//
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());	
	}
	
	@Test
	void getContinentByIdBadParameter() throws Exception {
		restMockMvc.perform(get("/continents/{id}","toto")//
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());	
	}
	
	@Test
	void getContinentByIdFound() throws Exception {
		when(continentService.findById(Mockito.anyInt())).thenReturn(Optional.of(getAfricaEntity()));
		restMockMvc.perform(get("/continents/{id}","18").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(Files.readString(continentFile.getFile().toPath()),true));
	}
	
	
	
	ContinentEntity getAfricaEntity() {
		return ContinentEntity.builder().id(1).code("AF").name("Africa").build();
	}
	
	
	
	
	


}
