
interface SolicitudObserver{
    fun notificacionExitosa(usuario: Usuario, figurita: Figurita) { if (debeSerNotificado(usuario,figurita)) evento(usuario,figurita)}
    fun debeSerNotificado(usuario: Usuario, figurita: Figurita):Boolean

     fun evento(usuario: Usuario, figurita: Figurita)

}

class siLosUltimos3intercambiosFueron() : SolicitudObserver {
    override fun debeSerNotificado(usuario: Usuario,figurita: Figurita): Boolean {
        return (comprobacionDeSelecciones(usuario)&&usuario.seLlenoElAlbum())
    }
    private fun comprobacionDeSelecciones(usuario: Usuario)=traeLasUltimasSolicitudes(usuario).all {it == traeLaUltimaSeleccionDeUnaListaDeFiguritas(usuario)}
    private fun traeLaUltimaSeleccionDeUnaListaDeFiguritas(usuario: Usuario) = usuario.listaDeSolicitudDeIntercambioXSeleccion.last()
    private fun traeLasUltimasSolicitudes(usuario: Usuario)=usuario.listaDeSolicitudDeIntercambioXSeleccion.takeLast(3)
    override fun evento(usuario: Usuario, figurita: Figurita) {
        traeLasUltimasSolicitudes(usuario).forEach { usuario.quitaSolicitudesDeIntercambio(it) }
        usuario.cambiarTipoUsuario(Nacionalista(usuario.seleccionAConservar))
    }
}
class siLaSolicitudFueExitosa(val repetidasReservadas: MutableList<Figurita> = mutableListOf()) :SolicitudObserver {
    override fun debeSerNotificado(usuario: Usuario,figurita: Figurita): Boolean =!usuario.tieneRepetidas()
    private fun buscarSiLaFiguritaSolicitadaEsMayorOIgual(figurita: Figurita)=repetidasReservadas.any{it.valoracionFigurita()>=figurita.valoracionFigurita()}
    private fun buscandoLaFiguritaDeMenorValoracion(): Figurita = repetidasReservadas.minBy { it.valoracionFigurita() }

    override fun evento(usuario: Usuario, figurita: Figurita) {
        if(buscarSiLaFiguritaSolicitadaEsMayorOIgual(figurita))
            usuario.aniadirFiguritaRepetidas( buscandoLaFiguritaDeMenorValoracion())
    }


}
class SiFaltan5FiguritasParaCompletar() : SolicitudObserver {

    override fun debeSerNotificado(usuario: Usuario, figurita: Figurita): Boolean = usuario.cantidadFiguritaFaltantes() <= 5

    override fun evento(usuario: Usuario, figurita: Figurita) {
        usuario.kmCercania *= 3
        usuario.quitarAcciones(this)
    }


}
class SiLlenoElAlbumYTieneRegalables() : SolicitudObserver {

    var cantidadDeFiguritasRagalabesParaSerDesprendido : Int = 0
    override fun debeSerNotificado(usuario: Usuario, figurita: Figurita): Boolean = usuario.seLlenoElAlbum() && tieneCantidadDeFiguritasRegalablesParaSerDesprendido(usuario)

    fun tieneCantidadDeFiguritasRegalablesParaSerDesprendido(usuario: Usuario) =
        usuario.cantidadDeFiguritasARegalar() > cantidadDeFiguritasRagalabesParaSerDesprendido
    override fun evento(usuario: Usuario, figurita: Figurita) {
        usuario.cambiarTipoUsuario(Desprendido)
    }

}
class SiLlenoElAlbumConNotificacion(val mail: MailSender) : SolicitudObserver {

    override fun debeSerNotificado(usuario: Usuario, figurita: Figurita): Boolean = usuario.seLlenoElAlbum()

    override fun evento(usuario: Usuario, figurita: Figurita) {
        mail.sendMail(Mail("info@WorldCup.com.ar",
            usuario.email,
            "Completaste el album",
            "completaste el album con la figurita numero" +
                    "${figurita.numero.toString()} " +
                    "${figurita.jugador.nombre} "))
    }

}






