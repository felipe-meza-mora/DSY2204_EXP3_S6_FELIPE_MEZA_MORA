
import com.example.lili.R
import com.example.lili.model.Receta

object DataProvider {

    val recetaList = listOf(
        Receta(
            name = "Cazuela de Vacuno",
            description = "Un guiso tradicional chileno con carne de vacuno y verduras.",
            imageResId = R.drawable.cazuela, // Reemplaza con el recurso de la imagen
            steps = listOf(
                "Cortar la carne en trozos y sellarla en una olla.",
                "Añadir agua, papas, zapallo, choclo, y porotos verdes.",
                "Cocinar a fuego lento hasta que todo esté tierno.",
                "Agregar arroz o fideos si se desea."
            ),
            preparationTime = "1 hora",
            calories = 350,
            status = "Saludable"
        ),
        Receta(
            name = "Charquicán",
            description = "Un plato a base de charqui y verduras, típico de la zona norte.",
            imageResId = R.drawable.charquican, // Reemplaza con el recurso de la imagen
            steps = listOf(
                "Cocinar las papas y la zapallo en una olla.",
                "Añadir las verduras y el charqui desmenuzado.",
                "Cocinar a fuego lento y mezclar bien los ingredientes.",
                "Servir caliente con huevo frito encima."
            ),
            preparationTime = "45 minutos",
            calories = 300,
            status = "Saludable"
        ),
        Receta(
            name = "Ensalada Chilena",
            description = "Una refrescante ensalada de tomate, cebolla y cilantro.",
            imageResId = R.drawable.ensalada_chilena, // Reemplaza con el recurso de la imagen
            steps = listOf(
                "Cortar los tomates en rodajas.",
                "Picar la cebolla fina y sumergirla en agua caliente para suavizar su sabor.",
                "Mezclar todo en un bol con cilantro picado y aderezar con sal, aceite y vinagre."
            ),
            preparationTime = "15 minutos",
            calories = 120,
            status = "Saludable"
        ),
        Receta(
            name = "Pastel de Choclo",
            description = "Un delicioso pastel de choclo con carne y pollo.",
            imageResId = R.drawable.pastel_choclo, // Reemplaza con el recurso de la imagen
            steps = listOf(
                "Preparar la pino con carne picada, cebolla, y especias.",
                "Cocinar el choclo y molerlo para hacer una crema.",
                "Colocar el pino en una fuente y cubrir con la crema de choclo.",
                "Hornear hasta dorar la superficie."
            ),
            preparationTime = "1 hora 30 minutos",
            calories = 450,
            status = "Saludable"
        ),
        Receta(
            name = "Porotos Granados",
            description = "Un plato veraniego con porotos frescos, maíz y zapallo.",
            imageResId = R.drawable.porotos_granados, // Reemplaza con el recurso de la imagen
            steps = listOf(
                "Cocinar los porotos en agua con sal hasta que estén tiernos.",
                "Añadir zapallo, choclo desgranado, y albahaca.",
                "Cocinar hasta que todo esté bien integrado y espeso.",
                "Servir caliente con una ensalada chilena."
            ),
            preparationTime = "1 hora",
            calories = 400,
            status = "Saludable"
        )
    )



}