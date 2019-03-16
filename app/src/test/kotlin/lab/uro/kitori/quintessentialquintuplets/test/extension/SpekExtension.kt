package lab.uro.kitori.quintessentialquintuplets.test.extension

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.spekframework.spek2.dsl.GroupBody

fun GroupBody.useLiveData() {
    ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
        override fun executeOnDiskIO(runnable: Runnable) {
            runnable.run()
        }

        override fun isMainThread() = true

        override fun postToMainThread(runnable: Runnable) {
            runnable.run()
        }
    })
}

fun GroupBody.useCoroutines() {
    beforeGroup {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    afterGroup {
        Dispatchers.resetMain()
    }
}
