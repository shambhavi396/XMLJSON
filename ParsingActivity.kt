package com.example.xmljsonactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.json.JSONObject
import javax.xml.parsers.DocumentBuilderFactory

class ParsingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parsing)
        var i=intent
        if (i.getStringExtra("type") == "xml") //XML Parsing
        {
            var istream = assets.open("city.xml")
            var builderFactory = DocumentBuilderFactory.newInstance()
            var dom = builderFactory.newDocumentBuilder()
            var doc = dom.parse(istream) //Do DOM parsing for XML
            var city_name = doc.getElementsByTagName("city_name").item(0).textContent
            var latitude = doc.getElementsByTagName("latitude").item(0).textContent
            var longitude = doc.getElementsByTagName("longitude").item(0).textContent
            var temperature = doc.getElementsByTagName("temperature").item(0).textContent
            var humidity = doc.getElementsByTagName("humidity").item(0).textContent
            var t = findViewById<View>(R.id.txtXML) as TextView
            t.setText("City Name: $city_name\nLatitude:$latitude\nLongitude:$longitude\nTemperature:$temperature\nHumidity:$humidity")
        }
        else //JSON Parsing
        {
            var json=assets.open("city.json").bufferedReader().use { it.readText() }
            var jsonobj= JSONObject(json)
            var city_name=jsonobj.getString("city_name")
            var latitude=jsonobj.getString("latitude")
            var longitude=jsonobj.getString("longitude")
            var temperature=jsonobj.getString("temperature")
            var humidity=jsonobj.getString("humidity")
            var t=findViewById<View>(R.id.txtJSON) as TextView
            t.setText("City Name: $city_name\nLatitude:$latitude\nLongitude:$longitude\nTemperature:$temperature\nHumidity:$humidity")
        }
    }

    }
