import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.b2023gr2sw.BEntrenador
import com.example.b2023gr2sw.FRecyclearView
import java.util.ArrayList

class FRecyclerViewAdapatadorNombreDescripcion (
    private  val contexto: FRecyclearView,
    private val lista: ArrayList<BEntrenador>,
    private val recyclearView: RecyclerView
): RecyclerView.Adapter<
        FRecyclerViewAdapatadorNombreDescripcion.MyViewHolder
        >() {
            inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){



            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}