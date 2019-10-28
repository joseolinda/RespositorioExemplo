/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import model.Bean.Offices;

/**
 *
 * @author joseo
 */
public class OfficeDao extends Dao<Offices, String>{
    
    EntityManager entityManager;

    public OfficeDao() {
        entityManager = super.getEntityManager();
    }

    @Override
    public List<Offices> getAll(int limit) {
        
        List<Offices> AllOffices = null;
        try {
            //Consulta uma office pelo seu ID.
            AllOffices = entityManager.createNamedQuery("Offices.findAll").setMaxResults(limit).getResultList();
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return AllOffices;
    }

    @Override
    public Offices consultarPorId(String id) {
        Offices Office = null;
        try {
            //Consulta uma office pelo seu ID.
            Office = entityManager.find(Offices.class, id);
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return Office;
    }

    @Override
    public void excluir(String id) {
        try {
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            // Consulta a customer na base de dados através do seu ID.
            Offices office = entityManager.find(Offices.class, id);
            System.out.println("Excluindo os dados de: " + office.toString());
            // Remove a customer da base de dados.
            entityManager.remove(office);
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
    public Offices salvar(Offices escritorio) throws Exception {
        try {
            String nomeEntidade = escritorio.getClass().getSimpleName();
            String transactionStatus;
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            System.out.println("Salvando objeto da classe " + nomeEntidade);
            // Verifica se a customer ainda não está salva no banco de dados.
            if (escritorio.getOfficeCode() == null ) {
                //Salva os dados da customer.
                entityManager.persist(escritorio);
                transactionStatus = nomeEntidade + " salvo com sucesso";
            } else {
                //Atualiza os dados da customer.
                escritorio = entityManager.merge(escritorio);
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
        return escritorio;
    }
    
    
    
}
