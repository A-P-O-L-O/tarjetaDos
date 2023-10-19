/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PQ.PERSISTENCIA;

import PQ.LOGICA.TarjetaNoPersonalizada;
import PQ.LOGICA.TarjetaPersonalizada;
import PQ.PERSISTENCIA.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Elias Jaramillo
 */
public class TarjetaNoPersonalizadaJpaController implements Serializable {

    public TarjetaNoPersonalizadaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public TarjetaNoPersonalizadaJpaController() {

        emf = Persistence.createEntityManagerFactory("TransmilenioPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TarjetaNoPersonalizada tarjetaNoPersonalizada) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tarjetaNoPersonalizada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TarjetaNoPersonalizada tarjetaNoPersonalizada) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tarjetaNoPersonalizada = em.merge(tarjetaNoPersonalizada);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tarjetaNoPersonalizada.getId_nopersonalizada();
                if (findTarjetaNoPersonalizada(id) == null) {
                    throw new NonexistentEntityException("The tarjetaNoPersonalizada with id " + id + " no longer exists.");
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
            TarjetaNoPersonalizada tarjetaNoPersonalizada;
            try {
                tarjetaNoPersonalizada = em.getReference(TarjetaNoPersonalizada.class, id);
                tarjetaNoPersonalizada.getId_nopersonalizada();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarjetaNoPersonalizada with id " + id + " no longer exists.", enfe);
            }
            em.remove(tarjetaNoPersonalizada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TarjetaNoPersonalizada> findTarjetaNoPersonalizadaEntities() {
        return findTarjetaNoPersonalizadaEntities(true, -1, -1);
    }

    public List<TarjetaNoPersonalizada> findTarjetaNoPersonalizadaEntities(int maxResults, int firstResult) {
        return findTarjetaNoPersonalizadaEntities(false, maxResults, firstResult);
    }

    private List<TarjetaNoPersonalizada> findTarjetaNoPersonalizadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TarjetaNoPersonalizada.class));
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

    public TarjetaNoPersonalizada findTarjetaNoPersonalizada(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TarjetaNoPersonalizada.class, id);
        } finally {
            em.close();
        }
    }

    public int getTarjetaNoPersonalizadaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TarjetaNoPersonalizada> rt = cq.from(TarjetaNoPersonalizada.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<TarjetaNoPersonalizada> getTodasLasTarjetas() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<TarjetaNoPersonalizada> query = em.createQuery("SELECT t FROM TarjetaNoPersonalizada t", TarjetaNoPersonalizada.class);
            List<TarjetaNoPersonalizada> tarjetas = query.getResultList();
            return tarjetas;
        } finally {
            em.close();
        }

    }
}
