
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.Random;

public final class Juego {

    //atributos
    //presentacion
    //juego
    JLabel matriz[][];
    int mat[][];
    int matAux[][];
    String Jugador;
    Random aleatorio;
    Timer tiempo;
    Timer tiempoEspera;
    Timer tiempoEspera1;

    int min;
    int seg;
    int contador;
    int contSegEspera;
    int ban;
    int ban1;

    int antNum;
    int antX;
    int antY;
    int actualNum;
    int actualX;
    int actualY;

    //constructor
    public Juego() {
        //JFrame ventana
        JFrame ventana = new JFrame("Juego de Memoria");
        ventana.setSize(405, 600);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);

        //JPanel presentacion
        JPanel panelPresentacion = new JPanel();
        panelPresentacion.setSize(ventana.getWidth(), ventana.getHeight());
        panelPresentacion.setLocation(0, 0);
        panelPresentacion.setLayout(null);
        panelPresentacion.setVisible(true);

        //fondo de presentacion
        JLabel fondoPresentacion = new JLabel();
        fondoPresentacion.setSize(ventana.getWidth(), ventana.getHeight());
        fondoPresentacion.setLocation(0, 0);
        fondoPresentacion.setIcon(new ImageIcon("assets/image/portada.jpg"));
        fondoPresentacion.setVisible(true);
        panelPresentacion.add(fondoPresentacion);

        //boton de jugar
        JLabel botonJugar = new JLabel();
        botonJugar.setSize(320, 180);
        botonJugar.setLocation(0, 0);
        botonJugar.setIcon(new ImageIcon("assets/image/botonJugar.gif"));
        botonJugar.setVisible(true);
        panelPresentacion.add(botonJugar, 0);

        //panelJuego
        JPanel panelJuego = new JPanel();
        panelJuego.setSize(ventana.getWidth(), ventana.getHeight());
        panelJuego.setLocation(0, 0);
        panelJuego.setLayout(null);
        panelJuego.setVisible(true);

        //fondo de juego
        JLabel FondoDeJuego = new JLabel();
        FondoDeJuego.setSize(ventana.getWidth(), ventana.getHeight());
        FondoDeJuego.setLocation(0, 0);
        FondoDeJuego.setIcon(new ImageIcon("assets/image/FondoJuego.png"));
        FondoDeJuego.setVisible(true);
        panelJuego.add(FondoDeJuego);

        //nombre del jugador
        JLabel nombreJugador = new JLabel();
        nombreJugador.setSize(200, 40);
        nombreJugador.setLocation(50, 20);
        nombreJugador.setForeground(Color.BLACK);
        nombreJugador.setVisible(true);
        panelJuego.add(nombreJugador, 0);

        //cronometro
        JLabel cronometro = new JLabel();
        cronometro.setSize(200, 40);
        cronometro.setLocation(ventana.getWidth() - 200, 20);
        cronometro.setForeground(Color.BLACK);
        cronometro.setVisible(true);
        panelJuego.add(cronometro, 0);

        //matriz logica
        mat = new int[4][5];
        matAux = new int[4][5];
        aleatorio = new Random();
        this.numerosAleatorios();

        //matriz de imagenes
        matriz = new JLabel[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = new JLabel();
                matriz[i][j].setBounds(40 + (i * 75), 50 + (j * 75), 75, 75);
                matriz[i][j].setIcon(new ImageIcon("assets/image/" + matAux[i][j] + ".png"));
                matriz[i][j].setVisible(true);
                panelJuego.add(matriz[i][j], 0);
            }
        }

        //tiempo
        min = 0;
        seg = 0;

        tiempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seg++;
                if (seg == 60) {
                    min++;
                    seg = 00;
                }
                cronometro.setText("Tiempo: " + min + " : " + seg);

            }
        });

        //tiempo de espera 
        contSegEspera = 0;
        tiempoEspera = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contSegEspera++;
            }
        });
        tiempoEspera.start();
        tiempoEspera.stop();
        contSegEspera = 0;
        ban = 0;
        ban1 = 0;

        //evento de click de las cartas
        contador = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < 5; l++) {
                                if (e.getSource() == matriz[k][l]) {
                                    //System.out.println(k+" "+l);
                                    if (matAux[k][l] == 0 && contador != 2) {

                                        matAux[k][l] = mat[k][l];
                                        matriz[k][l].setIcon(new ImageIcon("assets/image/" + matAux[k][l] + ".png"));
                                        contador++;
                                        actualNum = mat[k][l];
                                        actualX = k;
                                        actualY = l;

                                        if (contador == 1) {
                                            antNum = mat[k][l];
                                            antX = k;
                                            antY = l;
                                        }

                                        tiempoEspera1 = new Timer(500, new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {

                                                if (contador == 2 && ban1 == 0) {
                                                    tiempoEspera.restart();
                                                    ban1 = 1;
                                                }
                                                if (contador == 2 && contSegEspera == 2) {
                                                    tiempoEspera.stop();
                                                    contSegEspera = 0;
                                                    if (matAux[actualX][actualY] == matAux[antX][antY]) {
                                                        matAux[actualX][actualY] = -1;
                                                        matAux[antX][antY] = -1;
                                                        matriz[actualX][actualY].setIcon(new ImageIcon("assets/image/" + matAux[actualX][actualY] + ".png"));
                                                        matriz[antX][antY].setIcon(new ImageIcon("assets/image/" + matAux[antX][antY] + ".png"));
                                                        contador = 0;

                                                        //ganar
                                                        int acum = 0;
                                                        for (int m = 0; m < 4; m++) {
                                                            for (int n = 0; n < 5; n++) {
                                                                if (matAux[m][n] == -1) {
                                                                    acum++;
                                                                }
                                                            }
                                                        }
                                                        if (acum == 20) {
                                                            tiempo.stop();
                                                            JOptionPane.showMessageDialog(ventana, "FELICITACIONES GANO con un Tiempo: " + min + " " + seg);

                                                            for (int m = 0; m < 4; m++) {
                                                                for (int n = 0; n < 5; n++) {
                                                                    mat[m][n] = 0;
                                                                    matAux[m][n] = 0;
                                                                    matriz[m][n].setIcon(new ImageIcon("assets/image/" + matAux[m][n] + ".png"));
                                                                }
                                                            }
                                                            numerosAleatorios();
                                                            min = 0;
                                                            seg = 0;
                                                            tiempo.restart();
                                                        }
                                                    }
                                                    for (int m = 0; m < 4; m++) {
                                                        for (int n = 0; n < 5; n++) {
                                                            if (matAux[m][n] != 0 && matAux[m][n] != -1) {
                                                                matAux[m][n] = 0;
                                                                matriz[m][n].setIcon(new ImageIcon("assets/image/" + matAux[m][n] + ".png"));
                                                                contador = 0;
                                                            }
                                                        }
                                                    }
                                                    tiempoEspera1.stop();
                                                    ban1 = 0;
                                                }
                                            }
                                        });
                                        if (ban == 0) {
                                            tiempoEspera.start();
                                            ban = 1;
                                        }
                                        if (contador == 2) {
                                            tiempoEspera1.restart();
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }

        botonJugar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //System.out.println("presione el boton");
                Jugador = JOptionPane.showInputDialog(ventana, "Nombre del jugador", "Escribe aqui");
                while (Jugador == null || Jugador.compareTo("Escribe aqui") == 0 || Jugador.compareTo("") == 0) {
                    Jugador = JOptionPane.showInputDialog(ventana, "Debes ingresar usuario", "Escribe aqui");
                }
                nombreJugador.setText("Jugador: " + Jugador);
                tiempo.start();
                panelPresentacion.setVisible(false);
                ventana.add(panelJuego);
                panelJuego.setVisible(true);

            }
        });
        ReproduceAudio ReproAud = new ReproduceAudio();
        ReproAud.Fx(0);
        ventana.add(panelPresentacion);
        ventana.setVisible(true);
    }

    public void numerosAleatorios() {
        int acomulador;
        acomulador = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mat[i][j] = 0;
                matAux[i][j] = 0;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mat[i][j] = aleatorio.nextInt(10) + 1;
                do {
                    acomulador = 0;
                    for (int k = 0; k < 4; k++) {
                        for (int l = 0; l < 5; l++) {
                            if (mat[i][j] == mat[k][l]) {
                                acomulador += 1;
                            }
                        }
                    }
                    if (acomulador == 3) {
                        mat[i][j] = aleatorio.nextInt(10) + 1;
                    }
                } while (acomulador == 3);
            }
        }
    }
}
