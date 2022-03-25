package com.carvalho.todo.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


// Classe com as configurações do fragmente do calendário
class DatePickerFragment(private var timePickerListener: TimePickerListener) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {


    // Método para auxiliar a recuperação da data que o usuário selecionou
    interface TimePickerListener {
        fun onTimeSelected(date: LocalDate)
    }

    // Método que cria o calendário passando as datas atuais
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Recupera as datas atuais
        val c = Calendar.getInstance()
        val ano = c.get(Calendar.YEAR)
        val mes = c.get(Calendar.MONTH)
        val dia = c.get(Calendar.DAY_OF_MONTH)

        // Cria um fragmente do calendário com as datas
        return DatePickerDialog(requireContext(), this, ano, mes, dia)

    }

    // Recupera a data que o usuario selecionou
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        // Atribui as datas atuais no calendário as datas atuais
        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, p1)
        c.set(Calendar.MONTH, p2)
        c.set(Calendar.DAY_OF_MONTH, p3)

        // Retornamos a data que o usuário selecionou
        timePickerListener.onTimeSelected(
            c.time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        )
    }
}