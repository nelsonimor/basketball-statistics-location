package fr.basketball.statistics.location.exposition.api.region;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import fr.basketball.statistics.location.application.region.RegionService;
import fr.bsm.location.domain.common.entity.region.RegionEntity;
import fr.bsm.location.domain.common.entity.region.RegionsEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegionResourceIntTest {

	@MockBean
	private RegionService regionService;

	@Autowired
	private WebApplicationContext context;

	private MockMvc restMockMvc;

	@Value("classpath:response/regions.json")
	private Resource regionsFile;
	
	@Value("classpath:response/region.json")
	private Resource regionFile;

	@BeforeEach
	public void setup() {
		restMockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void getRegionsSuccess() throws Exception {
		RegionsEntity regionsEntity = new RegionsEntity();
		regionsEntity.setItems(Arrays.asList(getBalkanEntity()));
		when(regionService.findAll()).thenReturn(regionsEntity);
		restMockMvc.perform(get("/regions").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(Files.readString(regionsFile.getFile().toPath()),true));
	}
	
	@Test
	void getRegionsNoContent() throws Exception {
		RegionsEntity regionsEntity = new RegionsEntity();
		regionsEntity.setItems(new ArrayList<>());
		when(regionService.findAll()).thenReturn(regionsEntity);
		restMockMvc.perform(get("/regions").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}


	@Test
	void getRegionByIdNotFound() throws Exception {
		when(regionService.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		restMockMvc.perform(get("/regions/{id}","1521")//
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());	
	}
	
	@Test
	void getRegiontByIdBadParameter() throws Exception {
		restMockMvc.perform(get("/regions/{id}","toto")//
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());	
	}
	
	@Test
	void getRegionByIdFound() throws Exception {
		when(regionService.findById(Mockito.anyInt())).thenReturn(Optional.of(getBalkanEntity()));
		restMockMvc.perform(get("/regions/{id}","18").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(Files.readString(regionFile.getFile().toPath()),true));
	}
	
	
	
	RegionEntity getBalkanEntity() {
		return RegionEntity.builder().id(1).name("Balkan").build();
	}
	
	
	
	
	


}
