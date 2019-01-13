package br.com.peixeurbano.desafio.manager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.peixeurbano.desafio.document.BuyOption;
import br.com.peixeurbano.desafio.repository.BuyOptionRepository;

@Service
public class BuyOptionManager {

  private final BuyOptionRepository repository;

  @Autowired
  public BuyOptionManager(BuyOptionRepository repository) {
    this.repository = repository;
  }

  public Optional<BuyOption> findById(String id) {
    return repository.findById(id);
  }

  public List<BuyOption> findAll() {
    return repository.findAll();
  }

  public String insert(BuyOption buyOption) {
    return repository.save(buyOption).getId();
  }

  public void update(BuyOption buyOption) {
    repository.save(buyOption);
  }

  public void delete(String id) {
    repository.deleteById(id);
  }
}
