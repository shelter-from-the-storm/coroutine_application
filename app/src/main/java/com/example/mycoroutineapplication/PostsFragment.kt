package com.example.mycoroutineapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycoroutineapplication.databinding.FragmentPostsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostsFragment : Fragment() {

    var binding : FragmentPostsBinding? = null
    val viewModel : PostsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(inflater)
        // Inflate the layout for this fragment

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.posts.observe(viewLifecycleOwner){
            binding?.txtNumPosts?.text = it.size.toString()
            binding?.recPosts?.adapter?.notifyDataSetChanged()
        }
        binding?.btnRetrieve?.setOnClickListener{
            viewModel.retrievePosts()
        }

        binding?.recPosts?.layoutManager = LinearLayoutManager(context)
        binding?.recPosts?.adapter = PostListAdapter(viewModel.posts)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}