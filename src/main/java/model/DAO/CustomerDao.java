/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import model.Bean.Customers;

/**
 *
 * @author joseo
 */
public class CustomerDao extends Dao<Customers, Integer>{
    
    EntityManager entityManager;

    public CustomerDao() {
        entityManager = super.getEntityManager();
    }

    @Override
    public List<Customers> getAll(int limit) {
        
        List<Customers> AllCustomers = null;
        try {
            //Consulta uma customer pelo seu ID.
            AllCustomers = entityManager.createNamedQuery("Customers.findAll").setMaxResults(limit).getResultList();
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return AllCustomers;
    }

    @Override
    public Customers consultarPorId(Integer customerNumber) {
        Customers customer = null;
        try {
            //Consulta uma customer pelo seu ID.
            customer = entityManager.find(Customers.class, customerNumber);
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return customer;
    }

    @Override
    public void excluir(Integer customerNumber) {
        try {
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            // Consulta a customer na base de dados através do seu ID.
            Customers customer = entityManager.find(Customers.class, customerNumber);
            System.out.println("Excluindo os dados de: " + customer.toString());
            // Remove a customer da base de dados.
            entityManager.remove(customer);
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
    public Customers salvar(Customers cliente) throws Exception {
        try {
            String nomeEntidade = cliente.getClass().getSimpleName();
            String transactionStatus;
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            System.out.println("Salvando objeto da classe " + nomeEntidade);
            // Verifica se a customer ainda não está salva no banco de dados.
            if (cliente.getCustomerName() == null ) {
                //Salva os dados da customer.
                entityManager.persist(cliente);
                transactionStatus = nomeEntidade + " salvo com sucesso";
            } else {
                //Atualiza os dados da customer.
                cliente = entityManager.merge(cliente);
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
        return cliente;
    }
    
    
    
}
