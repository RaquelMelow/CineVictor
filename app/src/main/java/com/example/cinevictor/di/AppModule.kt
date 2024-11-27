import android.content.Context
import android.net.ConnectivityManager
import com.example.cinevictor.core.framework.network.interceptors.ConnectivityInterceptor
import com.example.cinevictor.data.network.MovieService
import com.example.cinevictor.data.repository.MovieRepository
import com.example.cinevictor.presentation.features.details.viewmodel.MovieDetailViewModel
import com.example.cinevictor.presentation.features.popular.films.viewmodel.FilmsViewModel
import com.example.cinevictor.presentation.features.popular.lists.viewmodel.ListsViewModel
import com.example.cinevictor.presentation.features.popular.reviews.viewModel.ReviewsViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.themoviedb.org/3/"

val presentationModule = module {
    viewModelOf(::FilmsViewModel)
    viewModelOf(::MovieDetailViewModel)
    viewModelOf(::ListsViewModel)
    viewModelOf(::ReviewsViewModel)
}

val dataModule = module {
    singleOf(::MovieRepository)
}

val coreModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(
                ConnectivityInterceptor(
                    get<Context>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                )
            )
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        get<Retrofit>().create(MovieService::class.java)
    }

    //TODO: Falta el Room y Dao
}