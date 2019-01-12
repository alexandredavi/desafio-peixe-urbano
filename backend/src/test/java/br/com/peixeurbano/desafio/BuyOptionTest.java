package br.com.peixeurbano.desafio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.peixeurbano.desafio.document.BuyOption;
import br.com.peixeurbano.desafio.manager.BuyOptionManager;

@DataMongoTest(includeFilters = @ComponentScan.Filter(Service.class))
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BuyOptionTest {

	@Autowired
	private BuyOptionManager manager;

	@Test
	void insertTest() {
		BuyOption buyOption = new BuyOption();
		buyOption.setTitle("Title");
		buyOption.setNormalPrice(100d);
		buyOption.setSalePrice(100d);
		buyOption.setStartDate(LocalDate.now());
		buyOption.setEndDate(LocalDate.now());
		String id = manager.insert(buyOption);
		Optional<BuyOption> createdBuyOptionOptional = manager.findById(id);
		assertTrue(createdBuyOptionOptional.isPresent());
		BuyOption createdBuyOption = createdBuyOptionOptional.get();
		assertEquals(buyOption.getTitle(), createdBuyOption.getTitle());
		assertEquals(buyOption.getNormalPrice(), createdBuyOption.getNormalPrice());
		assertEquals(buyOption.getSalePrice(), createdBuyOption.getSalePrice());
		assertEquals(buyOption.getStartDate(), createdBuyOption.getStartDate());
		assertEquals(buyOption.getEndDate(), createdBuyOption.getEndDate());
	}

	@Test
	void updateTest() {
		BuyOption buyOption = new BuyOption();
		buyOption.setTitle("Title");
		buyOption.setNormalPrice(100d);
		buyOption.setSalePrice(100d);
		buyOption.setStartDate(LocalDate.now());
		buyOption.setEndDate(LocalDate.now());
		String id = manager.insert(buyOption);
		Optional<BuyOption> createdBuyOptionOptional = manager.findById(id);
		assertTrue(createdBuyOptionOptional.isPresent());
		BuyOption createdBuyOption = createdBuyOptionOptional.get();
		createdBuyOption.setTitle("New title");
		manager.update(createdBuyOption);
		createdBuyOptionOptional = manager.findById(id);
		assertTrue(createdBuyOptionOptional.isPresent());
		createdBuyOption = createdBuyOptionOptional.get();
		assertEquals("New title", createdBuyOption.getTitle());
	}

	@Test
	void findAllTest() {
		BuyOption buyOption = new BuyOption();
		buyOption.setTitle("Title");
		buyOption.setNormalPrice(100d);
		buyOption.setSalePrice(100d);
		buyOption.setStartDate(LocalDate.now());
		buyOption.setEndDate(LocalDate.now());
		manager.insert(buyOption);

		BuyOption buyOption2 = new BuyOption();
		buyOption2.setTitle("Title 2");
		buyOption2.setNormalPrice(120d);
		buyOption2.setSalePrice(120d);
		buyOption2.setStartDate(LocalDate.now());
		buyOption2.setEndDate(LocalDate.now());
		manager.insert(buyOption2);

		List<BuyOption> buyOptions = manager.findAll();
		assertEquals(2, buyOptions.size());
	}

	@Test
	void deleteTest() {
		BuyOption buyOption = new BuyOption();
		buyOption.setTitle("Title");
		buyOption.setNormalPrice(100d);
		buyOption.setSalePrice(100d);
		buyOption.setStartDate(LocalDate.now());
		buyOption.setEndDate(LocalDate.now());
		String id = manager.insert(buyOption);
		manager.delete(id);
		assertTrue(!manager.findById(id).isPresent());
	}
}

