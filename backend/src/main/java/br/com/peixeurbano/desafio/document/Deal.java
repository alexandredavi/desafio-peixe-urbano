package br.com.peixeurbano.desafio.document;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.peixeurbano.desafio.enums.DealType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Deal {

  @Id
  private String id;

  @NotNull
  private String title;

  @NotNull
  private String text;

  @NotNull
  private LocalDate createDate;

  @NotNull
  private LocalDate publishDate;

  @NotNull
  private LocalDate endDate;
  private String url;
  private long totalSold;

  @NotNull
  private DealType type;
}
