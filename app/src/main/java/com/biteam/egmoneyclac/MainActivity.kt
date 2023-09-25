package com.biteam.egmoneyclac

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.biteam.egmoneyclac.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.LoadAdError
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        firebaseAnalytics = Firebase.analytics


        setContentView(binding.root)


       binding.adView.adListener = object: AdListener() {
            override fun onAdClicked() {
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM,Bundle().apply {
                    putString("onAdClicked","onAdClicked")
                })
            }

            override fun onAdClosed() {
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM,Bundle().apply {
                    putString("onAdClosed","onAdClosed")
                })
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdImpression() {
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM,Bundle().apply {
                    putString("onAdImpression","onAdImpression")
                })
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdOpened() {
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM,Bundle().apply {
                    putString("onAdOpened","onAdOpened")
                })
            }
        }


        binding.cat200.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                clacMoney()
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM,Bundle().apply {
                    putString("cat200","afterTextChanged")
                })
            }
        })
        binding.cat100.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                clacMoney()
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM,Bundle().apply {
                    putString("cat100","afterTextChanged")
                })
            }
        })
        binding.cat50.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                clacMoney()
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM,Bundle().apply {
                    putString("cat50","afterTextChanged")
                })
            }
        })
        binding.cat20.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                clacMoney()
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM,Bundle().apply {
                    putString("cat20","afterTextChanged")
                })
            }
        })
        binding.cat10.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                clacMoney()
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM,Bundle().apply {
                    putString("cat10","afterTextChanged")
                })
            }
        })
        binding.cat5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                clacMoney()
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM,Bundle().apply {
                    putString("cat5","afterTextChanged")
                })
            }
        })

        binding.resetBtn.setOnClickListener(View.OnClickListener {
            binding.cat200.setText("")
            binding.cat100.setText("")
            binding.cat50.setText("")
            binding.cat20.setText("")
            binding.cat10.setText("")
            binding.cat5.setText("")
        })
        binding.shareBtn.setOnClickListener(View.OnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, binding.resultCalcMoney.text.toString())
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "مجموع النقود")
            startActivity(Intent.createChooser(shareIntent, "إرسال..."))
        })
    }

    @SuppressLint("SetTextI18n")
    private fun clacMoney() {
        val c200 = getValueOfCateogry(binding.cat200)
        val c100 = getValueOfCateogry(binding.cat100)
        val c50 = getValueOfCateogry(binding.cat50)
        val c20 = getValueOfCateogry(binding.cat20)
        val c10 = getValueOfCateogry(binding.cat10)
        val c5 = getValueOfCateogry(binding.cat5)
        val summation = c200 * 200 + c100 * 100 + c50 * 50 + c20 * 20 + c10 * 10 + c5 * 5
        binding.resultCalcMoney.text = "$summation جنية"
    }

    private fun getValueOfCateogry(category: EditText?): Float {
        return if (category!!.text.toString().isNotEmpty()) {
            category.text.toString().toFloat()
        } else {
            0f
        }
    }
}