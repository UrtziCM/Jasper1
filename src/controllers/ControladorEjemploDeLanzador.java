package controllers;

import java.sql.SQLException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;




public class ControladorEjemploDeLanzador {

    @FXML
    private Button botonLanzar;

    @FXML
    private TextField fieldNombreUsuario;

    @FXML
    public void Lanza(ActionEvent event)  {
    	
    	//si no necesitamos conexión a bbdd
    	ConnectionDB con = null;
    	try {
			con = new ConnectionDB();
		} catch (SQLException e) {
			System.out.println("Couldn't connect to database.\n" + e.getMessage());
		}
    	
    	// Si no neceistamos parametros
    	HashMap<String, Object> parameters = new HashMap<String, Object>();
        //parameters.put("codigo", 5);
        //parameters.put("nombre", "xxxx");
    	
    	//llamada con parametros y bbdd
    	//JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/informe.jasper"));
		try {
			JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("paises.jasper"));
	        JasperPrint jprint = JasperFillManager.fillReport(report, parameters, con.getConexion());
	        JasperViewer viewer = new JasperViewer(jprint, false);
	        viewer.setVisible(true);
		} catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Ha ocurrido un error");
            alert.showAndWait();
            e.printStackTrace();
        }
        

    }

}
