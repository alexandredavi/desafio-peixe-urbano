package br.com.peixeurbano.desafio.manager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.peixeurbano.desafio.document.BuyOption;
import br.com.peixeurbano.desafio.document.Deal;
import br.com.peixeurbano.desafio.document.DealBuyOptionAssociation;
import br.com.peixeurbano.desafio.repository.DealBuyOptionAssociationRepository;

@Service
public class DealBuyOptionAssociationManager {

  private final DealBuyOptionAssociationRepository repository;
  private final DealManager dealManager;
  private final BuyOptionManager buyOptionManager;

  @Autowired
  public DealBuyOptionAssociationManager(DealBuyOptionAssociationRepository repository, DealManager dealManager, BuyOptionManager buyOptionManager) {
    this.repository = repository;
    this.dealManager = dealManager;
    this.buyOptionManager = buyOptionManager;
  }

  public Optional<DealBuyOptionAssociation> findById(String id) {
    return repository.findById(id);
  }

  public List<DealBuyOptionAssociation> findAll() {
    return repository.findAll();
  }

  public void delete(String id) {
    repository.deleteById(id);
  }

  public String insert(String dealId, String buyOptionId) {
    Optional<Deal> deal = dealManager.findById(dealId);
    if (!deal.isPresent()) {
      throw new IllegalArgumentException("Deal not found");
    }
    Optional<BuyOption> buyOption = buyOptionManager.findById(buyOptionId);
    if (!buyOption.isPresent()) {
      throw new IllegalArgumentException("Buy option not found");
    }
    DealBuyOptionAssociation association = new DealBuyOptionAssociation();
    association.setDeal(deal.get());
    association.setBuyOption(buyOption.get());
    return repository.save(association).getId();
  }
}
