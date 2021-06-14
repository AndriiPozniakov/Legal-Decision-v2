package com.hazymoonstudio.legaldecision.presentation.ui.article_detailed

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hazymoonstudio.legaldecision.databinding.FragmentDetailedArticleBinding
import com.hazymoonstudio.legaldecision.domain.model.Article
import com.hazymoonstudio.legaldecision.network.model.ArticleUserInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detailed_article.*

@AndroidEntryPoint
class ArticleDetailedFragment : Fragment() {
    private val mViewModel: ArticleDetailedViewModel by viewModels()
    private val args: ArticleDetailedFragmentArgs by navArgs()
    private val mAdapter = ArticleAdapter()
//    private var mArticleUserInfo = ArticleUserInfo()
    private var mArticleId = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentDetailedArticleBinding.inflate(inflater, container, false)

        mArticleId = args.articleId

        mViewModel.getArticle(mArticleId).observe(viewLifecycleOwner) {
            binding.article = it
            mAdapter.setComponents(it.components)
        }
//
//        mViewModel.getArticleUserInfo(mArticleId).observe(viewLifecycleOwner) {
//            //binding.userInfo = it
//            mArticleUserInfo = it
//        }

//        binding.click = object : ArticleDetailedClickHandler {
//            override fun onLikeClick(view: View) {
//                mArticleUserInfo.liked = !mArticleUserInfo.liked
//                binding.userInfo = mArticleUserInfo
//                Log.i("ArticleDetailedFragment", mArticleUserInfo.toString())
//                mViewModel.updateUserInfoAboutArticle(mArticleId, mArticleUserInfo)
//            }
//        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        articleRecyclerView.layoutManager = LinearLayoutManager(context)
        articleRecyclerView.adapter = mAdapter

    }
}