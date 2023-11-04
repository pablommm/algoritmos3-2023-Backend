package ar.edu.unsam.algo3.service

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@CrossOrigin("http://localhost:4200/")
@Service
public object DireccionService {
    var prov: String = ""
    var localidadVacia : MutableList<String> = mutableListOf()
    var provincias: MutableList<String> = mutableListOf("Cordoba", "Mendoza", "Buenos Aires","Jujuy")
    var localidadesBuenosAires: MutableList<String> = mutableListOf("La Matanza", "La Plata", "Lanús", "Lomas de Zamora")
    var localidadesMendoza: MutableList<String> = mutableListOf("Río Cuarto", "Córdoba", "Villa María", "Morteros")
    var localidadesCordoba: MutableList<String> = mutableListOf("Mendoza", "San Rafael", "Godoy Cruz", "Guaymallén")
    var localidades : MutableList<String> = mutableListOf("Cordoba", "Mendoza", "Buenos Aires","Jujuy","La Matanza", "La Plata", "Lanús", "Lomas de Zamora","Mendoza", "San Rafael", "Godoy Cruz", "Guaymallén","Río Cuarto", "Córdoba", "Villa María", "Morteros")
    val criterios = listOf (
        "Nacionalista",
        "Conservador",
        "Fanatico",
        "Desprendido",
        "Apostador",
        "Interesado",
        "Conservador")
    /*fun getProvinciasService(): MutableList<String> {
        return provincias
    }*/

    /*
    fun getLocalidadesService(prov:String): MutableList<String> {
        when (prov){
             "Buenos Aires" -> return this.localidadesBuenosAires
             "Mendoza" -> return this.localidadesMendoza
             "Cordoba" -> return this.localidadesCordoba
            else ->return this.localidadVacia
        }
    }

*/

    /*


  localidadvacia: string[] = []
  criterioSelecionado = ''
  criterios = [
    'Nacionalista',
    'Conservador',
    'Fanatico',
    'Desprendido',
    'Apostador',
    'Interesado',
    'Conservador'
  ]
  selecciones: string[] = ['Argentina', 'Brasil', 'Portugal']
  seleccionesElegidas = []
    */
}
