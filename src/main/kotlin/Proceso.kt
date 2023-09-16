abstract class Proceso{
    lateinit var mailSender: MailSender
    val direccionDeMail :String = "admin@worldcapp.com.ar"

    fun execute() {
        this.doExecute()
        this.enviarMail()
    }

    abstract fun doExecute()

    fun enviarMail() {
        mailSender.sendMail(crearMail())
    }

    fun crearMail() = Mail(direccionDeMail, direccionDeMail, getMensaje(), getMensaje())

    fun getMensaje() = """Se realizó el proceso: ${getNombre()}"""

    abstract fun getNombre(): String
}

class BorrarUsuarioInactivo(val repoUsuario: Repositorio<Usuario>): Proceso() {


     override fun doExecute() {
        usuariosInactivos().forEach { repoUsuario.delete(it) }
    }

    override fun getNombre(): String {
        return "Borrar usuarios inactivos"
    }

    private fun usuariosInactivos(): List<Usuario> {
        return repoUsuario.allInstances().filter { it.seLlenoElAlbum() && !it.tieneRepetidas() }
    }
}


class CreacionModificacionDeSelecciones(val repoSeleccion:CreateOrUpdateSeleccion,val repositorio: Repositorio<Seleccion>):
    Proceso() {

    override fun doExecute() {
     repoSeleccion.actualizar(repositorio)
    }

    override fun getNombre(): String {
        return "Creacion y modificacion de selecciones"
    }

}
class BorrarPuntosDeVentas(val repoPtoDeVenta : Repositorio<PuntoDeVentas>): Proceso() {

    override fun doExecute() {
        puntoDeVentasInactivos().forEach { repoPtoDeVenta.delete(it) }
    }

    override fun getNombre(): String {
        return "Borrar puntos de ventas inactivos"
    }

    private fun puntoDeVentasInactivos() :List<PuntoDeVentas> {
         return repoPtoDeVenta.allInstances().filter { it.esInactivo() }
    }
}



class CambiarOnFire(val repoFigurita: Repositorio<Figurita>): Proceso() {
    /*private fun figuritasActualizar():List<Int>  {
        return listaRamdom.add(Int)
    }*/
    override fun doExecute() {
        figuritasActualizar().forEach { repoFigurita.update(it) }
    }

    override fun getNombre(): String {
        return "Cambiar on fire"
    }


    private fun figuritasActualizar():List<Figurita> {
        return repoFigurita.allInstances().filter{ !it.onFire}
    }

}
class ActualizarPuntoDeVenta(val repoPtoDeVenta : Repositorio<PuntoDeVentas>) : Proceso() {
    override fun doExecute() {
        puntoDeVentasConPedidosSinProcesar().forEach { repoPtoDeVenta.update(it) }
    }

    override fun getNombre(): String {
        return "Actualizar punto de venta"
    }

    private fun puntoDeVentasConPedidosSinProcesar() :List<PuntoDeVentas> {
        return repoPtoDeVenta.allInstances().filter { it.faltaProcesar() }
    }


}