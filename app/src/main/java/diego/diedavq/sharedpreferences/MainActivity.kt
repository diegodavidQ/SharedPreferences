package diego.diedavq.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val KEY_PREFERENCE ="KEY"
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //---  SharedPreferences---//
        prefs =  getSharedPreferences(KEY_PREFERENCE, Context.MODE_PRIVATE)

        btnGet.setOnClickListener(this)
        btnPut.setOnClickListener(this)
        btnDelete.setOnClickListener(this)

    }


    //button listener
    override fun onClick(v: View?) {
        when (v?.id) {
            //Get text saved on SharedPreferences with KEY=KEY
            R.id.btnGet -> {
                var texto :String? = prefs?.getString(KEY_PREFERENCE,"No hay registro")
                showAlert("Valor obtenido: " + texto.toString())
            }
            //Save text on SharedPreferences with KEY=KEY
            R.id.btnPut -> {
                var texto = txtTextSave.text.toString()
                Toast.makeText(this,"Texto: "+texto,Toast.LENGTH_LONG).show()
                if (texto == ""){
                    texto = ""
                }
                val editor : SharedPreferences.Editor? = prefs?.edit()
                editor?.putString(KEY_PREFERENCE, texto)
                editor?.apply()
                showAlert("Valor guardado: "+texto.toString())
            }
            R.id.btnDelete -> {
                val editor : SharedPreferences.Editor? = prefs?.edit()
                editor?.remove(KEY_PREFERENCE)
                editor?.apply()
                showAlert("valor borrado")
            }
        }
    }

    private fun showAlert(message: String){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("My preferences")
        builder.setMessage(message)
        val dialog = builder.create()
        dialog.show()
    }

}