package com.rnewquist.monitorme

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.android.persistence.codelab.realmdb.utils.loanModel
import com.rnewquist.monitorme.Database.Loan
import com.rnewquist.monitorme.Database.utils.DatabaseInitializer
import io.realm.Realm
import io.realm.RealmResults
import java.text.SimpleDateFormat
import java.util.*

class ViewModelTest : ViewModel(){
    private val mDb: Realm = Realm.getDefaultInstance()
    lateinit var mLoansResult: LiveData<String>

    init {
        subscribeToMikesLoansSinceYesterday()
        simulateDataUpdates()
    }

    fun simulateDataUpdates() {
        DatabaseInitializer.populateAsync(mDb)
    }

    private fun subscribeToMikesLoansSinceYesterday() {
        val loans = mDb.loanModel()
                .findLoansByNameAfter("Mike", getYesterdayDate())
        mLoansResult = Transformations.map(loans){
            val sb = StringBuilder()
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm",
                    Locale.US)

            for (loan in it) {
                sb.append(String.format("%s\n  (Returned: %s)\n",
                        loan.book.title,
                        simpleDateFormat.format(loan.endTime)))
            }
            sb.toString()
        }
    }

    override fun onCleared() {
        mDb.close()
        super.onCleared()
    }

    private fun getYesterdayDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DATE, -1)
        return calendar.time
    }
}