package lab.uro.kitori.quintessentialquintuplets.extension

import android.app.Application
import lab.uro.kitori.quintessentialquintuplets.QuintessentialQuintupletsApplication
import lab.uro.kitori.quintessentialquintuplets.di.ApplicationComponent

fun Application.getInjector(): ApplicationComponent =
    (this as QuintessentialQuintupletsApplication).component
