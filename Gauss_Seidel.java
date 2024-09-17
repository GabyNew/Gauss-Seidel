package clases;

import java.util.Scanner;

public class Gauss_Seidel {

    // Método para implementar Gauss-Seidel
    public static void gaussSeidel(double[][] A, double[] b, double[] x, int maxIterations, double tolerance) {
        int n = A.length;
        double[] xOld = new double[n];
        
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            System.arraycopy(x, 0, xOld, 0, n); // Copia el valor de x a xOld

            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sum += A[i][j] * x[j];
                    }
                }
                x[i] = (b[i] - sum) / A[i][i]; // Actualiza el valor de x[i]
            }

            // Verificar la convergencia
            double maxError = 0;
            for (int i = 0; i < n; i++) {
                maxError = Math.max(maxError, Math.abs(x[i] - xOld[i]));
            }

            if (maxError < tolerance) {
                System.out.println("Converge después de " + (iteration + 1) + " iteraciones.");
                return;
            }
        }
        System.out.println("No converge en el número máximo de iteraciones.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ingresar el tamaño de la matriz
        System.out.print("Ingrese el número de ecuaciones: ");
        int n = scanner.nextInt();

        // Crear las matrices
        double[][] A = new double[n][n];
        double[] b = new double[n];
        double[] x = new double[n];  // Vector inicial

        // Ingresar los elementos de la matriz A
        System.out.println("Ingrese los coeficientes de la matriz A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("A[" + i + "][" + j + "]: ");
                A[i][j] = scanner.nextDouble();
            }
        }

        // Ingresar los elementos del vector b
        System.out.println("Ingrese los términos independientes del vector b:");
        for (int i = 0; i < n; i++) {
            System.out.print("b[" + i + "]: ");
            b[i] = scanner.nextDouble();
        }

        // Ingresar el vector inicial
        System.out.println("Ingrese los valores iniciales del vector x:");
        for (int i = 0; i < n; i++) {
            System.out.print("x[" + i + "]: ");
            x[i] = scanner.nextDouble();
        }

        // Ingresar el número máximo de iteraciones y la tolerancia
        System.out.print("Ingrese el número máximo de iteraciones: ");
        int maxIterations = scanner.nextInt();
        
        System.out.print("Ingrese la tolerancia: ");
        double tolerance = scanner.nextDouble();

        // Llamar al método de Gauss-Seidel
        gaussSeidel(A, b, x, maxIterations, tolerance);

        // Mostrar la solución
        System.out.println("Solución: ");
        for (double value : x) {
            System.out.printf("%.6f ", value);
        }

        scanner.close();
    }
}
