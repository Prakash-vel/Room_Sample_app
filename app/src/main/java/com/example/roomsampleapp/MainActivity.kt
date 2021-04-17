package com.example.roomsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.roomsampleapp.database.*

import com.example.roomsampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var data: List<SampleData>
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)

        val application = requireNotNull(this).application
        val dataSource = SampleDatabase.getInstance(application).SampleDatabaseDao


        val viewModelFactory=MainViewModelFactory(dataSource )
        val mainViewModel: MainViewModel=ViewModelProviders.of(this , viewModelFactory).get(MainViewModel::class.java)
        binding.button.setOnClickListener {
            val str=binding.textBox.text.toString()
            mainViewModel.insert(str)
            mainViewModel.getData()
        }
        mainViewModel.state.observe(this, Observer{
            if(it==true){
                data= mainViewModel.data!!
                mainViewModel.state.value=false
                showData()
            }
        })
        mainViewModel.getData()

    }
    fun showData(){
        val layout = binding.linearlayout
        layout.removeAllViews()

        data.forEach {
            val textView=TextView(this)
            textView.setText(it.sampleName)
            textView.setPadding(20,20,20,20)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.2F)
            layout.addView(textView)
        }
    }
}