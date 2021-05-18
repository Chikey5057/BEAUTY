package h8.chikey.daoimpl;

import h8.chikey.dao.DAO;
import h8.chikey.model.Product;
import h8.chikey.model.ProductSale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ProductSaleDAOIMPL implements DAO<ProductSale,Integer> {

    public ProductSaleDAOIMPL(SessionFactory factory) {
        this.factory = factory;
    }

    SessionFactory factory;

    @Override
    public void create(ProductSale productSale) {
        try(Session session= factory.openSession()){
            session.beginTransaction();
            session.save(productSale);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(ProductSale productSale) {
        try(Session session= factory.openSession()){
            session.beginTransaction();
            session.delete(productSale);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(ProductSale productSale) {
        try(Session session= factory.openSession()){
            session.beginTransaction();
            session.update(productSale);
            session.getTransaction().commit();
        }
    }

    @Override
    public ProductSale read(Integer integer) {
        try(Session session= factory.openSession()){
            return session.get(ProductSale.class,integer);
        }
    }

    @Override
    public List<ProductSale> readAll() {
        try(Session session= factory.openSession()){
            Query<ProductSale> query = session.createQuery("From ProductSale");
            return query.list();
        }
    }
}
