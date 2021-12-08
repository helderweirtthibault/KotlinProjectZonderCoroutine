import android.R
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView


class NetwerkDataAdapter(private val context: Context, private val arrayList: java.util.ArrayList<String>) : BaseAdapter() {
    private lateinit var jsonTekst: TextView
    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.simple_list_item_1, parent, false)
        jsonTekst = convertView.findViewById(R.id.text1)
        jsonTekst.text = " " + arrayList[position]
        return convertView
    }
}