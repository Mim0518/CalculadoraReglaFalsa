import java.util.*;
public class CalcReglaFalsa {
    public static void main(String[] args) {
        programa();
    }

    static double ax[] = new double[5];
    static boolean pol = false;    
    static int cont = 0;
    static double Es = 0, Ea = 0, x = 0, c = 0, x1 = 0, x2 = 0, xr = 0, fxr = 0, fx1 = 0, fx2 = 0;
    static Scanner sc = new Scanner(System.in);

    static void programa(){
        System.out.println("P A S O S");
        System.out.println("[1] - Ingresar polinomio");
        ingresaPol();
        System.out.println("[2] - Ingresar intervalo inicial");
        ingresaInter();
        System.out.println("[3] - Ingresar cifras significativas");
        ingresaCif();
        metodoRegla();
    }
    static void metodoRegla(){
       Ea = 100;
       boolean esX1 = true;
       fx1 = polinimio(x1);
       fx2 = polinimio(x2);
       xr = ((fx2*x1)-(fx1*x2))/(fx2-fx1);
       imprimir();
       do{
           fx1 = polinimio(x1);
           fx2 = polinimio(x2);
           xr = ((fx2*x1)-(fx1*x2))/(fx2-fx1);
           fxr = polinimio(xr);
           if(cont == 0) Ea = 100;
           else if(esX1) Ea = Math.abs((xr-x1)/xr)*100;
           else if(!esX1) Ea = Math.abs((xr-x2)/xr)*100;
           cont++;
           imprimir();
           if(fx1*fxr > 0){
               x1 = xr;
               esX1 = true;
           } else if(fx1*fxr < 0){
               x2 = xr;
               esX1 = false;
           }
       }while(Ea>Es);
       System.out.print("La raíz aproximada es: "+xr);
    }
    static void imprimir(){
        String abc = "-------------------------------------------------------------------------------";
        if(cont == 0){
            System.out.printf("+%s+\n", abc);
            System.out.printf("|%-6s|%-6s|%-9s|%-9s|%-8s|%-9s|%-10s|%-8s|%-8s|\n", "x1", "x2", "f(x1)", "f(x2)","xr","f(xr)", "f(x1)f(xr)", "ea","es");
            System.out.printf("+%s+\n", abc);
        }
        System.out.printf("|%04.4f|%04.4f|%05.6f|%05.6f|%04.4f|%05.6f|%04.4f|%04.4f|%04.4f|\n",x1, x2, fx1, fx2, xr, fxr, fx1*fxr,Ea,Es);
 
    }
    static void ingresaCif(){
        double temp = 0;
        do { 
            System.out.print("Ingrese las cifras significativas deseadas: ");
            temp = sc.nextDouble();
        } while(!(temp > 0));
        Es = 0.5 * (Math.pow(10, (2-temp)));
    }
    static void ingresaInter(){
        do { 
            System.out.print("Ingrese el primer punto (x1): ");
            x1 = sc.nextDouble();
            System.out.print("Ingrese el primer punto (x2): ");
            x2 = sc.nextDouble();
        } while(!intervaloValido());
    }
    static boolean intervaloValido(){
        if(!(x1 < 0 || x2 < 0) && (x1 < x2)) return true;
        else System.out.println("El intervalo no es válido");
        return false;
    }
    static void ingresaPol(){
        for (int i = 5; i > 0; i--) {
            System.out.print("Ingrese el coeficiente de x a la "+i + ":");
            ax[i-1] = sc.nextDouble();
        }
        System.out.print("Ingrese el término independiente: ");
        c = sc.nextDouble();

    }

    static double polinimio(double x){
        double n = 0;
        for (int i = 5; i > 0; i--) {
            n = n + (ax[i-1] * (Math.pow(x, i)));
        }
        return n+c;
    }

}
