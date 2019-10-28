/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import model.Bean.Orderdetails;
import model.Bean.OrderdetailsPK;

/**
 *
 * @author joseo
 */
public class OrderdetailDao extends Dao<Orderdetails, OrderdetailsPK>{
    
    EntityManager entityManager;

    public OrderdetailDao() {
        entityManager = super.getEntityManager();
    }

    @Override
    public List<Orderdetails> getAll(int limit) {
        
        List<Orderdetails> AllOrderdetails = null;
        try {
            //Consulta uma orderdetail pelo seu ID.
            AllOrderdetails = entityManager.createNamedQuery("Orderdetails.findAll").setMaxResults(limit).getResultList();
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return AllOrderdetails;
    }

    @Override
    public Orderdetails consultarPorId(OrderdetailsPK orderdetailsPK) {
        Orderdetails orderdetail = null;
        try {
            //Consulta uma orderdetail pelo seu ID.
            orderdetail = entityManager.find(Orderdetails.class, orderdetailsPK);
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return orderdetail;
    }

    @Override
    public void excluir(OrderdetailsPK orderdetailsPK) {
        try {
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            // Consulta a orderdetail na base de dados através do seu ID.
            Orderdetails orderdetail = entityManager.find(Orderdetails.class, orderdetailsPK);
            System.out.println("Excluindo os dados de: " + orderdetail.toString());
            // Remove a orderdetail da base de dados.
            entityManager.remove(orderdetail);
            // Finaliza a transação.
            entityManager.getTransaction().commit();
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public Orderdetails salvar(Orderdetails detalhesPedido) throws Exception {
        try {
            String nomeEntidade = detalhesPedido.getClass().getSimpleName();
            String transactionStatus;
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            System.out.println("Salvando objeto da classe " + nomeEntidade);
            // Verifica se a orderdetail ainda não está salva no banco de dados.
            if (detalhesPedido.getOrderdetailsPK() == null ) {
                //Salva os dados da orderdetail.
                entityManager.persist(detalhesPedido);
                transactionStatus = nomeEntidade + " salvo com sucesso";
            } else {
                //Atualiza os dados da orderdetail.
                detalhesPedido = entityManager.merge(detalhesPedido);
                transactionStatus = nomeEntidade + " atualizado com sucesso";
            }
            // Finaliza a transação.
            entityManager.getTransaction().commit();
            System.out.println(transactionStatus);
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } finally {
            entityManager.close();
        }
        return detalhesPedido;
    }
    
    
    
}
