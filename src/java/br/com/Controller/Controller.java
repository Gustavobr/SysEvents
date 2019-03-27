/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Controller;

import br.com.Model.Evento;
import br.com.Model.Palestrante;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean(name = "mvc")
@ViewScoped
public class Controller {

    Evento evento = new Evento();
    Palestrante palestrante = new Palestrante();
    
    List<Palestrante> lista;

    public List<Palestrante> getLista() {
        return lista;
    }

    public void setLista(List<Palestrante> lista) {
        this.lista = lista;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Palestrante getPalestrante() {
        return palestrante;
    }

    public void setPalestrante(Palestrante palestrante) {
        this.palestrante = palestrante;
    }

    public static EntityManager JPAUtil() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventAppPU");
            EntityManager em = emf.createEntityManager();
            return em;
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Nào foi possível obter conexào");
        }

    }

    public void savePalestrante() throws EntityNotFoundException, Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(palestrante);
        em.getTransaction().commit();
        System.out.println(palestrante.getNome());
        em.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Palestrante cadastrado" + "\n" + palestrante.getNome()));
    }

    public void saveEvento() throws EntityNotFoundException, Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(evento);
        em.getTransaction().commit();
        em.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento cadastrado com sucesso" + "\n" + "\n"+ evento.getLocal().toUpperCase()));
    }
    
    
    
    public List<Palestrante> listar_palestrante()throws EntityNotFoundException,Exception{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT palestra FROM Palestrante palestra");
        lista = query.getResultList();
            return lista;
    }
    
    

}
