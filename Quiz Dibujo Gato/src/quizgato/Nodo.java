package quizgato;

public class Nodo {

    String tipo;
    int Posx0;
    int Posy0;
    int Posx1;
    int Posy1;
    Nodo siguiente;

    public Nodo() {
        tipo = "";
        Posx0 = 0;
        Posy0 = 0;
        Posx1 = 0;
        Posy1 = 0;
        siguiente = null;
    }

    public Nodo(String tipo,
            int Posx0,
            int Posy0,
            int Posx1,
            int Posy1) {
        this.tipo = tipo;
        this.Posx0 = Posx0;
        this.Posy0 = Posy0;
        this.Posx1 = Posx1;
        this.Posy1 = Posy1;
        this.siguiente = null;
    }

    //Metodo para cambiar los datos de un nodo
    public void actualizar(String tipo,
            int Posx0,
            int Posy0,
            int Posx1,
            int Posy1) {
        this.tipo = tipo;
        this.Posx0 = Posx0;
        this.Posy0 = Posy0;
        this.Posx1 = Posx1;
        this.Posy1 = Posy1;
    }

}
