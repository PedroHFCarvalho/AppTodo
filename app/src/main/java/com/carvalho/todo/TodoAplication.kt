package com.carvalho.todo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // Define a classe como base de dependência
class TodoAplication : Application(){ // Estende o alcance da hilt para o programa por inteiro
}