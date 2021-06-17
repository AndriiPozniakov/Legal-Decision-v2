package com.hazymoonstudio.legaldecision.presentation.ui.articles_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hazymoonstudio.legaldecision.R
import com.hazymoonstudio.legaldecision.presentation.ui.articles_list.ArticlesListAdapter
import com.hazymoonstudio.legaldecision.presentation.ui.articles_list.ArticlesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_single_fragment.*
import kotlinx.android.synthetic.main.fragment_articles_list.view.*
import javax.inject.Inject

@AndroidEntryPoint
class ArticlesListFragment : Fragment() {
    private val mViewModel: ArticlesListViewModel by viewModels()

    @Inject
    lateinit var mAdapter: ArticlesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_articles_list, container, false)

        view.articlesList.adapter = mAdapter
        view.articlesList.layoutManager = LinearLayoutManager(requireContext())

        mViewModel.articles.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }

        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.shimmer_view_container.startShimmerAnimation()
    }
}