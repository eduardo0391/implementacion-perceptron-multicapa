/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author eduardo
 */

public class Principal {
    public static void main(String[] args)
    {
        try
        {
        String[] configuracion= new String[6];
        // 
        configuracion[0]="1";
        configuracion[1]="1";
        configuracion[2]="1";
        configuracion[3]="1";
        configuracion[4]="1";
        configuracion[5]="1";
        PerceptronMulticapa unModelo= new PerceptronMulticapa();
        unModelo.setConfiguracion(configuracion);
        pantalla.pantallaPrincipal unaPantallaPrincipal=new pantalla.pantallaPrincipal(unModelo); 
        unaPantallaPrincipal.show();
        }
        catch(Exception ex)
        {
        }
    }
}