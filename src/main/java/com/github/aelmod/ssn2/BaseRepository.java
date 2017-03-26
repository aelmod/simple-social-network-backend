package com.github.aelmod.ssn2;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<Entity, Pk> {

    protected final EntityManager entityManager;

    protected final Class<Entity> entityClass;

    public BaseRepository(EntityManager entityManager) {
        entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.entityManager = entityManager;
    }

    public void remove(Entity entity) {
        entityManager.remove(entity);
    }

    public void persist(Entity entity) {
        entityManager.persist(entity);
    }

    public List<Entity> findBy(EntitySpecification<Entity> specification) {
        CriteriaQuery<Entity> userCriteriaQuery = specification.toCriteria(entityManager.getCriteriaBuilder());
        return entityManager.createQuery(userCriteriaQuery).getResultList();
    }

    public Optional<Entity> findOneByPk(Pk pk) {
        return Optional.ofNullable(entityManager.find(entityClass, pk));
    }
}
