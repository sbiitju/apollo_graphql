package com.shahinbasahr.apollo.network

import android.content.Context
import com.apollographql.apollo.rx2.Rx2Apollo
import com.shahinbashar.apollo.GetZoneQuery
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import javax.inject.Inject

class BaseRepoIMP @Inject constructor(@ApplicationContext private val context:Context) : BaseRepo {
    override fun getZone(latitude: Double, longitude: Double): Observable<GetZoneQuery.Data>{
        val apolloQuery = GetZoneQuery(GeoPoint(latitude,longitude))
        return Rx2Apollo.from(ProvideApolloClient.getApolloClient(context).query(apolloQuery)).map { data ->
            if (data.data?.getZone?.statusCode == 200) {
                return@map data.data
            } else {
                throw RequestExceptionGraphQl(
                    data.data?.getZone?.statusCode ?: 500,
                    data.data?.getZone?.message
                )
            }
        }
    }

}
open class RequestExceptionGraphQl(var httpCode: Int = 500, override var message: String? = "") : Exception(message)
