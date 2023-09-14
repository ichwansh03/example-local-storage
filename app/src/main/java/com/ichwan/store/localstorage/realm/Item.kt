package com.ichwan.store.localstorage.realm

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Item : RealmObject {

    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var email: String = ""
    var address: String = ""
}