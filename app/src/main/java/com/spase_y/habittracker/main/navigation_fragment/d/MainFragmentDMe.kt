package com.spase_y.habittracker.main.navigation_fragment.d

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.play.core.review.ReviewException
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManagerFactory
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentMainDMeBinding
import com.spase_y.habittracker.main.navigation_fragment.d.general_settings.GeneralSettingsFragment
import com.spase_y.habittracker.main.navigation_fragment.d.language_parameters.LanguageParametersFragment
import com.spase_y.habittracker.main.navigation_fragment.d.notification.NotificationsFragment

class MainFragmentDMe : Fragment() {
    private var _binding: FragmentMainDMeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainDMeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.linearLayout14.setOnClickListener {
            askUserForReview {
                Toast.makeText(requireContext(), "Thanks", Toast.LENGTH_SHORT).show()
            }
        }

        binding.linearLayout15.setOnClickListener {
            showShareOptionsBottomSheet()
        }

        binding.linearLayout10.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp, NotificationsFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.linearLayout11.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp, GeneralSettingsFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.linearLayout12.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp, LanguageParametersFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.linearLayout13.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp, FeedbackFragment())
                .addToBackStack(null)
                .commit()
        }

    }
    private fun showShareOptionsBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_share_list, null)
        bottomSheetDialog.setContentView(bottomSheetView)

        val recyclerView = bottomSheetView.findViewById<RecyclerView>(R.id.recyclerViewShareApps)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4) // 4 элемента в строке

        // Получаем список приложений, поддерживающих Intent.ACTION_SEND
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
        }
        val resolveInfoList = requireContext().packageManager.queryIntentActivities(shareIntent, 0)

        // Преобразуем ResolveInfo в данные для адаптера
        val shareAppList = resolveInfoList.map { resolveInfo ->
            ShareApp(
                name = resolveInfo.loadLabel(requireContext().packageManager).toString(),
                icon = resolveInfo.loadIcon(requireContext().packageManager),
                packageName = resolveInfo.activityInfo.packageName,
                className = resolveInfo.activityInfo.name
            )
        }

        // Устанавливаем адаптер для RecyclerView
        val adapter = ShareAppAdapter(shareAppList) { selectedApp ->
            shareContentToApp(selectedApp)
            bottomSheetDialog.dismiss()
        }
        recyclerView.adapter = adapter

        // Показываем BottomSheetDialog
        bottomSheetDialog.show()
    }
    private fun shareContentToApp(app: ShareApp) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Текст для отправки через приложение")
            setClassName(app.packageName, app.className)
        }
        startActivity(shareIntent)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
fun Fragment.askUserForReview(onRate: () -> Unit) {
    val manager = ReviewManagerFactory.create(requireActivity())
    val request: Task<ReviewInfo> = manager.requestReviewFlow()
    request.addOnCompleteListener { task ->
        try {
            if (task.isSuccessful) {
                Log.i("inAppReview", "requestReviewFlow: Success ")
                // We can get the ReviewInfo object
                val reviewInfo: ReviewInfo = task.result
                val flow: Task<Void> = manager.launchReviewFlow(requireActivity(), reviewInfo)
                flow.addOnCompleteListener {
                    Log.i("inAppReview", "launchReviewFlow: Success ")
                    onRate.invoke()
                }.addOnFailureListener { e ->
                    Log.i("inAppReview", "launchReviewFlow: Failed ")
                }
            } else {
                // There was some problem, continue regardless of the result.
                val reviewErrorCode =
                    (task.exception as ReviewException).message
                Log.i("inAppReview", "launchReviewFlow: Failed $reviewErrorCode")
            }
        } catch (ex: Exception) {
            Log.i("inAppReview", "requestReviewFlow Exception: $ex")
        }
    }.addOnFailureListener { e ->
        Log.i(
            "inAppReview",
            "requestReviewFlow Exception: $e"
        )
    }
}