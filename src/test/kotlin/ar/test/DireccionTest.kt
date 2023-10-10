import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertDoesNotThrow

class DireccionTest : DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest
    val DireccionSanMartin = Direccion(org.uqbar.geodds.Point(-34.582137, -58.520687), "San Martin", "Buenos Aires", "25 de Mayo", 1653)
    val DireccionMaipu = Direccion(org.uqbar.geodds.Point(-34.577117, -58.525629), "San Martin", "Maipu", "cancha de chaca", 5236)
    val DireccionNoValida = Direccion(org.uqbar.geodds.Point(-34.577117, -58.525629), "San Martin", "Maipu", "cancha de chaca", -1)
    val DireccionJuanito = Direccion(org.uqbar.geodds.Point(-34.560476210109464, -58.55052358783349), "San Martin", "Maipu", "cancha de chaca", 3701)
    val DireccionPlaza = Direccion(org.uqbar.geodds.Point(-34.577476, -58.538158), "San Martin", "Maipu", "cancha de chaca", 3701)
    val DireccionNula = Direccion(org.uqbar.geodds.Point(0,0), "San Martin", "Maipu", "cancha de chaca", 123)
    it("distancia entre puntos"){
        DireccionJuanito.distancia(DireccionPlaza).shouldBe(2.2034374540809063)
        DireccionSanMartin.distancia(DireccionPlaza).shouldBe(1.681358039902174)
        DireccionMaipu.distancia(DireccionPlaza).shouldBe(1.147769456329179)
        DireccionMaipu.distancia(DireccionJuanito).shouldBe(2.935922299083545)
        DireccionMaipu.distancia(DireccionSanMartin).shouldBe(0.7.plusOrMinus(0.1))
    }
    it("direcciones validas"){
        DireccionJuanito.esValido().shouldBe(true)
        DireccionPlaza.esValido().shouldBe(true)
        DireccionSanMartin.esValido().shouldBe(true)
        DireccionMaipu.esValido().shouldBe(true)
    }
    it("comprobamos que la direccion no existe"){
        DireccionNoValida.esValido().shouldBe(false)
    }
    it("distancia entre la misma direccion debe ser cero") {
        DireccionSanMartin.distancia(DireccionSanMartin).shouldBe(0.0)
    }
    it("direccion invalida sin provincia") {
        val direccionSinProvincia = Direccion(
            org.uqbar.geodds.Point(0, 0),
            "",
            "Localidad",
            "Calle",
            123
        )
        direccionSinProvincia.esValido().shouldBe(false)
    }
    it("direccion invalida sin localidad") {
        val direccionSinLocalidad = Direccion(
            org.uqbar.geodds.Point(0, 0),
            "Provincia",
            "",
            "Calle",
            123
        )
        direccionSinLocalidad.esValido().shouldBe(false)
    }
    it("direccion invalida sin calle") {
        val direccionSinCalle = Direccion(
            org.uqbar.geodds.Point(0, 0),
            "Provincia",
            "Localidad",
            "",
            123
        )
        direccionSinCalle.esValido().shouldBe(false)
    }
    it("direccion invalida con altura cero") {
        val direccionAlturaCero = Direccion(
            org.uqbar.geodds.Point(0, 0),
            "Provincia",
            "Localidad",
            "Calle",
            0
        )
        direccionAlturaCero.esValido().shouldBe(false)
    }
    it("validaciones incorrectas") {
        assertDoesNotThrow { DireccionNula.esValido() }
        assertDoesNotThrow { DireccionNoValida.esValido() }
        
    }

})