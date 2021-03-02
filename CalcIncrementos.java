import java.util.*;
public class CalcIncrementos {
    public static void main(String[] args) {
        programa();
    }

    static double ax[] = new double[5];
    static boolean pol = false;    
    static int cont = 0;
    static double x[][] = new double[4][4000];
    static double Es = 0, Ea = 0, c = 0, x1 = 0, x2 = 0, xr = 0, fxr = 0, fx1 = 0, fx2 = 0, it = 0, q = 0, dX = 0;
    static Scanner sc = new Scanner(System.in);

    static void programa(){
        System.out.println("P A S O S");
        System.out.println("[1] - Ingresar polinomio");
        ingresaPol();
        System.out.println("[2] - Ingresar la aproximación inicial");
        ingresaAprox();
        System.out.println("[3] - Ingresar el incremento inicial");
        ingresarDelta();
        System.out.println("[4] - Ingresar el factor de división (q)");
        ingresaFac();
        System.out.println("[5] - Ingresar máximo de iteraciones");
        iteracionesMax();
        System.out.println("[6] - Ingresar cifras significativas");
        ingresaCif();
        metodoIncrementos();
    }
    static void iteracionesMax(){
        System.out.print("Ingrese el número máximo de iteraciones permitidas: ");
        it = sc.nextInt();
    }
    static void ingresarDelta(){
        System.out.print("Ingrese el incremento inicial (Delta x): ");
        dX = sc.nextDouble();
    }
    static void metodoIncrementos(){
        Ea = 100;
        int contTemp = 0;
        x[0][cont] = x1;
        x[1][cont] = dX;
        x[2][cont] = polinimio(x1);
        x[3][cont] = 100;
        imprimir();
        do {
            if(x[2][cont]<0){
                contTemp = cont;
                cont++;
                
                x[1][cont] = x[1][cont-1];
                x[0][cont] = x[0][cont-1] + x[1][cont];
                x[2][cont] = polinimio(x[0][cont]);
                x[3][cont] = Math.abs((x[0][cont]-x[0][cont-1])/x[0][cont])*100;
                Ea = x[3][cont];
            }
            else if(x[2][cont]>0){
                cont++;
                x[1][cont] = x[1][cont-1] / q;
                x[0][cont] = x[0][contTemp] + x[1][cont];
                x[2][cont] = polinimio(x[0][cont]);
                x[3][cont] =Math.abs((x[0][cont]-x[0][contTemp])/x[0][cont])*100;
                Ea = x[3][cont];
            }
            imprimir();
            if(cont == it) break;
        } while (Ea>Es || cont < it);
        System.out.print("La raíz aproximada es: "+x[0][cont]);
    }

    static void imprimir(){
        String abc = "---------------------------------------------------";
        if(cont == 0){
            System.out.printf("+%s+\n", abc);
            System.out.printf("|%-6s|%-15s|%-10s|%-8s|%-8s|\n", "x", "\u0394x", "f(x)", "ea","es");
            System.out.printf("+%s+\n", abc);
        }
        System.out.printf("|%04.4f|%04.13f|%05.6f|%04.4f|%04.4f|\n",x[0][cont], x[1][cont], x[2][cont], x[3][cont], Es);
 
    }
    static void ingresaFac(){
        double temp = 0;
        do { 
            System.out.print("Ingrese el factor de división deseado: ");
            temp = sc.nextDouble();
        } while(!(temp > 0));
        q = temp;
    }
    static void ingresaCif(){
        double temp = 0;
        do { 
            System.out.print("Ingrese las cifras significativas deseadas: ");
            temp = sc.nextDouble();
        } while(!(temp > 0));
        Es = 0.5 * (Math.pow(10, (2-temp)));
    }

    static void ingresaAprox(){
        do { 
            System.out.print("Ingrese la aproximación inicial (x0): ");
            x1 = sc.nextDouble();
        } while(!intervaloValido());
    }

    static boolean intervaloValido(){
        if(x1 >= 0) return true;
        else System.out.println("El aproximación no es válido");
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
