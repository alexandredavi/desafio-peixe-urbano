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

import br.com.peixeurbano.desafio.document.BuyOption;
import br.com.peixeurbano.desafio.manager.BuyOptionManager;

@RestController
@RequestMapping("/buy-option")
public class BuyOptionController {

  private final BuyOptionManager manager;

  @Autowired
  public BuyOptionController(BuyOptionManager manager) {
    this.manager = manager;
  }

  @GetMapping("/{id}")
  public ResponseEntity<BuyOption> findById(@PathVariable("id") String id) {
    Optional<BuyOption> buyOption = manager.findById(id);
    if (buyOption.isPresent()) {
      return ResponseEntity.ok(buyOption.get());
    }
    return ResponseEntity.badRequest().build();
  }

  @GetMapping
  public ResponseEntity<List<BuyOption>> findAll() {
    List<BuyOption> buyOptions = manager.findAll();
    if (!buyOptions.isEmpty()) {
      return ResponseEntity.ok(buyOptions);
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<String> insert(@RequestBody BuyOption buyOption) {
    return ResponseEntity.ok(manager.insert(buyOption));
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable("id") String id, @RequestBody BuyOption buyOption) {
    buyOption.setId(id);
    manager.update(buyOption);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable("id") String id) {
    manager.delete(id);
    return ResponseEntity.ok().build();
  }
}
