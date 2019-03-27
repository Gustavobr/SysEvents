/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Controller;

import br.com.Model.Evento;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

@ManagedBean(name = "EventoMB")
@ViewScoped
public class EventoMB implements Serializable {

    Evento evento = new Evento();

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void addEvento() throws EntityNotFoundException, Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(evento);
        em.getTransaction().commit();
        em.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Evento" + "\n" + evento.getLocal() + "Cadastrado com  sucesso"));

    }

    public List<Evento> listar() throws EntityNotFoundException, Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventPU");
        EntityManager em = emf.createEntityManager();
        // em.getTransaction().begin();
        Query query = em.createQuery("SELECT evt FROM Evento evt");
        List<Evento> lista = query.getResultList();

        return lista;
    }
    //return 
}
