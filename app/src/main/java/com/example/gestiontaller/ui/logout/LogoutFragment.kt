package com.example.gestiontaller.ui.logout

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gestiontaller.R
import com.example.gestiontaller.databinding.FragmentLogoutBinding
import com.example.gestiontaller.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class LogoutFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var btn_logout: Button
    private lateinit var btn_cancel: Button
    private var _binding: FragmentLogoutBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogoutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

        btn_logout = root.findViewById(R.id.buttonLogout)
        btn_cancel = root.findViewById(R.id.buttonCancel)

        btn_logout.setOnClickListener{
            val intent: Intent = Intent(activity, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            firebaseAuth.signOut()
            startActivity(intent)
            Toast.makeText(activity, "¡Sesión cerrada!", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LogoutFragment.
         */
        // TODO: Rename and change types and number of parameters

    }
}