package com.dicoding.picodiploma.storyapp.view.story

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.storyapp.databinding.FragmentStoryBinding
import com.dicoding.picodiploma.storyapp.view.StoryAdapter
import com.dicoding.picodiploma.storyapp.view.StoryViewModel
import com.dicoding.picodiploma.storyapp.view.ViewModelFactory
import com.dicoding.picodiploma.storyapp.data.Result

class StoryFragment : Fragment() {
    private var _binding: FragmentStoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<StoryViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storyAdapter = StoryAdapter()

        viewModel.getStories().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val data = result.data.listStory
                        storyAdapter.submitList(data)
                    }
                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
                        Log.d("TAG", "onViewCreated: ${result.error}")
                    }
                }
            }
        }

        binding.rvStories.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = storyAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}