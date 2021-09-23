package id.ac.ubaya.informatika.advweek4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ubaya.informatika.advweek4.R
import id.ac.ubaya.informatika.advweek4.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    init {
        instances = this
    }

    companion object {
        private var instances: MainActivity ?= null

        fun showNotification(title:String, content:String, icon:Int){
            val channelId = "${instances?.packageName}-${instances?.getString(R.string.app_name)}"
            val builder = NotificationCompat.Builder(instances!!.applicationContext, channelId)
                    .apply {
                        setSmallIcon(icon)
                        setContentTitle(title)
                        setContentText(content)
                        setStyle(NotificationCompat.BigTextStyle())
                        priority = NotificationCompat.PRIORITY_DEFAULT

                        setAutoCancel(true)
            }

            val notif = NotificationManagerCompat.from(instances!!.applicationContext)
            notif.notify(1001,builder.build())


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(this, NotificationManagerCompat.IMPORTANCE_DEFAULT, true,
                getString(R.string.app_name), "App channel")

        /*//var observable =
                Observable.just("hellow", "world", "!!")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {Log.d("Messages", "data captured: $it")},
                        {Log.d("Messages", "error: ${it.message.toString()}")},
                        {Log.d("Messages", "complete")}
                )
        */
        /*val observer = object: Observer<String> {
            override fun onSubscribe(d: Disposable?) {
                Log.d("Messages", "Start Subsribe")
            }

            override fun onNext(t: String?) {
                Log.d("Messages", "data capture: ${t.toString()}")
            }

            override fun onError(e: Throwable?) {
                Log.e("Messages", "Error: ${e!!.message.toString()}")
            }

            override fun onComplete() {
                Log.d("Messages", "Completed")
            }

        }*/

        /*observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {Log.d("Messages", "data captured: $it")},
                        {Log.d("Messages", "error: ${it.message.toString()}")},
                        {Log.d("Messages", "complete")}
                )
        */
    }
}