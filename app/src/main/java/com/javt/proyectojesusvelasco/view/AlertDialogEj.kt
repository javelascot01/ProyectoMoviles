package com.javt.proyectojesusvelasco.view

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.javt.proyectojesusvelasco.R
import com.javt.proyectojesusvelasco.databinding.ActivityAlertDialogBinding
import com.javt.proyectojesusvelasco.databinding.DialogoPersonalizadoBinding

class AlertDialogEj : AppCompatActivity() {
    private lateinit var binding: ActivityAlertDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlertDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpleAlert.setOnClickListener { showSimpleAlertDialog() }
        binding.btnOkCancel.setOnClickListener { showOkCancelAlertDialog() }
        binding.btnOkNoCancel.setOnClickListener { showOkNoCancelAlertDialog() }
        binding.btnListAlert.setOnClickListener { showListAlertDialog() }
        binding.btnSingleChoiceAlert.setOnClickListener { showSingleChoiceAlertDialog() }
        binding.btnMultiChoiceAlert.setOnClickListener { showMultiChoiceAlertDialog() }
    }
    private fun showSimpleAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Simple Alert")
            .setMessage("This is a simple alert dialog.")
            .setPositiveButton("OK", null)
            .setIcon(R.drawable.ic_launcher_foreground)
            .show()
    }
    private fun showOkCancelAlertDialog() {
        AlertDialog.Builder(ContextThemeWrapper(this,R.style.MyAlertDialogTheme))
            .setTitle("Confirmation")
            .setMessage("Are you sure you want to proceed?")
            .setPositiveButton("OK") { dialog, which ->
                // Handle OK button click
                Toast.makeText(this, "OK clicked", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                // Handle Cancel button click
                Toast.makeText(this, "Cancel clicked", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
    private fun showOkNoCancelAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Confirmation")
            .setMessage("Are you sure you want to proceed?")
            .setPositiveButton("OK") { dialog, which ->
                // Handle OK button click
                Toast.makeText(this, "OK clicked", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No") { dialog, which ->
                // Handle No button click
                Toast.makeText(this, "No clicked", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("Cancel") { dialog, which ->
                // Handle Cancel button click
                Toast.makeText(this, "Cancel clicked", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    private fun showListAlertDialog() {
        val items = arrayOf("Item 1", "Item 2", "Item 3") //este array podría venir cargado de un fichero o una BBDD.
        AlertDialog.Builder(this)
            .setTitle("List Alert")
            .setItems(items) { dialog, which ->
                // Handle item selection
                Toast.makeText(this, "Selected: ${items[which]}", Toast.LENGTH_SHORT).show()
            }

            .show()
    }

    private fun showSingleChoiceAlertDialog() {
        val items = arrayOf("Item 1", "Item 2", "Item 3")
        var checkedItem = -1 // No item selected initially
        AlertDialog.Builder(this)
            .setTitle("Single Choice Alert")
            .setSingleChoiceItems(items, checkedItem) { dialog, which ->
                checkedItem = which
            }
            .setPositiveButton("OK") { dialog, which ->
                // Handle selection
                Toast.makeText(this, "Selected: ${items[checkedItem]}", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    private fun showMultiChoiceAlertDialog() {
        val items = arrayOf("Item 1", "Item 2", "Item 3")
        val selectedItems = booleanArrayOf(false, false, false) // Initially, no items are selected
        AlertDialog.Builder(this)
            .setTitle("Multi Choice Alert")
            .setMultiChoiceItems(items, selectedItems) { dialog, which, isChecked ->
                selectedItems[which] = isChecked
            }
            .setPositiveButton("OK") { dialog, which ->
                // Handle selections
                val selectedItemsList = mutableListOf<String>()
                for (i in selectedItems.indices) {
                    if (selectedItems[i]) {
                        selectedItemsList.add(items[i])
                    }
                }
                Toast.makeText(this, "Selected: $selectedItemsList", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
    public fun showCustomInputAlertDialog(view: View) {
        val builder = AlertDialog.Builder(this)
        //val inflater = layoutInflater
        // val dialogLayout = inflater.inflate(R.layout.dialogo_personalizado, null)
        //val editText = dialogLayout.findViewById<EditText>(R.id.editTextData)
        val bindingDialog = DialogoPersonalizadoBinding.inflate(layoutInflater)
        val editText = bindingDialog.editTextData
        builder.setView(bindingDialog.root)
            //builder.setView(dialogLayout)
            .setPositiveButton("OK") { dialogInterface, i ->
                val inputText = editText.text.toString()
                // Process the input text
                Toast.makeText(this, "Input: $inputText", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}