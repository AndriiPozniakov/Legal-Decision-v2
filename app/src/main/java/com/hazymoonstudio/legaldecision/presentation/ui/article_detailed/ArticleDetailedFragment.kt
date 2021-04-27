package com.hazymoonstudio.legaldecision.presentation.article_detailed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hazymoonstudio.legaldecision.databinding.FragmentDetailedArticleBinding
import com.hazymoonstudio.legaldecision.models.ArticleUserInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailedFragment : Fragment() {
    private val mViewModel: ArticleDetailedViewModel by viewModels()
    private val args: ArticleDetailedFragmentArgs by navArgs()
    private var mArticleUserInfo = ArticleUserInfo()
    private var mArticleId = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDetailedArticleBinding.inflate(inflater, container, false)

        mArticleId = args.articleId

        mViewModel.getArticle(mArticleId).observe(viewLifecycleOwner) {
            binding.article = it
        }

        mViewModel.getArticleUserInfo(mArticleId).observe(viewLifecycleOwner) {
            binding.userInfo = it
            mArticleUserInfo = it
        }

        binding.click = object : ArticleDetailedClickHandler {
            override fun onLikeClick(view: View) {
                mArticleUserInfo.liked = !mArticleUserInfo.liked
                binding.userInfo = mArticleUserInfo
                Log.i("ArticleDetailedFragment", mArticleUserInfo.toString())
                mViewModel.updateUserInfoAboutArticle(mArticleId, mArticleUserInfo)
            }
        }

        return binding.root
    }
}