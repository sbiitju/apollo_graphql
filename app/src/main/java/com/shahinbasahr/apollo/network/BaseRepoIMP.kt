package com.shahinbasahr.apollo.network

import com.apollographql.apollo.rx2.Rx2Apollo
import com.shahinbashar.apollo.GetZoneQuery
import io.reactivex.Observable
import javax.inject.Inject

class BaseRepoIMP @Inject constructor(private val webService:ProvideApolloClient) : BaseRepo {
    override fun getZone(latitude: Double, longitude: Double): Observable<GetZoneQuery.Data>{
        return Rx2Apollo.from(webService.getApolloClient().query(GetZoneQuery(1))).map { data ->
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
