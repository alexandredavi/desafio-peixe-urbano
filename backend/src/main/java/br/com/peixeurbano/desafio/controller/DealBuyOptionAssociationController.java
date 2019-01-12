package br.com.peixeurbano.desafio.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.peixeurbano.desafio.document.DealBuyOptionAssociation;
import br.com.peixeurbano.desafio.dto.DealBuyOptionAssociationDto;
import br.com.peixeurbano.desafio.manager.DealBuyOptionAssociationManager;

@RestController
@RequestMapping("/deal-buy-option-association")
public class DealBuyOptionAssociationController {

  private final DealBuyOptionAssociationManager manager;

  @Autowired
  public DealBuyOptionAssociationController(DealBuyOptionAssociationManager manager) {
    this.manager = manager;
  }

  @GetMapping("/{id}")
  public ResponseEntity<DealBuyOptionAssociationDto> findById(@PathVariable("id") String id) {
    Optional<DealBuyOptionAssociation> dealBuyOptionAssociation = manager.findById(id);
    if (dealBuyOptionAssociation.isPresent()) {
      return ResponseEntity.ok(documentToDto(dealBuyOptionAssociation.get()));
    }
    return ResponseEntity.badRequest().build();
  }

  @GetMapping
  public ResponseEntity<List<DealBuyOptionAssociationDto>> findAll() {
    List<DealBuyOptionAssociation> dealBuyOptionAssociations = manager.findAll();
    if (!dealBuyOptionAssociations.isEmpty()) {
      return ResponseEntity.ok(dealBuyOptionAssociations.stream().map(this::documentToDto).collect(Collectors.toList()));
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<String> insert(@RequestBody DealBuyOptionAssociationDto dealBuyOptionAssociation) {
    return ResponseEntity.ok(manager.insert(dealBuyOptionAssociation.getDealId(), dealBuyOptionAssociation.getBuyOptionId()));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable("id") String id) {
    manager.delete(id);
    return ResponseEntity.ok().build();
  }

  private DealBuyOptionAssociationDto documentToDto(DealBuyOptionAssociation association) {
    DealBuyOptionAssociationDto dto = new DealBuyOptionAssociationDto();
    dto.setId(association.getId());
    dto.setDealId(association.getDeal().getId());
    dto.setBuyOptionId(association.getBuyOption().getId());
    return dto;
  }
}
