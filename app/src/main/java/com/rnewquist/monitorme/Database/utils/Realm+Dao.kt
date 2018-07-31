@file:JvmName("RealmUtils") // pretty name for utils class if called from
package com.example.android.persistence.codelab.realmdb.utils

import com.rnewquist.monitorme.Database.BookDao
import com.rnewquist.monitorme.Database.LoanDao
import com.rnewquist.monitorme.Database.UserDao
import com.rnewquist.monitorme.Database.utils.LiveRealmData
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults

fun Realm.userModel(): UserDao = UserDao(this)
fun Realm.bookModel(): BookDao = BookDao(this)
fun Realm.loanModel(): LoanDao = LoanDao(this)

fun <T:RealmModel> RealmResults<T>.asLiveData() = LiveRealmData<T>(this)
