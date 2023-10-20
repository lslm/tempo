package ls.dev.tempo.views

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import ls.dev.tempo.databinding.ChangeLocationFormBinding

class ChangeLocationDialog(
    private val context: Context
) {
    fun show(updateCurrentWeatherFn: (latitude: String, longitude: String) -> Unit) {

        ChangeLocationFormBinding.inflate(LayoutInflater.from(context)).apply {
            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("OK") { _, _ ->
                    val latitudeText = latitudeField.text.toString();
                    val longitudeText = longitudeField.text.toString();

                    updateCurrentWeatherFn(latitudeText, longitudeText)
                }
                .setNegativeButton("Cancelar") { _, _ ->}
                .show()
        }
    }
}
