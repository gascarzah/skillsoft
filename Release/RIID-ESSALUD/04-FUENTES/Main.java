package pe.essalud.sistema.gui;

import org.springframework.stereotype.Component;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Frank
 */
@Component
public class MainApp {

	public static void main(final String[] args) {	                
		FrmLogin frmLogin=new FrmLogin();
                frmLogin.setLocationRelativeTo(null);
                frmLogin.setVisible(true);
                
	}

}
