package waslim.githubuserapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import waslim.githubuserapp.databinding.ItemListUserBinding
import waslim.githubuserapp.model.User

class ListUserAdapter (private val listUser: ArrayList<User>) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder> () {
    private lateinit var onItemClickDetails: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickDetails: OnItemClickCallback) {
        this.onItemClickDetails = onItemClickDetails
    }

    interface OnItemClickCallback {
        fun onItemClicked(dataUser: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]
        holder.apply {
            binding.apply {
                ivAvatarsList.setImageResource(user.avatars)
                tvNameList.text = user.name
                tvUsernameList.text = user.username
                tvLocationList.text = user.location
            }
            itemView.setOnClickListener {
                onItemClickDetails.onItemClicked(listUser[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int = listUser.size

    class ListViewHolder (var binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root)


}