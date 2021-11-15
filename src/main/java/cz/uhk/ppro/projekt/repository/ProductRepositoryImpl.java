package cz.uhk.ppro.projekt.repository;

import cz.uhk.ppro.projekt.entity.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager em;


    /**
     * @param desiredCategories list of desired categories
     * @param desiredTags list of desired tags
     * @return List<Product> by criteria, paged, sorted by date timestamp (ADDED)
     */
    @Override
    public List<Product> findAllProductsByCriteria(List<String> desiredCategories, List<String> desiredTags, Pageable paging) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> q = cb.createQuery(Product.class);

        Root<Product> product = q.from(Product.class);
        Join<Product, ProductCategory> productCategories = product.join(Product_.productCategoriesByCategoryId,JoinType.LEFT);
        Join<Product, Tag> tags = product.join(Product_.tagsByTagId, JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if(desiredTags != null && !desiredTags.isEmpty()){
            Expression<String> exp = tags.get(Tag_.NAME);
            Predicate predicate = exp.in(desiredTags);
            predicates.add(predicate);
        }
        if(desiredTags != null && !desiredCategories.isEmpty()){
            Expression<String> exp = productCategories.get(ProductCategory_.NAME);
            Predicate predicate = exp.in(desiredCategories);
            predicates.add(predicate);
        }

        q.orderBy(cb.desc(product.get(Product_.ADDED)));
        q.select(product).distinct(true).where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(q).setFirstResult(((int) paging.getOffset()))
                .setMaxResults(paging.getPageSize()).getResultList();
    }

    /*@Override
    public List<Product> findAllProducts(Pageable paging) {
        return findAllProductsByCriteria(null,null,paging);
    }*/

}