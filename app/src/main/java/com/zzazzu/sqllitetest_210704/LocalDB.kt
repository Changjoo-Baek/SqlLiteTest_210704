package com.zzazzu.sqllitetest_210704

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

    fun createDatabase(db: SQLiteDatabase) {
     // 테이블이 존재하지 않는 경우 생성
        var sql: String = "CREATE TABLE if not exists $(LocalDatas.userData.TABLE_NAME} (" +
            "${BaseColumns._ID} integer primary key autoincrement," +
            "$(LocalDatas.userData.COLUMN_NAME_ID} varchar(15)," +
            "$(LocalDatas.userData.COLUMN_NAME_PASSWORD} varchar(20)" +
            ");"

    db.execSQL(sql)
    }

    fun registerUser(id : String, password:String){
        val db = this.writableDatabase
        val values = ContentValues().apply { // insert 될 데이터
            put(LocalDatas.userData.COLUMN_NAME_ID, id)
            put(LocalDatas.userData.COLUMN_NAME_PASSWORD, password)
            }
        val newRowId = db?.insert(LocalDatas.userData.TABLE_NAME, null, values)
    // 인서트 후 인서트된 primary key column 의 값(_id_ 반환.
    }

    fun checkIdExists(id: String): Boolean {
        val db = this.readableDatabase

        // 리턴받고자 하는 컬럼 값의 array
        val projection = arrayOf(BaseColumns._ID)
        //,LocalDatas.userData.COLUMN_NAME_ID, LocalDatas.userData.COLUMN_NAME_PASSWORD)

        // WHERE "id" = id AND "PASSWORD"=password 구문 적용하는 부분
        val selection = "$(LocalDatas.userData.COLUMN_NAME_ID} =?"
        val selectionArgs = arrayOf(id)

        // 정렬조건 지정
        // val sortOrder = "${FeedEntry.COLUMN_NAME_SUBTITLE} DESC"

        val cursor = db.query(
            LocalDatas.userData.TABLE_NAME, //테이블
            projection,                     //리턴받고자 하는 컬럼
            selection,                      //where 조건
            selectionArgs,                  //where 조건에 해당하는 값의 배열
            null,                           //그룹 조건
            null,                           //having 조건
            null                            //orderby 조건 지정
        )
        if (cursor.count > 0) {
            return true;
        } else{
            return false;
        }
    }

    fun logIn(id: String, password: String) : Boolean {
        val db = this.readableDatabase

        // 리턴받고자 하는 컬럼 값의 array
        val projection = arrayOf(BaseColumns._ID)
        //,LocalDatas.userData.COLUMN_NAME_ID, LocalDatas.userData.COLUMN_NAME_PASSWORD)

        //WHERE "id" = id AND "password"=password 구문 적용하는 부분
        val selection =
            "${LocalDatas.userData.COLUMN_NAME_ID} =? AND $(LocalDatas.userData.COLUMN_NAME_PASSWORD} =?"
        val selectionArgs = arrayOf(id, password)

        // 정렬조건 지정
        // val sortOrder = "${FeedEntry.COLUMN_NAME_SUBTITLE} DESC"

        val cursor = db.query(
            LocalDatas.userData.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        if (cursor.count > 0) {// 반환된 cursor 의 0번째 값이 nulldlaus
            return true;
        } else {
            return false;
        }
    }
