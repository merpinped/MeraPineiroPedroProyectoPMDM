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

class ListaPeliculasAdapters(val peliculas: List<Pelicula>): RecyclerView.Adapter<ListaPeliculasAdapters.PeliculasViewHolder>(){

    class PeliculasViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvDirector = itemView.findViewById<TextView>(R.id.tvDirectorDetalles)
        val tvTitulo = itemView.findViewById<TextView>(R.id.tvTituloDetalles)
        val ivImagen = itemView.findViewById<ImageView>(R.id.ivImagen)
        val tvGenero = itemView.findViewById<TextView>(R.id.tvGeneroDetalles)
        val tvNota = itemView.findViewById<TextView>(R.id.tvNota)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)

        return PeliculasViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        val pelicula = peliculas.get(position)

        holder.tvTitulo.setText(pelicula.nombre)
        holder.tvGenero.setText(pelicula.genero)
        holder.tvDirector.setText(pelicula.director)
        holder.tvNota.setText(pelicula.nota)

        // Libreria picasso
        Picasso.get().load(pelicula.url).into(holder.ivImagen)

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, holder.tvTitulo.text, Toast.LENGTH_SHORT).show()
            val intent = Intent(holder.itemView.context, DetallesActivity::class.java)

            intent.putExtra("pelicula", pelicula)
            holder.itemView.context.startActivity(intent)
        } // Click event, se abre la nueva activity
    }
    //este metodo te da la cuenta del largo, entonces simplemente podemos igualarlo
    override fun getItemCount() = peliculas.size
}
