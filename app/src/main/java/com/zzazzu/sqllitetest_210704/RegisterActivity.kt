package com.zzazzu.sqllitetest_210704

import LocalDB
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zzazzu.sqllitetest_210704.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var localDB: LocalDB
    val DATABASE_VERSION = 1
    val DATABASE_NAME = "LocalDB.db"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        localDB = LocalDB(this, DATABASE_NAME, null, DATABASE_VERSION)

        binding.btnRegister.setOnClickListener { view ->

            if (binding.editId.text.isEmpty() || binding.editPw.text.isEmpty() || binding.editPwRe.text.isEmpty()) {
                Toast.makeText(this, "값을 전부 입력해주세요", Toast.LENGTH_LONG).show()
            } else {
                if (binding.editPw.text.toString().equals(binding.editPwRe.text.toString())) {
                    if (localDB.checkIdExist(binding.editId.text.toString())) {
                        Toast.makeText(this, "아이디가 이미 존재합니다.", Toast.LENGTH_LONG).show()
                    } else {
                        localDB.registerUser(
                            binding.editId.text.toString(),
                            binding.editPw.text.toString()
                        )}
                    }else{
                        Toast.makeText(this, "패스워드가 틀렸습니다.", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    }