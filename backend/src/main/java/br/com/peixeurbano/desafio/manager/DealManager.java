package br.com.peixeurbano.desafio.manager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.peixeurbano.desafio.document.Deal;
import br.com.peixeurbano.desafio.repository.DealRepository;

@Service
public class DealManager {

  private final DealRepository repository;

  @Autowired
  public DealManager(DealRepository repository) {
    this.repository = repository;
  }

  public Optional<Deal> findById(String id) {
    return repository.findById(id);
  }

  public List<Deal> findAll() {
    return repository.findAllByPublishDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate.now(), LocalDate.now());
  }

  public String insert(Deal deal) {
    deal.setCreateDate(LocalDate.now());
    return repository.save(deal).getId();
  }

  public void update(Deal deal) {
    repository.save(deal);
  }

  public void delete(String id) {
    repository.deleteById(id);
  }
}
