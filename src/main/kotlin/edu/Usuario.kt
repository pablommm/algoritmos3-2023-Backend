import ar.edu.unsam.algo3.dto.UsuarioLoginDTO
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate




class Usuario (
    val name: String = "",
    val apellido: String = "",
    @JsonIgnore
    var username: String = "",
    var fechaDeNacimiento: LocalDate = LocalDate.now(),
    val email: String = "",
    @JsonIgnore
    val direccion: Direccion = Direccion(),
    @JsonIgnore
    val figuritasFaltantes: MutableList<Figurita> =  mutableListOf(),
    @JsonIgnore
    val figuritasRepetidas: MutableList<Figurita> = mutableListOf(),
    @JsonIgnore
    var tipoDeUsuario: TipoDeUsuario = Par,
    @JsonIgnore
    var jugadorFavorito: Jugador = Jugador(),
    var kmCercania: Int = 0,
    @JsonIgnore
    var listaDeAccion : MutableList<SolicitudObserver> = mutableListOf(),
    @JsonIgnore
    var listaDeSolicitudDeIntercambioXSeleccion : MutableList<Seleccion> = mutableListOf(),
    @JsonIgnore
    var password :String = ""
) :Entidad() {

    fun notificarEvento(figurita: Figurita){
        return listaDeAccion.toList().forEach { it.notificacionExitosa(this,figurita)}
    }

    fun agregarAcciones(accion : SolicitudObserver){
        listaDeAccion.add(accion)
    }
    fun quitarAcciones(accion : SolicitudObserver){
        listaDeAccion.remove(accion)
    }
    fun removerAccion(accion:SolicitudObserver){
        listaDeAccion.toList().forEach { quitarAcciones(accion) }

    }
    var seleccionAConservar:MutableList<Seleccion> = mutableListOf()

    fun aniadirFiguritaRepetidas(figurita: Figurita) {
        validarFiguritaFaltante(figurita)
        figuritasRepetidas.add(figurita)
    }

    fun quitarFiguritaRepetidas(figurita: Figurita) {
        figuritasRepetidas.remove(figurita)
    }
    fun aniadirFiguritaFaltantes(figurita: Figurita) {
        validarFiguritaRepetida(figurita)
        figuritasFaltantes.add(figurita)
    }
    fun quitarFiguritaFaltantes(figurita: Figurita) {
        figuritasFaltantes.remove(figurita)
    }


    fun tieneRepetidaLa(figurita: Figurita) = figuritasRepetidas.contains(figurita)
    fun figuritaNoRepetida(figurita: Figurita){
        if(!tieneRepetidaLa(figurita)) throw genericException("NO tiene la figurita repetida")
    }
    fun tieneFaltanteLa(figurita: Figurita) = figuritasFaltantes.contains(figurita)
    fun figuritaNoFaltante(figurita: Figurita){
        if(!tieneFaltanteLa(figurita)) throw genericException("NO tiene la figurita faltante")
    }
    fun cuantasTieneRepetidas(figurita: Figurita) = figuritasRepetidas.count{ it==figurita}

    fun edad() = fechaDeNacimiento.edad()
    fun seLlenoElAlbum(): Boolean = figuritasFaltantes.isEmpty()

    fun esCercanoA(usuario: Usuario): Boolean = this.direccion.distancia(usuario.direccion) <= kmCercania
    fun cercanoA(usuario: Usuario) {
        if (!esCercanoA(usuario)) throw genericException("El usuario no es cercano")
    }
    fun esJugadorFavorito(figurita: Figurita) = jugadorFavorito == figurita.jugador
    fun cantidadFiguritaFaltantes() = figuritasFaltantes.count()
    fun tieneFiguritasFaltantes() = figuritasRepetidas.isEmpty()
    fun tieneFiguritasRegalables() = figuritasRepetidas.isNotEmpty()
    fun cantidadDeFiguritasARegalar() :Int {
        return figuritasRepetidas.filter { puedeRegalarFigurita(it) }.count()
    }

    fun topFive(): List<Figurita> {
        return figuritasRepetidas.distinct().sortedByDescending { figurita -> figurita.valoracionFigurita() }.take(5)
    }
    fun coincideParcialNombre(parametro:String) = name.contains(parametro)
    fun coincideParcialApellido(parametro:String) = apellido.contains(parametro)
    fun coincideParcialUsername(parametro:String) = username.contains(parametro)

    fun coincideNombreOApellidoOUsername(parametro :String) =  coincideParcialNombre(parametro) || coincideParcialApellido(parametro) || coincideParcialUsername(parametro)
    override fun busqueda(condicionDeBusqueda: String): Boolean {
        return  coincideNombreOApellidoOUsername(condicionDeBusqueda)
    }
    fun puedeRegalarFigurita(figurita: Figurita) = tipoDeUsuario.puedeRegalar(figurita,this) && tieneRepetidaLa(figurita)
    fun validarSiPuedeRegalar(figurita: Figurita){
        if(!puedeRegalarFigurita(figurita)) throw genericException("No puede regalar esta figurita")
    }
    fun validarSolicitud(figurita: Figurita, usuario: Usuario) {
        this.cercanoA(usuario)
        this.figuritaNoFaltante(figurita) // valida que la tenga faltante y tira la excepcion
        usuario.validarSiPuedeRegalar(figurita) // valida la figurita la pueda regalar
    }
    fun solicitar(figurita: Figurita, usuario: Usuario) {
        validarSolicitud(figurita, usuario)
        regalarFigurita(figurita,usuario)
        notificarEvento(figurita)
    }
    fun regalarFigurita(figurita: Figurita,usuario: Usuario){
        this.quitarFiguritaFaltantes(figurita)
        usuario.quitarFiguritaRepetidas(figurita)
    }
    fun cambiarTipoUsuario(nuevoTipoDeUsuario: TipoDeUsuario) {
        tipoDeUsuario = nuevoTipoDeUsuario
    }

    fun repesMenorA5() :Boolean = cantidadFiguritaFaltantes() < 5

    fun triplicarKmDeCercania(){
        if (repesMenorA5()) kmCercania *= 3
    }
    fun puedeRegalarYEsCercano(figurita: Figurita, usuario: Usuario): Boolean = usuario.puedeRegalarFigurita(figurita) && esCercanoA(usuario)

    // validaciones
    fun esValidoNombre() :Boolean = name.esValido()
    fun validarNombre(){
        if(!esValidoNombre()) throw ExceptionNombre()
    }
    fun esValidoapellido() :Boolean = apellido.esValido()
    fun validarApellido() { if(!esValidoapellido()) throw ExceptionApellido()}

    fun esValidoUsername() :Boolean = username.esValido()
    fun validarUsername() { if(!esValidoUsername()) throw genericException("El username  no es valido")}

    fun esValidoCorreo() :Boolean = email.esValido()
    fun validarCorreo() { if(!esValidoCorreo()) throw genericException("El correo  no es valido ")}
    fun accesoUsuario(user:UsuarioLoginDTO): Boolean {
        return user.user == username && user.pass == password
    }

    override fun validar() {
        validarNombre()
        validarApellido()
        validarUsername()
        validarCorreo()
    }

    fun validarFiguritaFaltante(figurita:Figurita) {
        if(tieneFaltanteLa(figurita)) throw genericException("error,la figurita  se encuentra en figuritas faltantes")
    }
    fun validarFiguritaRepetida(figurita: Figurita){
        if(tieneRepetidaLa(figurita)) throw genericException("error, la figurita se encuentra en figuritas repetidas")
    }

    fun tieneRepetidas() = figuritasRepetidas.count() >0
    //fun seleccionAConservar()=seleccionAConservar     // la seleccion la tiene que agregar nacionalista no la tenemos que agregar nosotros
    //fun agregarSeleccionFavoritaAAconcervar(seleccion:Seleccion){seleccionAConservar.add(seleccion)}
    fun quitaSolicitudesDeIntercambio(seleccion:Seleccion){
        listaDeSolicitudDeIntercambioXSeleccion.remove(seleccion)
    }
    fun cantidadDeFiguritasRepetidas()=figuritasRepetidas.size
    fun cantidadDeAcciones()=listaDeAccion.size

    @JsonProperty
    fun calle() = direccion.calle

    @JsonProperty
    fun altura() = direccion.altura

    @JsonProperty
    fun posicionX() = direccion.ubicacion.x

    @JsonProperty
    fun posicionY() = direccion.ubicacion.y

}



