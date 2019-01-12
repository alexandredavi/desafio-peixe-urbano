package br.com.peixeurbano.desafio.document;

import java.time.LocalDate;

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
public class BuyOption {

  @Id
  private String id;

  @NotNull
  private String title;

  @NotNull
  private Double normalPrice;

  @NotNull
  private Double salePrice;
  private Double percentageDiscount;
  private long quantityCupom;

  @NotNull
  private LocalDate startDate;

  @NotNull
  private LocalDate endDate;
}
