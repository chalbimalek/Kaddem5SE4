package tn.esprit.spring.kaddem.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class DetailEquipe implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idDetailEquipe;
    private Integer salle;
    private String thematique;
    @OneToOne(mappedBy="detailEquipe")
    private Equipe equipe;
    public DetailEquipe() {
        // TODO Auto-generated constructor stub
    }






}
