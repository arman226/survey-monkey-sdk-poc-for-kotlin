package com.example.survey_mokey_poc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.surveymonkey.surveymonkeyandroidsdk.SurveyMonkey
import com.surveymonkey.surveymonkeyandroidsdk.utils.SMError
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    companion object{
        val HASH = "LBQK27G"
    }

    lateinit var sdkInstance: SurveyMonkey
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sdkInstance = SurveyMonkey()

        btn_test.setOnClickListener{
            sdkInstance.onStart(this, getString(R.string.app_name), 0, HASH)
            sdkInstance.startSMFeedbackActivityForResult(this,0, HASH);
        }


    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            val respondent = intent.getStringExtra("smRespondent")
            val surveyResponse = JSONObject(respondent)
            val e = intent.getSerializableExtra("SM_ERROR") as SMError?
            Toast.makeText(this, surveyResponse.toString(), Toast.LENGTH_LONG).show()
        } catch (e:Exception) {
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}