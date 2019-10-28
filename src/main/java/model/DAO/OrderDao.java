/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import model.Bean.Orders;

/**
 *
 * @author joseo
 */
public class OrderDao extends Dao<Orders, Integer>{
    
    EntityManager entityManager;

    public OrderDao() {
        entityManager = super.getEntityManager();
    }

    @Override
    public List<Orders> getAll(int limit) {
        
        List<Orders> AllOrders = null;
        try {
            //Consulta uma order pelo seu ID.
            AllOrders = entityManager.createNamedQuery("Orders.findAll").setMaxResults(limit).getResultList();
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return AllOrders;
    }

    @Override
    public Orders consultarPorId(Integer orderNumber) {
        Orders order = null;
        try {
            //Consulta uma order pelo seu ID.
            order = entityManager.find(Orders.class, orderNumber);
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return order;
    }

    @Override
    public void excluir(Integer orderNumber) {
        try {
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            // Consulta a order na base de dados através do seu ID.
            Orders order = entityManager.find(Orders.class, orderNumber);
            System.out.println("Excluindo os dados de: " + order.toString());
            // Remove a order da base de dados.
            entityManager.remove(order);
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
    public Orders salvar(Orders pedido) throws Exception {
        try {
            String nomeEntidade = pedido.getClass().getSimpleName();
            String transactionStatus;
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            System.out.println("Salvando objeto da classe " + nomeEntidade);
            // Verifica se a order ainda não está salva no banco de dados.
            if (pedido.getOrderNumber() == null ) {
                //Salva os dados da order.
                entityManager.persist(pedido);
                transactionStatus = nomeEntidade + " salvo com sucesso";
            } else {
                //Atualiza os dados da order.
                pedido = entityManager.merge(pedido);
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
        return pedido;
    }
    
    
    
}
