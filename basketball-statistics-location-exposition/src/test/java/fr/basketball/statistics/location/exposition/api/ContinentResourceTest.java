package fr.basketball.statistics.location.exposition.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.basketball.statistics.location.application.continent.ContinentService;
import fr.basketball.statistics.location.application.region.RegionService;
import fr.basketball.statistics.location.domain.common.entity.ContinentEntity;
import fr.basketball.statistics.location.domain.common.entity.ContinentsEntity;
import fr.basketball.statistics.location.exposition.dto.ContinentDto;
import fr.basketball.statistics.location.exposition.dto.ContinentsDto;
import fr.basketball.statistics.location.exposition.util.ContinentDtoMapper;
import fr.basketball.statistics.location.exposition.util.RegionDtoMapper;


@WebMvcTest
class ContinentResourceTest {


  @Autowired
  private MockMvc restMockMvc;

  @MockBean
  private ContinentService continentService;
  
  @MockBean
  private RegionService regionService;
  
  @MockBean
  private RegionDtoMapper regionDtoMapper;

  @MockBean
  private ContinentDtoMapper mapperDto;


  @Test
  void testFindAllSuccessful() throws Exception {
    
	  ContinentsEntity continentsEntity = ContinentsEntity.builder()
        .items(createContinentList())
        .build();
	  
    ContinentsDto continentsDto = ContinentsDto.builder()
        .items(createItems())
        .build();
    when(continentService.findAll()).thenReturn(continentsEntity);
    when(mapperDto.entityToContinentsDto(continentsEntity)).thenReturn(continentsDto);

    restMockMvc.perform(get("/continents")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].code").value("AF"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.items[1].code").value("EU"));
  }
  
  private List<ContinentEntity> createContinentList() {
	    return Stream.of("AF", "EU")
	        .map(this::createContinent)
	        .collect(Collectors.toList());
	  }
  
  private ContinentEntity createContinent(String code) {
	    return ContinentEntity.builder()
	        .code(code)
	        .build();
	  }
  
  private List<ContinentDto> createItems() {
	    return Stream.of("AF", "EU")
	        .map(this::createContinentDto)
	        .collect(Collectors.toList());
	  }
  
  private ContinentDto createContinentDto(String code) {
	    return ContinentDto.builder()
	        .code(code)
	        .build();
	  }

  



}
