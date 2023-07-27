class Hobby(val titulo: String, val descripcion: String, val urlPhoto: String?)

class PerfilUsuario(
    val id: Int,
    val nombres: String,
    val apellidos: String,
    val urlPhoto: String?,
    val edad: Int,
    val correo: String,
    val biografia: String?,
    var estado: String,
    val hobbies: MutableList<Hobby> = mutableListOf()
) {
    fun agregarHobby(hobby: Hobby) {
        hobbies.add(hobby)
    }
}

fun main() {
    val listaPerfiles: MutableList<PerfilUsuario> = mutableListOf(
        // Perfiles precargados, puedes definir más perfiles aquí.
        PerfilUsuario(
            1,
            "Juan",
            "Perez",
            "url_foto_juan",
            30,
            "juan@example.com",
            "Descripción de Juan...",
            "Activo"
        ),
        PerfilUsuario(
            2,
            "Maria",
            "Gomez",
            null,
            25,
            "maria@example.com",
            null,
            "Pendiente"
        )
    )

    while (true) {
        println("Menu:")
        println("1. Crear perfil")
        println("2. Buscar perfil de usuario(s)")
        println("3. Eliminar perfil")
        println("4. Agregar Hobby")
        println("5. Salir")

        try {
            val opcion = readlnOrNull()?.toIntOrNull()

            when (opcion) {
                1 -> crearPerfil(listaPerfiles)
                2 -> buscarPerfil(listaPerfiles)
                3 -> eliminarPerfil(listaPerfiles)
                4 -> agregarHobby(listaPerfiles)
                5 -> {
                    println("Saliendo...")
                    break
                }
                else -> println("Opción inválida. Por favor, seleccione una opción válida.")
            }
        } catch (e: Exception) {
            println("Ha ocurrido un error. Por favor, inténtelo de nuevo.")
        }
    }
}

fun crearPerfil(listaPerfiles: MutableList<PerfilUsuario>) {
    println("Creando perfil...")
    println("Ingrese el ID:")
    val id = readlnOrNull()?.toIntOrNull() ?: return

    println("Ingrese los nombres del usuario:")
    val nombres = readlnOrNull() ?: return

    println("Ingrese los apellidos del usuario:")
    val apellidos = readlnOrNull() ?: return

    println("Ingrese la URL de la foto de perfil (opcional, presione Enter si no tiene):")
    val urlPhoto = readlnOrNull()

    println("Ingrese la edad del usuario:")
    val edad = readlnOrNull()?.toIntOrNull() ?: return

    println("Ingrese el correo electrónico del usuario:")
    val correo = readlnOrNull() ?: return

    println("Ingrese la biografía del usuario (opcional, presione Enter si no tiene):")
    val biografia = readlnOrNull()

    println("Ingrese el estado del usuario (Activo, Pendiente, Inactivo):")
    val estado = readlnOrNull()?.capitalize() ?: return

    val nuevoPerfil = PerfilUsuario(id, nombres, apellidos, urlPhoto, edad, correo, biografia, estado)
    listaPerfiles.add(nuevoPerfil)
    println("Perfil creado exitosamente.")
}

fun buscarPerfil(listaPerfiles: MutableList<PerfilUsuario>) {
    println("Buscar perfil de usuario(s):")
    println("Ingrese el ID o los nombres y/o apellidos:")
    val filtro = readlnOrNull()?.trim() ?: return

    val resultados = listaPerfiles.filter {
        it.id.toString() == filtro ||
                it.nombres.contains(filtro, ignoreCase = true) ||
                it.apellidos.contains(filtro, ignoreCase = true)
    }

    if (resultados.isEmpty()) {
        println("No se encontró ningún perfil con la información ingresada.")
    } else {
        for (perfil in resultados) {
            println("ID: ${perfil.id}")
            println("Nombres: ${perfil.nombres}")
            println("Apellidos: ${perfil.apellidos}")
            println("URL Foto: ${perfil.urlPhoto ?: "N/A"}")
            println("Edad: ${perfil.edad}")
            println("Correo: ${perfil.correo}")
            println("Biografía: ${perfil.biografia ?: "N/A"}")
            println("Estado: ${perfil.estado}")
            if (perfil.hobbies.isNotEmpty()) {
                println("Hobbies:")
                for (hobby in perfil.hobbies) {
                    println("  - ${hobby.titulo}")
                }
            }
            println()
        }
    }
}

fun eliminarPerfil(listaPerfiles: MutableList<PerfilUsuario>) {
    println("Eliminar perfil...")
    println("Ingrese el ID del perfil a eliminar:")
    val idEliminar = readlnOrNull()?.toIntOrNull() ?: return

    val perfilAEliminar = listaPerfiles.find { it.id == idEliminar }
    if (perfilAEliminar != null) {
        listaPerfiles.remove(perfilAEliminar)
        println("Perfil con ID $idEliminar eliminado correctamente.")
    } else {
        println("No se encontró ningún perfil con el ID ingresado.")
    }
}

fun agregarHobby(listaPerfiles: MutableList<PerfilUsuario>) {
    println("Agregar Hobby...")
    println("Ingrese el ID o los nombres y/o apellidos del perfil:")
    val filtro = readlnOrNull()?.trim() ?: return

    val perfilEncontrado = listaPerfiles.find {
        it.id.toString() == filtro ||
                it.nombres.contains(filtro, ignoreCase = true) ||
                it.apellidos.contains(filtro, ignoreCase = true)
    }

    if (perfilEncontrado == null) {
        println("No se encontró ningún perfil con la información ingresada.")
        return
    }

    println("Ingrese el título del Hobby:")
    val titulo = readlnOrNull() ?: return

    println("Ingrese la descripción del Hobby:")
    val descripcion = readlnOrNull() ?: return

    println("Ingrese la URL de la foto asociada al Hobby (opcional, presione Enter si no tiene):")
    val urlPhoto = readlnOrNull()

    val nuevoHobby = Hobby(titulo, descripcion, urlPhoto)
    perfilEncontrado.agregarHobby(nuevoHobby)
    println("Hobby agregado correctamente.")
}
