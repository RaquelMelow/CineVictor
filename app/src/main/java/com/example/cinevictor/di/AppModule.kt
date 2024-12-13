import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.example.cinevictor.core.framework.network.interceptors.ConnectivityInterceptor
import com.example.cinevictor.core.framework.network.retrofit.MovieService
import com.example.cinevictor.core.framework.network.retrofit.ReviewService
import com.example.cinevictor.data.local.database.AppDatabase
import com.example.cinevictor.data.repository.AuthRepository
import com.example.cinevictor.data.repository.MovieRepository
import com.example.cinevictor.data.repository.ReviewRepository
import com.example.cinevictor.presentation.features.films.viewmodel.FilmsViewModel
import com.example.cinevictor.presentation.features.login.viewmodel.LoginViewModel
import com.example.cinevictor.presentation.features.popular.details.viewmodel.MovieDetailViewModel
import com.example.cinevictor.presentation.features.popular.journal.viewmodel.JournalViewModel
import com.example.cinevictor.presentation.features.popular.lists.viewmodel.ListsViewModel
import com.example.cinevictor.presentation.features.popular.reviews.viewModel.ReviewsViewModel
import com.example.cinevictor.presentation.features.register.viewmodel.RegisterViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
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
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::JournalViewModel)
}

val dataModule = module {
    singleOf(::MovieRepository)
    singleOf(::AuthRepository)
    singleOf(::ReviewRepository)
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

    single {
        get<Retrofit>().create(ReviewService::class.java)
    }

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "cine_victor.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().movieDao() }

    single { get<AppDatabase>().reviewDao() }

}