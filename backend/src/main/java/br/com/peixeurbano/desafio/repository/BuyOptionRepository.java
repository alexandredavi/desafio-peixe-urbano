package br.com.peixeurbano.desafio.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.peixeurbano.desafio.document.BuyOption;

public interface BuyOptionRepository extends MongoRepository<BuyOption, String> {

}
