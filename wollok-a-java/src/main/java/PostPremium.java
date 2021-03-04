import java.util.stream.Stream;

public class PostPremium extends Post{

    public PostPremium(String contenido) {
        super(contenido);
    }

    @Override
    public int sumaDePuntajes(){
        return puntaje*2;
    }

    @Override
    public boolean tieneComentariosExtensos() {
        return Stream.of(comentarios).allMatch(unComentario -> unComentario.size() > 240);
    }
}
