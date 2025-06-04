package Proyecto;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Operaciones extends Applet  {
    
    private double NumIma; //numero imaginario
    private double NumReal; //numero real
    private double NumIma2; //Numero imaginario 2
    private double NumReal2; //Numero real 2
    private double ResIma; //RESULTADO IMAGINARIO
    private double ResReal; //RESULTADO REAL
    //private double angulo; //Angulo para la forma trigo
    private Calculadora calcu; //Tipo calculadora
    private double NID; //NUMERO IMAGINARIO DIVISION
    private double NRD; //NUMERO Real DIVISION
    private double NM; //NUMERO magnitud
    double tem; //variable temporal
    double tem2; //variable temporal 2
    double angulo; //angulo
    double magnitud; //Magnitud para la forma trigo
    int potencia; //Potenci De Moivre
    public double x; 
    public double y;
    
    

    public Operaciones(Calculadora calcu) { //Metodo constructor
        
        this.calcu = calcu;
    }
     
     public XYSeries graficar(){ //grafica 1, grafica el dato que este muestre en el display
    
    XYSeries grafica = new XYSeries("Grafica");
    grafica.add(0,0);
    grafica.add(NumReal2, NumIma2);
    return grafica;
    }
    
     
     public void graficarZ(){ //grafica 1, grafica el dato que este muestre en el display
    
         
         XYSeriesCollection dataset = new XYSeriesCollection();
            XYSeries series;
            
            
         
         series= new XYSeries("Z0+Z1= "+"("+NumReal2+","+ NumIma2+")" ); //cambiar nombre de variable de la serie que se mostrara
         series.add(0, 0); //el vector iniciara en el punto 0,0 
         series.add(NumReal2 ,NumIma2 ); //el vector terminara en el punto x, y que se sacara mediante la suma de los angulos
        dataset.addSeries(series); //se agregan los datos a la grafica
        
        
        series= new XYSeries("Im" ); //cambiar nombre de variable
         series.add(0, 0); //el vector iniciara en el punto 0,0 
         series.add(NumReal2  , 0); //graficara el angulo de los reales    
        dataset.addSeries(series); //se agregan los datos a la grafica
        
        series= new XYSeries("Re" ); //cambiar nombre de variable
         series.add(0, 0); //el vector iniciara en el punto 0,0 
         series.add(0 , NumIma2); //se graficara el dato de los imaginarios   
        dataset.addSeries(series); //se agregan los datos a la grafica
        
        
        series= new XYSeries("Z0 = ("+calcu.getAuxNR2()+","+calcu.getAuxNI2()+")" ); //cambiar nombre de variable
                
         series.add(0, 0); //el vector iniciara en el punto 0,0 
         series.add(calcu.getAuxNR2(), calcu.getAuxNI2()); //el vector iniciara en el punto 0,0 
         dataset.addSeries(series); 
         
        
        series= new XYSeries("Z1 = ("+NumReal+","+NumIma+")"); //cambiar nombre de variable
        series.add(0, 0); //el vector iniciara en el punto 0,0 
        series.add(NumReal, NumIma);//el vector iniciara en el punto 0,0 
        dataset.addSeries(series);
        
            
            JFreeChart xylineChart = ChartFactory.createXYLineChart(
                    "Grafica XY",
                    "Reales (X)",
                    "Imaginarios (Y)",
                    dataset,
                    PlotOrientation.VERTICAL, true, true, false);

            XYPlot plot = xylineChart.getXYPlot();

            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

            renderer.setSeriesPaint(0, Color.RED);

            renderer.setSeriesStroke(0, new BasicStroke(10.0f));

            plot.setRenderer(renderer);

            ChartPanel panel = new ChartPanel(xylineChart);

            // Creamos la ventana
            JFrame ventana = new JFrame("Grafica");
            ventana.setVisible(true);
            ventana.setSize(800, 600);
            ventana.add(panel); //Añadimos el componente a la ventana
         
         
    }
     
  
     
     public void graficadora(){ //Grafica que graficara las raices de euler
    
         XYSeriesCollection dataset = new XYSeriesCollection();
         XYSeries series;
         
         //Se realizan los mismos calculos para sacar euler
            int numRaiz=Integer.parseInt(calcu.numRaiz.getText());
            double exponente = 0;
            trigonometrica();
            tem=(double)Math.pow(tem, (1/numRaiz));
         

        for(int i=0; i<Integer.parseInt(calcu.numRaiz.getText()); i++)
        {
         exponente=formatearDecimales(((2*i*3.1416+angulo)/numRaiz));
         series= new XYSeries("k="+i+"   ("+tem+")(e^i"+exponente+")"); //cambiar nombre de variable
         series.add(0, 0); //el vector iniciara en el punto 0,0
         series.add((tem*Math.cos(exponente)) , (tem*Math.sin(exponente))); //el vector terminara en el punto x, y que se sacara mediante el angulo y la magnitud
        
        dataset.addSeries(series); //se agregan los datos a la grafica
        }
            

            JFreeChart xylineChart = ChartFactory.createXYLineChart(
                    "Grafica XY",
                    "Reales (X)",
                    "Imaginarios (Y)",
                    dataset,
                    PlotOrientation.VERTICAL, true, true, false);

            XYPlot plot = xylineChart.getXYPlot();

            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

            renderer.setSeriesPaint(0, Color.RED);

            renderer.setSeriesStroke(0, new BasicStroke(10.0f));

            plot.setRenderer(renderer);

            ChartPanel panel = new ChartPanel(xylineChart);
            
            
                // Creamos la ventana
            JFrame ventana = new JFrame("Grafica");
            ventana.setVisible(true);
            ventana.setSize(800, 600);
            ventana.add(panel);
      
}
   
    public void suma()
    {
         //Se optiene los numeros de las casillas
        setNumIma(Double.parseDouble(calcu.NumeroComplejo.getText()));
        setNumReal(Double.parseDouble(calcu.NumeroReal.getText())); 
        
        //Se suman en las variables dos para posteriormente mostrar en display
        NumReal2=NumReal+NumReal2;
        NumIma2=NumIma+NumIma2;  
    }
    
    public void resta()
    {
        if(NumReal2==0 && NumIma2==0) //Si no tiene un numero guardado
        {
            //guarda los datos en las variables 2
            NumReal2=NumReal;
            NumIma2=NumIma;
            
            setNumIma(Double.parseDouble(calcu.NumeroComplejo.getText()));
           setNumReal(Double.parseDouble(calcu.NumeroReal.getText()));
            
            //Usando las variables temporales hacemos la multiplicacion 
            tem=NumReal2-NumReal;//real
            tem2=NumIma2-NumIma;//iamginario
            
            //luego asignamos el resultado a las variables 
            
            NumReal2=tem;
            NumIma2=tem2;
           
            
        }
        else //si tiene numero, realiza la multiplicacion con el numero anterior y el nuevo, guardando el dato en las variables temporales
        {
            //Usando las variables temporales hacemos la multiplicacion 
            tem=NumReal2-NumReal;//real
            tem2=NumIma2-NumIma;//iamginario
            
            NumReal2=tem;
            NumIma2=tem2;
        }
        
        resultadoDisplay();
        
        
        
    }
    
    public void multi() //funcion de multiplicar
    {//Si checa si tenia un numero guardado
        if(NumReal2==0 && NumIma2==0) //Si no tiene un numero guardado
        {
            //guarda los datos en las variables 2
            NumReal2=NumReal;
            NumIma2=NumIma;
            
            setNumIma2(Double.parseDouble(calcu.NumeroComplejo.getText()));
            setNumReal2(Double.parseDouble(calcu.NumeroReal.getText()));
            
            
            //Usando las variables temporales hacemos la multiplicacion 
            tem=(NumReal2*NumReal)-(NumIma2*NumIma);
            tem2=(NumReal2*NumIma)+(NumReal*NumIma2);
            
            //luego asignamos el resultado a las variables 
            
            NumReal2=tem;
            NumIma2=tem2;
           
            
        }
        else //si tiene numero, realiza la multiplicacion con el numero anterior y el nuevo, guardando el dato en las variables temporales
        {
            
            tem=(NumReal2*NumReal)-(NumIma2*NumIma);
            tem2=(NumReal2*NumIma)+(NumReal*NumIma2);
            
            NumReal2=tem;
            NumIma2=tem2;
        }
        
        resultadoDisplay();
    }
    
    
    public void divi() //La divicion trabaja igual que la multiplicacion
    {
       //Compara si tiene gato guardado o no
        if(NumReal2==0 && NumIma2==0)
        {
            
            NumReal2=NumReal;
            NumIma2=NumIma;
            //Si no tiene numero guardado, guarda el resultado en las temporales y asigna el numero a las variables 2
            setNumIma(Double.parseDouble(calcu.NumeroComplejo.getText()));
            setNumReal(Double.parseDouble(calcu.NumeroReal.getText())); 
            
            tem=((NumReal2*NumReal)+(NumIma*NumIma2))/((float)(Math.pow(NumReal, 2))+(float)(Math.pow(NumIma, 2)));
            tem2=((NumReal*NumIma2)-(NumReal2*NumIma))/((float)(Math.pow(NumReal, 2))+(float)(Math.pow(NumIma, 2))); 
            
            NumReal2=formatearDecimales(tem);
            NumIma2=formatearDecimales(tem2);
            
            
        }
        else
        {
            //Si tiene numero guardado
            tem=((NumReal2*NumReal)+(NumIma*NumIma2))/((float)(Math.pow(NumReal, 2))+(float)(Math.pow(NumIma, 2)));
            tem2=((NumReal*NumIma2)-(NumReal2*NumIma))/((float)(Math.pow(NumReal, 2))+(float)(Math.pow(NumIma, 2))); 
           
            NumReal2=tem;
            NumIma2=tem2;
        }
        
        resultadoDisplay();
        
    }
    
    
    
    public void magnitud()
    {
        //la variable tem, guardara el valor de la magnitud sacada del valor que se encuentre en las casillas
        tem=(double)Math.sqrt(((double)Math.pow(NumIma, 2))+((double)Math.pow(NumReal, 2)));
        //se transforma el resultado a texto, pasandolo por la funcion "formatearDecimales" para acortar el resultado
        calcu.ResultadoMagnitud.setText(formatearDecimales(tem)+"");
       
    }
    
    public void ingresar_euler()
    {
      calcu.resultadoEuler.setText(magnitud+"e^i("+angulo+")");
    
    }
    
    public void ingresar_trigonometrica()
    {
        
      calcu.DisplayTrigo.setText(magnitud+"(cos("+angulo+")+isen("+angulo+")"+")");//mostramos el resultado con una cadena en el display de trigonometrica
    
    }
    
    

    public void trigonometrica()
    {
        double anguloradianes;
        //temp tendra el valor de la magnitud
        //tem2 tendra la division para sacar el angulo
        
        magnitud(); //se pide la magnitud, que esta seguira guardada en temp
        
        
        tem2=NumIma/tem;
        
        anguloradianes=Math.asin(tem2); //obtenemos el angulo en radianes de seno
        angulo=Math.toDegrees(anguloradianes);//lo pasamos a angulo en grados, para poder trabajar los calculos mas facilmente
        angulo=formatearDecimales(angulo);//el angulo lo redondeamos para obtener solo dos cifras en caso que tenga varios decimales
        tem=formatearDecimales(tem);//y formateamos la magnitud
        calcu.DisplayTrigo.setText(tem+"(cos("+angulo+")+isen("+angulo+")"+")");//mostramos el resultado con una cadena en el display de trigonometrica
        
        
    }
    
    public void Trigo_a_Complejo()
    {
       double angulorad;
       angulorad = Math.toRadians(angulo); //Primero convertimos el alguno a radianes
       
       NumReal2 = magnitud * (Math.cos(angulorad)); // Ahora si calculamos la magnitud por el coseno del angulo
       NumIma2 = magnitud * (Math.sin(angulorad)); // Igual con el seno
       NumReal2=formatearDecimales(NumReal2); //Lo mandamos a la variable NumReal para pasarlo a display
       NumIma2=formatearDecimales(NumIma2);
       
       resultadoDisplay(); //Se muestra el resultado en el display
    
    }
    
    public void DeMoivre()
    {
        double anguloradianes;
        //temp tendra el valor de la magnitud
        //tem2 tendra la division para sacar el angulo
        
        magnitud(); //se pide la magnitud, que esta seguira guardada en temp
        
        
        tem2=NumIma/tem;
        
        anguloradianes=Math.asin(tem2); //obtenemos el angulo en radianes de seno
        angulo=Math.toDegrees(anguloradianes);//lo pasamos a angulo en grados, para poder trabajar los calculos mas facilmente
        angulo=formatearDecimales(angulo);//el angulo lo redondeamos para obtener solo dos cifras en caso que tenga varios decimales
        tem=formatearDecimales(tem);//y formateamos la magnitud
        potencia = Integer.parseInt(calcu.Potencia.getText());
        calcu.DisplayTrigo.setText(formatearDecimales(Math.pow(tem, potencia))+"(cos("+angulo*potencia+")+isen("+angulo*potencia+")"+")");
    }
    
    public void euler()
    {
        trigonometrica();//realizamos los pasos de la trigonometrica para solo mostrarlo en la forma de euler
        calcu.resultadoEuler.setText(tem+"e^i("+angulo+")");//mostramos el resultado de euler
        
        
    }
    
    public void raiz()
    {
        double numRaiz=Double.parseDouble(calcu.numRaiz.getText()); //obtenemos el numero de raizes que necesitamos, para esto es entero para evitar los decimales
        double exponente = 0;//tenemos un exponente 0
        //magnitud();
        trigonometrica(); //llamamos a la funcion triconometrica para obtener ya los angulos y magnitud
        tem=(double)Math.pow(tem, (1/numRaiz));// sacamos delta/fita (la comparacion de euler con la raiz)
       
        for(int i=0; i<Integer.parseInt(calcu.numRaiz.getText()); i++) //realizamos un for de k0 hasta la raiz insertada por el usuario que se obtiene de la casilla numRaiz
        {
            exponente=formatearDecimales(((2*i*3.1416+angulo)/numRaiz)); //obtenemos el exponente
           calcu.listaRaices.add("k="+i+"   ("+tem+")(e^i"+exponente+")");//mostramos en la lista las n k´s de las raices
           
               
          
        }
        
    }
    
    public void resultadoConjugado()
    {
        NumIma*=-1; //sacamos el inverso del numero imaginario
        
        if(NumIma<0) //vemos si es mayo o menos para mostrar en el display
            {
                calcu.resultadoConjugado.setText(NumReal+""+NumIma+"i"); 
            }
            else
            {
               calcu.resultadoConjugado.setText(NumReal+"+"+ NumIma+"i"); 
            }
    }
    
    //FUNCIONES (No operaciones)
    
    public void resultadoDisplay() //el dato guardado en la variable 2 son los que se muestra en el displat
    {
        
        if(getNumIma2()<0) //se analiza si el numero imaginario es negativo o positivo para ver con cual concatenar
            {
                calcu.Display.setText(getNumReal2()+""+getNumIma2()+"i"); 
            }
            else
            {
               calcu.Display.setText(getNumReal2()+"+"+getNumIma2()+"i"); 
            }
    }
    
    public void limpia() //limpiamos las casillas para que el usuario inserte nuevos datos
    {
        calcu.NumeroComplejo.setText("0"); //casilla del numero complejo/imaginario
        calcu.NumeroReal.setText("0"); //casilla del numero real
        calcu.Potencia.setText("0");
        calcu.MagnitudTri.setText("0");
        calcu.AnguloTri.setText("0");
    }
    
    public static double formatearDecimales(Double numero)  
    {
    return Math.round(numero * Math.pow(10, 2)) / Math.pow(10, 2); //con la funcion matematica nos permite acordar los decimales a 2 cifras o redondearlos si es el caso
    }
    
    public void resguardo() //Resguadramos los datos en las variables 2 de la calculadora, para poder realizar la grafica
    {
        calcu.setAuxNI2(NumIma2);
        calcu.setAuxNR2(NumReal2);
    }
    
    
    //geters and setters de las variables

    public double getNumIma() {
        return NumIma;
    }

    public void setNumIma(double NumIma) {
        this.NumIma = NumIma;
    }

    public double getNumReal() {
        return NumReal;
    }

    public void setNumReal(double NumReal) {
        this.NumReal = NumReal;
    }

    public double getNumIma2() {
        return NumIma2;
    }

    public void setNumIma2(double NumIma2) {
        this.NumIma2 = NumIma2;
    }

    public double getNumReal2() {
        return NumReal2;
    }

    public void setNumReal2(double NumReal2) {
        this.NumReal2 = NumReal2;
    }

    public double getResIma() {
        return ResIma;
    }

    public void setResIma(double ResIma) {
        this.ResIma = ResIma;
    }

    public double getResReal() {
        return ResReal;
    }

    public void setResReal(double ResReal) {
        this.ResReal = ResReal;
    }

    public double getNID() {
        return NID;
    }

    public void setNID(double NID) {
        this.NID = NID;
    }

    public double getNRD() {
        return NRD;
    }

    public void setNRD(double NRD) {
        this.NRD = NRD;
    }

    public double getNM() {
        return NM;
    }

    public void setNM(double NM) {
        this.NM = NM;
    }

    public double getTem() {
        return tem;
    }

    public void setTem(double tem) {
        this.tem = tem;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }
    
    public double getmagnitud() {
        return magnitud;
    }

    public void setmagnitud(double magnitud) {
        this.magnitud = magnitud;
    }
    
    
   
    
    
    
}
