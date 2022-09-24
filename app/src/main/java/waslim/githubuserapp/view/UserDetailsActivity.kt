package waslim.githubuserapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import waslim.githubuserapp.R
import waslim.githubuserapp.databinding.ActivityUserDetailsBinding
import waslim.githubuserapp.model.User

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.title = getString(R.string.github_user_details)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataUser = intent.getParcelableExtra<User>(DATA_USER) as User
        binding.apply {
            ivAvatarsUserDetail.setImageResource(dataUser.avatars)
            tvNameDetails.text = dataUser.name
            tvUsernameDetail.text = dataUser.username
            tvLocationDetail.text = dataUser.location
            tvRepositoryDetail.text = dataUser.repository
            tvCompanyDetail.text = dataUser.company
            tvFollowersDetail.text = dataUser.followers
            tvFollowingDetail.text = dataUser.following

            btnShare.setOnClickListener{
                val sendUserData = "Name: ${dataUser.name}\n" +
                        "Username: ${dataUser.username}\n" +
                        "Location: ${dataUser.location}\n" +
                        "Repository: ${dataUser.repository}\n" +
                        "Company: ${dataUser.company}\n" +
                        "Follower: ${dataUser.followers}\n" +
                        "Following: ${dataUser.following}"
                val send: Intent = Intent()
                    .apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, sendUserData)
                        type = "text/plain"
                    }
                val sendIntent = Intent.createChooser(send, "Send Data Using")
                startActivity(sendIntent)
            }
        }
    }

    companion object {
        const val DATA_USER = "data_user"
    }
}