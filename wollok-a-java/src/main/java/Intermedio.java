public class Intermedio extends Categoria{
    @Override
    public Categoria siguienteNivel() {
        return new Experto();
    }

    @Override
    public int requisitoDePasaje() {
        return 1000;
    }

    @Override
    public Post crearPost(String contenido) {
        return null;
    }

    @Override
    public boolean puedeRecategorizar(Usuario usuario){
        return super.puedeRecategorizar(usuario) & usuario.tienePostDestacado();
    }
}
