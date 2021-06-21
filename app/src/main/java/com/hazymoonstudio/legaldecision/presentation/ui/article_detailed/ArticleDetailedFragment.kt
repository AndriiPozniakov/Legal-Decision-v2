package com.hazymoonstudio.legaldecision.presentation.ui.article_detailed

import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hazymoonstudio.legaldecision.databinding.FragmentDetailedArticleBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detailed_article.*
import kotlinx.android.synthetic.main.fragment_detailed_article.view.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class ArticleDetailedFragment : Fragment() {
    private val mViewModel: ArticleDetailedViewModel by viewModels()
    private val args: ArticleDetailedFragmentArgs by navArgs()
    private val mAdapter = ArticleAdapter()
//    private var mArticleUserInfo = ArticleUserInfo()
    private var mArticleId = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailedArticleBinding.inflate(inflater, container, false)

//        sharedElementEnterTransition =
//            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
//        postponeEnterTransition(250, TimeUnit.MILLISECONDS)

        mArticleId = args.articleId

        mViewModel.getArticle(mArticleId).observe(viewLifecycleOwner) {
            binding.article = it
            mAdapter.setComponents(it.components)
        }

        val window: Window = requireActivity().window
        window.statusBarColor = Color.TRANSPARENT
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var url = args.imgUrl

        view.card.transitionName = url

        view.card_image.apply {
            Glide
                .with(view.context)
                .load(url)
                .centerCrop()
                .into(this)
        }
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