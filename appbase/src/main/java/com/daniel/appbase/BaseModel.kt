package com.daniel.appbase

import android.text.TextUtils
import com.daniel.appbase.components.SharePrefUtils
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.apache.commons.codec.binary.Hex

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

open class BaseModel {

    //    private final String BASE_URL = "http://po.localhost.run/api/";
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClientBuilder = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .cache(null)
        .addInterceptor(logger)
    private val gson = GsonBuilder().setLenient().create()
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
/*

    public String getToken() {
        return SharePrefUtils.getInstance().load(SharePrefUtils.KEYS.TOKEN);
    }
*/

    private fun <T> createService(serviceClass: Class<T>, authToken: String?): T {
        if (!TextUtils.isEmpty(authToken)) {
            val interceptor = AuthenticationInterceptor(authToken!!)
            if (!httpClientBuilder.interceptors().contains(interceptor)) {
                httpClientBuilder.addInterceptor(interceptor)
                retrofitBuilder.client(httpClientBuilder.build())
            }
        }
        return retrofitBuilder.build().create(serviceClass)
    }

    private fun <T> createService(
        serviceClass: Class<T>,
        authToken: String?,
        bearerToken: String
    ): T {
        if (!TextUtils.isEmpty(authToken)) {
            val interceptor = AuthenticationInterceptor(authToken!!, bearerToken)
            if (!httpClientBuilder.interceptors().contains(interceptor)) {
                httpClientBuilder.addInterceptor(interceptor)
                retrofitBuilder.client(httpClientBuilder.build())
            }
        }
        return retrofitBuilder.build().create(serviceClass)
    }


    internal fun <T> createService(serviceClass: Class<T>, needsAuthentication: Boolean): T {
        val apiKey: String?
        apiKey = try {
            getAPIKey()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

        return if (needsAuthentication) {
            val bearerToken = SharePrefUtils.instance.load(SharePrefUtils.KEYS.TOKEN)
            createService(serviceClass, apiKey, bearerToken!!)
        } else
            createService(serviceClass, apiKey)
    }

    fun <T> createService(serviceClass: Class<T>) = retrofitBuilder.build().create<T>(serviceClass)

    @Throws(Exception::class)
    private fun getAPIKey(): String {
        val key = getAppTokenKey()
        val data = getAppTokenMessage()
        val sha256_HMAC = Mac.getInstance("HmacSHA256")
        val secret_key = SecretKeySpec(key.toByteArray(StandardCharsets.UTF_8), "HmacSHA256")
        sha256_HMAC.init(secret_key)
        return (Hex.encodeHex(sha256_HMAC.doFinal(data.toByteArray(StandardCharsets.UTF_8)))).toString()
    }

    external fun getAppTokenKey(): String

    external fun getAppTokenMessage(): String

//    public native String getDebugBaseURL();
//    public native String getReleaseBaseURL();

    /*  fun handleHttpErrorCode(errorLD: SingleEventLiveData<ErrorVO>, code: Int) {
          when (code) {
              301 -> {
                  errorLD.setValue(ErrorVO(R.string.error_msg_moved_permenantly, ErrorVO.TYPE.DIALOG))
              }
              401 -> {
                  errorLD.setValue(ErrorVO(R.string.error_msg_session_expired, ErrorVO.TYPE.DIALOG))
              }
              403 -> {
                  errorLD.setValue(ErrorVO(R.string.error_msg_payment_overdue, ErrorVO.TYPE.DIALOG))
              }
              404 -> {
                  errorLD.setValue(ErrorVO(R.string.error_msg_no_results, ErrorVO.TYPE.ERROR))
              }
              500 -> {
                  errorLD.setValue(ErrorVO(R.string.network_error_500, ErrorVO.TYPE.ERROR))
              }
          }

      }*/
//
//    protected fun getUserFont(): String {
//        return if (SharePrefUtils.getInstance().load(SharePrefUtils.KEYS.CURRENT_LANGUAGE).equals("zg")) "zg" else "uni"
//    }

    internal inner class AuthenticationInterceptor : Interceptor {
        private var authToken: String? = null
        private var bearerToken = ""

        /**
         * For Adding @param app_token in the header
         */
        constructor(authToken: String) {
            this.authToken = authToken
            showLogD("Auth Token Generated in HMAC  :$authToken")
        }

        constructor(authToken: String, bearerToken: String) {
            this.authToken = authToken
            this.bearerToken = bearerToken
            showLogD("Auth Token Generated in HMAC  :$authToken")
        }

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            val builder = originalRequest.newBuilder()
                .addHeader("apptoken", authToken)
            if (bearerToken != "")
                builder.addHeader("Authorization", "Bearer $bearerToken")
            builder.addHeader("Accept", "application/json")
            val request = builder.build()
            return chain.proceed(request)
        }

    }

    companion object {
        //    private final String BASE_URL = (BuildConfig.BUILD_TYPE.equals("debug")) ? getDebugBaseURL() : getReleaseBaseURL();
        private const val BASE_URL = "http://178.128.101.234/api/"
    }


}