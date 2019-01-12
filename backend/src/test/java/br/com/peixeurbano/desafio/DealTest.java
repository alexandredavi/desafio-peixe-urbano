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

import br.com.peixeurbano.desafio.document.Deal;
import br.com.peixeurbano.desafio.enums.DealType;
import br.com.peixeurbano.desafio.manager.DealManager;

@DataMongoTest(includeFilters = @ComponentScan.Filter(Service.class))
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DealTest {

	@Autowired
	private DealManager manager;

	@Test
	void insertTest() {
		Deal deal = new Deal();
		deal.setTitle("Title");
		deal.setText("Text");
		deal.setType(DealType.LOCAL);
		deal.setPublishDate(LocalDate.now());
		deal.setEndDate(LocalDate.now());
		String id = manager.insert(deal);
		Optional<Deal> createdDealOptional = manager.findById(id);
		assertTrue(createdDealOptional.isPresent());
		Deal createdDeal = createdDealOptional.get();
		assertEquals(deal.getTitle(), createdDeal.getTitle());
		assertEquals(deal.getText(), createdDeal.getText());
		assertEquals(deal.getType(), createdDeal.getType());
		assertEquals(deal.getPublishDate(), createdDeal.getPublishDate());
		assertEquals(deal.getEndDate(), createdDeal.getEndDate());
	}

	@Test
	void updateTest() {
		Deal deal = new Deal();
		deal.setTitle("Title");
		deal.setText("Text");
		deal.setType(DealType.LOCAL);
		deal.setPublishDate(LocalDate.now());
		deal.setEndDate(LocalDate.now());
		String id = manager.insert(deal);
		Optional<Deal> createdDealOptional = manager.findById(id);
		assertTrue(createdDealOptional.isPresent());
		Deal createdDeal = createdDealOptional.get();
		createdDeal.setTitle("New title");
		manager.update(createdDeal);
		createdDealOptional = manager.findById(id);
		assertTrue(createdDealOptional.isPresent());
		createdDeal = createdDealOptional.get();
		assertEquals("New title", createdDeal.getTitle());
	}

	@Test
	void findAllTest() {
		Deal deal = new Deal();
		deal.setTitle("Title");
		deal.setText("Text");
		deal.setType(DealType.LOCAL);
		deal.setPublishDate(LocalDate.now());
		deal.setEndDate(LocalDate.now());
		manager.insert(deal);

		Deal deal2 = new Deal();
		deal2.setTitle("Title 2");
		deal2.setText("Text 2");
		deal2.setType(DealType.LOCAL);
		deal2.setPublishDate(LocalDate.now());
		deal2.setEndDate(LocalDate.now());
		manager.insert(deal2);

		List<Deal> deals = manager.findAll();
		assertEquals(2, deals.size());
	}

	@Test
	void deleteTest() {
		Deal deal = new Deal();
		deal.setTitle("Title");
		deal.setText("Text");
		deal.setType(DealType.LOCAL);
		deal.setPublishDate(LocalDate.now());
		deal.setEndDate(LocalDate.now());
		String id = manager.insert(deal);
		manager.delete(id);
		assertTrue(!manager.findById(id).isPresent());
	}
}

