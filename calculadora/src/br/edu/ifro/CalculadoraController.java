/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifro;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author emanu
 */
public class CalculadoraController implements Initializable {


    @FXML
    private JFXTextField n1;
    @FXML
    private JFXTextField n2;
    @FXML
    private JFXButton btnDiv;
    @FXML
    private JFXButton btnMult;
    @FXML
    private JFXButton btnSoma;
    @FXML
    private JFXButton btnSub;
    @FXML
    private TableView<?> tabelacio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("4a_grupo3");
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT a FROM Historico a");
        
        List<Historico> historicos = query.getResultList();
        
        ObservableList oHistoricos =  FXCollections.observableArrayList(historicos);
        
        tabelacio.setItems(oHistoricos); 
        
        em.getTransaction().begin();
        
        em.getTransaction().commit();
    }    

    @FXML
    private void divisao(ActionEvent event) {
        String ope = "/";
        double num1 = Double.parseDouble(n1.getText());
        double num2 = Double.parseDouble(n2.getText());
        double res = num1 / num2;
       
        salvaria(num1, num2, ope, res);
    }

    @FXML
    private void multiplicacao(ActionEvent event) {
        String ope = "*";
        double num1 = Double.parseDouble(n1.getText());
        double num2 = Double.parseDouble(n2.getText());
        double res = num1 * num2;
        
        salvaria(num1, num2, ope, res);
    }

    @FXML
    private void soma(ActionEvent event) {
        String ope = "+";
        double num1 = Double.parseDouble(n1.getText());
        double num2 = Double.parseDouble(n2.getText());
        double res = num1 + num2;
        
        salvaria(num1, num2, ope, res);
    }

    @FXML
    private void subtracao(ActionEvent event) {
        String ope = "-";
        double num1 = Double.parseDouble(n1.getText());
        double num2 = Double.parseDouble(n2.getText());
        double res = num1 - num2;
        

        salvaria(num1, num2, ope, res);
    }
    
    
    private void salvaria(double num1 , double num2, String ope, double res){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("4a_grupo3");
        EntityManager em = emf.createEntityManager();
        
        Historico resu1 = new Historico();
        resu1.setPrimeiroValor(num1);
        resu1.setSegundoValor(num2);
        resu1.setOperador(ope);
        resu1.setResultado(res);
        
        
        Query query = em.createQuery("SELECT a FROM Historico a");
        
        List<Historico> historicos = query.getResultList();
        
        ObservableList oHistoricos =  FXCollections.observableArrayList(historicos);
        
        tabelacio.setItems(oHistoricos); 
        
        em.getTransaction().begin();
        
        em.persist(resu1);
        
        em.getTransaction().commit();
    }
    
}
