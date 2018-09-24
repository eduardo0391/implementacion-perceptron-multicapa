/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author polaco
 */
public class PerceptronMulticapa {
        datosProcesados unosDatosProcesados=new datosProcesados();
        DecimalFormat df =  new DecimalFormat("0.000");
        double[][] pesoEntrada;
        double pesoSalida[];      
        double[] variablesTesteo= new double [3];
        Integer cantidadDatosEntrenamiento=0;
        double[] entrada;
        double funcionActivacionOculta[];
        double funcionActivacionOcultaAnterior[] ;  
        double incrementalOculta[];
        double incrementalOcultaAnterior[];
        Double e;
        Integer neuronasOcultas;
        double[] aciertos;
        double net=0;
        double funcionActivacionSalida;
        double funcionActivacionSalidaAnterior;
        double sumatoria=0;
        double incrementalSalida;
        double incrementalSalidaAnterior;
        String datos[]= new String[24];
        Boolean valor;
        boolean antiguo = false;
        List datosEntrenamiento;
        List datosValidacion;
        double errorTesteo = 0;
        Integer aciertoTesteo= 0;
        //array list con los resultados
        List entrenamiento;
        List testeo;
        List datosTesteo;
        List erroresEntrenamiento;
        List erroresValidacion;
        Integer contador=0;
        String[] configuracion;
        String[] configuracionTesteo;

    public String[] getConfiguracionTesteo() {
        return configuracionTesteo;
    }

    public void setConfiguracionTesteo(String[] configuracionTesteo) {
        this.configuracionTesteo = configuracionTesteo;
    }
        
    public List getDatosEntrenamiento() {
        return datosEntrenamiento;
    }

    public void setDatosEntrenamiento(List datosEntrenamiento) {
        this.datosEntrenamiento = datosEntrenamiento;
    }

    public List getDatosValidacion() {
        return datosValidacion;
    }

    public void setDatosValidacion(List datosValidacion) {
        this.datosValidacion = datosValidacion;
    }
        
    public List getTesteo() {
        return testeo;
    }

    public void setTesteo(List testeo) {
        this.testeo = testeo;
    }
        
        public String[] getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(String[] configuracion) {
        this.configuracion = configuracion;
    }
    
    public double[] getAciertos() {
        return aciertos;
    }

    public double getErrorTesteo() {
        return errorTesteo;
    }

    public void setErrorTesteo(double errorTesteo) {
        this.errorTesteo = errorTesteo;
    }

    public void setAciertos(double[] aciertos) {
        this.aciertos = aciertos;
    }
        String[] opcionesConfiguracion;
        double funcionEsperadaSalida;
        int iteraciones;
        double[] error;
        double factorMomento=0;
        double promedio =0;
        
    public double[] getError() {
        return error;
    }

    public void setError(double[] error) {
        this.error = error;
    }

    public Integer getAciertoTesteo() {
        return aciertoTesteo;
    }

    public void setAciertoTesteo(Integer aciertoTesteo) {
        this.aciertoTesteo = aciertoTesteo;
    }
     
    public List getEntrenamiento() {
        return entrenamiento;
    }

    public void setEntrenamiento(List entrenamiento) {
        this.entrenamiento = entrenamiento;
    }

    public List getErroresEntrenamiento() {
        return erroresEntrenamiento;
    }

    public void setErroresEntrenamiento(List erroresEntrenamiento) {
        this.erroresEntrenamiento = erroresEntrenamiento;
    }

    public List getErroresValidacion() {
        return erroresValidacion;
    }

    public void setErroresValidacion(List erroresValidacion) {
        this.erroresValidacion = erroresValidacion;
    }
        
        public PerceptronMulticapa() 
        {
        }
        
        private Double[] resultadosFuncionamientoTesteo(String[] datosEntrada)
        {
            Double[] auxiliar= new Double[2];
            entrada = new double[23-contador];
            //primer cuatrimestre
            if (variablesTesteo[0]==1)
            {
                
            for (int j=1;j<=8;j++)
                            entrada[j-1]= Double.parseDouble(datosEntrada[j].toString());
            }
            else 
            for (int j=1;j<=8;j++)
                            entrada[j-1]= 0;
            //segundo cuatrimestre
            if (variablesTesteo[1]==1)
            {
            for (int j=9;j<=16;j++)
                            entrada[j-1]= Double.parseDouble(datosEntrada[j].toString());
            }
            else 
            for (int j=9;j<=16;j++)
                            entrada[j-1]=0;
            
            //cargo los datos censales
            int y= 16;
            if(variablesTesteo[2]==1)
            
            for(int j=0; j<=5;j++)
            {
            if (opcionesConfiguracion[j].equals("1"))
            {
                entrada[y]= Double.parseDouble(datosEntrada[17+j].toString());
                y++;
            }
            }
            else
            for (int j=16;j<23-contador;j++)
                            entrada[j]= -1;
            
            funcionEsperadaSalida = Double.parseDouble(datosEntrada[23].toString().replace(',', '.'));
            entrada[22-contador]=Double.parseDouble("1");
            auxiliar= procesamientoEntrenamiento(entrada);
            return auxiliar;
        }
        
        private Double[] resultadosFuncionamiento(String[] datosEntrada)
        {
            
            Double[] auxiliar= new Double[2];
            
            //16 + 6 entradas censales + 1 bia
            entrada = new double[23-contador];
            for (int j=1;j<=16;j++)
                        {
                            entrada[j-1]= Double.parseDouble(datosEntrada[j].toString());
                        }
            //en 15 queda el registro de entrada, empiezo con el 16
            int y = 16;
            for(int j=0; j<=5;j++)
            {
            if (opcionesConfiguracion[j].equals("1"))
            {
                entrada[y]= Double.parseDouble(datosEntrada[17+j].toString());
                y+=1;
            }
            //en caso de que configuracion = 0, quiere decir que no se incluye la variable
            }
            funcionEsperadaSalida = Double.parseDouble(datosEntrada[23].toString().replace(',', '.'));
            entrada[22-contador]=Double.parseDouble("1");
            auxiliar= procesamientoEntrenamiento(entrada);
            return auxiliar;
        }
            
        public Double[] procesamientoEntrenamiento(double[] entrada)
        {
          Double[] auxiliar= new Double[2];
//                  ETAPA DE FUNCIONAMIENTO
                    for (int j=0; j<neuronasOcultas;j++)
                    {
                    net=0;
                    for (int y=0;y<cantidadDatosEntrenamiento;y++)
                     {
                         net=net+(entrada[y] * pesoEntrada[y][j]);
                     }
                     e = (Math.pow(Math.E, -net));
                     funcionActivacionOculta[j] = (1/(1+ e));                      
                    }
//                    capa de salida
                        net=0;    
                        for (int y=0;y<=neuronasOcultas;y++)
                        {
                         net= net+ (funcionActivacionOculta[y]*pesoSalida[y]);
                        }
                        e = (Math.pow(Math.E, -net));
                        funcionActivacionSalida=(1/(1+ e));
                    auxiliar[0]=funcionActivacionSalida;    
                    auxiliar[1]=funcionEsperadaSalida;
          
          return auxiliar;
        }
        
        public void testeo(List listaDatos)
        {
            Double[] salidas= new Double[2];
            String data[]= new String[4];
            testeo= new ArrayList();
            aciertoTesteo=0;
            errorTesteo=0;
            //contar las variables censales incluidas
            contador=0;
            for(int i=0; i<=5;i++)
            {
            if(configuracionTesteo[i].equals("0"))
            contador+=1;
            }
            
            //  
         for (int x=0; x< listaDatos.size(); x++)
          {   
                     datos = (String[])listaDatos.get(x);//auxiliar: guarda los valores de entrada de cada patron
                     salidas =this.resultadosFuncionamientoTesteo(datos);
                     errorTesteo= errorTesteo+ ((0.5)*(Math.pow(salidas[1]-salidas[0], 2)));
                     data[0]=datos[0];  
                     data[1]=String.valueOf(df.format(salidas[0]));
                     data[2]=String.valueOf(df.format(salidas[1]));
                     data[3]=String.valueOf(df.format(salidas[1]-salidas[0]));
                     testeo.add(data.clone());
                     
                     if (data[1].charAt(2)==data[2].charAt(2))
                     {                      
                         aciertoTesteo=aciertoTesteo+1;
                     }
                     else if (Double.parseDouble(String.valueOf(data[1].charAt(2)))<6 &&Double.parseDouble(String.valueOf(data[2].charAt(2)))<6 )
                         aciertoTesteo=aciertoTesteo+1;
                     }
        }
         
       
        public void entrenarRed(Double factorError, Double factorMomento, Integer iteraciones, Integer neuronasOcultas)
        {
        this.configuracionTesteo= this.configuracion;
        entrenamiento=new ArrayList();
        erroresEntrenamiento=new ArrayList();
        erroresValidacion=new ArrayList();
        aciertos= new double[2];
        incrementalOculta= new double[neuronasOcultas+1];
        funcionActivacionOculta= new double[neuronasOcultas+1];
        funcionActivacionOcultaAnterior= new double[neuronasOcultas+1];
        incrementalOcultaAnterior= new double[neuronasOcultas+1];
        this.opcionesConfiguracion=configuracion;
        this.neuronasOcultas= neuronasOcultas;
        //valores de BIAS
        funcionActivacionOculta[neuronasOcultas]=1;
        pesoSalida= new double[neuronasOcultas+1];
        incrementalOculta[neuronasOcultas]=1;
        String [] datosparaImprimir = new String[4];
        Double[] salidas= new Double[2];
        error = new double[2];
        //generar numero aleatorios
        //contar numero de elemento
        contador=0;
        for(int i=0; i<=5;i++)
        {
            if(configuracion[i].equals("0"))
            contador+=1;
        }
        cantidadDatosEntrenamiento =23-contador;
        pesoEntrada= new double[cantidadDatosEntrenamiento][neuronasOcultas];
        for (int i=0; i<=22-contador;i++)
        {
            for (int j=0;j<=neuronasOcultas-1;j++)
            {
            pesoEntrada[i][j]=(Math.random()*(0.9 + 0.9))-0.9;
            }   
        }
        for(int i=0;i<=neuronasOcultas;i++)
         pesoSalida[i]=(Math.random()*(0.9 + 0.9))-0.9;
        
        for (int i=0;i<iteraciones;i++)
        {            
                error[0]=0;
                error[1]=0;
                    for (int x=0; x< datosEntrenamiento.size(); x++)
                    {
                      datos = (String[])datosEntrenamiento.get(x);//auxiliar: guarda los valores de entrada de cada patron
                      salidas = resultadosFuncionamiento(datos);
                      //ETAPA DE APRENDIZAJE
                      //calcular peso de la capa de salida 
                      //si los datos son de entrenamientos, actualiza los pesos
                      incrementalSalida = (salidas[0]*(1-salidas[0])*(salidas[1] - salidas[0]));
                      //peso de la capa de salida                  
                    for (int h=0;h<=neuronasOcultas;h++)
                    {                    
                       //sumatoria para calcular el incremental de la neurona h
                       sumatoria=pesoSalida[h]*incrementalSalida;
                       incrementalOculta[h] = (funcionActivacionOculta[h]*(1-funcionActivacionOculta[h])*sumatoria);                    
                    }                   
                      //ACTUALIZACION DE LOS PESOS
                      //actualizar peso de la capa de salida
                     for (int n=0;n<=neuronasOcultas;n++)
                        {
                            pesoSalida[n] = pesoSalida[n] +(factorError*funcionActivacionOculta[n]*incrementalSalida)+factorMomento*(factorError*funcionActivacionOcultaAnterior[n]*incrementalSalidaAnterior);                          
                        }
                     for (int h=0;h<=19-contador;h++)
                     { 
                      //actualiza pesos entre la entrada y la primera capa oculta
                        for(int n=0;n<neuronasOcultas;n++)
                         {
                           pesoEntrada[h][n]=pesoEntrada[h][n]+(factorError*entrada[h]*incrementalOculta[n])+factorMomento*(factorError*entrada[h]*incrementalOcultaAnterior[n]);
                         }    
                     }
                      //guardar incrementales y los resultados las neuronas
                        funcionActivacionSalidaAnterior=funcionActivacionSalida;
                        incrementalSalidaAnterior= incrementalSalida;
                    for (int n=0;n<=neuronasOcultas;n++)
                        {
                        funcionActivacionOcultaAnterior[n]=funcionActivacionOculta[n];
                        incrementalOcultaAnterior[n]=incrementalOculta[n];
                        }
                  if (i==(iteraciones-1))
                    {
                     if (salidas[1].toString().charAt(2)==salidas[0].toString().charAt(2))
                     {                      
                         aciertos[0]=aciertos[0]+1;
                     }
                      //en el caso de que tenga un rendimiento malo
                     else if (Double.parseDouble(String.valueOf(salidas[1].toString().charAt(2))) <6 &&Double.parseDouble(String.valueOf(salidas[0].toString().charAt(2)))<6 )
                         aciertos[0]=aciertos[0]+1;
                    }
                  
                error[0]= error[0] +((0.5)*(Math.pow((salidas[1]-salidas[0]),2)));
                if (i==(iteraciones-1))
                {
                     datosparaImprimir = datosParaImprimir(datos[0],String.valueOf(df.format(salidas[0])), String.valueOf(df.format(Double.valueOf(salidas[1]))));   
                     entrenamiento.add(datosparaImprimir);
                }
                }
        //cargo en la serie el error 0
                erroresEntrenamiento.add(Float.parseFloat(String.valueOf(error[0]/datosEntrenamiento.size())));
                for(int x=0; x< datosValidacion.size();x++)
                {
                  datos = (String[])datosValidacion.get(x);//auxiliar: guarda los valores de entrada de cada patron
                  salidas = resultadosFuncionamiento(datos);
                  error[1]= error[1]+ ((0.5)*(Math.pow(salidas[1]-salidas[0], 2)));
                if (i==(iteraciones-1))
                    {
                     if (salidas[1].toString().charAt(2)==salidas[0].toString().charAt(2))
                     {                          
                         aciertos[1]=aciertos[1]+1;
                     }
                    //en el caso de que tenga un rendimiento malo
                     else if (Double.parseDouble(String.valueOf(salidas[1].toString().charAt(2)))<6 &&Double.parseDouble(String.valueOf(salidas[0].toString().charAt(2)))<6 )
                         aciertos[1]=aciertos[1]+1;
                    }
                if(i==(iteraciones-1)){
               datosparaImprimir= this.datosParaImprimir( datos[0],String.valueOf(df.format(salidas[0])), String.valueOf(df.format(Double.valueOf(salidas[1]))));   
               entrenamiento.add(datosparaImprimir);
                }
                }
                erroresValidacion.add( Float.parseFloat(String.valueOf(error[1]/datosValidacion.size())));                         
        }
       
        }
        
    private String[] datosParaImprimir(String legajo,String salidaObtenida,String salidaEsperada)
    {
        String[] datosparaRetornar= new String[3];
        datosparaRetornar[0]= legajo;
        datosparaRetornar[1]= salidaObtenida;
        datosparaRetornar[2]= salidaEsperada;
        return datosparaRetornar;
    }
    
   public void cargarDatos(String entrenamiento, String validacion, String censales)
    {   
        if(configuracion[0].equals("0") && configuracion[1].equals("0")&& configuracion[2].equals("0")
           && configuracion[3].equals("0") && configuracion[4].equals("0")&& configuracion[5].equals("0"))
        unosDatosProcesados.cargarsoloHistorialAcademico(entrenamiento, validacion);
        else
        unosDatosProcesados.cargarDatos(entrenamiento, validacion, censales, configuracion);
        datosEntrenamiento=unosDatosProcesados.getDatosEntrenamientoProcesados();
        datosValidacion= unosDatosProcesados.getDatosValidacionProcesados();
    }
     
         
    public void testeo(String rutaDatosHistorial, String rutaDatosCensales,double[] variables)
    {
        
        unosDatosProcesados.cargarDatosTesteo(rutaDatosHistorial, rutaDatosCensales,configuracionTesteo,variables);
        this.variablesTesteo= variables;
        datosTesteo= unosDatosProcesados.getDatosTesteoProcesados();
        testeo(datosTesteo);
     }
    
}
