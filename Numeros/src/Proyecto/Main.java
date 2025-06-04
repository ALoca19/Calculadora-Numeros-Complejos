package Proyecto;

public class Main {
    
    
    
    
    public static void main(String[] args) {
       
        
        Calculadora calcu = new Calculadora(); //Inicializamos los componentes de la calculadora
        
        Operaciones o= new Operaciones(calcu); //declaramos un objeto de tipo operacion para mandarlo despues al controlador
        Controlador controlador=new Controlador(o, calcu); //inicializamos un objeto de tipo contolador, mandandole dos parametros: uno tipo operacion y otro calculadora
    
        calcu.setVisible(true);
    
    
    
    
    }
}
