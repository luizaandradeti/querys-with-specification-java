package br.com.luiza.localizacao.domain.repository;

import br.com.luiza.localizacao.domain.entity.City;
import br.com.luiza.localizacao.domain.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {

    @Query(nativeQuery = true, value = "select c.id_cidade as id, c.nome from tb_cidade as c where c.nome =:nome ")
    List<br.com.luiza.localizacao.domain.repository.CityInterface> findByNomeSqlNativo(@Param("nome") String nome);

    List<City> findByNome(String nome);

    @Query(" select c from City c where upper(c.nome) like upper(?1) ")
    List<City> findByNomeLike(String nome, Sort sort);



    List<City> findByNomeContaining(String nome);

    @Query(" select c from City c where upper(c.nome) like upper(?1) ")
    Page<City> findByNomeLike(String nome, Pageable pageable);

    List<City> findByNomeStartingWith(String nome);

    List<City> findByNomeEndingWith(String nome);

    List<City> findBypopulacaoLessThan(Long populacao);

    List<City> findBypopulacaoGreaterThan(Long populacao);

    List<City> findBypopulacaoLessThanEqual(Long populacao);

    List<City> findBypopulacao(Long populacao);    List<City> findBypopulacaoLessThanAndNomeLike(Long populacao, String nome);
}
