package com.hazymoonstudio.legaldecision.ui.articles_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hazymoonstudio.legaldecision.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_single_fragment.*
import kotlinx.android.synthetic.main.fragment_articles_list.view.*
import javax.inject.Inject


@AndroidEntryPoint
class ArticlesListFragment : Fragment() {
    private val mViewModel: ArticlesListViewModel by viewModels()

    @Inject
    lateinit var mAdapter: ArticlesListAdapter

//    @Inject
//    lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_articles_list, container, false)

        //showNavigation()
//        (activity as AppCompatActivity?)!!.setSupportActionBar(view.toolbar)
//        view.toolbar.title = "Home"

        view.articlesList.adapter = mAdapter
        //view.articlesList.layoutManager = StaggeredGridLayoutManager(2, 1)
        view.articlesList.layoutManager = LinearLayoutManager(requireContext())

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

    override fun onStart() {
        super.onStart()

        mAdapter.startListening()
    }

    private fun showNavigation(){
        requireActivity().bottomNavigationMenu.visibility = View.VISIBLE
        requireActivity().app_bar_layout.visibility = View.VISIBLE
    }
}