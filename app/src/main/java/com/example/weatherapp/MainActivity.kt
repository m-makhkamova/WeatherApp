package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.weatherapp.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    var binding = ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Tashkent?unitGroup=metric&key=UGZS22ZDW59WQQ83RC7EJ5TXK&contentType=json"
        val requestQueue: RequestQueue = Volley.newRequestQueue(requireContext())
        val request = JsonArrayRequest(url, object :Response.Listener<JSONArray> {
            override fun onResponse(response: JSONArray?) {
                for (i in 0 until response!!.length()) {
                    val obj = response.getJSONObject(i)
                    var temp = obj.getString("temp")
                    var icon = obj.getString("icon")

                    binding.temp.text = temp
                    binding.icon = icon


                }
            }
        },
            object :Response.ErrorListener{
                override fun onErrorResponse(error: VolleyError?) {

                }
            })

        requestQueue.add(request)
    }
}