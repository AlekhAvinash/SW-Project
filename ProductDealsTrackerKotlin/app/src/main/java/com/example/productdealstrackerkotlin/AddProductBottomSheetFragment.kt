package com.example.productdealstrackerkotlin
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.add_tracking_details_layout.*

class AddProductBottomSheetFragment : BottomSheetDialogFragment(){

    private lateinit var mListener:BottomSheetListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_tracking_details_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addItemButton.setOnClickListener {

            var url = url_input.text
            mListener.onAddItemButtonClicked(url.toString())
            dismiss()
        }
    }

    public interface BottomSheetListener{
        fun onAddItemButtonClicked(url_input: String);
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BottomSheetListener) {
            mListener = context
        }
        else{
            throw RuntimeException(requireContext().toString() + " must implement BottomSheetListener")
        }
    }
}