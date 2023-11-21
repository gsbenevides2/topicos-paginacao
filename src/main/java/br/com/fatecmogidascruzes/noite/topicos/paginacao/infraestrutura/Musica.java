package br.com.fatecmogidascruzes.noite.topicos.paginacao.infraestrutura;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String nome;
    private String artista;
    private String imageUrl;
    private Date inseridoEm;
    
    public Musica(String nome, String artista, String imageUrl) {
        this.nome = nome;
        this.artista = artista;
        this.imageUrl = imageUrl;
        this.inseridoEm = Date.from(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC));
    }

    private Musica() {
        this.inseridoEm = Date.from(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC));
    }

    public String getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }
    public String getArtista() {
        return artista;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public Date getInseridoEm() {
        return inseridoEm;
    }
    
    
}
