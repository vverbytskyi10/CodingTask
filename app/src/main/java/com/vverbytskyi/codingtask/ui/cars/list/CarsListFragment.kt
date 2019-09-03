package com.vverbytskyi.codingtask.ui.cars.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vverbytskyi.codingtask.R
import com.vverbytskyi.codingtask.domain.cars.model.CarsData
import com.vverbytskyi.codingtask.ui.MainViewModel
import com.vverbytskyi.codingtask.ui.common.CompletedState
import com.vverbytskyi.codingtask.ui.common.ErrorState
import com.vverbytskyi.codingtask.ui.common.StartedState
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
        layoutSwipeRefresh.setOnRefreshListener {
            model.refreshCars()
        }

        carsList.apply {
            adapter = carsListAdapter
            addItemDecoration(
                VerticalSpaceItemDecoration(
                    context!!, R.dimen.dimen_8, drawFirst = true, drawLast = true
                )
            )
        }

        model.getCarsLiveData().observe(this, Observer { state ->
            when (state) {
                is StartedState -> {
                    layoutSwipeRefresh.isRefreshing = true
                }
                is CompletedState<*> -> {
                    (state.data as? CarsData)?.also { carsListAdapter.items = it.cars }
                    layoutState.visibility = View.GONE
                    layoutSwipeRefresh.isRefreshing = false
                }
                is ErrorState -> {
                    if (carsListAdapter.itemCount == 0) {
                        layoutState.visibility = View.VISIBLE
                    } else {
                        Toast.makeText(requireContext(), R.string.refresh_failed_message, Toast.LENGTH_SHORT).show()
                    }
                    layoutSwipeRefresh.isRefreshing = false
                }
            }

        })
    }
}