/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import java.time.LocalDate;
import java.time.Period;
import vew.insertion.ProductlineEdit;

/**
 *
 * @author joseo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ProductlineEdit().setVisible(true);
        
//        OfficeDao officesDao = new OfficeDao();
//        List<Offices> escritorios = officesDao.getAll(5);
//        
//        escritorios.stream().map((escritorio) -> {
//            System.out.println("________________________");
//            return escritorio;
//        }).map((escritorio) -> {
//            System.out.println("Detalhes do escritório:");
//            return escritorio;
//        }).map((escritorio) -> {
//            System.out.println(escritorio.getOfficeCode());
//            return escritorio;
//        }).map((escritorio) -> {
//            System.out.println(escritorio.getCity());
//            return escritorio;
//        }).map((escritorio) -> {
//            System.out.println(escritorio.getState());
//            return escritorio;
//        }).map((escritorio) -> {
//            System.out.println(escritorio.getAddressLine1());
//            return escritorio;
//        }).forEachOrdered((escritorio) -> {
//            System.out.println(escritorio.getPhone());
//        });
    }

}
