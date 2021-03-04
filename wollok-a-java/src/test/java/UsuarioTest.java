import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioTest{
    Usuario usuario = new Usuario();

    @Test
    public void postearContenidoAgregaPost(){
        usuario.postearContenido("Un contenido");
        Assert.assertEquals(1,usuario.cantidadDePost());
    }

    @Test
    public void puntajeSinPuntosEs0(){
        Assert.assertTrue(usuario.noTienePuntos());
    }

    @Test
    public void puntajeDeUnUsuario(){
        usuario.postearContenido("Un contenido");
        usuario.postearContenido("Otro contenido");
        Post unPost = usuario.posteos.get(0);
        Post otroPost = usuario.posteos.get(1);
        unPost.calificar(100);
        otroPost.agregarComentario("Grande pa!");
        otroPost.agregarComentario("Reportado lince");
        otroPost.calificar(10);
        //100 + 2 + 10 = 112

        Assert.assertEquals(112,usuario.puntaje());

    }

    @Test
    public void usuarioTienePostDestacado(){
        usuario.postearContenido("Un contenido");
        usuario.postearContenido("Otro contenido");
        Post unPost = usuario.posteos.get(0);
        Post otroPost = usuario.posteos.get(1);
        unPost.calificar(100);
        otroPost.agregarComentario("Grande pa!");
        otroPost.agregarComentario("Reportado lince");
        otroPost.calificar(10);

        Assert.assertTrue(usuario.tienePostDestacado());
    }

    @Test
    public void usuarioTienePostsInteresantes(){
        usuario.postearContenido("Un contenido");
        usuario.postearContenido("Otro contenido");
        Post unPost = usuario.posteos.get(0);
        String unComentario = new String("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vitae leo placerat, finibus sem vel, ultricies nisl. Vivamus eget dapibus risus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc mi metus, pulvinar quis arcu id vestibulum.\\n");
        for(int i=0;i<21;i++){
            unPost.agregarComentario(unComentario);
        }

        unPost.calificar(1000);
        Assert.assertEquals(1,usuario.cantidadPostInteresantes());
    }

    @Test
    public void usuarioNoRecategoriza() {
        usuario.recategorizar();
        Assert.assertTrue(usuario.categoria.getClass() == Novato.class);
    }

    @Test
    public void novatoRecategorizaAIntermedio(){
        usuario.postearContenido("Un post");
        usuario.posteos.get(0).calificar(300);
        usuario.recategorizar();

        Assert.assertTrue(usuario.categoria.getClass() == Intermedio.class);
    }



    @Test
    public void intermedioRecategorizaAExperto(){
        usuario.postearContenido("Un post");
        usuario.posteos.get(0).calificar(300);
        usuario.recategorizar();

        usuario.posteos.get(0).calificar(1000);

        usuario.recategorizar();

        Assert.assertTrue(usuario.categoria.getClass() == Experto.class);
    }

    @Test
    public void expertoPublicaPremium(){
        usuario.postearContenido("Un post");
        usuario.posteos.get(0).calificar(300);
        usuario.recategorizar();

        usuario.posteos.get(0).calificar(1000);

        usuario.recategorizar();
        usuario.postearContenido("Post premium");

        Post postPremium = usuario.posteos.get(1);
        Assert.assertTrue(postPremium.getClass() == PostPremium.class);
    }
}