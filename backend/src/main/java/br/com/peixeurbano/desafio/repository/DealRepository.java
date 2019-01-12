package br.com.peixeurbano.desafio.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.peixeurbano.desafio.document.Deal;

public interface DealRepository extends MongoRepository<Deal, String> {

  List<Deal> findAllByPublishDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate before, LocalDate after);
}
