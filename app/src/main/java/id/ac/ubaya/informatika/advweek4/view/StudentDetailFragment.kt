package id.ac.ubaya.informatika.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.gson.Gson
import id.ac.ubaya.informatika.advweek4.R
import id.ac.ubaya.informatika.advweek4.databinding.FragmentStudentDetailBinding
import id.ac.ubaya.informatika.advweek4.model.Student
import id.ac.ubaya.informatika.advweek4.util.loadImage
import id.ac.ubaya.informatika.advweek4.viewmodel.DetailViewModel
import id.ac.ubaya.informatika.advweek4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(),ButtonShowNotificationListener, ButtonUpdateClickListener {
    private lateinit var viewModel: ListViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_student_detail, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            txtId.setText(it.id)
            txtName.setText(it.name)
            txtBod.setText(it.bod)
            txtPhone.setText(it.phone)
        })*/
        if (arguments != null){
            viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

            val id = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel.DetailStudent(id)

            dataBinding.notif = this
            dataBinding.update = this

            /*viewModel.studensLD.observe(viewLifecycleOwner, Observer {
                txtId.setText(it[0].id)
                txtName.setText(it[0].name)
                txtBod.setText(it[0].bod)
                txtPhone.setText(it[0].phone)
                imageView2.loadImage(it[0].photoUrl.toString(), progressBar2)
            })*/

        }

        /*btnNotif.setOnClickListener {
            Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    MainActivity.showNotification(txtName.text.toString(), "A new notification created", R.drawable.ic_baseline_person_24)
                }
        }

        btnUpdate.setOnClickListener {
            val action = StudentDetailFragmentDirections.actionStudentList()
            Navigation.findNavController(it).navigate(action)
        }*/

        observerViewModel()
    }

    fun observerViewModel(){
        viewModel.studensLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student = it[0]
        })
    }

    override fun onButtonShowNotification(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    MainActivity.showNotification(txtName.text.toString(), "A new notification created", R.drawable.ic_baseline_person_24)
                }
    }

    override fun onButtonUpdateClick(v: View) {
        val action = StudentDetailFragmentDirections.actionStudentList()
        Navigation.findNavController(v).navigate(action)
    }
}