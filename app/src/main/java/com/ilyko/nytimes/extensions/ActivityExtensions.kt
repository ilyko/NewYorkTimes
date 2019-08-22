package com.ilyko.nytimes.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}


fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
        backStackTag?.let {
            addToBackStack(fragment.javaClass.name)
        }
    }
}

fun Fragment.replaceFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
    fragmentManager?.inTransaction {
        replace(frameId, fragment)
        backStackTag?.let {
            addToBackStack(fragment.javaClass.name)
        }
    }
}