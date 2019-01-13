package br.com.peixeurbano.desafio.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DealBuyOptionAssociationListDto {
  private String id;
  private String deal;
  private String buyOption;
}
