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
import br.com.peixeurbano.desafio.document.Deal;
import br.com.peixeurbano.desafio.document.DealBuyOptionAssociation;
import br.com.peixeurbano.desafio.enums.DealType;
import br.com.peixeurbano.desafio.manager.BuyOptionManager;
import br.com.peixeurbano.desafio.manager.DealBuyOptionAssociationManager;
import br.com.peixeurbano.desafio.manager.DealManager;

@DataMongoTest(includeFilters = @ComponentScan.Filter(Service.class))
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DealBuyOptionAssociationTest {

	@Autowired
	private DealBuyOptionAssociationManager manager;

	@Autowired
	private DealManager dealManager;

	@Autowired
	private BuyOptionManager buyOptionManager;

	@Test
	void insertTest() {
		Deal deal = new Deal();
		deal.setTitle("Title");
		deal.setText("Text");
		deal.setType(DealType.LOCAL);
		deal.setPublishDate(LocalDate.now());
		deal.setEndDate(LocalDate.now());
		String dealId = dealManager.insert(deal);

		BuyOption buyOption = new BuyOption();
		buyOption.setTitle("Title");
		buyOption.setNormalPrice(100d);
		buyOption.setSalePrice(100d);
		buyOption.setStartDate(LocalDate.now());
		buyOption.setEndDate(LocalDate.now());
		String buyOptionId = buyOptionManager.insert(buyOption);

		String id = manager.insert(dealId, buyOptionId);
		Optional<DealBuyOptionAssociation> createdAssociationOptional = manager.findById(id);
		assertTrue(createdAssociationOptional.isPresent());
		DealBuyOptionAssociation createdAssociation = createdAssociationOptional.get();
		assertEquals(buyOptionId, createdAssociation.getBuyOption().getId());
		assertEquals(dealId, createdAssociation.getDeal().getId());
	}

	@Test
	void findAllTest() {
		Deal deal = new Deal();
		deal.setTitle("Title");
		deal.setText("Text");
		deal.setType(DealType.LOCAL);
		deal.setPublishDate(LocalDate.now());
		deal.setEndDate(LocalDate.now());
		String dealId = dealManager.insert(deal);

		BuyOption buyOption = new BuyOption();
		buyOption.setTitle("Title");
		buyOption.setNormalPrice(100d);
		buyOption.setSalePrice(100d);
		buyOption.setStartDate(LocalDate.now());
		buyOption.setEndDate(LocalDate.now());
		String buyOptionId = buyOptionManager.insert(buyOption);

		manager.insert(dealId, buyOptionId);
		assertEquals(1, manager.findAll().size());
	}

	@Test
	void deleteTest() {
		Deal deal = new Deal();
		deal.setTitle("Title");
		deal.setText("Text");
		deal.setType(DealType.LOCAL);
		deal.setPublishDate(LocalDate.now());
		deal.setEndDate(LocalDate.now());
		String dealId = dealManager.insert(deal);

		BuyOption buyOption = new BuyOption();
		buyOption.setTitle("Title");
		buyOption.setNormalPrice(100d);
		buyOption.setSalePrice(100d);
		buyOption.setStartDate(LocalDate.now());
		buyOption.setEndDate(LocalDate.now());
		String buyOptionId = buyOptionManager.insert(buyOption);

		String id = manager.insert(dealId, buyOptionId);
		manager.delete(id);
		assertTrue(!manager.findById(id).isPresent());
	}
}

