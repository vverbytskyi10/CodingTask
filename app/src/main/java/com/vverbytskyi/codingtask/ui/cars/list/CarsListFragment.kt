package com.vverbytskyi.codingtask.ui.cars.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vverbytskyi.codingtask.R
import com.vverbytskyi.codingtask.domain.cars.model.CarsData
import com.vverbytskyi.codingtask.ui.MainViewModel
import com.vverbytskyi.codingtask.ui.common.CompletedState
import com.vverbytskyi.codingtask.ui.common.decorators.VerticalSpaceItemDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_cars_list.*
import javax.inject.Inject

class CarsListFragment : DaggerFragment() {

    @Inject
    lateinit var carsListAdapter: CarsListAdapter

    private lateinit var model: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cars_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        model = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)

        initView()
    }

    private fun initView() {
        carsList.apply {
            adapter = carsListAdapter
            addItemDecoration(
                VerticalSpaceItemDecoration(
                    context!!, R.dimen.item_cars_list_spacing, drawFirst = true, drawLast = true
                )
            )
        }

        model.getCarsLiveData().observe(this, Observer { state ->
            when (state) {
                is CompletedState<*> -> {
                    (state.data as? CarsData)?.also { carsListAdapter.items = it.cars }
                }
            }
        })
    }
}