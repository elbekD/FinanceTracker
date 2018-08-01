import com.example.ignition.financetracker.di.module.ActivityModule
import com.example.ignition.financetracker.ui.billActivity.BillScreen
import com.example.ignition.financetracker.ui.billActivity.IBillScreenContract
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))

interface ActivityComponent {

    fun inject(billActivity: BillScreen)
}