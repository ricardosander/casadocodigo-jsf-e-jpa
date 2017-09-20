package br.com.ricardosander.jsfejpa.main;

import br.com.ricardosander.jsfejpa.Automovel;
import br.com.ricardosander.jsfejpa.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersistidorDeAutomovel {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.createEntityManager();

        Automovel automovel = new Automovel();
        automovel.setAnoFabricacao(2012);
        automovel.setAnoModelo(2001);
        automovel.setMarca("Fiat");
        automovel.setModel("Uno");
        automovel.setObservacoes("Meu primeiro carro.");

        em.getTransaction().begin();
        em.persist(automovel);
        em.getTransaction().commit();

        TypedQuery<Automovel> query = em.createQuery("select a from Automovel a", Automovel.class);
        List<Automovel> automoveis = query.getResultList();

        Automovel remover = em.getReference(Automovel.class, 3L);

        em.getTransaction().begin();
        em.remove(remover);
        em.getTransaction().commit();

        automoveis.forEach(System.out::println);

        em.close();

    }
}
