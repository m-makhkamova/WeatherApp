package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    var binding = ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val url = "http://api.weatherapi.com/v1/forecast.json?key=34775b5a5d5f4d92ada34706231010&q=Tashkent&days=6&aqi=no&alerts=no"
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        val request = JsonArrayRequest(url, object :Response.Listener<JSONArray> {
            override fun onResponse(response: JSONArray?) {
                for (i in 0 until response!!.length()) {
                    val obj = response.getJSONObject(i)
                    var temp = obj.getString("temp")
                    var icon = obj.getString("icon")

                    binding.temp.text = temp

                }
            }
        },
            object :Response.ErrorListener{
                override fun onErrorResponse(error: VolleyError?) {

                }
            })

        requestQueue.add(request)

        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }
}