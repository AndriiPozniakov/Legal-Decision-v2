package com.hazymoonstudio.legaldecision.ui.articles_saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hazymoonstudio.legaldecision.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArticlesSavedFragment : Fragment() {
//    private val mViewModel: ArticlesListViewModel by viewModels()
//    private val  mAdapter = ArticlesListAdapter()

//    @Inject
//    lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_articles_saved, container, false)

//        view.articlesList.adapter = mAdapter
//        view.articlesList.layoutManager = LinearLayoutManager(activity)
//
//        mViewModel.articles.observe(viewLifecycleOwner) {
//            mAdapter.submitList(it)
//        }

//        var text = "Давно <i>121</i>, что при оценке дизайна и композиции читаемый текст мешает сосредоточиться. <i>122</i> используют потому, что тот обеспечивает <i>123</i> или менее стандартное заполнение шаблона."
//        val spannableString = HighlightsTextUtils.highlightText(text, "<i>", "</i>", object : HighlightsTextClickHandler {
//            override fun onTextClick(text: String) {
//                Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
//            }
//
//        })
//
//        view.test.setText(spannableString, TextView.BufferType.SPANNABLE)
//        view.test.movementMethod = LinkMovementMethod.getInstance()
//        view.test.highlightColor = Color.TRANSPARENT

//        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        var mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), options)
//        mGoogleSignInClient.signOut()
//        mAuth.signOut()

        return  view
    }
}