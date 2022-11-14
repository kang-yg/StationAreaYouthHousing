package com.kyg.stationareayouthhousing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyandroid.model.Resource
import com.kyg.stationareayouthhousing.Constants.concatAddress
import com.kyg.stationareayouthhousing.model.Repository
import com.kyg.stationareayouthhousing.model.dto.Address
import com.kyg.stationareayouthhousing.model.dto.Geocoding
import com.kyg.stationareayouthhousing.model.dto.Plan
import com.kyg.stationareayouthhousing.model.dto.Supply
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _planListMutableLiveData = MutableLiveData<List<Plan>>()
    private val _geocodingMutableLiveData = MutableLiveData<Resource<Geocoding>>()
    val planListLiveData: LiveData<List<Plan>>
        get() = _planListMutableLiveData
    val geocodingLiveData: LiveData<Resource<Geocoding>>
        get() = _geocodingMutableLiveData

    fun createAndReadPlan(isNetworkConnected: Boolean) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                if (isNetworkConnected) {
                    launch {
                        deleteAllPlan()
                    }.join()

                    launch {
                        crawlingPlan()
                    }.join()
                }

                launch {
                    getAllPlan()
                }
            }
        }
    }

    fun getGeocodingAddress(address: Address) =
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                _geocodingMutableLiveData.postValue(Resource.loading(null))
                repository.getGeocoding(address.concatAddress()).let {
                    if (it.isSuccessful)
                        _geocodingMutableLiveData.postValue(Resource.success(it.body()))
                    else
                        _geocodingMutableLiveData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }


    private fun crawlingPlan() {
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

    private fun getAllPlan() {
        _planListMutableLiveData.postValue(repository.getAllPlan())
    }

    private fun deleteAllPlan() = repository.deleteAllPlan()
}