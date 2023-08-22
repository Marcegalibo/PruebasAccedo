import java.util.InputMismatchException;
import java.util.Scanner;

public class CodigoMorse{

    public static void main(String[]args) {
        String[] abecedario = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        boolean fin = false;
        Scanner s = new Scanner(System.in);
        int opc = 0;

        System.out.println("Bienvenido al traductor de código morse");
        do {
            System.out.println("Elija una opción");
            System.out.println("1. Traducir español a morse");
            System.out.println("2. Traducir morse a español");
            System.out.println("3. Salir");
            opc = comprobarDatoEnteroIngresado(s,1,3);
            s.nextLine();
            fin = procesarOpcion(opc, s, abecedario, morse);
        }while(!fin);
    }
public static boolean procesarOpcion(int opc, Scanner s, String[]abecedario, String[]morse){       
        if(opc==1){
            System.out.println("Ingrese la frase que quiere traducir");
            String frase = s.nextLine();
            String fraseATraducir = frase.toUpperCase();            
            String mensajeTraducido = fraseAMorse(fraseATraducir, abecedario, morse);
            System.out.println("Traducción del mensaje a codigo morse:");
            System.out.println(mensajeTraducido);
        }else if(opc==2){
            System.out.println("Ingrese la frase que quiere traducir en morse");
            String frase = s.nextLine();
            String mensajeTraducido = morseAFrase(frase, abecedario,morse);
            System.out.println("Traducción al español: ");
            System.out.println(mensajeTraducido);         
        }else if(opc==3){
            return true;    
        }
        return false;
}
public static String fraseAMorse(String fraseATraducir, String[] abecedario, String[] morse){
    String mensajeMorse = "";
    for (int i = 0; i < fraseATraducir.length(); i++) {
        int j = 0;
        boolean termina = false;
        if(Character.toString(fraseATraducir.charAt(i)).equals(" ")){
            mensajeMorse += "  ";
        }else{
            do{
                if(Character.toString(fraseATraducir.charAt(i)).equals(abecedario[j])){
                    mensajeMorse += morse[j] + " ";
                    termina = true;
                }
                j++;
                if(j==abecedario.length){
                    if(!termina){
                        termina = true;
                    }
                }
            }while(!termina);
        }
    }
    return mensajeMorse;
}
public static String morseAFrase(String frase, String[] abecedario, String[] morse){
    String morseEspañol = "";
    String caracterMorse = "";
    int i = 0;

    do{
        boolean terminaCaracter = false;
        do{
            if(Character.toString(frase.charAt(i)).equals(" ")) {
                terminaCaracter = true;
            }else{
                caracterMorse+=Character.toString(frase.charAt(i));
                i++;
            }
        }while((!terminaCaracter)&&(i<frase.length()));
        int j = 0;
        boolean termina = false;

        do {
            if(caracterMorse.equals(morse[j])) {
                morseEspañol += abecedario[j];
                termina = true;
            }
            j++;
            if(j==morse.length){
                if(!termina){
                    termina=true;
                }
            }
        }while(!termina);

        caracterMorse ="";
        i++;

        try{
        if(Character.toString(frase.charAt(i)).equals(" ")) {
            if(Character.toString(frase.charAt(i+1)).equals(" ")) {
                morseEspañol += " ";
                i+=2;
            }
        }
        }catch(StringIndexOutOfBoundsException e){

        }catch(Exception e){

        }
    }while(i<frase.length());
    
    return morseEspañol;
}

public static int comprobarDatoEnteroIngresado(Scanner s, int min, int max){
    int valor = 0;
    boolean error = false;
    do{
        error = false;
        try{
            valor = s.nextInt();
            if(!((valor>=min)&&(valor<=max))) {
                System.out.println("El valor ingresado no es valido");
                System.out.println("El valor debe estar entre " + min +" y " + max);
                error = true;
            }
        }catch(InputMismatchException e){
            System.out.println("Tipo de dato mal ingresado, se esperaba un número");
            error = true;
            s.nextLine();
        }catch(Exception e) {
            System.out.println("Error inesperado." + e);
            error=true;            
        }
    }while(error);
    return valor;
}
}
