package es.murallaromana.proyecto.adpaters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.murallaromana.proyecto.R
import es.murallaromana.proyecto.activities.DetallesActivity
import es.murallaromana.proyecto.modelos.entidades.Pelicula


class ListaPeliculasAdapters(val peliculas: MutableList<Pelicula>): RecyclerView.Adapter<ListaPeliculasAdapters.PeliculasViewHolder>(){

    class PeliculasViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvDirector: TextView = itemView.findViewById(R.id.tvDirectorDetalles)
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTituloDetalles)
        val ivImagen: ImageView = itemView.findViewById(R.id.ivImagen)
        val tvGenero: TextView = itemView.findViewById(R.id.tvGeneroDetalles)
        val tvNota: TextView = itemView.findViewById(R.id.tvNota)
        val tvTelefono: TextView = itemView.findViewById(R.id.tvTelefono)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)

        return PeliculasViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        val pelicula = peliculas[position]

        holder.tvTitulo.text = pelicula.nombre
        holder.tvGenero.text = pelicula.genero
        holder.tvDirector.text = pelicula.director
        holder.tvNota.text = pelicula.nota.toString()
        holder.tvTelefono.text = pelicula.telefono

        // Libreria picasso
        Picasso.get().load(pelicula.url).into(holder.ivImagen)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetallesActivity::class.java)

            intent.putExtra("pelicula", pelicula)
            intent.putExtra("position", position)
            holder.itemView.context.startActivity(intent)
        } // Click event, se abre la nueva activity
    }
    override fun getItemCount() = peliculas.size
}
