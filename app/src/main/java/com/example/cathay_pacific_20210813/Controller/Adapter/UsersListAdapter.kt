package com.example.cathay_pacific_20210813.Controller.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.cathay_pacific_20210813.Controller.Activity.OneUserActivity
import com.example.cathay_pacific_20210813.Model.Data.Users
import com.example.cathay_pacific_20210813.Model.Uill.CircleTransform
import com.example.cathay_pacific_20210813.R


import com.squareup.picasso.Picasso


class UsersListAdapter() : BaseAdapter() {
    var mIncomingC: Context? = null
    lateinit var mIncomingAU: Array<Users.Response>
    var mIncomingA: Activity? = null

    fun UsersListAdapter(activity: Activity, context: Context, AllUser: Array<Users.Response>) {
        mIncomingAU = AllUser
        mIncomingC = context
        mIncomingA = activity
    }


    override fun getCount(): Int {
        Log.d("UsersListAdapter", "mAllUser.length():" + mIncomingAU.size)
        if (mIncomingAU.size < 100) {
            return mIncomingAU.size + 1
        } else {
            return 100
        }
    }

    override fun getItem(position: Int): Any? {
        //取得 ListView 列表於 position 位置上的 Item
        return position
    }

    override fun getItemId(position: Int): Long {
        //取得 ListView 列表於 position 位置上的 Item 的 ID
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //設定與回傳 convertView 作為顯示在這個 position 位置的 Item 的 View。
        if (position < mIncomingAU.size && position != 100) {
            val layoutInflater = LayoutInflater.from(mIncomingC)
            val view = layoutInflater.inflate(R.layout.list_user, parent, false)

            val imgView: ImageView = view.findViewById<View>(R.id.imgAvatar) as ImageView
            val txtView = view.findViewById<View>(R.id.txtName) as TextView

            txtView.text = mIncomingAU[position].login

            Picasso.get()
                .load(mIncomingAU[position].avatar_url)
                .transform(CircleTransform())
                .placeholder(R.drawable.refresh)
                .error(R.drawable.xx)
                .into(imgView)

            val example = View.OnClickListener {
                // 寫要做的事...
                val intent = Intent()
                intent.setClass(mIncomingC!!, OneUserActivity::class.java)
                val bundle = Bundle()
                bundle.putString("ID",mIncomingAU[position].login)
                intent.putExtras(bundle)
                mIncomingC!!.startActivity(intent)
            }
            view.setOnClickListener(example)
            return view
        } else {
            val layoutInflater = LayoutInflater.from(mIncomingC)
            val view = layoutInflater.inflate(R.layout.list_user_separate, parent, false)
            return view
        }
    }


}