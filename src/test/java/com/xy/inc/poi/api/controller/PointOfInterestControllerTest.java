package com.xy.inc.poi.api.controller;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.xy.inc.poi.api.services.PointOfInterestService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PointOfInterestControllerTest {

	@MockBean
	private PointOfInterestService pointOfInterestService;
	
	@Test
	public void testInvalidPointOfInterestNearest() throws Exception {
		BDDMockito.given(this.pointOfInterestService.findNearest(100, 100, 3)).willReturn(Optional.empty());
	}
}
