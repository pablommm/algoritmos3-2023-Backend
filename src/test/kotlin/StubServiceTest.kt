class StubSelecciones : ServiceDeSeleccion {

    private val JSON = """
[
    {
        "id": 1,
        "pais": "Argentina",
        "confederacion": "CONMEBOL",
        "copasDelMundo": 3,
        "copasConfederacion": 15
    },
    {
        "id": 2,
        "pais": "Brasil",
        "confederacion": "CONMEBOL",
        "copasDelMundo": 5,
        "copasConfederacion": 9
    },
    {
        "pais": "Alemania",
        "confederacion": "UEFA",
        "copasDelMundo": 4,
        "copasConfederacion": 3
    },
    {
        "id": 3,
        "pais": "Mexico",
        "confederacion": "CONCACAF",
        "copasDelMundo": 0,
        "copasConfederacion": 1
    },
    {
        "id": 4,
        "pais": "España",
        "confederacion": "UEFA",
        "copasDelMundo": 1,
        "copasConfederacion": 3
    },
    {
        "id": 5,
        "pais": "Italia",
        "confederacion": "UEFA",
        "copasDelMundo": 4,
        "copasConfederacion": 1
    }
]
"""

    override fun getSelecciones(): String {
        return JSON
    }

}
class StubSeleccionesCreate : ServiceDeSeleccion {
    val jsonCreate =  """ [  {
        "pais": "Alemania",
        "confederacion": "UEFA",
        "copasDelMundo": 4,
        "copasConfederacion": 3
    }]"""

    override fun getSelecciones(): String {
        return jsonCreate
    }

}