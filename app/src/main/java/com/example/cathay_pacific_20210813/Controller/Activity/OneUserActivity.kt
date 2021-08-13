package com.example.cathay_pacific_20210813.Controller.Activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.cathay_pacific_20210813.R
import com.example.githubusers.Model.Uill.CircleTransform

import com.google.gson.Gson
import com.squareup.picasso.Picasso
import okhttp3.*
import java.io.IOException

//單人頁面
class OneUserActivity : Activity() {

    lateinit var mXXImage: ImageView
    lateinit var mUserPictureImage: ImageView
    lateinit var mIDTV: TextView
    lateinit var mBadgeImage: ImageView
    lateinit var mNameTV: TextView
    lateinit var mLocationTV: TextView
    lateinit var mLinkTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oneuser)

        mXXImage = findViewById(R.id.XXImage)
        mUserPictureImage = findViewById(R.id.UserPictureImage)
        mIDTV = findViewById(R.id.IDTV)
        mBadgeImage = findViewById(R.id.BadgeImage)
        mNameTV = findViewById(R.id.NameTV)
        mLocationTV = findViewById(R.id.LocationTV)
        mLinkTV = findViewById(R.id.LinkTV)

        mXXImage.setOnClickListener(View.OnClickListener() {
            onBackPressed()
        })

        val bundle = intent.extras
        val mID = bundle!!.getString("ID")
        getOneUserForAPI(mID!!)
    }


    fun getOneUserForAPI(name: String) {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            // 實例化一個 Builder
            //加上要發送請求的 API 網址
            //name 為傳入的參數
            .url("https://api.github.com/users" + "/" + name)
            //建立 Request
            .build()
        val call = okHttpClient.newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                println("fail : $e")
            }

            override fun onResponse(call: Call?, response: Response?) {
                //處理回來的 Response

            }
        })
    }

}