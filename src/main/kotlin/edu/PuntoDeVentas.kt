import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import kotlin.math.ceil

abstract class PuntoDeVentas(
    var nombre: String = "",
    @JsonIgnore
    val ubicacion: Direccion = Direccion(),
    var stockDeSobres: Int = 0,
    var pedidosPendientes: Pedido,
    val listaDePedidosPendientes :MutableList<Pedido> = mutableListOf()
):Entidad(){
    companion object{
        const val COSTO_BASE_ENVIO = 1000.0
        const val COSTO_POR_SOBRE: Int = 170
        const val COSTO_POR_KM_EXCEDIDO = 100
        const val CANTIDAD_KM_MAXIMO = 10.0
    }
    fun hayDisponibilidad() = stockDeSobres > 0
    fun reciboPedidoDeFabrica(pedido: Pedido) { stockDeSobres += pedido.cantidadDeSobres  }
    fun pedidoSuperioarA( cantidadDeSobres: Int) :Boolean = cantidadDeSobres > stockDeSobres
    fun ventaPor(cantidadDeSobres: Int) {
        validacionDeVenta(cantidadDeSobres)
        stockDeSobres -= cantidadDeSobres
    }

    fun tienePedidosDentroDe(dias :Int) : Boolean {
        return listaDePedidosPendientes.any(){it.seCumpleDentroDe(90)}
    }
    fun hayPedidosSinProcesar() : Boolean {
        return listaDePedidosPendientes.any(){ it.faltaProcesar()}
    }
    fun esInactivo() = !hayDisponibilidad() && !tienePedidosDentroDe(90) && !hayPedidosSinProcesar()
    fun faltaProcesar() = Pedido().fechaEstimadaDeEntrega < LocalDate.now()

    override fun busqueda(condicionDeBusqueda: String): Boolean = nombre.lowercase() == condicionDeBusqueda.lowercase()

    fun cantidadDeKM(usuario: Usuario)  = ceil(this.ubicacion.distancia(usuario.direccion))
    fun esCercano(usuario: Usuario) :Boolean = cantidadDeKM(usuario) < 10
    fun cantidadDeKmDeExceso(usuario: Usuario)  = maxOf( cantidadDeKM(usuario) - CANTIDAD_KM_MAXIMO, 0.0) // retorna el mayor de los dos valores
    fun factorCostoDeEnvio(usuario: Usuario)= maxOf(0.0,cantidadDeKmDeExceso(usuario))
    fun costoDeEnvio(usuario: Usuario): Double = COSTO_BASE_ENVIO + factorCostoDeEnvio(usuario) * COSTO_POR_KM_EXCEDIDO

    fun costoPorPedido(cantidadDeSobres:Int, usuario: Usuario) = COSTO_POR_SOBRE * cantidadDeSobres
    fun costoMinimo(cantidadDeSobres: Int,usuario: Usuario) = costoPorPedido(cantidadDeSobres,usuario) + costoDeEnvio(usuario)
    abstract fun factorPuntoDeventa(cantidadDeSobres:Int):Double
    fun importeACobrar(usuario: Usuario,cantidadDeSobres: Int) = costoMinimo(cantidadDeSobres,usuario) * factorPuntoDeventa(cantidadDeSobres)

    // validaciones
    fun validacionDeVenta(cantidadDeSobres: Int) {
        if (pedidoSuperioarA(cantidadDeSobres)) throw genericException("No tenemos el stock que solicita")
    }
    fun esValidoNombre() :Boolean = nombre.esValido()
    fun validarNombre(){
        if(!esValidoNombre()) throw ExceptionNombre()
    }
    fun esValidoStockDeSobre() :Boolean = stockDeSobres >= 0
    fun validarStockDeSobres(){if(!esValidoStockDeSobre())throw genericException("El numero no es valido ")}
    fun esValidoPedidoPendientesDeEntrega():Boolean = pedidosPendientes != null
    fun validarPedidoPendientesDeEntrega(){if(!esValidoPedidoPendientesDeEntrega())throw genericException("El numero no es valido ")}
    override fun validar() {
        validarNombre()
        validarStockDeSobres()
        validarPedidoPendientesDeEntrega()
    }

    @JsonProperty
    fun cantidadPedidosPendientes() = listaDePedidosPendientes.count()

    @JsonProperty
    fun direccion () = ubicacion.calle + " " + ubicacion.altura

    @JsonProperty
    fun ubicacionGeografica () = ubicacion.ubicacion



}

class Kiosco (

  @JsonIgnore val esDueño: Boolean, @JsonIgnore val esEmpleado: Boolean, costoDeSobre: Int, nombre: String, ubicacion: Direccion,
    stockDeFigurita: Int, pedidosPendientesDeEntrega: Pedido,listaDePedidosPendientes: MutableList<Pedido>
) : PuntoDeVentas(nombre, ubicacion, stockDeFigurita, pedidosPendientesDeEntrega, listaDePedidosPendientes) {

    companion object {
        const val FACTOR_DUENIO = 0.10
        const val FACTOR_EMPLEADO = 0.25
        const val SIN_FACTOR = 1.0
    }
    fun factorDuenio(): Double = if (esDueño) FACTOR_DUENIO else SIN_FACTOR

    fun factorEmpleado(): Double = if (esEmpleado) FACTOR_EMPLEADO else SIN_FACTOR

    override fun factorPuntoDeventa(cantidadDeSobres: Int): Double = 1.0 + factorDuenio() + factorEmpleado()
}


class Libreria(
    costoDeSobre: Int, nombre: String, ubicacion: Direccion,
    stockDeFigurita: Int, pedidosPendientes: Pedido,listaDePedidosPendientes: MutableList<Pedido>) : PuntoDeVentas(nombre, ubicacion, stockDeFigurita, pedidosPendientes,listaDePedidosPendientes) {


    fun entregaDentroDeLos10() = pedidosPendientes.seCumpleDentroDe(10)
    fun factorEntregaSuperiorA10() = if(entregaDentroDeLos10()) 1.10 else 1.05

    override fun factorPuntoDeventa(cantidadDeSobres : Int): Double = if(entregaDentroDeLos10()) 1.10 else 1.05


}
class Supermercado(
    costoDeSobre: Int, nombre: String, ubicacion: Direccion,
    stockDeFigurita: Int, pedidosPendientes: Pedido,var promocion: Promocion = NoHayPromocion,listaDePedidosPendientes: MutableList<Pedido>
) : PuntoDeVentas(nombre, ubicacion, stockDeFigurita, pedidosPendientes,listaDePedidosPendientes) {

    fun cambioDePromocion(nuevaPromocion: Promocion) {
        promocion = nuevaPromocion
    }


    override fun factorPuntoDeventa(cantidadDeSobres: Int): Double =promocion.factorPromocion(cantidadDeSobres)


}