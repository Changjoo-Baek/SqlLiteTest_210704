package com.zzazzu.sqllitetest_210704

import LocalDB
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zzazzu.sqllitetest_210704.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val DATABASE_VERSION = 1
    val DATABASE_NAME = "LocalDB.db"
    private lateinit var binding: ActivityMainBinding
    private lateinit var localDB: LocalDB

    //override fun onCreate(savedInstanceState: Bundle?) {
    //    super.onCreate(savedInstanceState)
    //    setContentView(R.layout.activity_main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        localDB = LocalDB(this, DATABASE_NAME, null, DATABASE_VERSION) // sqlite 모듈 생성

        binding.btnLogin.setOnClickListener { view ->
            val id = binding.editId.text.toString()
            val passwd = binding.editPw.text.toString()
            val exist = localDB.logIn(id, passwd) // 로그인 실행
            if (exist) {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this@MainActivity, "아이디나 비밀번호가 틀렸습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.btnRegister.setOnClickListener { view ->
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        localDB.close()
        super.onDestroy()

    }
}