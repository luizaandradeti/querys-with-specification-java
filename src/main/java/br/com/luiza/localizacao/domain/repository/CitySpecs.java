package br.com.luiza.localizacao.domain.repository;

import br.com.luiza.localizacao.domain.entity.City;
import org.springframework.data.jpa.domain.Specification;

public abstract class CitySpecs {

    public static Specification<City> propertyEqual(String prop, Object value){
        return (root, query, cb) -> cb.equal( root.get(prop) , value );
    }

    public static Specification<City> idEqual(Long id){
        return (root, query, cb) -> cb.equal( root.get("id") , id );
    }

    public static Specification<City> nomeEqual(String nome){
        return (root, query, cb) -> cb.equal( root.get("nome") , nome );
    }

    public static Specification<City> populacaoGreaterThan(Long value){
        return (root, query, cb) -> cb.greaterThan( root.get("populacao") , value );
    }

    public static Specification<City> populacaoBetwenn(Long min, Long max){
        return (root, query, cb) -> cb.between( root.get("populacao"), min, max );
    }

    public static Specification<City> nomeLike(String nome){
        return (root, query, cb) -> cb.like( cb.upper(root.get("nome")) , "%" + nome + "%".toUpperCase() );
    }
}
