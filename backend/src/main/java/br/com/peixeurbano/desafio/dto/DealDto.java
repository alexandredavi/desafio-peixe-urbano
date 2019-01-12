package br.com.peixeurbano.desafio.dto;

import java.time.LocalDate;

import br.com.peixeurbano.desafio.enums.DealType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DealDto {
  private String id;
  private String title;
  private String text;
  private LocalDate createDate;
  private LocalDate publishDate;
  private Long endDate;
  private String url;
  private long totalSold;
  private DealType type;
}
