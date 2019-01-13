package br.com.peixeurbano.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.peixeurbano.desafio.manager.BuyOptionManager;
import br.com.peixeurbano.desafio.manager.DealManager;

@RestController
@RequestMapping("/buy")
public class BuyController {

  private final DealManager dealManager;
  private final BuyOptionManager buyOptionManager;

  @Autowired
  public BuyController(DealManager dealManager, BuyOptionManager buyOptionManager) {
    this.dealManager = dealManager;
    this.buyOptionManager = buyOptionManager;
  }

  @PatchMapping("/{dealId}/{buyOptionId}")
  public ResponseEntity buy(@PathVariable("dealId") String dealId, @PathVariable("buyOptionId") String buyOptionId) {
    dealManager.updateTotalSold(dealId);
    buyOptionManager.sellUnity(buyOptionId);
    return ResponseEntity.ok().build();
  }
}
