package com.rihoko.movieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class AccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {
        private var fragment: AccountFragment? = null
        fun getFragment(): AccountFragment {
            if (fragment == null) {
                fragment = AccountFragment()
            }
            return fragment!!
        }
    }
}