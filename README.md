# aadhaar-esign-android-sdk-sample-app
Sample application for Aadhaar eSignature SDK

Step to use the SDK below as well:

#### 1. build.grade (project):

```
allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven {  url "https://dl.bintray.com/surepassio/esign-android-sdk" }
    }
}
```

#### 2. build.grade (app):


```
dependencies {
    implementation "io.surepass.sdk:esign-android-sdk:2.0.0"
}
```

#### 3. Inside Application:

```
import com.surepass.surepassesign.InitSDK
    fun startEsignProcess(){

        val token = "Bearer TOKEN” // Token received from the Initialize API
        val fullVerificationIntent = Intent(this, InitSDK::class.java)

        fullVerificationIntent.putExtra("token", token)
        fullVerificationIntent.putExtra("env", "PREPROD”) // Please use PREPROD or PROD

        startActivityForResult(
            fullVerificationIntent,
            10001
        )
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            10001 -> {
                val eSignResponse = data!!.getStringExtra("signedResponse");
                Log.d(SAMPLE_APP, "eSign Response $eSignResponse")
            }
        }
    }
```
