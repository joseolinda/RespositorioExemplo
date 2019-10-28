/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import model.Bean.Payments;
import model.Bean.PaymentsPK;

/**
 *
 * @author joseo
 */
public class PaymentDao extends Dao<Payments, PaymentsPK>{
    
    EntityManager entityManager;

    public PaymentDao() {
        entityManager = super.getEntityManager();
    }

    @Override
    public List<Payments> getAll(int limit) {
        
        List<Payments> AllPayments = null;
        try {
            //Consulta uma payments pelo seu ID.
            AllPayments = entityManager.createNamedQuery("Payments.findAll").setMaxResults(limit).getResultList();
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return AllPayments;
    }

    @Override
    public Payments consultarPorId(PaymentsPK paymentsPK) {
        Payments payments = null;
        try {
            //Consulta uma payments pelo seu ID.
            payments = entityManager.find(Payments.class, paymentsPK);
        } catch(Exception e) {
            System.err.println("Hove um erro: " + e.getMessage());
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        finally {
            entityManager.close();
        }
        return payments;
    }

    @Override
    public void excluir(PaymentsPK paymentsPK) {
        try {
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            // Consulta a payments na base de dados através do seu ID.
            Payments payments = entityManager.find(Payments.class, paymentsPK);
            System.out.println("Excluindo os dados de: " + payments.toString());
            // Remove a payments da base de dados.
            entityManager.remove(payments);
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
    public Payments salvar(Payments pagamentos) throws Exception {
        try {
            String nomeEntidade = pagamentos.getClass().getSimpleName();
            String transactionStatus;
            // Inicia uma transação com o banco de dados.
            entityManager.getTransaction().begin();
            System.out.println("Salvando objeto da classe " + nomeEntidade);
            // Verifica se a payments ainda não está salva no banco de dados.
            if (pagamentos.getPaymentsPK() == null ) {
                //Salva os dados da payments.
                entityManager.persist(pagamentos);
                transactionStatus = nomeEntidade + " salvo com sucesso";
            } else {
                //Atualiza os dados da payments.
                pagamentos = entityManager.merge(pagamentos);
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
        return pagamentos;
    }
    
    
    
}
