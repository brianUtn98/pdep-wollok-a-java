public class Novato extends Categoria{

    @Override
    public Categoria siguienteNivel() {
        return new Intermedio();
    }

    @Override
    public int requisitoDePasaje() {
        return 100;
    }

    @Override
    public Post crearPost(String contenido) {
        return new Post(contenido);
    }
}
