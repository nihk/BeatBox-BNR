package rosenich.beatbox.extensions

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * https://medium.com/thoughts-overflow/how-to-add-a-fragment-in-kotlin-way-73203c5a450b
 */

fun AppCompatActivity.addFragment(@IdRes containerViewId: Int, fragment: Fragment)
    = doSupportFragmentTransaction { add(containerViewId, fragment)}

fun AppCompatActivity.replaceFragment(@IdRes containerViewId: Int, fragment: Fragment)
    = doSupportFragmentTransaction { replace(containerViewId, fragment)}

private fun AppCompatActivity.doSupportFragmentTransaction(
        function: FragmentTransaction.() -> FragmentTransaction)
    = supportFragmentManager.inTransaction(function)

fun AppCompatActivity.findFragmentById(@IdRes id: Int)
    = supportFragmentManager.findFragmentById(id)
