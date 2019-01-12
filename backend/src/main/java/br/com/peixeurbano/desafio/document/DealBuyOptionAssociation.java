package br.com.peixeurbano.desafio.document;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DealBuyOptionAssociation {

  @Id
  private String id;

  @NotNull
  private Deal deal;

  @NotNull
  private BuyOption buyOption;
}
