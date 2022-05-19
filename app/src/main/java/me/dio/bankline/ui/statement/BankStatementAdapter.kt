package me.dio.bankline.ui.statement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.dio.bankline.R
import me.dio.bankline.databinding.BankStatementItemBinding
import me.dio.bankline.domain.Movimentacao
import me.dio.bankline.domain.TipoMovimentacao
import java.util.*

/**
 * Reference: https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=pt-br#kotlin
 */
class BankStatementAdapter(private val dataSet: List<Movimentacao>) : RecyclerView.Adapter<BankStatementAdapter.ViewHolder>() {

    class ViewHolder(private val binding: BankStatementItemBinding) : RecyclerView.ViewHolder(binding.root) {



        fun bind(item: Movimentacao) = with(binding) {

            if(item.valor < 0){
                tvValue.text = "R$ %,.2f".format(Locale.ENGLISH,(item.valor) * -1 )
            }else{
                tvValue.text = "R$ %,.2f".format(Locale.ENGLISH,item.valor )
            }

            tvDescription.text = item.descricao
            tvDatetime.text = item.dataHora

            val typeIcon = if(TipoMovimentacao.RECEITA == item.tipo) R.drawable.ic_money_in_outline else R.drawable.ic_money_out_outline
            ivIcon.setImageResource(typeIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BankStatementItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet[position]
        viewHolder.bind(item)
    }

    override fun getItemCount() = dataSet.size
}