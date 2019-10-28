/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import model.Bean.Products;

/**
 *
 * @author joseo
 */
public class ProductDao extends Dao<Products, String>{
    
    EntityManager entityManager;

    public ProductDao() {
        entityManager = super.getEntityManager();
    }

    @Override
    public List<Products> getAll(int limit) {
        
        List<Products> AllProducts = null;
        try {
            //Consulta uma product pelo seu ID.
            AllProducts = entityManager.createNamedQuery("Products.findAll").setMaxResults(limit).getResultList();
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return AllProducts;
    }

    @Override
    public Products consultarPorId(String productCode) {
        Products product = null;
        try {
            //Consulta uma product pelo seu ID.
            product = entityManager.find(Products.class, productCode);
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return product;
    }

    @Override
    public void excluir(String productCode) {
        try {
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            // Consulta a product na base de dados através do seu ID.
            Products product = entityManager.find(Products.class, productCode);
            System.out.println("Excluindo os dados de: " + product.toString());
            // Remove a product da base de dados.
            entityManager.remove(product);
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
    public Products salvar(Products produto) throws Exception {
        try {
            String nomeEntidade = produto.getClass().getSimpleName();
            String transactionStatus;
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            System.out.println("Salvando objeto da classe " + nomeEntidade);
            // Verifica se a product ainda não está salva no banco de dados.
            if (produto.getProductCode() == null ) {
                //Salva os dados da product.
                entityManager.persist(produto);
                transactionStatus = nomeEntidade + " salvo com sucesso";
            } else {
                //Atualiza os dados da product.
                produto = entityManager.merge(produto);
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
        return produto;
    }
    
    
    
}
