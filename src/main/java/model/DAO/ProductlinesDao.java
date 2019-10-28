/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import model.Bean.Productlines;

/**
 *
 * @author joseo
 */
public class ProductlinesDao extends Dao<Productlines, String>{
    
    EntityManager entityManager;

    public ProductlinesDao() {
        entityManager = super.getEntityManager();
    }

    @Override
    public List<Productlines> getAll(int limit) {
        
        List<Productlines> AllProductlines = null;
        try {
            //Consulta uma productline pelo seu ID.
            AllProductlines = entityManager.createNamedQuery("Productlines.findAll").setMaxResults(limit).getResultList();
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return AllProductlines;
    }

    @Override
    public Productlines consultarPorId(String productLine) {
        Productlines productline = null;
        try {
            //Consulta uma productline pelo seu ID.
            productline = entityManager.find(Productlines.class, productLine);
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return productline;
    }

    @Override
    public void excluir(String productLine) {
        try {
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            // Consulta a productline na base de dados através do seu ID.
            Productlines productline = entityManager.find(Productlines.class, productLine);
            System.out.println("Excluindo os dados de: " + productline.toString());
            // Remove a productline da base de dados.
            entityManager.remove(productline);
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
    public Productlines salvar(Productlines linhaDeProdutos) throws Exception {
        try {
            String nomeEntidade = linhaDeProdutos.getClass().getSimpleName();
            String transactionStatus;
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            System.out.println("Salvando objeto da classe " + nomeEntidade);
            // Verifica se a productline ainda não está salva no banco de dados.
            if (linhaDeProdutos.getProductLine() == null ) {
                //Salva os dados da productline.
                entityManager.persist(linhaDeProdutos);
                transactionStatus = nomeEntidade + " salvo com sucesso";
            } else {
                //Atualiza os dados da productline.
                linhaDeProdutos = entityManager.merge(linhaDeProdutos);
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
        return linhaDeProdutos;
    }
    
    
    
}
