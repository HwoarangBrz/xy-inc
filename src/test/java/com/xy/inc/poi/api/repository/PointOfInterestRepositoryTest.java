package com.xy.inc.poi.api.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.xy.inc.poi.api.entities.PointOfInterest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PointOfInterestRepositoryTest {
	@Autowired
	private PointOfInterestRepository pointOfInterestRepository;

	@Before
	public void setUp() throws Exception {
		PointOfInterest poi1 = new PointOfInterest();
		PointOfInterest poi2 = new PointOfInterest();
		PointOfInterest poi3 = new PointOfInterest();
		poi1.setName("POI Test 1");
		poi1.setCoord_x(10);
		poi1.setCoord_y(10);
		poi2.setName("POI Test 2");
		poi2.setCoord_x(45);
		poi2.setCoord_y(30);
		poi3.setName("POI Test 3");
		poi3.setCoord_x(15);
		poi3.setCoord_y(20);
		this.pointOfInterestRepository.save(poi1);
		this.pointOfInterestRepository.save(poi2);
		this.pointOfInterestRepository.save(poi3);
	}

	@After
	public final void tearDown() {
		this.pointOfInterestRepository.deleteAll();
	}

	@Test
	public void testFindAll() {
		List<PointOfInterest> list = this.pointOfInterestRepository.findAll();

		assertEquals(list.size(), 3);
	}

	@Test
	public void testFindByName() {
		PointOfInterest obj = this.pointOfInterestRepository.findByName("POI Test 2");

		assertEquals(obj.getName(), "POI Test 2");
	}

}
