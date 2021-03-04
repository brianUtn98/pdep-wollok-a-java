public class Experto extends Categoria{
    @Override
    Categoria siguienteNivel() {
        return this;
    }

    @Override
    int requisitoDePasaje() {
        return 0;
    }

    @Override
    Post crearPost(String contenido) {
        return new PostPremium(contenido);
    }

    @Override
    public void recategorizar(Usuario usuario){}
}
