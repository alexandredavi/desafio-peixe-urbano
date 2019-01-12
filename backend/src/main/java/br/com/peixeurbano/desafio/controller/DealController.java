package br.com.peixeurbano.desafio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.peixeurbano.desafio.document.Deal;
import br.com.peixeurbano.desafio.manager.DealManager;

@RestController
@RequestMapping("/deal")
public class DealController {

  private final DealManager manager;

  @Autowired
  public DealController(DealManager manager) {
    this.manager = manager;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Deal> findById(@PathVariable("id") String id) {
    Optional<Deal> deal = manager.findById(id);
    if (deal.isPresent()) {
      return ResponseEntity.ok(deal.get());
    }
    return ResponseEntity.badRequest().build();
  }

  @GetMapping
  public ResponseEntity<List<Deal>> findAll() {
    List<Deal> deals = manager.findAll();
    if (!deals.isEmpty()) {
      return ResponseEntity.ok(deals);
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<String> insert(@RequestBody Deal deal) {
    return ResponseEntity.ok(manager.insert(deal));
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable("id") String id, @RequestBody Deal deal) {
    deal.setId(id);
    manager.update(deal);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable("id") String id) {
    manager.delete(id);
    return ResponseEntity.ok().build();
  }
}
