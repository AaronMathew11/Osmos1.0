package com.example.awt10

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.awt10.fragments.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_code_fragment.*
import kotlinx.android.synthetic.main.fragment_scheduling_fragment.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val homeFragment = Home_fragment()
        val codeFragment = Code_fragment()
        val schedulingFragment = Scheduling_fragment()
        val aboutFragment = About_fragment()

        makeCurrentfragment(homeFragment)

        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->makeCurrentfragment(homeFragment)
                R.id.code ->makeCurrentfragment(codeFragment)
                R.id.scheduling-> makeCurrentfragment(schedulingFragment)
                R.id.about->makeCurrentfragment(aboutFragment)

            }
            true
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val profileFragment=Profile_fragment()
        val feedbackFragment= Feedback_fragment()
        val contributeFragment =  Contribute_fragment()
        when(item.itemId){
            R.id.Profile-> supportFragmentManager.beginTransaction().apply {
                replace(R.id.wrapper,profileFragment)
                commit()
            }
            R.id.Logout-> {Firebase.auth.signOut()
            intent= Intent(this,Login_activity::class.java)
                startActivity(intent)
            }

            R.id.Feedback->supportFragmentManager.beginTransaction().apply {
                replace(R.id.wrapper,feedbackFragment)
                commit()
            }
            R.id.Contribute->supportFragmentManager.beginTransaction().apply {
                replace(R.id.wrapper,contributeFragment)
                commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun makeCurrentfragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.wrapper,fragment)
            commit()
        }

    fun process_scheduling_clicked(view: android.view.View){
        supportFragmentManager.beginTransaction().apply {
            val processSchedulingInfo = Process_scheduling_info()
            replace(R.id.wrapper,processSchedulingInfo)
            commit()
        }
    }

    fun ps_learnmore(view: android.view.View) {
        gotourl("https://www.tutorialspoint.com/operating_system/os_process_scheduling.htm")
    }

    private fun gotourl(s: String) {
        val uri:Uri = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW,uri))
    }

    fun processcalc(view: android.view.View) {
        pro_schedulebutton.visibility=View.GONE
        pro_crebtn.visibility=View.GONE
        pro_manbtn.visibility=View.GONE
        probx_btn.visibility=View.GONE
        mem_manbtn.visibility=View.GONE
        file_btn.visibility=View.GONE
    }


}