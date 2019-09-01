package com.vverbytskyi.codingtask.ui.cars.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.vverbytskyi.codingtask.R
import com.vverbytskyi.codingtask.domain.carslist.CarsListUseCase
import com.vverbytskyi.codingtask.domain.carslist.model.CarsData
import com.vverbytskyi.codingtask.ui.common.CompletedState
import com.vverbytskyi.codingtask.ui.common.decorators.VerticalSpaceItemDecoration
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_cars_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CarsListFragment : DaggerFragment() {

    @Inject
    lateinit var carsListAdapter: CarsListAdapter

    @Inject
    lateinit var carsListUseCase: CarsListUseCase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cars_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()

        fetchCars()
    }

    private fun initView() {
        carsList.adapter = carsListAdapter
        carsList.addItemDecoration(
            VerticalSpaceItemDecoration(
                context!!, R.dimen.item_cars_list_spacing, drawFirst = true, drawLast = true
            )
        )
    }

    private fun fetchCars() {
        GlobalScope.launch(Dispatchers.Main) {
            when (val state = withContext(Dispatchers.IO) { carsListUseCase.getCarsList() }) {
                is CompletedState<*> -> {
                    (state.data as? CarsData)?.also {
                        carsListAdapter.items = it.cars
                    }
                }
            }
        }
    }
}