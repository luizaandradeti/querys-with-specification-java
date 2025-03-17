package br.com.luiza.localizacao.service;

import br.com.luiza.localizacao.domain.entity.City;
import br.com.luiza.localizacao.domain.repository.CityRepository;
import static br.com.luiza.localizacao.domain.repository.CitySpecs.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CityService {

    private CityRepository repository;

    public CityService(CityRepository repository){
        this.repository = repository;
    }

    @Transactional
    public void salvarCity(){
        var cidade = new City(1L, "São Paulo", 12396372L );
        repository.save(cidade);
    }

    public void listarCitysPorQuantidadepopulacao(){
        repository
                .findBypopulacaoLessThanAndNomeLike(1000001L, "Br%")
                .forEach(System.out::println);
    }
    public void listarCitysPorNomeSQL(){
        repository
                .findByNomeSqlNativo("São Paulo")
                .stream().map(cidadeInterface -> new City(cidadeInterface.getId(), cidadeInterface.getNome(), null))
                .forEach(System.out::println);
    }

    public void listarCitysPorNome(){
        Pageable pageable = PageRequest.of(2, 2, Sort.by("populacao"));
        repository
                .findByNomeLike("%%%%", pageable)
                .forEach(System.out::println);
    }

    public void listarCitysPorpopulacao(){
        repository.findBypopulacao(78787900L).forEach(System.out::println);
    }

    void listarCitys(){
        repository.findAll().forEach(System.out::println);
    }

    public List<City> selecaoDinamico(City cidade){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<City> example = Example.of(cidade, matcher);
        return repository.findAll(example);
    }

    public void listarCitysByNomeSpec(){
        repository
                .findAll(nomeEqual("São Paulo").and(idEqual(1L)))
                .forEach(System.out::println);
    }

    public void listarCitysSpecsselecaoDinamico(City selecao){
        Specification<City> specs = Specification.where((root, query, cb) -> cb.conjunction());

        //select * from cidade where 1 = 1

        if(selecao.getId() != null){
            specs = specs.and( idEqual(selecao.getId()) );
        }

        if(StringUtils.hasText(selecao.getNome())){
            specs = specs.and(nomeLike(selecao.getNome()));
        }

        if(selecao.getPopulacao() != null){
            specs = specs.and(populacaoGreaterThan(selecao.getPopulacao()));
        }

        repository.findAll(specs).forEach(System.out::println);
    }
}
