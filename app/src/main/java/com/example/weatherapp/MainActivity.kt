package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var binding = ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val url = "http://api.weatherapi.com/v1/forecast.json?key=34775b5a5d5f4d92ada34706231010&q=Tashkent&days=6&aqi=no&alerts=no"
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        var request = JsonObjectRequest(url, object :Response.Listener<JSONObject>{
            override fun onResponse(response: JSONObject?) {
                val resobj = response!!.getJSONObject("location")
                val region = resobj.getString("region")
                val current = response.getJSONObject("current")
                val condition = current.getJSONObject("condition")
                val weatherIcon = "http:"+condition.getString("icon")
                binding.temp.text = current.getString("temp")


            }
                                                                                  },
            object :Response.ErrorListener{
                override fun onErrorResponse(error: VolleyError?) {
                    Log.d("TAG", "onErrorResponse: $error")
                }
                                                                                  })

    }
}