package com.company;

public class Main {

    public static double[] Calcular(double[][] matriz, double b0, double b1){
        double[] resultado= new double[3];

        double aux = 0;
        for (int i = 0; i < matriz.length; i++) {
             aux = matriz[i][0] -(b0+b1*matriz[i][1]);
            resultado[0] += aux;
            resultado[1] +=matriz[i][1]*aux;
            resultado[2] +=aux;
        }

        resultado[0] = -2*(resultado[0])/ matriz.length;
        resultado[1] = -2*(resultado[1])/ matriz.length;
        resultado[2] = (resultado[2]*resultado[2])* 1/matriz.length;

        return resultado;
    }

    public static void getGradinte(double[][] matriz){
        double b0 = 0, b1 = 0, a = 0.0005, error = 0;
        for (int i = 0; i <150000 ; i++) {
            double[] aux = Calcular( matriz,  b0,  b1);
            //System.out.println("iteracion "+i+" -------------------------");
            b0 = (b0 - a*aux[0]);
            b1 = (b1 - a*aux[1]);
            error = aux[2];

           // System.out.println(aux[0]);
           // System.out.println(aux[1]);
        }
        System.out.println("error: "+error);
        System.out.println("b0= "+b0);
        System.out.println("b1= "+b1);


    }

    public static void generador(double x){
        double x2 = x;
        double a=13;
        double c = 7;
        double m = 9;
        for (int i = 0; i < 1000; i++) {
            x = (a*x +c)%m;
            System.out.println("  x"+i+": "+x +"  r"+i+": "+(x/m-1) );
            if (x2 == x)
                break;
        }
    }
    static double[][] dataset = {
            {1,1,0},
            {4,2,1},
            {2,4,1}

    };
    static double[][] x= getX(dataset);
    static double[][] y= getY(dataset);
    public static void ImprimirMatriz(double[][] m){
        for (int i = 0; i < m.length; i++) { //filas
            for (int j = 0; j < m[i].length ; j++) { //columnas
                System.out.print(m[i][j] + " ");

            }
            System.out.println("");
        }
    }

    public static double[][] getX(double[][] x){
        double[][] resultado = new double[x.length][x[0].length];
        for (int i = 0; i < x.length; i++) { //filas
            for (int j = 1; j <x[0].length ; j++) {
                resultado[i][0] =1;
                resultado[i][j] =x[i][j-1];
            }
        }
        return resultado;
    }
    public static double[][] getY(double[][] y){
        double[][] resultado = new double[y.length][1];
        for (int i = 0; i < y.length; i++) { //filas
            resultado[i][0] = y[i][y[0].length-1];

        }
        return resultado;
    }
    public static double Sigmoide(double x){
        return 1/(1+(Math.exp(-x)));
    }
    public static double getFuncionX(int numero, double[]w){
        double resultado  = 0;
        //System.out.println("valiendo madres: "+ numero);
        for (int i = 1; i < w.length; i++) {
            resultado += x[i][numero]*w[i];
        }
        //System.out.println(w[0]+resultado);
        return Sigmoide(w[0]+resultado);

    }

    public static void LR(){
        double[] Wold = {0,0,0};
        double[] Wnew = new double[Wold.length];
        double alpha =0.1;
        //ImprimirMatriz(y);

        for (int q = 0; q < 100; q++) {

            System.out.println(Wold[0]+ " " + Wold[1]+ " ");

            for (int i = 0; i <x.length ; i++) {
                double wtem = 0;

                for (int j = 0; j < x[0].length; j++) {
                    wtem += (getFuncionX(j, Wold)-y[j][0] )*x[i][j];
                }
                Wnew[i] = Wold[i] - alpha * wtem;
                wtem = 0;
            }
            for (int i = 0; i <Wold.length ; i++) {
                Wold[i] = Wnew[i];
                //System.out.println(Wold[i]);
            }
        }
    }




    public static void main(String[] args) {
        LR();
        //ImprimirMatriz(getX( dataset));
        //ImprimirMatriz(getY( dataset));


        //generador(6);
        //getGradinte(new  double[][]{{651,23},{762,26},{856,30},{1063,34},{1190,43},{1298,48},{1421,52},{1440,57},{1518,58}});




    }
}
