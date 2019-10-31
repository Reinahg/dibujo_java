package quizgato;

import java.io.BufferedReader;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class Lista {

    Nodo cabeza;

    public Lista() {
        cabeza = null;
    }

    public void agregar(Nodo n) {
        if (cabeza == null) {
            cabeza = n;
        } else {
            Nodo a = cabeza;
            while (a.siguiente != null) {
                a = a.siguiente;
            }
            //Enlazar el nuevo nodo al final de la lista
            a.siguiente = n;
            n.siguiente = null;
        }
    }//

    public void desdeArchivo(String nombreArchivo) {
        cabeza = null;
        try {
            BufferedReader br = Archivo.abrirArchivo(nombreArchivo);

            String linea = br.readLine();
            linea = br.readLine();
            while (linea != null) {
                String[] textos = linea.split(";");
                if (textos.length >= 5) {
                    Nodo n = new Nodo(textos[0],
                            Integer.parseInt(textos[1]),
                            Integer.parseInt(textos[2]),
                            Integer.parseInt(textos[3]),
                            Integer.parseInt(textos[4])
                    );
                    agregar(n);
                }
                linea = br.readLine();
            }
        } catch (Exception ex) {
        }

    }

    //Metodo para conocer el número de nodos de la lista
    public int obtenerLongitud() {
        int tn = 0;
        Nodo a = cabeza;
        while (a != null) {
            a = a.siguiente;
            tn++;
        }
        return tn;
    }

    //Metodo que devuelve el nodo ubicado en una posicion
    public Nodo obtenerNodo(int posicion) {
        int p = 0;
        Nodo a = cabeza;
        while (a != null && p != posicion) {
            a = a.siguiente;
            p++;
        }
        if (a != null && p == posicion) {
            return a;
        } else {
            return null;
        }
    }

    //Metodo que cambia los valores de un nodo dada la posicion
    public void actualizar(int posicion,
            String tipo,
            int Posx0,
            int Posy0,
            int Posx1,
            int Posy1) {
        Nodo n = obtenerNodo(posicion);
        if (n != null) {
            n.actualizar(tipo, Posx0, Posy0, Posx1, Posy1);
        }
    }

    //Metodo para listar la informacion de los nodos en una tabla
    public void mostrar(JTable tbl) {
        int tn = obtenerLongitud();
        String[][] datos = new String[tn][5];
        Nodo a = cabeza;
        int f = 0;
        while (a != null) {
            datos[f][0] = a.tipo;
            datos[f][1] = Integer.toString(a.Posx0);
            datos[f][2] = Integer.toString(a.Posy0);
            datos[f][3] = Integer.toString(a.Posx1);
            datos[f][4] = Integer.toString(a.Posy1);

            a = a.siguiente;
            f++;
        }
        String[] encabezados = new String[]{"FigGeometrica", "X0", "Y0", "X1", "Y1"};

        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);

        dtm.addTableModelListener(
                new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent evt) {
                int p = evt.getFirstRow();
                DefaultTableModel dtm = (DefaultTableModel) evt.getSource();
                actualizar(p,
                        (String) dtm.getValueAt(p, 0),
                        (int) dtm.getValueAt(p, 1),
                        (int) dtm.getValueAt(p, 2),
                        (int) dtm.getValueAt(p, 3),
                        (int) dtm.getValueAt(p, 4));
            }
        });
        tbl.setModel(dtm);

    }//mostrar()

    //Metodo que busca el nodo que esta antes de el nodo buscado
    public Nodo obtenerPredecesor(Nodo n) {
        Nodo predecesor = null;
        if (n != null && cabeza != null && !cabeza.equals(n)) {
            predecesor = cabeza;
            while (predecesor != null && !predecesor.siguiente.equals(n)) {
                predecesor = predecesor.siguiente;
            }
        }
        return predecesor;
    }

    //Metodo que permite intercambiar en la lista 2 nodos diferentes
    public void intercambiar(Nodo n1, Nodo n2,
            Nodo a1, Nodo a2) {
        if (cabeza != null && n1 != n2
                && n1 != null && n2 != null) {
            if (a1 != null) {
                a1.siguiente = n2;
            } else {
                cabeza = n2;
            }
            //Se guarda temporalmente el apuntador siguiente del segundo nodo
            Nodo t = n2.siguiente;
            if (n1 != a2) {
                n2.siguiente = n1.siguiente;
                a2.siguiente = n1;
            } else {
                n2.siguiente = n1;
            }
            n1.siguiente = t;
        }
    }

    //Metodo que ordena la lista por uno de los datos
    public void ordenar() {
        Nodo ni = cabeza;
        Nodo ai = null;
        while (ni.siguiente != null) {
            Nodo nj = ni.siguiente;
            Nodo aj = ni;
            while (nj != null) {
                if (ni.tipo.compareTo(nj.tipo) > 0) {
                    intercambiar(ni, nj, ai, aj);
                    Nodo t = ni;
                    ni = nj;
                    nj = t;
                }
                aj = nj;
                nj = nj.siguiente;
            }
            ai = ni;
            ni = ni.siguiente;
        }
    }

    public boolean haciaArchivo(String nombreArchivo) {
        int totalNodos = obtenerLongitud();
        if (totalNodos > 0) {
            String[] lineas = new String[totalNodos + 1];
            lineas[0] = "FigGeometrica;X0;Y0;X1;Y1";
            int i = 1;
            Nodo a = cabeza;
            while (a != null) {
                lineas[i] = a.tipo + ";"
                        + a.Posx0 + ";"
                        + a.Posy0 + ";"
                        + a.Posx1 + ";"
                        + a.Posy1;
                a = a.siguiente;
                i++;
            }
            return Archivo.guardarArchivo(nombreArchivo, lineas);
        } else {
            return false;
        }
    }

    public void eliminar(Nodo n) {
        if (n != null && cabeza != null) {
            if (n != cabeza) {
                Nodo a = cabeza;
                while (a.siguiente != n) {
                    a = a.siguiente;
                }
                a.siguiente = n.siguiente;
            } else {
                cabeza = cabeza.siguiente;
            }
        }
    }

}
