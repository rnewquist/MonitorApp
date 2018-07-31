package com.rnewquist.monitorme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mShowUserViewModel: CustomResultViewModel? = null
    private var mBooksTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mShowUserViewModel = ViewModelProviders.of(this).get(CustomResultViewModel::class.java)
        mShowUserViewModel?.loansResult?.observe(this, Observer<String> { t ->
            books_tv.text = t
        })
    }

    fun onRefreshBtClicked(view: View) {
        mShowUserViewModel?.simulateDataUpdates()
    }
}
