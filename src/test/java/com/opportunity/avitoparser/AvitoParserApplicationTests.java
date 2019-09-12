package com.opportunity.avitoparser;

import com.opportunity.avitoparser.service.ParserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvitoParserApplicationTests {

	@Autowired
	private ParserService parserService;

	@Test
	public void testParse() throws Exception {
		String section = "/ekaterinburg/noutbuki";
		Map<String, String> params = new LinkedHashMap<>();
		params.put("pmax", "5000");
		params.put("s", "104");
		params.put("user", "1");

		parserService.parse(section, params);
	}

}
