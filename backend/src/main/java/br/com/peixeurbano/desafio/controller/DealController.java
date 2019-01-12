package br.com.peixeurbano.desafio.controller;

import static java.time.temporal.ChronoUnit.DAYS;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import br.com.peixeurbano.desafio.dto.DealDto;
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
  public ResponseEntity<DealDto> findById(@PathVariable("id") String id) {
    Optional<Deal> deal = manager.findById(id);
    if (deal.isPresent()) {
      return ResponseEntity.ok(documentToDto(deal.get()));
    }
    return ResponseEntity.badRequest().build();
  }

  @GetMapping
  public ResponseEntity<List<DealDto>> findAll() {
    List<Deal> deals = manager.findAll();
    if (!deals.isEmpty()) {
      return ResponseEntity.ok(deals.stream().map(this::documentToDto).collect(Collectors.toList()));
    }
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/available")
  public ResponseEntity<List<DealDto>> findAllAvailable() {
    List<Deal> deals = manager.findAllAvailable();
    if (!deals.isEmpty()) {
      return ResponseEntity.ok(deals.stream().map(this::documentToDto).collect(Collectors.toList()));
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<String> insert(@RequestBody DealDto deal) {
    return ResponseEntity.ok(manager.insert(dtoToDocument(deal)));
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable("id") String id, @RequestBody DealDto deal) {
    deal.setId(id);
    manager.update(dtoToDocument(deal));
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable("id") String id) {
    manager.delete(id);
    return ResponseEntity.ok().build();
  }

  private Deal dtoToDocument(DealDto dto) {
    Deal deal = new Deal();
    deal.setId(dto.getId());
    deal.setTitle(dto.getTitle());
    deal.setText(dto.getText());
    deal.setType(dto.getType());
    deal.setUrl(dto.getUrl());
    deal.setPublishDate(dto.getPublishDate());
    deal.setCreateDate(dto.getCreateDate());
    deal.setTotalSold(dto.getTotalSold());
    deal.setEndDate(deal.getPublishDate().plusDays(dto.getEndDate()));
    return deal;
  }

  private DealDto documentToDto(Deal deal) {
    DealDto dto = new DealDto();
    dto.setId(deal.getId());
    dto.setTitle(deal.getTitle());
    dto.setText(deal.getText());
    dto.setType(deal.getType());
    dto.setUrl(deal.getUrl());
    dto.setPublishDate(deal.getPublishDate());
    dto.setCreateDate(deal.getCreateDate());
    dto.setTotalSold(deal.getTotalSold());
    dto.setEndDate(DAYS.between(deal.getPublishDate(), deal.getEndDate()));
    return dto;
  }
}
