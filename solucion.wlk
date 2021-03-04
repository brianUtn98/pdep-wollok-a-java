object paradigma{
	const property usuarios = []
	
	method crearUsuario(){
		usuarios.add(new Usuario())
	}
	
	method recategorizacionGeneral(){
		usuarios.forEach({unUsuario => unUsuario.recategorizar()})
	}
}

class Post{
	var property puntaje = 0
	const property comentarios = []
	var property cerrado = false
	const property contenido
	
	
	method cerrarPost(){
		cerrado = true
	}
	
	method agregarComentario(texto){
		if(self.noEstaCerrado()){
			comentarios.add(texto)
		}
	}
	
	method calificar(puntos){
		puntaje+=puntos
	}
	
	method valor(){
		return self.sumaDePuntajes() + comentarios.size()
	}
	
	method sumaDePuntajes() = puntaje
	
	method noEstaCerrado() = cerrado
	
	method esInteresante() = self.valor() > 300 and self.tieneComentariosExtensos()
	
	method tieneComentariosExtensos() = comentarios.filter({unComentario => unComentario.size()>240}).size() > 20
	
}

class PostPremium inherits Post{
	override method sumaDePuntajes() = puntaje*2
	
	override method tieneComentariosExtensos() = comentarios.all({unComentario => unComentario.size() >240})
}

class Usuario{
	const property posteos = []
	var property categoria = novato
	
	method postearContenido(contenido){
		const unPost = categoria.crearPost(contenido)
		self.agregarPost(unPost)
	}
	
	method agregarPost(post){
		posteos.add(post)
	}
	
	method puntaje(){
		return posteos.sum({unPost => unPost.valor()})
	}
	
	method recategorizar(){
		categoria.recategorizar(self)
	}
	
	method cambiarA(nuevaCategoria){
		categoria = nuevaCategoria
	}
	
	method tienePostDestacado() = posteos.any({unPost => unPost.valor() > 500})
	
	method cantidadPostInteresantes() = self.postInteresantes().size()
	
	method postInteresantes() = posteos.filter({unPosteo => unPosteo.esInteresante()})
	
}

class Categoria{
	method recategorizar(usuario) {
		if(self.puedeRecategorizar(usuario)){
			usuario.cambiar(self.siguienteNivel())
		}
	}
	
	method puedeRecategorizar(usuario) = usuario.puntaje() > self.requisitoDePasaje()
	
	method siguienteNivel()
	
	method requisitoDePasaje()
	
	method crearPost(unContenido) = new Post(contenido=unContenido)
}

object novato inherits Categoria{
	override method siguienteNivel() = intermedio
	
	override method requisitoDePasaje() = 100
}

object intermedio inherits Categoria{
	
	override method requisitoDePasaje() = 1000
	
	override method siguienteNivel() = experto
	
	override method puedeRecategorizar(usuario) = super(usuario) and usuario.tienePostDestacado()
	
}

object experto inherits Categoria{

	override method crearPost(unContenido) = new PostPremium(contenido=unContenido)
	
	override method recategorizar(usuario){
		
	}
	
	override method requisitoDePasaje() = 0
	
	override method siguienteNivel() = self
}