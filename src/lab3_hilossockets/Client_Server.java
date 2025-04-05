package lab3_hilossockets;

import java.io.*;
import static java.lang.Math.random;
import java.net.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;

public class Client_Server {

    public static Scanner scanner = new Scanner(System.in);
    public static List<Long> vector;
    public static int opcion;
    public static Long timeLimit;
    public static boolean sorted = false;
    public static long totalTime = 0;
    public static long elapsedTime = 0;
    public static int workerActual = 0;

    public static final String IP_ADDRESS = Config.SERVER_IP_ADDRESS;
    public static final int PORT = Config.SERVER_PORT;
    public static final int MAX_WORKERS = Config.SERVER_MAX_WORKERS;

    private static Socket worker_0;
    private static Socket worker_1;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Cliente();
        Server();
    }

    public static void Cliente() throws FileNotFoundException {
        // Elegir el problema
        try {
            System.out.println("Seleccione el problema a resolver:");
            System.out.println("1. Ordenar con Mergesort");
            System.out.println("2. Ordenar con Heapsort");
            System.out.println("3. Ordenar con Quicksort");
            opcion = scanner.nextInt();

            if (opcion < 1 || opcion > 3) {
                System.out.println("Opción inválida. Saliendo...");
                System.exit(0);
            }

            System.out.print("Ingrese el tiempo límite por worker (en segundos): ");
            timeLimit = (long) (scanner.nextFloat() * 1000);

            if (timeLimit <= 0) {
                System.out.println("El tiempo límite debe ser mayor que cero. Saliendo...");
                System.exit(0);
            }

            llenarVector();
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Saliendo...");
            System.exit(1);
        }
    }

    public static void Server() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT); ServerSocket serverSocket1 = new ServerSocket(PORT + 1)) {
            System.out.println("Esperando conexiones de workers...");
            List<Socket> workerSockets = new ArrayList<>();

            // Aceptar conexiones de los dos workers
            worker_0 = serverSocket.accept();
            System.out.println("Worker_0 conectado.");
            worker_1 = serverSocket.accept();
            System.out.println("Worker_1 conectado.");

            // Flujos de datos para cada worker
            ObjectOutputStream out_0 = new ObjectOutputStream(worker_0.getOutputStream());
            ObjectInputStream in_0 = new ObjectInputStream(worker_0.getInputStream());
            ObjectOutputStream out_1 = new ObjectOutputStream(worker_1.getOutputStream());
            ObjectInputStream in_1 = new ObjectInputStream(worker_1.getInputStream());

            // Enviar información para conectar entre ellos
            out_0.writeObject(worker_1.getInetAddress().getHostAddress());
            out_0.writeInt(worker_1.getPort());

            out_1.writeObject(worker_0.getInetAddress().getHostAddress());
            out_1.writeInt(worker_0.getPort());

            out_0.flush();
            out_1.flush();
            // Alternar entre los workers hasta que el vector esté ordenado
            while (!sorted) {
                if (workerActual == 0) {
                    handleWorker(worker_0, in_0, out_0, workerActual);
                } else {
                    if (!isSorted(vector)) {
                        handleWorker(worker_1, in_1, out_1, workerActual);
                    }
                }
                // Cambiar al siguiente worker
                workerActual = (workerActual + 1) % 2;
            }

            // Resultado final
            System.out.println("Vector ordenado: " + vector);
            System.out.println("Tiempo total tomado: " + totalTime + " ms");

        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } finally {
            worker_0.close();
            worker_1.close();
        }
    }

    private static void handleWorker(Socket socket, ObjectInputStream in, ObjectOutputStream out, int workerId) {
        try {

            // Enviar datos al worker
            out.writeInt(opcion);
            out.writeLong(timeLimit); // Tiempo límite en milisegundos
            out.writeObject(vector);
            out.flush();

            // Recibir datos del worker
            vector = (List<Long>) in.readObject();
            elapsedTime = in.readLong();
            totalTime += elapsedTime;

            sorted = isSorted(vector);

            // Log de progreso
            System.out.println("Worker_" + workerId + " procesó.");
            System.out.println("Tiempo tomado por Worker_" + workerId + ": " + elapsedTime + " ms");

        } catch (Exception e) {
            System.err.println("Error procesando datos con Worker_" + workerId + ": " + e.getMessage());
        }
    }

    public static void llenarVector() throws FileNotFoundException {
        vector = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader("random_numbers.txt"))) {
            String cadena;
            while ((cadena = bf.readLine()) != null) {
                vector.add(Long.parseLong(cadena));
            }
        } catch (Exception e) {
            System.err.println("Error " + e);
        }
    }

    public static boolean isSorted(List<Long> vector) {
        for (int i = 1; i < vector.size() - 1; i++) {
            if (vector.get(i - 1) > vector.get(i)) {
                return false;
            }
        }
        return true;
    }
}
