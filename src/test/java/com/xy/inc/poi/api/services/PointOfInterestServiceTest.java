package com.xy.inc.poi.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.xy.inc.poi.api.entities.PointOfInterest;
import com.xy.inc.poi.api.repository.PointOfInterestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PointOfInterestServiceTest {
	
	@MockBean
	private PointOfInterestRepository pointOfInterestRepository;

	@Autowired
	private PointOfInterestService pointOfInterestService;
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.pointOfInterestRepository.findByName(Mockito.anyString())).willReturn(new PointOfInterest());
		BDDMockito.given(this.pointOfInterestRepository.save(Mockito.any(PointOfInterest.class))).willReturn(new PointOfInterest());
	}
	
	@Test
	public void testSearchPointOfInterestNearest() {
		Optional<List<PointOfInterest>> pointOfInterest = this.pointOfInterestService.findNearest(10, 10, 10);

		assertTrue(pointOfInterest.isPresent());
	}
	
	@Test
	public void testSavePointOfInterest() {
		PointOfInterest pointOfInterest = this.pointOfInterestService.save(new PointOfInterest());

		assertNotNull(pointOfInterest);
	}
}
