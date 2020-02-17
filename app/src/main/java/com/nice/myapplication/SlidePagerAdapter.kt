package com.nice.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.squareup.okhttp.OkHttpClient
import com.squareup.picasso.OkHttpDownloader
import com.squareup.picasso.Picasso
import androidx.annotation.NonNull



class SlidePagerAdapter(context: Context,list: MutableList<String>) : PagerAdapter() {
    var mContext:Context = context
    var mList:MutableList<String> = list

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var layoutInflater:LayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val slideLayout:View = layoutInflater.inflate(R.layout.slide_item,null)
        var slideImage:ImageView = slideLayout.findViewById(R.id.ivSlideItem)
        val picasso: Picasso
        val okHttpClient: OkHttpClient
        okHttpClient = OkHttpClient()
        picasso = Picasso.Builder(mContext)
            .downloader(OkHttpDownloader(okHttpClient))
            .build()
        picasso.load(mList[position]).into(slideImage)


        container.addView(slideLayout)
        return slideLayout
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
    override fun getCount(): Int {
        return mList.count()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}