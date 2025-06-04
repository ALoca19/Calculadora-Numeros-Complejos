package Proyecto;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeriesCollection;
import Proyecto.Operaciones.*;
import static Proyecto.Operaciones.formatearDecimales;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import org.jfree.data.contour.ContourDataset;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYSeries;

public class Controlador implements ActionListener {

       //Inicializacion de componentes tipo operacion, calculadora y un string 
    private Operaciones opera;
    private Calculadora calcu;
    Operaciones A,B;
    private String Operacion = "Nula";//Nos guiara para ver que boton es oprimido, por lo que inicia como NULA
   

    public Controlador(Operaciones opera, Calculadora calcu) { //Metodo Constructor
        this.opera = opera;
        this.calcu = calcu;
        
        //Declaramos todos los botones
        calcu.BtnGraficaRaiz.addActionListener(this);
        calcu.BtnIgual.addActionListener(this);
        calcu.BtnSuma.addActionListener(this);
        calcu.BtnResta.addActionListener(this);
        calcu.BtnBorrar.addActionListener(this);
        calcu.BtnMulti.addActionListener(this);
        calcu.BtnDivi.addActionListener(this);
        calcu.BtnConjugado.addActionListener(this);
        calcu.BtnTrigo.addActionListener(this);
        calcu.BtnMagnitud.addActionListener(this);
        calcu.BtnGraficar.addActionListener(this);
        calcu.BtnEuler.addActionListener(this);
        calcu.BtnRaiz.addActionListener(this);
        calcu.BtnTrigonometrica.addActionListener(this);
        calcu.BtnTrigo_A_Complejo.addActionListener(this);
        calcu.BtnIngresarEuler.addActionListener(this);
        calcu.BtnDeMoivre.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) { //El escuchador

           
        if (ae.getSource() == calcu.BtnGraficar) { //Para graficar el resultado de las Z
            opera.graficarZ();

        }
        
        if(ae.getSource() == calcu.BtnGraficaRaiz){ //Grafica las raices de euler
            opera.graficadora();
                
        }
        
        
        if (ae.getSource() == calcu.BtnIgual) { //Detecta si se preciono el boton igual
            //Empieza a comparar para saber que operacion realizar
            if (Operacion == "S") { //Si es Suma
                opera.suma(); //Llama a la funcion suma
                opera.resultadoDisplay(); //llama a la funcion para mostrar el reusltado en el display
              

            }

            if (Operacion == "R") { //Si es resta
                opera.resta();
                opera.resultadoDisplay();
            }

            if (Operacion == "M") { //Si es multiplicacion
                opera.multi();
            }

            if (Operacion == "D") { //Si es division
                opera.divi();
                opera.resultadoDisplay();
            }

            if (Operacion == "C") { //Si es el conjugado
                //Toma los numero 1 para guardarlos en el numero 2 para mostrarse en el display
                opera.setNumReal2(opera.getNumReal()); //oma el dato del 1, guarda ene l 2
                opera.setNumIma2(opera.getNumIma());
                opera.resultadoDisplay();
            }
            
            if (Operacion == "Nula") { //Si es que no se realizo ninguna operacion toma el numero imaginario y real, los guarda en las variables 2 y muestra en display
                opera.setNumIma2(Double.parseDouble(calcu.NumeroComplejo.getText()));
                opera.setNumReal2(Double.parseDouble(calcu.NumeroReal.getText()));
                
                if (opera.getNumIma2() < 0) {
                    calcu.Display.setText(opera.getNumReal2() + "" + opera.getNumIma2() + "i");
                } else {
                    calcu.Display.setText(opera.getNumReal2() + "+" + opera.getNumIma2() + "i");

                }
                
            }
            
            

        }

        if (ae.getSource() == calcu.BtnSuma) { //Si se oprime el boton suma

            opera.suma(); //LLama a la funcion suma
            opera.resultadoDisplay(); //muestra resultado en display
            Operacion = "S"; //se cambia la operacion para indicar que la operaicon es sum

        }
        
        //IGUAL COMO SE HIZO PARA SUMA SE REALIZA EL MISMO PROCESO PARA RESTA

        if (ae.getSource() == calcu.BtnResta) {
            opera.resta();
            opera.resultadoDisplay();
            Operacion = "R";
        }

        if (ae.getSource() == calcu.BtnMulti) { //Si es multiplicacion
            //TOMA LOS NUMEROS DE LAS CASILLAS
            opera.setNumIma(Double.parseDouble(calcu.NumeroComplejo.getText())); 
            opera.setNumReal(Double.parseDouble(calcu.NumeroReal.getText()));
            
            //ANALIZA SI SE TENIA ALGUN NUMERO GUARDADO ANTERIORMENTE EN EL DISPLAY O NO

            if (opera.getNumIma2() == 0 && opera.getNumReal2() == 0) { //SI no lo tiene lo guarda esperando a que se inserte en segundo numero y la operacion se realiza en el igual
                if (opera.getNumIma() < 0) {
                    calcu.Display.setText(opera.getNumReal() + "" + opera.getNumIma() + "i");
                } else {
                    calcu.Display.setText(opera.getNumReal() + "+" + opera.getNumIma() + "i");

                }
            } else {
                opera.multi(); //Si tenia un numero anteriormente guardado, lo multiplica con ese
            }
            Operacion = "M"; //marca que la operaicon es M de muiltiplicar

        }

        if (ae.getSource() == calcu.BtnDivi) { //Si es el boton dividir, hace el mismo proceso que con el de multiplicar
            //guarda numeros
            opera.setNumIma(Double.parseDouble(calcu.NumeroComplejo.getText()));
            opera.setNumReal(Double.parseDouble(calcu.NumeroReal.getText()));

            if (opera.getNumIma2() == 0 && opera.getNumReal2() == 0) { //no tiene numero guardado
                if (opera.getNumIma() < 0) {
                    calcu.Display.setText(opera.getNumReal() + "" + opera.getNumIma() + "i");
                } else {
                    calcu.Display.setText(opera.getNumReal() + "+" + opera.getNumIma() + "i");

                }
            } else { //si tiene guardado uno
                opera.divi();
            }
            Operacion = "D";

        }

        if (ae.getSource() == calcu.BtnConjugado) { //Si se preciona el boton de conjugado
            //Toma los numeros de las casiilas
            opera.setNumIma(Double.parseDouble(calcu.NumeroComplejo.getText()));
            opera.setNumReal(Double.parseDouble(calcu.NumeroReal.getText()));
            opera.resultadoConjugado();//Muestra en el apartado de conjugado
            Operacion = "C"; //Marca la operacion conjugado, por si el usuario despues al dar igual este se pasa al display

        }

        if (ae.getSource() == calcu.BtnBorrar) { //Se formatea todas las variables y ventadas de la calculadora para que queden en 0
            opera.setNumIma(0);
            opera.setNumIma2(0);
            opera.setNumReal2(0);
            opera.setNumReal(0);
            opera.limpia();
            calcu.Display.setText("0+0i");
            calcu.resultadoConjugado.setText("Nulo");
            Operacion = "Nula";
            calcu.DisplayTrigo.setText("r(cos + i sen)");
            calcu.ResultadoMagnitud.setText("Nulo");
            calcu.numRaiz.setText("0");
            calcu.listaRaices.clear(); //limpia la lista
            calcu.resultadoEuler.setText("ie^(iø)");
        }
        
        if(ae.getSource() == calcu.BtnTrigonometrica)
        {
        opera.setAngulo(Double.parseDouble(calcu.AnguloTri.getText()));
        opera.setmagnitud(Double.parseDouble(calcu.MagnitudTri.getText()));
        opera.ingresar_trigonometrica();
        }
        
        if(ae.getSource() == calcu.BtnTrigo_A_Complejo)
        {
           opera.setAngulo(Double.parseDouble(calcu.AnguloTri.getText()));
           opera.setmagnitud(Double.parseDouble(calcu.MagnitudTri.getText()));
           opera.Trigo_a_Complejo();
        }
        
        if(ae.getSource() == calcu.BtnDeMoivre)
        {
           opera.setNumIma(Double.parseDouble(calcu.NumeroComplejo.getText()));
           opera.setNumReal(Double.parseDouble(calcu.NumeroReal.getText()));
           opera.DeMoivre();
        
        }
        
        if(ae.getSource() == calcu.BtnIngresarEuler)
        {
          opera.setAngulo(Double.parseDouble(calcu.AnguloTri.getText()));
          opera.setmagnitud(Double.parseDouble(calcu.MagnitudTri.getText()));
          opera.ingresar_euler();
        
        }

        if (ae.getSource() == calcu.BtnMagnitud) {
            //Toma el numero de casilla
            opera.setNumIma(Double.parseDouble(calcu.NumeroComplejo.getText()));
            opera.setNumReal(Double.parseDouble(calcu.NumeroReal.getText()));
            opera.magnitud(); //llama a la funcion magnitud

        }

        if (ae.getSource() == calcu.BtnTrigo) {
            opera.setNumIma(Double.parseDouble(calcu.NumeroComplejo.getText()));
            opera.setNumReal(Double.parseDouble(calcu.NumeroReal.getText()));
            opera.trigonometrica();

        }

        if (ae.getSource() == calcu.BtnEuler) {
            opera.setNumIma(Double.parseDouble(calcu.NumeroComplejo.getText()));
            opera.setNumReal(Double.parseDouble(calcu.NumeroReal.getText()));
            opera.euler();

        }
        
      

        if (ae.getSource() == calcu.BtnRaiz) {
            calcu.listaRaices.clear();
            opera.setNumIma(Double.parseDouble(calcu.NumeroComplejo.getText()));
            opera.setNumReal(Double.parseDouble(calcu.NumeroReal.getText()));
            opera.raiz();

        }
       
    }

    public void bloquearBtn() {
        calcu.BtnConjugado.setEnabled(false);
        calcu.BtnDivi.setEnabled(false);
        calcu.BtnMulti.setEnabled(false);
        calcu.BtnSuma.setEnabled(false);
        calcu.BtnResta.setEnabled(false);
    }

    public void iniciarBtn() {
        calcu.BtnConjugado.setEnabled(true);
        calcu.BtnDivi.setEnabled(true);
        calcu.BtnMulti.setEnabled(true);
        calcu.BtnSuma.setEnabled(true);
        calcu.BtnResta.setEnabled(true);
        calcu.BtnIgual.setEnabled(true);

    }
    
   

}
