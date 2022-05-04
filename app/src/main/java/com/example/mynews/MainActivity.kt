package com.example.mynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger
import com.littlemango.stacklayoutmanager.StackLayoutManager

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    lateinit var newsList:RecyclerView
    private var articles= mutableListOf<Article>()
    var totalPages=-1
    var pageNum=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newsList=findViewById<RecyclerView>(R.id.newsList)
        adapter= NewsAdapter(this@MainActivity,articles)
        newsList.adapter=adapter
        val layoutManager=StackLayoutManager(StackLayoutManager.ScrollOrientation.RIGHT_TO_LEFT)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        layoutManager.setItemChangedListener(object :StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
                if(totalPages>layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition()>= layoutManager.itemCount-3){
                    pageNum++
                    getNews()
                }
            }

        })
        newsList.layoutManager=layoutManager
        getNews()
    }

    private fun getNews() {
        val news = NewsInterface.newsInstance.getHeadlines("in", pageNum)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news: News? = response.body()
                if (news != null) {
                    // Log.d("Vyom", news.toString())
                        totalPages=news.totalResults
                        articles.addAll(news.articles)
                        adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                    Log.d("Vyom", "Fail", t)
            }
        })
    }
}