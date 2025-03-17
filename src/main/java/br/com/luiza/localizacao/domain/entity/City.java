package br.com.luiza.localizacao.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_city")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @Column(name = "id_city")
    private Long id;

    @Column(name = "nome", length = 45)
    private String nome;

    @Column(name = "populacao")
    private Long populacao;

}
