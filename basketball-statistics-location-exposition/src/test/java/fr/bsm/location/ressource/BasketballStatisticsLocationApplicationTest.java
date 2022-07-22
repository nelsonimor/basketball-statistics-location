package fr.bsm.location.ressource;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.bsm.location.exposition.api.ContinentResource;

@SpringBootTest
class BasketballStatisticsLocationApplicationTest {

	@Autowired
	private ContinentResource resource;

	
	@Test
	void contextLoads() {
		assertThat(resource).isNotNull();
	}
}
