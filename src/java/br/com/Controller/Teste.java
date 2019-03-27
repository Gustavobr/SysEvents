/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Controller;

import br.com.Model.Palestrante;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author gustavoscipiao
 */
public class Teste {
    
    public List<Palestrante> listar_palestrante() throws EntityNotFoundException, Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNativeQuery("SELECT P FROM PUBLIC.PALESTRANTE P");
        List<Palestrante> lista = query.getResultList();
        return lista;
    }
    
    
    
    public static void main(String[] args) throws Exception {
        Palestrante p = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNativeQuery("SELECT P FROM PALESTRANTE P");
        List<Palestrante> lista = query.getResultList();
        for(Palestrante palestrante : lista){
           // p.setId(palestrante.getId());
            p.setNome(palestrante.getNome());
            p.setTema(palestrante.getTema());
            System.out.println(p.getNome());
            
        
    }
        System.out.println(lista);
        
    }
    
}
