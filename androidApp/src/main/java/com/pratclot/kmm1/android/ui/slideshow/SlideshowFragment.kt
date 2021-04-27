package com.pratclot.kmm1.android.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pratclot.kmm1.android.R
import com.pratclot.kmm1.android.databinding.FragmentSlideshowBinding
import com.pratclot.kmm1.data.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SlideshowFragment : Fragment() {

    companion object {
        const val TAG = "SlideshowFragment"
    }

    private val slideshowViewModel: SlideshowViewModel by viewModels()
    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        slideshowViewModel.apply {
            connect()
            temps.observe(viewLifecycleOwner) {
                binding.apply {
                    textFragmentSlideshowHeater.setCompoundDrawablesWithIntrinsicBounds(
                        null, null, getStatusDrawable(it.heater_status), null
                    )
                    textFragmentSlideshowPump.setCompoundDrawablesWithIntrinsicBounds(
                        null, null, getStatusDrawable(it.pump_status), null
                    )
                    textFragmentSlideshowHeaterTemp.text = it.temp_heater.toString()
                    textFragmentSlideshowCauldronTemp.text = it.temp_cauldron.toString()
                }
            }
            lastUpdate.observe(viewLifecycleOwner) {
                it?.let {
                    binding.apply {
                        textFragmentSlideshowLastUpdate.text = resources.getQuantityString(
                            R.plurals.textFragmentSlideshowLastUpdateQuantity,
                            it,
                            it
                        )
                    }
                }
            }
        }
    }

    private fun getStatusDrawable(status: Status) =
        if (status == Status.OFF) {
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_baseline_remove_circle_outline_24
            )
        } else {
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_baseline_check_circle_24
            )
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
