/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import model.Bean.Employees;

/**
 *
 * @author joseo
 */
public class EmployeeDao extends Dao<Employees, Integer>{
    
    EntityManager entityManager;

    public EmployeeDao() {
        entityManager = super.getEntityManager();
    }

    @Override
    public List<Employees> getAll(int limit) {
        
        List<Employees> AllEmployees = null;
        try {
            //Consulta uma employee pelo seu ID.
            AllEmployees = entityManager.createNamedQuery("Employees.findAll").setMaxResults(limit).getResultList();
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return AllEmployees;
    }

    @Override
    public Employees consultarPorId(Integer employeeNumber) {
        Employees employee = null;
        try {
            //Consulta uma employee pelo seu ID.
            employee = entityManager.find(Employees.class, employeeNumber);
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return employee;
    }

    @Override
    public void excluir(Integer employeeNumber) {
        try {
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            // Consulta a employee na base de dados através do seu ID.
            Employees employee = entityManager.find(Employees.class, employeeNumber);
            System.out.println("Excluindo os dados de: " + employee.toString());
            // Remove a employee da base de dados.
            entityManager.remove(employee);
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
    public Employees salvar(Employees funcionario) throws Exception {
        try {
            String nomeEntidade = funcionario.getClass().getSimpleName();
            String transactionStatus;
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            System.out.println("Salvando objeto da classe " + nomeEntidade);
            // Verifica se a employee ainda não está salva no banco de dados.
            if (funcionario.getEmployeeNumber() == null ) {
                //Salva os dados da employee.
                entityManager.persist(funcionario);
                transactionStatus = nomeEntidade + " salvo com sucesso";
            } else {
                //Atualiza os dados da employee.
                funcionario = entityManager.merge(funcionario);
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
        return funcionario;
    }
    
    
    
}
