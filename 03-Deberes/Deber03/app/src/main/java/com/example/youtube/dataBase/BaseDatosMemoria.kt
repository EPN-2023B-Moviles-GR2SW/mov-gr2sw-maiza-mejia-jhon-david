package com.example.youtube.dataBase

import com.example.youtube.R
import com.example.youtube.model.Categoria
import com.example.youtube.model.Video
import com.example.youtube.model.Short

class BaseDatosMemoria {
    companion object {
        val arregloVideos = arrayListOf<Video>()
        val arregloCategorias = arrayListOf<Categoria>()
        val arregloShort = arrayListOf<Short>()
        init {
            arregloVideos.add(Video("Metro Boomin, James Blake - Hummingbird","Metro Boomin", "2.56 M", "hace 7 meses", R.drawable.imagenyoutube))
            arregloVideos.add(Video("LATIN MAFIA - JULIETOTA","LATIN MAFIA", "18 M", "hace 5 meses", R.drawable.imagenvideo2))
            arregloVideos.add(Video("Tipling Rock - Staring","Tipling Rock", "14 M", "hace 6 años", R.drawable.imagenvideo5))
            arregloVideos.add(Video("Sunflower - Post Malone, Swae Lee","Post Malone", "26.3 M", "hace 5 años", R.drawable.imagenvideo3))
            arregloVideos.add(Video("Ain't No Mountain High Enough ","Joel Gustafasson", "71 M", "hace 9 años", R.drawable.imagenvideo4))

            arregloCategorias.add(Categoria("Pódcast"))
            arregloCategorias.add(Categoria("Historial"))
            arregloCategorias.add(Categoria("Música"))
            arregloCategorias.add(Categoria("Mixes"))
            arregloCategorias.add(Categoria("Juegos"))

            arregloShort.add((Short(R.drawable.imageshort1)))
            arregloShort.add((Short(R.drawable.imageshort2)))
            arregloShort.add((Short(R.drawable.imageshort3)))
            arregloShort.add((Short(R.drawable.imageshort4)))

        }
    }

}