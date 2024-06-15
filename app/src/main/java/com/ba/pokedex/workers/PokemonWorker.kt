package com.ba.pokedex.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ba.pokedex.R
import com.ba.pokedex.coroutines.ICoroutineContextProvider
import com.ba.pokedex.usecases.IWorkerPokemonUseCase
import com.ba.pokedex.utils.notifications.INotificationService
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonWorker(private val context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams), KoinComponent {

    companion object {
        private const val REPEAT_IN_SECONDS = 30000L
        private const val NUMBER_OF_EXECUTIONS = 5
        private const val COUNTER_START = 1
        private const val QUERY_LIMIT = 10
    }

    private val notificationService: INotificationService by inject()

    private val coroutineCtx: ICoroutineContextProvider by inject()

    private val useCase: IWorkerPokemonUseCase by inject()

    private var offset = 15

    override suspend fun doWork(): Result {

        return withContext(coroutineCtx.getIoContext()) {

            return@withContext try {
                for (i in COUNTER_START..NUMBER_OF_EXECUTIONS) {
                    val numberInPokedex = useCase.execute(offset, QUERY_LIMIT)
                    offset += QUERY_LIMIT
                    Log.d("PokemonWorker", "Offset: $offset")
                    Log.d("PokemonWorker", "Number in Pokedex: $numberInPokedex")
                    notificationService.showNotification(
                        context.getString(R.string.pokemon_worker_notification_title),
                        context.getString(
                            R.string.pokemon_worker_notification_body,
                            numberInPokedex.toString()
                        )
                    )
                    delay(REPEAT_IN_SECONDS)
                }
                Result.success()
            } catch (e: Exception) {
                notificationService.showNotification(
                    context.getString(R.string.pokemon_worker_error_notification_title),
                    context.getString(
                        R.string.pokemon_worker_error_notification_body
                    )
                )
                Result.failure()
            }
        }
    }
}