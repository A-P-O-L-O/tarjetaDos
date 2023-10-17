/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PQ.PERSISTENCIA;

import PQ.LOGICA.TarjetaPersonalizada;
import PQ.PERSISTENCIA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Elias Jaramillo
 */
public class TarjetaPersonalizadaJpaController implements Serializable {

    public TarjetaPersonalizadaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public TarjetaPersonalizadaJpaController() {
        emf = Persistence.createEntityManagerFactory("TransmilenioPU");

    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TarjetaPersonalizada tarjetaPersonalizada) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tarjetaPersonalizada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TarjetaPersonalizada tarjetaPersonalizada) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tarjetaPersonalizada = em.merge(tarjetaPersonalizada);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tarjetaPersonalizada.getId_personalizada();
                if (findTarjetaPersonalizada(id) == null) {
                    throw new NonexistentEntityException("The tarjetaPersonalizada with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TarjetaPersonalizada tarjetaPersonalizada;
            try {
                tarjetaPersonalizada = em.getReference(TarjetaPersonalizada.class, id);
                tarjetaPersonalizada.getId_personalizada();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarjetaPersonalizada with id " + id + " no longer exists.", enfe);
            }
            em.remove(tarjetaPersonalizada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TarjetaPersonalizada> findTarjetaPersonalizadaEntities() {
        return findTarjetaPersonalizadaEntities(true, -1, -1);
    }

    public List<TarjetaPersonalizada> findTarjetaPersonalizadaEntities(int maxResults, int firstResult) {
        return findTarjetaPersonalizadaEntities(false, maxResults, firstResult);
    }

    private List<TarjetaPersonalizada> findTarjetaPersonalizadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TarjetaPersonalizada.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TarjetaPersonalizada findTarjetaPersonalizada(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TarjetaPersonalizada.class, id);
        } finally {
            em.close();
        }
    }

    public int getTarjetaPersonalizadaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TarjetaPersonalizada> rt = cq.from(TarjetaPersonalizada.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
