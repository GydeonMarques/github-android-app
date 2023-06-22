package br.com.gms.github.core.di

import br.com.gms.github.BuildConfig
import br.com.gms.github.core.data.api.GitApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 30L

    @Provides
    fun provideRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(provideHttpLoggingInterceptor())
            .build()

        return Retrofit
            .Builder()
            .client(httpClient)
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideGitApiService(retrofit: Retrofit): GitApiServices {
        return retrofit.create(GitApiServices::class.java)
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BASIC)
            }
        }
    }
}