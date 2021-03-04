import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Post {
    public int puntaje=0;
    List<String> comentarios = new ArrayList<String>();
    public boolean estaCerrado=false;
    public String contenido;

    public Post(String contenido) {
        this.contenido = contenido;
    }

    public void cerrarPost(){
        estaCerrado = true;
    }

    public void agregarComentario(String texto){
        if(!estaCerrado){
            comentarios.add(texto);
        }
    }

    public void calificar(int puntos){
        puntaje += puntos;
    }

    public int valor(){
        return this.sumaDePuntajes() + comentarios.size();
    }

    public int sumaDePuntajes(){
        return puntaje;
    }

    public boolean esInteresante(){
        return this.valor()> 300 && this.tieneComentariosExtensos();
    }


    public boolean tieneComentariosExtensos(){
        return comentarios.stream().filter(unComentario -> unComentario.length()>240).toArray().length>20;
    }
}
