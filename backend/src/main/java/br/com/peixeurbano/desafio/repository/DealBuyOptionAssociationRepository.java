package br.com.peixeurbano.desafio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.peixeurbano.desafio.document.DealBuyOptionAssociation;

public interface DealBuyOptionAssociationRepository extends MongoRepository<DealBuyOptionAssociation, String> {
}
