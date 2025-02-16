package com.portfolio.fxexpensetrack.repositories;

import com.portfolio.fxexpensetrack.entities.Value;
import com.portfolio.fxexpensetrack.entities.Value;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ValueRepository implements GenericCRUD<com.portfolio.fxexpensetrack.entities.Value>{
    private final EntityManager manager;
    private static final Logger logger = Logger.getLogger(ValueRepository.class.getName());

    public ValueRepository(EntityManager manager) {
        this.manager = manager;

    }

    @Override
    public void save(Value entity) {
    try {
        manager.getTransaction().begin();
        manager.persist(entity);
        manager.getTransaction().commit();
    } catch (Exception e){
        logger.log(Level.SEVERE,"Error saving Value",e);
    }

    }

    @Override
    public Optional<Value> findById(Long id) {
        try {
            return Optional.ofNullable(manager.find(Value.class, id));
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error finding Value by id", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Value> findAll() {
        try {
            TypedQuery<Value> query = manager.createQuery("Select e from Value e", Value.class);
            return query.getResultList();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error querying all Values", e);
        }
        return null;
    }

    @Override
    public void update(Value entity) {
        try {
            manager.getTransaction().begin();
            manager.merge(entity);
            manager.getTransaction().commit();
        }catch (Exception e) {
            logger.log(Level.WARNING, "Error updating entity", e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            manager.getTransaction().begin();
            manager.remove(manager.find(Value.class, id));
            manager.getTransaction().commit();
        }catch (Exception e) {
            logger.log(Level.WARNING, "Error updating entity", e);
        }
    }

    @Override
    public void delete(Value entity) {
        try {
            manager.getTransaction().begin();
            manager.remove(entity);
            manager.getTransaction().commit();
        }catch (Exception e) {
            logger.log(Level.WARNING, "Error removing entity", e);
        }
    }
}
