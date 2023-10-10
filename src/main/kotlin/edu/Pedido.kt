import java.time.LocalDate

class Pedido(
    val cantidadDeSobres :Int = 0,
    var fechaEstimadaDeEntrega : LocalDate = LocalDate.now()
){
    fun faltaProcesar() = fechaEstimadaDeEntrega < LocalDate.now()
    fun seCumpleDentroDe(dias:Long) :Boolean {
        val hoy = LocalDate.now()
        val diaLimite = hoy.plusDays(dias)
        return fechaEstimadaDeEntrega in hoy..diaLimite
    }


}