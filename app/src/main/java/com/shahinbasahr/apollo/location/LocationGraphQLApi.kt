package com.shahinbasahr.apollo.location


import android.content.Context
import com.apollographql.apollo.api.Error
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.Rx2Apollo
import com.shahinbasahr.apollo.network.GeoPoint
import com.shahinbasahr.apollo.network.ProvideApolloClient
import com.shahinbasahr.apollo.network.RequestExceptionGraphQl
import com.shahinbashar.apollo.HomeBannersQuery
import com.shahinbashar.apollo.ReverseGeoCodeQuery
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationGraphQLApi @Inject constructor(
    @ApplicationContext private val context: Context
) : LocationNetworkSource {
    override fun reverseGeoCode(
        latitude: Double,
        longitude: Double
    ): Observable<ReverseGeoCodeQuery.Data> {
        val apolloQuery = ReverseGeoCodeQuery(GeoPoint(latitude, longitude))

        return Rx2Apollo.from(ProvideApolloClient.getApolloClient(context).query(apolloQuery))
            .onResponse()
            .map { data ->
                if (data.reverseGeoCode?.statusCode == 200) {
                    return@map data
                } else {
                    throw RequestExceptionGraphQl(
                        data.reverseGeoCode?.statusCode ?: 500,
                        data.reverseGeoCode?.message
                    )
                }
            }
    }

    override fun getHomeBanners(lat: Double, lng: Double): Observable<HomeBannersQuery.Data> {
        val homeBannersQuery = HomeBannersQuery(GeoPoint(lat, lng))

        return Rx2Apollo.from(ProvideApolloClient.getApolloClient(context).query(homeBannersQuery))
            .onResponse()
            .map { data: HomeBannersQuery.Data -> return@map data }
    }

}

fun <T> Observable<Response<T>>.onResponse(): Observable<T> {
    return this.map { response ->
        if (!response.hasErrors()) {
            response.data()
        } else {
            throw ApiExceptionGraphQl(response.errors())
        }
    }
}

class ApiExceptionGraphQl(val errorList: List<Error>?) : Exception()
