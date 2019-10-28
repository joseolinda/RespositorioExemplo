package model.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author joseo
 */
public abstract class Dao<T, TypeID> implements iDao<T, TypeID> {
    
    /**
     * M�todo utilizado para obter o entity manager.
     *
     * @return
     */
    public EntityManager getEntityManager() {
        EntityManagerFactory factory = null;
        EntityManager entityManager = null;

        //Obt�m o factory a partir da unidade de persist�ncia.
        factory = Persistence.createEntityManagerFactory("ShoppingPU");
        //Cria um entity manager.
        entityManager = factory.createEntityManager();
        //Fecha o factory para liberar os recursos utilizado.
        
        return entityManager;
    }
    
}