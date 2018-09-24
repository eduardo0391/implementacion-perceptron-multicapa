/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author polaco
 */

public class datosProcesados {
    List datosEntrenamientoProcesados = new ArrayList();
    DecimalFormat df =  new DecimalFormat("0.000");
    DecimalFormat formato = new DecimalFormat("0");
    List datosValidacionProcesados= new ArrayList();
    List datosTesteoProcesados= new ArrayList();
    List datosValidacion= new ArrayList();
    List datosEntrenamiento= new ArrayList();
    List datosTesteo= new ArrayList();
    List datosCensales= new ArrayList();
    String[] configuracionCensales;
    double[] variablesTesteo;
    public datosProcesados()
    {
    }

    public List getDatosTesteoProcesados() {
        return datosTesteoProcesados;
    }

    public void setDatosTesteoProcesados(List datosTesteoProcesados) {
        this.datosTesteoProcesados = datosTesteoProcesados;
    }
   
    public List getDatosEntrenamientoProcesados() {
        return datosEntrenamientoProcesados;
    }

    public void setDatosEntrenamientoProcesados(List datosEntrenamientoProcesados) {
        this.datosEntrenamientoProcesados = datosEntrenamientoProcesados;
    }

    public List getDatosValidacionProcesados() {
        return datosValidacionProcesados;
    }

    public void setDatosValidacionProcesados(List datosValidacionProcesados) {
        this.datosValidacionProcesados = datosValidacionProcesados;
    }

  
    private List recuperarArchivo(String nombreArchivo, List lista)
    {
        lista= new ArrayList();
        if (nombreArchivo.contains(".xls")) 
            {
                obtenerValores(nombreArchivo, lista);
            } 
        else if (nombreArchivo.contains(".xlsx")) 
            {
               GENERAR_XLSX(nombreArchivo, lista);
            }
        return lista;
        
    }
    
    public void cargarDatos(String entrenamiento, String validacion, String censales, String[] configuracion)
    {
        configuracionCensales= configuracion;
        datosEntrenamiento= recuperarArchivo(entrenamiento, datosEntrenamiento);
        datosCensales= recuperarArchivo(censales, datosCensales);
         
        datosEntrenamientoProcesados =this.procesarDatos(datosEntrenamiento, datosCensales, configuracion,false);
        
         datosValidacion=recuperarArchivo(validacion, datosValidacion);
        
        datosValidacionProcesados =this.procesarDatos(datosValidacion, datosCensales, configuracion,false);
       
    }
    
    public void cargarsoloHistorialAcademico(String entrenamiento, String validacion)
    {
       datosEntrenamiento=  recuperarArchivo(entrenamiento, datosEntrenamiento);
        datosValidacion= recuperarArchivo(validacion, datosValidacion);
        datosEntrenamientoProcesados= this.procesarSoloHistorial(datosEntrenamiento);
        datosValidacionProcesados =this.procesarSoloHistorial(datosValidacion);
         
    }
    public void cargarDatosTesteo(String testeo, String censales, String[] configuracion, double[] variablesTesteoIncluidas)
    {
        variablesTesteo=variablesTesteoIncluidas;
        datosTesteo= recuperarArchivo(testeo.toString(), datosTesteo);
        datosCensales = recuperarArchivo(censales.toString(),  datosCensales);
        datosTesteoProcesados =this.procesarDatos(datosTesteo, datosCensales, configuracion, true);
       
    }
    
     private List procesarDatos(List historial, List datosCensales, String[] configuracion,boolean testeo)
        {
        //variable para calcular el RGA
        List datosparaRetornar= new ArrayList();
        List alumno= new ArrayList();
        //variable para para cargar en el JModel
        String[] auxiliar= new String[24];
        List listaTemporal= null;
            for (int n = 1; n < historial.size(); n++) 
            {
                listaTemporal = (List) historial.get(n);
                for (int i=1; i< datosCensales.size();i++)
                {            
                    if(Array.get(listaTemporal.toArray(),0).toString().equals((((List)datosCensales.get(i)).toArray())[0].toString()) )
                    {
                        while(Array.get(((List)datosCensales.get(i)).toArray(), 0).toString().equals(Array.get(listaTemporal.toArray(), 0).toString()) && n<historial.size())
                        {   
                            if (testeo && (variablesTesteo[1]==0))
                            auxiliar= cargarMateriaPrimerCuatrimestre(auxiliar, listaTemporal);
                            else
                            auxiliar= cargarMateria(auxiliar, listaTemporal);
                            alumno.add(listaTemporal);
                            n++;
                            if (n <historial.size() )
                            listaTemporal = (List) historial.get(n);
                            }

                        auxiliar[0]=Array.get(((List)datosCensales.get(i)).toArray(),0).toString(); 
                        //cargo la edad
                        if(configuracion[0].equals("1"))
                        auxiliar[17]=formato.format(Double.parseDouble(Array.get(((List)datosCensales.get(i)).toArray(),1).toString())).toString();
                        else
                        auxiliar[17]="-";
                        //cargo el colegio
                        if(configuracion[1].equals("1"))
                        auxiliar[18]=formato.format(Double.parseDouble(Array.get(((List)datosCensales.get(i)).toArray(),2).toString())).toString(); 
                        else
                        auxiliar[18]="-";
                        //cargo el cod de ciudad
                        if(configuracion[2].equals("1"))
                        auxiliar[19]=formato.format(Double.parseDouble(Array.get(((List)datosCensales.get(i)).toArray(), 3).toString())).toString();
                        else
                        auxiliar[19]="-";
                        //esta parte esta de mas por agregar 3 atributos mas (estudios de los padres y situacion laboral)
                        if(configuracion[3].equals("1"))
                        auxiliar[20]=formato.format(Double.parseDouble(Array.get(((List)datosCensales.get(i)).toArray(), 4).toString())).toString();
                        else 
                        auxiliar[20]="-"; 
                        if(configuracion[4].equals("1"))
                        auxiliar[21]=formato.format(Double.parseDouble(Array.get(((List)datosCensales.get(i)).toArray(), 5).toString())).toString();
                        else 
                        auxiliar[21]="-";
                        if(configuracion[5].equals("1"))
                        auxiliar[22]=formato.format(Double.parseDouble(Array.get(((List)datosCensales.get(i)).toArray(), 6).toString())).toString();
                        else
                        auxiliar[22]="-";
                        auxiliar[23]=df.format(Double.parseDouble(String.valueOf(this.obtenerRGA(alumno)))).toString() ;
                        if (testeo && (variablesTesteo[1]==0)&& (variablesTesteo[2]==0)) 
                        
                        for(int x=0; x<auxiliar.length;x++)
                        {
                            if ( auxiliar[x]==null ||(auxiliar[x].equals("2") && x<17 ) )
                                
                                auxiliar[x]="1000";
                       }
                        
                        for(int x=0; x<auxiliar.length;x++)
                        {
                            if ((auxiliar[x]==null || auxiliar[x].equals("0") )&& x<17)
                                auxiliar[x]="-1";
                        }
                        
                     datosparaRetornar.add(auxiliar.clone());
                        
                        for(int x=0; x <auxiliar.length;x++)
                            auxiliar[x]=null;
                        alumno.clear(); 
                        n--;
                        break;
                    }
                  
                }
            }
            return datosparaRetornar;
}
     
      private String[] cargarMateria(String[] auxiliarDatos, List registro)
     {
        String[] auxiliar= auxiliarDatos;
        List listaTemporal= registro;
                if (listaTemporal.get(1).equals("Algoritmo y estructura de datos I"))
                            {
                                auxiliar[1]=formato.format(Double.parseDouble(listaTemporal.get(6).toString()));
                                auxiliar[2]= formato.format(Double.parseDouble(listaTemporal.get(5).toString())).toString();
                            }
                            else if (listaTemporal.get(1).equals("Ingles Técnico I"))
                            {
                                auxiliar[3]= formato.format(Double.parseDouble(listaTemporal.get(6).toString())).toString();
                                auxiliar[4]= formato.format(Double.parseDouble(listaTemporal.get(5).toString())).toString();
                            }
                            else if(listaTemporal.get(1).equals("Matemática I"))
                            {
                                auxiliar[5]= formato.format(Double.parseDouble(listaTemporal.get(6).toString())).toString();
                                auxiliar[6]= formato.format(Double.parseDouble(listaTemporal.get(5).toString())).toString();
                            }
                             else if(listaTemporal.get(1).equals("Introducción a la informática"))
                            {
                                auxiliar[7]= formato.format(Double.parseDouble(listaTemporal.get(6).toString())).toString();
                                auxiliar[8]= formato.format(Double.parseDouble(listaTemporal.get(5).toString())).toString();
                            }
                             else if(listaTemporal.get(1).equals("Sistemas y Organización"))
                            {   
                                auxiliar[9]= formato.format(Double.parseDouble(listaTemporal.get(6).toString())).toString();
                                auxiliar[10]= formato.format(Double.parseDouble(listaTemporal.get(5).toString())).toString();
                       }
                             else if(listaTemporal.get(1).equals("Arquitectura de computadoras"))
                            {
                                auxiliar[11]= formato.format(Double.parseDouble(listaTemporal.get(6).toString())).toString();
                                auxiliar[12]= formato.format(Double.parseDouble(listaTemporal.get(5).toString())).toString();
                            }
                             else if(listaTemporal.get(1).equals("Matemática II"))
                            {
                                auxiliar[13]= formato.format(Double.parseDouble(listaTemporal.get(6).toString())).toString();
                                auxiliar[14]= formato.format(Double.parseDouble(listaTemporal.get(5).toString())).toString();
                            }
                             else if(listaTemporal.get(1).equals("Estadística I"))
                            {
                                auxiliar[15]= formato.format(Double.parseDouble(listaTemporal.get(6).toString())).toString();
                                auxiliar[16]= formato.format(Double.parseDouble(listaTemporal.get(5).toString())).toString();
                            }
             return auxiliar;
     }
     
     private String[] cargarMateriaPrimerCuatrimestre(String[] auxiliarDatos, List registro)
     {
        String[] auxiliar= auxiliarDatos;
        List listaTemporal= registro;
        if (listaTemporal.get(1).equals("Algoritmo y estructura de datos I"))
                            {
                                if(Double.parseDouble(listaTemporal.get(7).toString())==1)
                                auxiliar[1]=formato.format(Double.parseDouble(listaTemporal.get(6).toString()));
                                else
                                auxiliar[1]=formato.format(-1);
                                auxiliar[2]= formato.format(Double.parseDouble(listaTemporal.get(5).toString())).toString();
                            }
                            else if (listaTemporal.get(1).equals("Ingles Técnico I"))
                            {
                                if(Double.parseDouble(listaTemporal.get(7).toString())==1)
                                auxiliar[3]= formato.format(Double.parseDouble(listaTemporal.get(6).toString())).toString();
                                else
                                auxiliar[3]=formato.format(-1);
                                auxiliar[4]= formato.format(Double.parseDouble(listaTemporal.get(5).toString())).toString();
                            }
                            else if(listaTemporal.get(1).equals("Matemática I"))
                            {
                                if(Double.parseDouble(listaTemporal.get(7).toString())==1)
                                auxiliar[5]= formato.format(Double.parseDouble(listaTemporal.get(6).toString())).toString();
                                else
                                auxiliar[5]=formato.format(-1);
                                auxiliar[6]= formato.format(Double.parseDouble(listaTemporal.get(5).toString())).toString();
                            }
                             else if(listaTemporal.get(1).equals("Introducción a la informática"))
                            {
                                if(Double.parseDouble(listaTemporal.get(7).toString())==1)
                                 auxiliar[7]= formato.format(Double.parseDouble(listaTemporal.get(6).toString())).toString();
                                else
                                 auxiliar[7]=formato.format(-1);
                                auxiliar[8]= formato.format(Double.parseDouble(listaTemporal.get(5).toString())).toString();
                            }
                             else if(listaTemporal.get(1).equals("Sistemas y Organización"))
                            {   
                               if(Double.parseDouble(listaTemporal.get(7).toString())==1)
                                auxiliar[9]= formato.format(Double.parseDouble(listaTemporal.get(6).toString())).toString();
                                else
                                auxiliar[9]=formato.format(-1);
                                auxiliar[10]= formato.format(Double.parseDouble(listaTemporal.get(5).toString())).toString();
                            }
                             else if(listaTemporal.get(1).equals("Arquitectura de computadoras"))
                            {
                                auxiliar[11]= formato.format(2);
                                auxiliar[12]= formato.format(2);
                            }
                             else if(listaTemporal.get(1).equals("Matemática II"))
                            {
                                auxiliar[13]= formato.format(2);
                                auxiliar[14]= formato.format(2);
                            }
                             else if(listaTemporal.get(1).equals("Estadística I"))
                            {
                                auxiliar[15]= formato.format(2);
                                auxiliar[16]= formato.format(2);
                            }
             return auxiliar;
     }
    private List procesarSoloHistorial(List historial)
        {
        //variable para calcular el RGA
        List datosparaRetornar= new ArrayList();
        List alumno= new ArrayList();
        //variable para para cargar en el JModel
        String[] auxiliar = new String[24];
        List listaTemporal = null;
            for (int n = 1; n < historial.size(); n++) 
            {
                listaTemporal = (List) historial.get(n);
                String legajo= Array.get(listaTemporal.toArray(),0).toString();
                // datosCargar= listaTemporal.toArray();
                        while(legajo.toString().equals(Array.get(listaTemporal.toArray(), 0).toString()) && n<historial.size())
                        {
                            auxiliar= cargarMateria(auxiliar, listaTemporal);
                            alumno.add(listaTemporal);
                            n++;
                            if (n <historial.size() )
                            listaTemporal = (List) historial.get(n);
                            }

                        auxiliar[0]=legajo.toString(); 
                        //cargo la edad
                        auxiliar[17]="-";
                        //cargo el colegio
                        auxiliar[18]="-";
                        //cargo el cod de ciudad
                        auxiliar[19]="-";
                        //cargo la situacion laboral
                        auxiliar[20]="-";
                        //cargo el estudio de madre
                        auxiliar[21]="-";
                        //cargo el estudio del padre
                        auxiliar[22]="-";
                   
          
                        auxiliar[23]= df.format(Double.parseDouble(String.valueOf(this.obtenerRGA(alumno)))).toString() ;
                        
                        for(int x=0; x<auxiliar.length;x++)
                        {
                            if (auxiliar[x]==null)
                                auxiliar[x]="0";
                        }
                     datosparaRetornar.add(auxiliar.clone());
                        
                        for(int x=0; x <auxiliar.length;x++)
                            auxiliar[x]=null;
                        alumno.clear(); 
                        n--;
                }
            return datosparaRetornar;
        }
    private void obtenerValores(String Nombre_Archivo, List Lista_Datos_Celda) {
    try {
    /**
    * Crea una nueva instancia de la clase FileInputStream
    */
     String legajo ="";
      FileInputStream fileInputStream = new FileInputStream(Nombre_Archivo);
   /**
    * Crea una nueva instancia de la clase POIFSFileSystem
    */
   POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
   /**
    * Crea una nueva instancia de la clase HSSFWorkBook
    */
   HSSFWorkbook Libro_trabajo = new HSSFWorkbook(fsFileSystem);
   HSSFSheet Hoja_hssf = Libro_trabajo.getSheetAt(0);
   /**  
    * Iterar las filas y las celdas de la hoja de cálculo para obtener
    * toda la data.
    */
   //guardar en un iterator las filas de la hoja Hoja_hssf
   Iterator Iterador_de_Fila = Hoja_hssf.rowIterator();
  //recorrer el iterator hoja_hssf
   int i=0; 
   while (Iterador_de_Fila.hasNext()) {
    //obtiene el valor de la fila   
    HSSFRow Fila_hssf = (HSSFRow) Iterador_de_Fila.next();
    
         //guarda en un iterator las celdas de un de las filas de Fila_hssf
    Iterator iterador = Fila_hssf.cellIterator();
    //crea un array para guardar una fila 
    List Lista_celda_temporal = new ArrayList();
    //recorre la fila (fila_hssf)
    iterador.hasNext();
    while (iterador.hasNext()) {
     
     HSSFCell Celda_hssf = (HSSFCell) iterador.next(); //fila_hssf  
     //guarda la celda en el vector de la fila
     Lista_celda_temporal.add(Celda_hssf.toString());
     
    }   
    i+=1 ;
  
    //guarda la fila completa en el vector lista_datos_celda\
    if(Lista_celda_temporal!=null)
    {
        if (Lista_celda_temporal.get(0)=="" && Lista_celda_temporal.get(1)!="")
        {            
            legajo=String.valueOf(Array.get(((List)Lista_Datos_Celda.get(i-2)).toArray(),0));
            Lista_celda_temporal.set(0, legajo); 
        }
        if (Lista_celda_temporal.get(0)!="")
        Lista_Datos_Celda.add(Lista_celda_temporal);
    }
   }

  } catch (Exception e) {

   e.printStackTrace();

  }

 }
 private double obtenerRGA(List listaDatos)
    {
        double gpa=0;
        double rendimientoRegularizacion=0;
        double rendimientoAprobacion=0;
        double rendimientoCognitivo=0;
        int cantidadMateriasAprobadas=0;
        Object registro[];
        registro = ((List)listaDatos.get(0)).toArray();
       //recorro todos los registros del archivo xls
        for (int i=1; i<= listaDatos.size(); i++) {
            //calcular el rendimiento en cuanto a la regularizacion de cada materia cursada
            if(Double.parseDouble(registro[2].toString())!=0)
                rendimientoRegularizacion= (1/ Double.parseDouble(registro[2].toString())) + rendimientoRegularizacion;
            //preguntar si la materia que cursó, la rindió. En caso de que no la haya rendido, no calcula
            //el rendimiento integral de aprobacion de la materia en cuestion
            if (Double.parseDouble(registro[3].toString())!=0)
                rendimientoAprobacion = (1/Double.parseDouble(registro[3].toString())) + rendimientoAprobacion;
            //si aprobó la materia en cuestión, calcular el rendimiento cognitivo a traves de la calificacion obtenida
            if(Double.parseDouble(registro[4].toString()) !=0) {
                rendimientoCognitivo= rendimientoCognitivo + Double.parseDouble(registro[4].toString());
                cantidadMateriasAprobadas= cantidadMateriasAprobadas +1;
            }
            //obtener otro registro
            if (i != listaDatos.size())
                registro = ((List)listaDatos.get(i)).toArray();
            //si el registro que se obtuvo tiene un legajo distinto al anterior, calcula el rendimiento general academico
        }
        
                gpa = this.calcularGPA(rendimientoRegularizacion, rendimientoAprobacion, cantidadMateriasAprobadas, rendimientoCognitivo);
                rendimientoAprobacion=0;
                rendimientoRegularizacion=0;
                rendimientoCognitivo=0;
                cantidadMateriasAprobadas=0;
       return gpa;
    }  
    
       public double calcularGPA(double rendimientoRegularizacion, double rendimientoAprobacion,int cantMaterias, double rendimientoCognitivo)
        {
            double gpa=0;
            if (cantMaterias ==0 && rendimientoAprobacion==0)
            gpa = (rendimientoRegularizacion/18)/3;
            else if(rendimientoAprobacion ==0)
                    gpa = ((rendimientoRegularizacion/17) + ((rendimientoCognitivo/cantMaterias)/10))/3;
                else if(cantMaterias==0)
                    gpa = ((rendimientoRegularizacion/17) + (rendimientoAprobacion/17))/3;
                    else
                     gpa = ((rendimientoRegularizacion/17) + (rendimientoAprobacion/17)+ ((rendimientoCognitivo/cantMaterias)/10))/3;
            return gpa;
            
        }
       
    
    private void GENERAR_XLSX(String Nombre_Archivo, List Lista_Datos_Celda) {
    try {
   /**
    * Crea una nueva instancia de la clase FileInputStream
    */
    FileInputStream fileInputStream = new FileInputStream(Nombre_Archivo);
   /**
    * Crea una nueva instancia de la clase XSSFWorkBook
    */
    XSSFWorkbook Libro_trabajo = new XSSFWorkbook(fileInputStream);
    XSSFSheet Hoja_hssf = Libro_trabajo.getSheetAt(0);
   /**
    * Iterar las filas y las celdas de la hoja de cálculo para obtener
    * toda la data.
    */
    Iterator Iterador_de_Fila = Hoja_hssf.rowIterator();
    while (Iterador_de_Fila.hasNext()) {
    XSSFRow Fila_hssf = (XSSFRow) Iterador_de_Fila.next();
    Iterator iterador = Fila_hssf.cellIterator();
    List Lista_celda_temporal = new ArrayList();
    while (iterador.hasNext()) 
    {
     XSSFCell Celda_hssf = (XSSFCell) iterador.next();
     Lista_celda_temporal.add(Celda_hssf);
        }
        Lista_Datos_Celda.add(Lista_celda_temporal);
        }
    } 
    catch (Exception e)
    {
        e.printStackTrace();
    }

 }

}