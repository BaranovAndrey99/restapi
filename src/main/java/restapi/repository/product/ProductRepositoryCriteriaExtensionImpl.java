package restapi.repository.product;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import restapi.entity.Product;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Interface, which realize methods with Criteria Api.
 */
@Repository
public class ProductRepositoryCriteriaExtensionImpl implements  ProductRepositoryCriteriaExtension {
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductRepositoryCriteriaExtensionImpl(EntityManagerFactory entityManagerFactory){
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    /**
     * Method which can get product from repository with filtering by price: min price, max price and interval.
     * @param type - searchable type.
     * @param minPrice - min price constraint.
     * @param maxPrice - max price constraint.
     * @return - entity with list of searchable products.
     */
    @Override
    public List<Product> findAllProductsByTypeWithPrice(String type, Long minPrice, Long maxPrice) {

        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        Predicate typeEqual = criteriaBuilder.equal(productRoot.get("type"), type);
        Predicate betweenPrices = criteriaBuilder.between(productRoot.get("price"), minPrice, maxPrice);
        Predicate resultPredicate = criteriaBuilder.and(typeEqual, betweenPrices);
        criteriaQuery.select(productRoot).where(resultPredicate);

        Query<Product> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}

