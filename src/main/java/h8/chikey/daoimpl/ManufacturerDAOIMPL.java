package h8.chikey.daoimpl;

import h8.chikey.dao.DAO;
import h8.chikey.model.Manufacturer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ManufacturerDAOIMPL implements DAO<Manufacturer,Integer> {

    public ManufacturerDAOIMPL(SessionFactory factory) {
        this.factory = factory;
    }

    SessionFactory factory;


    @Override
    public void create(Manufacturer manufacturer) {
        try(Session session= factory.openSession()){
            session.beginTransaction();
            session.save(manufacturer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        try(Session session= factory.openSession()){
            session.beginTransaction();
            session.delete(manufacturer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        try(Session session= factory.openSession()){
            session.beginTransaction();
            session.update(manufacturer);
            session.getTransaction().commit();
        }
    }

    @Override
    public Manufacturer read(Integer integer) {
        try(Session session= factory.openSession()){
            return session.get(Manufacturer.class,integer);
        }
    }

    @Override
    public List<Manufacturer> readAll() {
        try(Session session= factory.openSession()){
            Query<Manufacturer> query = session.createQuery("From Manufacturer");
            return query.list();
        }
    }
}
