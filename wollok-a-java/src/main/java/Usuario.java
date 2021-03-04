import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Usuario {
    List<Post> posteos = new ArrayList<Post>();
    Categoria categoria = new Novato();

    public void postearContenido(String contenido){
        Post nuevoPost = categoria.crearPost(contenido);
        this.agregarPost(nuevoPost);
    }

    public void agregarPost(Post unPost){
        posteos.add(unPost);
    }

    public int puntaje(){
        return posteos.stream().mapToInt(unPost -> unPost.valor()).sum();
    }

    public void recategorizar(){
        categoria.recategorizar(this);
    }

    public void cambiarA(Categoria nuevaCategoria){
        categoria = nuevaCategoria;
    }

    public boolean tienePostDestacado(){
        return posteos.stream().anyMatch(unPost -> unPost.valor()>500);
    }

    public int cantidadPostInteresantes(){
        return  this.postInteresantes().size();
    }

    public List<Post> postInteresantes(){
        return posteos.stream().filter(unPost -> unPost.esInteresante()).collect(Collectors.toList());
    }

    public int cantidadDePost(){
        return posteos.size();
    }

    public boolean noTienePuntos(){
        return this.puntaje() == 0;
    }
}
