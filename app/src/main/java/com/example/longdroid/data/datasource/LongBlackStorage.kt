package com.example.longdroid.data.datasource

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.longdroid.BuildConfig

object LongBlackStorage {
    private lateinit var pref: SharedPreferences

    fun init(context: Context) {
        val masterKeyAlias = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

        pref = if (BuildConfig.DEBUG) {
            context.getSharedPreferences(
                BOOK_MARK,
                Context.MODE_PRIVATE,
            )
        } else {
            EncryptedSharedPreferences.create(
                context,
                context.packageName,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
            )
        }
    }

    var bookMarkIdx: Int
        get() = pref.getInt(BOOK_IDX, -1)
        set(value) = pref.edit { putInt(BOOK_IDX, value) }
}

const val BOOK_IDX = "bookMarkIdx"
const val BOOK_MARK = "bookMark"
