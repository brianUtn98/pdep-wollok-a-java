import java.util.ArrayList;
import java.util.List;

public class ServidorPosteos {
    /*
    * Esto podría ser una singleton class
    * */
    List<Usuario> usuarios = new ArrayList<Usuario>();

    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public void recategorizacionGeneral(){
        usuarios.forEach(unUsuario -> unUsuario.recategorizar());
    }
}
