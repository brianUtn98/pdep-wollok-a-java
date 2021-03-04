import java.util.stream.Stream;

public abstract class Categoria {
    public void recategorizar(Usuario usuario){
        if(this.puedeRecategorizar(usuario)){
            usuario.cambiarA(this.siguienteNivel());
        }
    }

    public boolean puedeRecategorizar(Usuario usuario){
        return usuario.puntaje() > this.requisitoDePasaje();
    }

    abstract Categoria siguienteNivel();

    abstract int requisitoDePasaje();

    abstract Post crearPost(String contenido);
}
