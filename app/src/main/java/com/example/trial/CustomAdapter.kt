package com.example.trial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView

class CustomAdapter (var context: Context, var data: ArrayList<StudentSchema>): BaseAdapter(){

    private class ViewHolder(row: View?){
        var email: TextView
        var admno: TextView
        var firstname: TextView
        var lastname: TextView
        var myLinearLayout: LinearLayout


        init {

            this.email = row?.findViewById(R.id.tv_email) as TextView
            this.admno = row?.findViewById(R.id.tv_admnoname) as TextView
            this.firstname = row?.findViewById(R.id.tv_firstname) as TextView
            this.lastname = row?.findViewById(R.id.tv_lastname) as TextView
            this.myLinearLayout = row?.findViewById(R.id.linearlayoutID) as LinearLayout

        }
    }



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View?
        val viewHolder:ViewHolder
        if (convertView == null){
            val layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.card_layout,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val userschema: StudentSchema = getItem(position) as StudentSchema
        viewHolder.email.text = userschema.user_mail
        viewHolder.admno.text = userschema.admissionno
        viewHolder.firstname.text = userschema.first_name
        viewHolder.lastname.text = userschema.last_name

        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }

}