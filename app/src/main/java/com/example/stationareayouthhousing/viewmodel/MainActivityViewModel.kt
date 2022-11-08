package com.example.stationareayouthhousing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stationareayouthhousing.model.Repository
import com.example.stationareayouthhousing.model.dto.Address
import com.example.stationareayouthhousing.model.dto.Plan
import com.example.stationareayouthhousing.model.dto.Supply
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun crawlingPlan() {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                val url = "https://soco.seoul.go.kr/youth/main/contents.do?menuNo=400014"
                val doc = Jsoup.connect(url).get()
                val contentData = doc.select(".status_apply_table tbody tr")
                for (plan in contentData) {
                    val element = plan.select("td")
                    val year = element[0].text()
                    val serialNumber = element[1].text().toInt()
                    val address = Address(element[2].text(), element[3].text(), element[4].text())
                    val station = element[5].text()
                    val supply = Supply(element[6].text().toInt(), element[7].text().toInt(), element[8].text().toInt())
                    val publicDueDate = element[9].text()
                    val privateDueDate = element[10].text()
                    val expectedMoveInDate = element[11].text()
                    val tempPlan = Plan(year, serialNumber, address, station, supply, publicDueDate, privateDueDate, expectedMoveInDate)
                    repository.insertPlan(tempPlan)
                }
            }
        }
    }
}