package waslim.githubuserapp.view

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import waslim.githubuserapp.R
import waslim.githubuserapp.adapter.ListUserAdapter
import waslim.githubuserapp.databinding.ActivityMainBinding
import waslim.githubuserapp.model.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var repository : Array<String>
    private lateinit var followers : Array<String>
    private lateinit var following : Array<String>
    private lateinit var username : Array<String>
    private lateinit var location : Array<String>
    private lateinit var company : Array<String>
    private lateinit var name : Array<String>
    private lateinit var avatars : TypedArray
    private lateinit var rvUser : RecyclerView
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        this.title = getString(R.string.app_name)
        setContentView(binding.root)

        rvUser = binding.rvUser
        rvUser.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerList()
    }

    private val listUsers : ArrayList<User>
        get() {
            username = resources.getStringArray(R.array.username)
            name = resources.getStringArray(R.array.name)
            location = resources.getStringArray(R.array.location)
            repository = resources.getStringArray(R.array.repository)
            company = resources.getStringArray(R.array.company)
            followers = resources.getStringArray(R.array.followers)
            following = resources.getStringArray(R.array.following)
            avatars = resources.obtainTypedArray(R.array.avatar)

            val listUser = ArrayList<User>()
            for (i in name.indices) {
                val user = User(
                    username[i],
                    name[i],
                    location[i],
                    repository[i],
                    company[i],
                    followers[i],
                    following[i],
                    avatars.getResourceId(i, -1)
                )
                listUser.add(user)
            }
            return listUser
        }

    private fun showRecyclerList(){
        rvUser.layoutManager = LinearLayoutManager(applicationContext)
        val listUserAdapter = ListUserAdapter(list)
        rvUser.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object: ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(dataUser: User) {
                move(dataUser)
            }
        })
    }

    private fun move(dataUser: User) {
        val intent = Intent(applicationContext, UserDetailsActivity::class.java)
        intent.putExtra(UserDetailsActivity.DATA_USER, dataUser)
        startActivity(intent)
    }
}